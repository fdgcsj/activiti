package org.fosu.workflow.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.fosu.workflow.entities.Leave;
import org.fosu.workflow.entities.Loan;
import org.fosu.workflow.entities.Speak;
import org.fosu.workflow.entities.Talk;
import org.fosu.workflow.req.LoanREQ;
import org.fosu.workflow.req.TalkREQ;
import org.fosu.workflow.service.TalkService;
import org.fosu.workflow.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("话题申请控制层")
@ResponseBody
@RestController
@RequestMapping("/talk")
public class TalkController {
    @Autowired
    TalkService talkService;
    @ApiOperation("新增话题申请")
    @PostMapping
    public Result add(@RequestBody Talk talk) {
        return talkService.add(talk);
    }
    @ApiOperation("查询话题申请列表")
    @PostMapping("/list")
    public Result listPage(@RequestBody TalkREQ req) {
        return talkService.listPage(req);
    }
    @ApiOperation("更新话题详情信息")
    @PutMapping
    public Result update(@RequestBody Talk talk) {
        return talkService.update(talk);
    }
    @ApiOperation("查询话题详情信息")
    @GetMapping("/{id}")
    public Result view(@PathVariable String id) {
        Talk entity = talkService.getById(id);
        return Result.ok(entity);
    }
}
