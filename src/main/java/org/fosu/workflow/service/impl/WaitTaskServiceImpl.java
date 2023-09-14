package org.fosu.workflow.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.bpmn.model.*;
import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.fosu.workflow.enums.BusinessStatusEnum;
import org.fosu.workflow.req.CompleteBackTaskREQ;
import org.fosu.workflow.req.TaskCompleteREQ;
import org.fosu.workflow.req.TaskREQ;
import org.fosu.workflow.service.BusinessStatusService;
import org.fosu.workflow.service.WaitTaskService;
import org.fosu.workflow.utils.DateUtils;
import org.fosu.workflow.utils.Result;
import org.fosu.workflow.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WaitTaskServiceImpl implements WaitTaskService {
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private BusinessStatusService businessStatusService;
    @Autowired
    private TaskRuntime taskRuntime;
    @Autowired
    private HistoryService historyService;

    @Override
    public Map<String, Object> findWaitTask(TaskREQ req) {
        // 办理人（当前用户）
        String assignee = UserUtils.getUsername();
        TaskQuery query = taskService.createTaskQuery()
                .taskCandidateOrAssigned(assignee) // 作为办理人或候选人
                .orderByTaskCreateTime().desc();
        if (StringUtils.isNotEmpty(req.getTaskName())) {
            query.taskNameLike("%" + req.getTaskName() + "%");
        }
        List<Task> taskList = query.listPage(req.getFirstResult(),
                req.getSize());
        // 用于前端显示页面，总记录数
        long total = query.count();
        List<Map<String, Object>> records = taskList.stream().map(task ->
        {
            Map<String, Object> map = new HashMap<>();
            map.put("taskId", task.getId()); // 任务ID
            map.put("taskName", task.getName()); // 任务名称
            map.put("processStatus", task.isSuspended() ? "已暂停" : "已启动");
            map.put("taskCreateTime",
                    DateUtils.format(task.getCreateTime())); // 任务的创建时间
            map.put("taskAssignee", task.getAssignee()); // 任务的办理人：为null，说明当前是候选人，不是办理人，要先签收
            map.put("processInstanceId", task.getProcessInstanceId());
            // 流程实例ID
            map.put("executionId", task.getExecutionId()); // 执行对象ID
            map.put("processDefinitionId",
                    task.getProcessDefinitionId()); // 流程定义ID
            // 查询流程实例
            ProcessInstance processInstance =
                    runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
            map.put("businessKey", processInstance.getBusinessKey()); //业务唯一标识
            map.put("proposer", processInstance.getStartUserId()); // 获取发起人
            map.put("processName",
                    processInstance.getProcessDefinitionName()); // 流程名称
            map.put("version",
                    processInstance.getProcessDefinitionVersion()); // 版本号
            return map;
        }).collect(Collectors.toList());
        // 返回数据封装
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("records", records);
        return result;
    }

    @Override
    public Result getNextNodeInfo(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            return Result.error("任务不存在");
        }
        // 获取当前模型
        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
        // 根据任务节点id获取当前节点
        FlowElement flowElement = bpmnModel.getFlowElement(task.getTaskDefinitionKey());
        // 封装下一个节点信息
        List<Map<String, Object>> nextNodes = new ArrayList<>();
        getNextNodes(flowElement, nextNodes);
        return Result.ok(nextNodes);
    }

    private void getNextNodes(FlowElement flowElement, List<Map<String, Object>> nextNodes) {
        // 获取当前节点的连线信息
        List<SequenceFlow> outgoingFlows = ((FlowNode) flowElement).getOutgoingFlows();
        outgoingFlows.stream().forEach(outgoingFlow -> {
            // 下一节点
            FlowElement nextFlowElement = outgoingFlow.getTargetFlowElement();
            if (nextFlowElement instanceof EndEvent) {
                return;
            } else if (nextFlowElement instanceof UserTask) {
                Map<String, Object> node = new HashMap<>();
                // 用户任务，获取节点id和名称
                node.put("id", nextFlowElement.getId());
                node.put("name", nextFlowElement.getName());
                nextNodes.add(node);
            } else if (nextFlowElement instanceof ParallelGateway
                    // 并行网关
                    || nextFlowElement instanceof ExclusiveGateway) {
                // 互斥网关
                getNextNodes(nextFlowElement, nextNodes);
            }
        });
    }

    @Override
    public Result completeTask(TaskCompleteREQ req) {
        // 任务id
        String taskId = req.getTaskId();
        // 1.查询任务信息
        org.activiti.api.task.model.Task task = taskRuntime.task(taskId);
        if (task == null) {
            return Result.error("任务不存在或不是您办理的任务");
        }
        String procInstId = task.getProcessInstanceId();
        // 2. 指定任务审批意见
        taskService.addComment(taskId, procInstId, req.getMessage());
        // 3.完成任务
        taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(taskId).build());
        // 4.查询下一个任务
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(procInstId).list();
        // 5. 指定办理人
        if (CollectionUtils.isEmpty(taskList)) {
            // 1.获取流程实例拿到业务id
            // 注意新api的Task对象有businessKey属性，但是没有值
            // 并且是整个流程结束，所以查询历史实例获取businessKey
            HistoricProcessInstance historicProcessInstance =
                    historyService.createHistoricProcessInstanceQuery()
                            .processInstanceId(procInstId).singleResult();
            // 2.更新业务状态
            return businessStatusService.updateState(historicProcessInstance.getBusinessKey(), BusinessStatusEnum.FINISH);
        } else {
            // 有下一个人工任务
            Map<String, String> assigneeMap = req.getAssigneeMap();
            if (assigneeMap == null) {
                // 没有分配处理人，直接结束删除流程实例
                return deleteProcessInstance(procInstId);
            }
            // 针对每个任务分配审批人
            for (Task t : taskList) {
                // 当前任务有审批人，则不设置新的审批人
                if (StringUtils.isNotEmpty(t.getAssignee())) {
                    continue;
                }
                // 任务的节点id，获取对应审批人
                String[] assignees = req.getAssignees(t.getTaskDefinitionKey());
                if (ArrayUtils.isEmpty(assignees)) {
                    // 如果下个节点未分配审批人：结束删除流程实例
                    return deleteProcessInstance(procInstId);
                }
                // 分配第一个任务办理人
                if (assignees.length == 1) {
                    // 一个审批人，直接作为办理人
                    taskService.setAssignee(t.getId(), assignees[0]);
                } else {
                    // 多个审批人，作为候选人
                    for (String assignee : assignees) {
                        taskService.addCandidateUser(t.getId(), assignee);
                    }
                }
            }
        }
        return Result.ok();
    }

    @Override
    public Result backTask(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            return Result.error("任务不存在");
        }
        // 获取当前模型
        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
        // 根据任务节点id获取当前节点
        FlowElement flowElement = bpmnModel.getFlowElement(task.getTaskDefinitionKey());

        // 获取历史任务实例
        List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                .finished()
                .orderByHistoricTaskInstanceEndTime().desc()
                .list();
        // 封装上一个节点信息
        Map<String, Object> lastNode = new HashMap<>();
        // 检查是否有历史任务实例
        if (!historicTaskInstances.isEmpty()) {
            // 获取上一个任务的办理人
            String previousTaskAssignee = historicTaskInstances.get(0).getAssignee();
            lastNode.put("activityId",taskId);
            lastNode.put("name",previousTaskAssignee);
        }

        return Result.ok(lastNode);
    }

    @Override
    public Result completebackTask(CompleteBackTaskREQ req) {
        String taskId=req.getTaskId();
        String assigner =req.getTargetActivityId();
        // 1.查询任务信息
        org.activiti.api.task.model.Task task = taskRuntime.task(taskId);
        if (task == null) {
            return Result.error("任务不存在或不是您办理的任务");
        }
        System.out.println(task);
        String procInstId = task.getProcessInstanceId();
/*        // 2. 指定任务审批意见
        taskService.addComment(taskId, procInstId, req.getMessage());*/
        // 3.完成任务
        taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(taskId).build());
        // 4.查询下一个任务
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(procInstId).list();
        // 5. 指定办理人
        if (CollectionUtils.isEmpty(taskList)) {
            // 1.获取流程实例拿到业务id
            // 注意新api的Task对象有businessKey属性，但是没有值
            // 并且是整个流程结束，所以查询历史实例获取businessKey
            HistoricProcessInstance historicProcessInstance =
                    historyService.createHistoricProcessInstanceQuery()
                            .processInstanceId(procInstId).singleResult();
            // 2.更新业务状态
            return businessStatusService.updateState(historicProcessInstance.getBusinessKey(), BusinessStatusEnum.FINISH);
        }
        else {
            // 有下一个人工任务
//            Map<String, String> assigneeMap = req.getAssigneeMap();
            if (assigner == null) {
                // 没有分配处理人，直接结束删除流程实例
                return deleteProcessInstance(procInstId);
            }
            // 针对每个任务分配审批人
            for (Task t : taskList) {
                // 当前任务有审批人，则不设置新的审批人
                if (StringUtils.isNotEmpty(t.getAssignee())) {
                    continue;
                }
                // 任务的节点id，获取对应审批人
//                String[] assignees = req.getAssignees(t.getTaskDefinitionKey());
                if (StringUtils.isNotEmpty(assigner)) {
                    // 如果下个节点未分配审批人：结束删除流程实例
                    return deleteProcessInstance(procInstId);
                }
                // 分配第一个任务办理人
                if (StringUtils.isNotEmpty(assigner)) {
                    // 一个审批人，直接作为办理人
                    taskService.setAssignee(t.getId(), assigner);
                }
            }
        }
        return Result.ok();
    }

    private Result deleteProcessInstance(String procInstId) {
        // 1.删除流程实例
        runtimeService.deleteProcessInstance(procInstId, "审批节点未分配审批人，流程自动中断取消");
        // 2.通过任务对象获取流程实例
        HistoricProcessInstance historicProcessInstance =
                historyService.createHistoricProcessInstanceQuery()
                        .processInstanceId(procInstId).singleResult();
        // 3.更新业务状态
        businessStatusService.updateState(historicProcessInstance.getBusinessKey(), BusinessStatusEnum.CANCEL);
        return Result.ok("审批节点未分配审批人，流程自动中断取消");
    }
}
