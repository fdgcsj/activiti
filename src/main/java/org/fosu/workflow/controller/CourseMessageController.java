package org.fosu.workflow.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.fosu.workflow.entities.CourseMessage;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.req.CourseMessageREQ;
import org.fosu.workflow.req.courseManagerREQ;
import org.fosu.workflow.service.ClassService;
import org.fosu.workflow.service.CourseMessageService;
import org.fosu.workflow.utils.Result;
import org.fosu.workflow.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api("课程通知控制层")
@ResponseBody
@RestController
@RequestMapping("/CourseMessage")
public class CourseMessageController {
    @Autowired
    private CourseMessageService courseMessageService;
    @Autowired
    private ClassService classService;

    @ApiOperation("新增课程通知")
    @PostMapping
    public Result add(@RequestBody CourseMessage courseMessage) {
        courseMessage.setCreateDate(new Date());
        return courseMessageService.add(courseMessage);
    }

    @ApiOperation("更新课程通知详情信息")
    @PutMapping
    public Result update(@RequestBody CourseMessage courseMessage) {
        return courseMessageService.update(courseMessage);
    }

    @ApiOperation("查询课程通知详情信息")
    @GetMapping("/{id}")
    public Result viewById(@PathVariable String id) {
        return Result.ok(courseMessageService.viewById(id));
    }

    @ApiOperation("查询课程通知列表")
    @PostMapping("/list")
    public Result listPage(@RequestBody CourseMessageREQ req) {
        return courseMessageService.listPage(req);
    }

    @ApiOperation("查询课程通知的班级信息(包含该教师所教学的课程及班级)")
    @PostMapping("/courseInfo")
    public Result courseName() {
        return classService.getTeacherClasses(UserUtils.getUsername());
    }

    @ApiOperation("查询学生课程通知列表")
    @PostMapping("/classMessageList")
    public Result classMessageList(@RequestBody CourseMessageREQ req) {
        return courseMessageService.classMessageList(req);
    }
}