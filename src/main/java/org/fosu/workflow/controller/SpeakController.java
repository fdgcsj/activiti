package org.fosu.workflow.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.fosu.workflow.entities.Leave;
import org.fosu.workflow.entities.Speak;
import org.fosu.workflow.req.SpeakREQ;
import org.fosu.workflow.service.SpeakService;
import org.fosu.workflow.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("话题申请控制层")
@ResponseBody
@RestController
@RequestMapping("/speak")
public class SpeakController {
    @Autowired
    SpeakService speakService;
    @ApiOperation("新增发言申请")
    @PostMapping
    public Result add(@RequestBody Speak speak) {
        return speakService.add(speak);
    }
    @ApiOperation("查询发言申请列表")
    @PostMapping("/list")
    public Result listPage(@RequestBody SpeakREQ req) {
        return speakService.listPage(req);
    }
    @ApiOperation("更新发言详情信息")
    @PutMapping
    public Result update(@RequestBody Speak speak) {
        return speakService.update(speak);
    }

    @ApiOperation("查询发言详情信息")
    @GetMapping("/{id}")
    public Result view(@PathVariable String id) {
        Speak entity = speakService.getById(id);
        return Result.ok(entity);
    }
}
