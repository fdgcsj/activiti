package org.fosu.workflow.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.fosu.workflow.entities.TeacherScore;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.req.courseManagerREQ;
import org.fosu.workflow.service.TeacherScoreService;
import org.fosu.workflow.service.courseManagerService;
import org.fosu.workflow.utils.Result;
import org.fosu.workflow.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api("教学反馈控制层")
@ResponseBody
@RestController
@RequestMapping("/teacherScore")
public class TeacherScoreController {
    @Autowired
    private TeacherScoreService teacherScoreService;

    @ApiOperation("新增教师评分")
    @PostMapping
    public Result add(@RequestBody TeacherScore teacherScore) {
        return teacherScoreService.add(teacherScore);
    }

    @ApiOperation("查询教师评分详情信息")
    @GetMapping("/{id}")
    public Result viewById(@PathVariable String id) {
        return Result.ok(teacherScoreService.getById(id));
    }

    @ApiOperation("查询所有教师评分信息")
    @PostMapping("/list")
    public Result listPage(@RequestBody courseManagerREQ req) {
        return Result.ok(teacherScoreService.listPage(req));
    }
}
