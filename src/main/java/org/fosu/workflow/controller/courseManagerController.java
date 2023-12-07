package org.fosu.workflow.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.req.courseManagerREQ;
import org.fosu.workflow.service.ClassService;
import org.fosu.workflow.service.StudentService;
import org.fosu.workflow.service.courseManagerService;
import org.fosu.workflow.utils.DateUtils;
import org.fosu.workflow.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api("借款申请控制层")
@ResponseBody
@RestController
@RequestMapping("/courseManager")
public class courseManagerController {
    @Autowired
    private courseManagerService CourseManagerService;
    @Autowired
    private ClassService classService;
    @Autowired
    private StudentService studentService;

    @ApiOperation("新增课程管理")
    @PostMapping
    public Result add(@RequestBody courseManager CourseManager) {
        CourseManager.setCreateDate(new Date());
        return CourseManagerService.add(CourseManager);
    }

    @ApiOperation("获取所有课程班级")
    @GetMapping("/classes")
    public Result getAllClasses() {
        return classService.getAllClasses();
    }

    @ApiOperation("获取指定班级的所有学生")
    @GetMapping("/classes/{classId}/students")
    public Result getStudentsByClass(@PathVariable String classId) {
        return studentService.getStudentsByClass(classId);
    }

    @ApiOperation("查询课程管理列表")
    @PostMapping("/list")
    public Result listPage(@RequestBody courseManagerREQ req) {
        return CourseManagerService.listPage(req);
    }

    @ApiOperation("更新课程管理详情信息")
    @PutMapping
    public Result update(@RequestBody courseManager CourseManager) {
        CourseManager.setCreateDate(new Date());
        return CourseManagerService.update(CourseManager);
    }

    @ApiOperation("查询课程管理详情信息")
    @GetMapping("/{id}")
    public Result view(@PathVariable String id) {
        courseManager CourseManager = CourseManagerService.getById(id);
        return Result.ok(CourseManager);
    }
}
