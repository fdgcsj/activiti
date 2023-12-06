package org.fosu.workflow.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.fosu.workflow.entities.Loan;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.req.LoanREQ;
import org.fosu.workflow.req.courseManagerREQ;
import org.fosu.workflow.service.LoanService;
import org.fosu.workflow.service.courseManagerService;
import org.fosu.workflow.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("借款申请控制层")
@ResponseBody
@RestController
@RequestMapping("/courseManager")
public class courseManagerController {
    @Autowired
    private courseManagerService CourseManagerService;

    @ApiOperation("新增课程管理")
    @PostMapping
    public Result add(@RequestBody courseManager CourseManager) {
        return CourseManagerService.add(CourseManager);
    }

    @ApiOperation("查询课程管理列表")
    @PostMapping("/list")
    public Result listPage(@RequestBody courseManagerREQ req) {
        return CourseManagerService.listPage(req);
    }

    @ApiOperation("更新课程管理详情信息")
    @PutMapping
    public Result update(@RequestBody courseManager CourseManager) {
        return CourseManagerService.update(CourseManager);
    }

    @ApiOperation("查询课程管理详情信息")
    @GetMapping("/{id}")
    public Result view(@PathVariable String id) {
        courseManager CourseManager = CourseManagerService.getById(id);
        return Result.ok(CourseManager);
    }
}
