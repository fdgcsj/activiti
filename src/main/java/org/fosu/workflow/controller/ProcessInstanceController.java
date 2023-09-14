package org.fosu.workflow.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.fosu.workflow.service.ProcessInstanceService;
import org.fosu.workflow.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Api("流程实例管理")
@Slf4j
@RestController
@RequestMapping("/instance")
public class ProcessInstanceController {
    @Autowired
    private ProcessInstanceService processInstanceService;

    @ApiOperation("通过实例ID获取申请表单组件名")
    @GetMapping("/form/name/{procInstId}")
    public Result getFormName(@PathVariable String procInstId) {
        return processInstanceService.getFormNameByProcInstId(procInstId);
    }

    @ApiOperation("获取流程示例图审批历史图")
    @GetMapping("/history/image")
    public void getHistoryProcessImage(@RequestParam String procInstId, HttpServletResponse response) {
        processInstanceService.getHistoryProcessImage(procInstId, response);
    }
}
