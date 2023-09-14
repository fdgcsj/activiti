package org.fosu.workflow.service.impl;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.fosu.workflow.activiti.image.CustomProcessDiagramGenerator;
import org.fosu.workflow.entities.BusinessStatus;
import org.fosu.workflow.enums.BusinessStatusEnum;
import org.fosu.workflow.service.BusinessStatusService;
import org.fosu.workflow.service.ProcessConfigService;
import org.fosu.workflow.service.ProcessInstanceService;
import org.fosu.workflow.utils.DateUtils;
import org.fosu.workflow.utils.Result;
import org.fosu.workflow.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProcessInstanceServiceImpl extends ActivitiService implements ProcessInstanceService {
    @Autowired
    private ProcessConfigService processConfigService;
    @Autowired
    private BusinessStatusService businessStatusService;

    /** 通过流程实例id获取申请表单 */
    @Override
    public Result getFormNameByProcInstId(String procInstId) {
        // 通过历史流程实例查询，因为如果流程实例全部审批完后，正在运行的流程实例数据会被删除，只有历史中有
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .includeProcessVariables()  // 查询流程变量
                .processInstanceId(procInstId).singleResult();

        // 再获取流程实例中的流程变量(启动流程实例时设置了)
        return Result.ok(historicProcessInstance.getProcessVariables().get("formName"));
    }

    /** 获取流程实例审批历史图 */
    @Override
    public void getHistoryProcessImage(String procInstId, HttpServletResponse response) {
        InputStream inputStream = null;
        try {
            // 1.查询流程实例历史数据
            HistoricProcessInstance instance = historyService.createHistoricProcessInstanceQuery()
                    .processInstanceId(procInstId).singleResult();

            // 2. 查询流程中已执行的节点，按时开始时间降序排列
            List<HistoricActivityInstance> historicActivityInstanceList = historyService.createHistoricActivityInstanceQuery()
                    .processInstanceId(procInstId)
                    .orderByHistoricActivityInstanceStartTime().desc()
                    .list();

            // 3. 单独的提取高亮节点id ( 绿色）
            List<String> highLightedActivityIdList =
                    historicActivityInstanceList.stream()
                            .map(HistoricActivityInstance::getActivityId).collect(Collectors.toList());

            // 4. 正在执行的节点 （红色）
            List<Execution> runningActivityInstanceList = runtimeService.createExecutionQuery()
                    .processInstanceId(procInstId).list();

            List<String> runningActivityIdList = new ArrayList<>();
            for (Execution execution : runningActivityInstanceList) {
                if(StringUtils.isNotEmpty(execution.getActivityId())) {
                    runningActivityIdList.add(execution.getActivityId());
                }
            }

            // 获取流程定义Model对象
            BpmnModel bpmnModel = repositoryService.getBpmnModel(instance.getProcessDefinitionId());

            // 实例化流程图生成器
            CustomProcessDiagramGenerator generator = new CustomProcessDiagramGenerator();
            // 获取高亮连线id
            List<String> highLightedFlows = generator.getHighLightedFlows(bpmnModel, historicActivityInstanceList);
            // 生成历史流程图
            inputStream = generator.generateDiagramCustom(bpmnModel, highLightedActivityIdList,
                    runningActivityIdList, highLightedFlows,
                    "宋体", "微软雅黑", "黑体");

            // 响应相关图片
            response.setContentType("image/svg+xml");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
