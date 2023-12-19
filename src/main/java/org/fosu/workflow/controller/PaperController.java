package org.fosu.workflow.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.fosu.workflow.entities.Paper;
import org.fosu.workflow.entities.Talk;
import org.fosu.workflow.req.PaperREQ;
import org.fosu.workflow.req.TalkREQ;
import org.fosu.workflow.service.PaperService;
import org.fosu.workflow.service.TalkService;
import org.fosu.workflow.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("试卷申请控制层")
@ResponseBody
@RestController
@RequestMapping("/paper")
public class PaperController {
    @Autowired
    PaperService paperService;
    @ApiOperation("新增试卷申请")
    @PostMapping
    public Result add(@RequestBody Paper paper) {
        return paperService.add(paper);
    }
    @ApiOperation("查询试卷申请列表")
    @PostMapping("/list")
    public Result listPage(@RequestBody PaperREQ req) {
        return paperService.listPage(req);
    }
    @ApiOperation("查询学生试卷列表")
    @PostMapping("/studentPaperList")
    public Result getStudentPaperList(@RequestBody PaperREQ req) {
        return paperService.getStudentPaperList(req);
    }
    @ApiOperation("更新试卷详情信息")
    @PutMapping
    public Result update(@RequestBody Paper paper) {
        return paperService.update(paper);
    }
    @ApiOperation("查询试卷详情信息")
    @GetMapping("/{id}")
    public Result view(@PathVariable String id) {
        Paper entity = paperService.getById(id);
        return Result.ok(entity);
    }

    @ApiOperation("查询某试卷的选择题总分")
    @GetMapping("/total/{id}")
    public Result total(@PathVariable String id) {
        return Result.ok(paperService.getTotalScore(id));
    }
}
