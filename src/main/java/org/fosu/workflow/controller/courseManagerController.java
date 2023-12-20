package org.fosu.workflow.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.req.courseManagerREQ;
import org.fosu.workflow.service.ClassService;
import org.fosu.workflow.service.StudentService;
import org.fosu.workflow.service.courseManagerService;
import org.fosu.workflow.utils.Result;
import org.fosu.workflow.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api("课程管理控制层")
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

    @ApiOperation("获取当前教师的所有课程")
    @PostMapping("/teacherCourse")
    public Result getTeacherCourse() {
        return classService.getTeacherCourse(UserUtils.getUsername());
    }

    @ApiOperation("获取指定教师的课程的所有班级学生")
    @GetMapping("/{courseName}/allStudents")
    public Result getAllStudents(@PathVariable String courseName) {
        return CourseManagerService.getAllStudents(courseName,UserUtils.getUsername());
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

    @ApiOperation("查询所有课程名称和任课老师唯一的列表")
    @PostMapping("/allCourseNameAndTeacherlist")
    public Result allCourseNameAndTeacherlist(@RequestBody courseManagerREQ req) {
        return CourseManagerService.allCourseNameAndTeacherlist(req);
    }

    @ApiOperation("查询班级的课程名称和任课老师唯一的列表")
    @PostMapping("/classCourseNameAndTeacherlist")
    public Result classCourseNameAndTeacherlist(@RequestBody courseManagerREQ req) {
        return CourseManagerService.classCourseNameAndTeacherlist(req);
    }

    @ApiOperation("更新课程管理详情信息")
    @PutMapping
    public Result update(@RequestBody courseManager CourseManager) {
        return CourseManagerService.update(CourseManager);
    }

    @ApiOperation("查询课程管理详情信息")
    @GetMapping("/{id}")
    public Result viewById(@PathVariable String id) {
        return Result.ok(CourseManagerService.viewById(id));
    }

    @ApiOperation("查询学生课程信息")
    @PostMapping("/classInfolist")
    public Result viewClassInfo(@RequestBody courseManagerREQ req) {
        return Result.ok(CourseManagerService.listClassInfoPage(req));
    }
    @ApiOperation("查询选修课程信息")
    @PostMapping("/electiveCourse")
    public Result viewelectiveCourse(@RequestBody courseManagerREQ req) {
        return Result.ok(CourseManagerService.listelectiveCourse(req));
    }

    @ApiOperation("选择选修课程")
    @PostMapping("/selectElectiveCourse/{id}")
    public Result selectElectiveCourse(@PathVariable String id) {
        return Result.ok(CourseManagerService.selectElectiveCourse(id,UserUtils.getUsername()));
    }

    @ApiOperation("删除选修课程")
    @DeleteMapping("/deleteElectiveCourse/{id}")
    public Result deleteElectiveCourse(@PathVariable String id) {
        return Result.ok(CourseManagerService.deleteElectiveCourse(id,UserUtils.getUsername()));
    }

    @ApiOperation("查询所有课程名称和任课老师和班级唯一的列表")
    @PostMapping("/allCourseNameAndTeacherAndClasslist")
    public Result allCourseNameAndTeacherAndClasslist(@RequestBody courseManagerREQ req) {
        return CourseManagerService.allCourseNameAndTeacherAndClasslist(req);
    }

    @ApiOperation("查询班级的课程名称和任课老师和班级唯一的列表")
    @PostMapping("/classCourseNameAndTeacherAndClasslist")
    public Result classCourseNameAndTeacherAndClasslist(@RequestBody courseManagerREQ req) {
        return CourseManagerService.classCourseNameAndTeacherAndClasslist(req);
    }

    @ApiOperation("判断用户是否为学生")
    @PostMapping("/isStudent")
    public Boolean isStudent() {
        return studentService.isStudent(UserUtils.getUsername());
    }
}
