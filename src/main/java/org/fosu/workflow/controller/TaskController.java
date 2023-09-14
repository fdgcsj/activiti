package org.fosu.workflow.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.fosu.workflow.req.CompleteBackTaskREQ;
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
    @ApiOperation("驳回任务")
    @GetMapping("/back/nodes")
    public Result backTask(@RequestParam("taskId") String taskId) {
        return waitTaskService.backTask(taskId);
    }

    @ApiOperation("执行驳回的任务")
    @PostMapping("/back")
    public Result completebackTask(@RequestBody CompleteBackTaskREQ req) {
        return waitTaskService.completebackTask(req);
    }
}