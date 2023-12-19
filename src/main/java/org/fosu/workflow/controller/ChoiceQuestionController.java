package org.fosu.workflow.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.fosu.workflow.entities.ChoiceQuestion;
import org.fosu.workflow.entities.Paper;
import org.fosu.workflow.req.PaperREQ;
import org.fosu.workflow.req.QuestionREQ;
import org.fosu.workflow.service.ChoiceQuestionService;
import org.fosu.workflow.service.PaperService;
import org.fosu.workflow.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("选择题申请控制层")
@ResponseBody
@RestController
@RequestMapping("/choiceQuestion")
public class ChoiceQuestionController {
    @Autowired
    ChoiceQuestionService choiceQuestionService;
    @ApiOperation("新增选择题申请")
    @PostMapping
    public Result add(@RequestBody ChoiceQuestion question) {
        return choiceQuestionService.add(question);
    }
    @ApiOperation("查询选择题申请列表")
    @PostMapping("/list")
    public Result listPage(@RequestBody QuestionREQ req) {
        return choiceQuestionService.listPage(req);
    }
    @ApiOperation("更新选择题详情信息")
    @PutMapping
    public Result update(@RequestBody ChoiceQuestion question) {
        return choiceQuestionService.update(question);
    }
    @ApiOperation("查询选择题详情信息")
    @GetMapping("/{id}")
    public Result view(@PathVariable String id) {
        ChoiceQuestion entity = choiceQuestionService.getById(id);
        return Result.ok(entity);
    }
    @ApiOperation("查询某试卷的选择题总分")
    @GetMapping("/total/{id}")
    public Result total(@PathVariable String id) {
        return Result.ok(choiceQuestionService.getTotalScore(id));
    }
}
