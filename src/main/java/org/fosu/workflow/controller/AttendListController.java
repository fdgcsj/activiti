package org.fosu.workflow.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.fosu.workflow.entities.Attend;
import org.fosu.workflow.entities.AttendList;
import org.fosu.workflow.req.AttendREQ;
import org.fosu.workflow.service.AttendListService;
import org.fosu.workflow.service.AttendService;
import org.fosu.workflow.service.courseManagerService;
import org.fosu.workflow.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api("考勤详情控制层")
@ResponseBody
@RestController
@RequestMapping("/attendList")
public class AttendListController {
    @Autowired
    private AttendListService attendListService;

    @ApiOperation("新增考勤详情")
    @PostMapping
    public Result add(@RequestBody AttendList[] attendList) {
        return attendListService.add(attendList);
    }
    @ApiOperation("查询考勤详情列表")
    @PostMapping("/list")
    public Result attendListPage(@RequestParam String id) {
        return attendListService.getAttendList(id);
    }
    @ApiOperation("更新考勤详情信息")
    @PutMapping
    public Result update(@RequestBody AttendList[] attendList) {
        return attendListService.update(attendList);
    }
}
