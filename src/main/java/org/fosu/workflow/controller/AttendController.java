package org.fosu.workflow.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.fosu.workflow.entities.Attend;
import org.fosu.workflow.entities.Leave;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.req.AttendREQ;
import org.fosu.workflow.req.courseManagerREQ;
import org.fosu.workflow.service.AttendService;
import org.fosu.workflow.service.courseManagerService;
import org.fosu.workflow.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api("考勤管理控制层")
@ResponseBody
@RestController
@RequestMapping("/attend")
public class AttendController {
    @Autowired
    private AttendService attendService;

    @ApiOperation("新增考勤管理")
    @PostMapping
    public Result add(@RequestBody Attend attend) {
        attend.setCreateDate(new Date());
        return attendService.add(attend);
    }
    @ApiOperation("查询考勤管理列表")
    @PostMapping("/list")
    public Result attendListPage(@RequestBody AttendREQ req) {
        return attendService.getAttendList(req);
    }
    @ApiOperation("更新考勤管理详情信息")
    @PutMapping
    public Result update(@RequestBody Attend attend) {
        return attendService.update(attend);
    }
    @ApiOperation("查询考勤详情信息")
    @GetMapping("/{id}")
    public Result view(@PathVariable String id) {
        Attend attend = attendService.getById(id);
        return Result.ok(attend);
    }
}
