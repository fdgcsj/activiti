package org.fosu.workflow.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.fosu.workflow.entities.Attend;
import org.fosu.workflow.entities.StudentLeave;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.req.AttendREQ;
import org.fosu.workflow.req.courseManagerREQ;
import org.fosu.workflow.service.StudentLeaveService;
import org.fosu.workflow.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api("请假管理控制层")
@ResponseBody
@RestController
@RequestMapping("/studentLeave")
public class StudentLeaveController {
    @Autowired
    private StudentLeaveService studentLeaveService;
    @ApiOperation("新增请假管理")
    @PostMapping
    public Result add(@RequestBody StudentLeave studentLeave) {
        studentLeave.setCreateDate(new Date());
        return studentLeaveService.add(studentLeave);
    }
    @ApiOperation("查询请假管理列表")
    @PostMapping("/list")
    public Result listPage(@RequestBody AttendREQ req) {
        return studentLeaveService.listPage(req);
    }
    @ApiOperation("更新请假管理详情信息")
    @PutMapping
    public Result update(@RequestBody StudentLeave studentLeave) {
        return studentLeaveService.update(studentLeave);
    }
    @ApiOperation("查询请假管理详情信息")
    @GetMapping("/{id}")
    public Result view(@PathVariable String id) {
        return Result.ok(studentLeaveService.viewById(id));
    }
}
