package org.fosu.workflow.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.fosu.workflow.req.TaskCompleteREQ;
import org.fosu.workflow.req.TaskREQ;
import org.fosu.workflow.service.WaitTaskService;
import org.fosu.workflow.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("任务相关接口")
@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    WaitTaskService waitTaskService;

    @ApiOperation("查询当前用户是办理人或候选人的待办任务")
    @PostMapping("/list/wait")
    public Result findWaitTask(@RequestBody TaskREQ req) {
        try {
            return Result.ok("查询成功", waitTaskService.findWaitTask(req));
        } catch (Exception e) {
            log.error("根据流程assignee查询当前人的个人任务,异常:{}", e);
            return Result.error("查询失败" + e.getMessage());
        }
    }

    @ApiOperation("获取目标节点（下一节点）")
    @GetMapping("/next/node")
    public Result getNextNodeInfo(@RequestParam("taskId") String taskId) {
        return waitTaskService.getNextNodeInfo(taskId);
    }

    @ApiOperation("完成任务")
    @PostMapping("/complete")
    public Result completeTask(@RequestBody TaskCompleteREQ req) {
        return waitTaskService.completeTask(req);
    }

    @ApiOperation("任务转办，把任务交给别人处理")
    @PostMapping("/turn")
    public Result turnTask(@RequestParam String taskId,
                           @RequestParam String assigneeUserKey) {
        try {
            return waitTaskService.turnTask(taskId, assigneeUserKey);
        } catch (Exception e) {
            log.error("任务转办,异常:{}", e);
            return Result.error("转办任务失败:"+e.getMessage());
        }
    }

    @ApiOperation("通过流程实例ID获取已完成历史任务节点，用于驳回功能")
    @GetMapping("/back/nodes")
    public Result getBackNodes(@RequestParam("taskId") String taskId) {
        try {
            return waitTaskService.getBackNodes(taskId);
        } catch (Exception e) {
            return Result.error("查询失败"+ e.getMessage());
        }
    }

    @ApiOperation("驳回指定历史节点")
    @PostMapping("/back")
    public Result backProcess(@RequestParam String taskId,
                              @RequestParam String targetActivityId) {
        try {
            return waitTaskService.backProcess(taskId, targetActivityId);
        } catch (Exception e) {
            log.error("完成任务,异常:{}", e.getMessage());
            return Result.error("驳回任务失败: " + e.getMessage());
        }
    }

    @ApiOperation("查询当前登录用户已完成任务信息")
    @PostMapping("/list/complete")
    public Result findCompleteTask(@RequestBody TaskREQ req) {
        try {
            return waitTaskService.findCompleteTask(req);
        } catch (Exception e) {
            log.error("查询当前用户的已处理任务,异常:{}", e);
            return Result.error("查询失败：" + e.getMessage());
        }
    }
}