package org.fosu.workflow.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.fosu.workflow.entities.Answer;
import org.fosu.workflow.entities.ChoiceQuestion;
import org.fosu.workflow.req.AnswerREQ;
import org.fosu.workflow.req.QuestionREQ;
import org.fosu.workflow.service.AnswerService;
import org.fosu.workflow.service.ChoiceQuestionService;
import org.fosu.workflow.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("简答题申请控制层")
@ResponseBody
@RestController
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    AnswerService answerService;
    @ApiOperation("新增简答题申请")
    @PostMapping
    public Result add(@RequestBody Answer answer) {
        return answerService.add(answer);
    }
    @ApiOperation("查询简答题请列表")
    @PostMapping("/list")
    public Result listPage(@RequestBody AnswerREQ req) {
        return answerService.listPage(req);
    }
    @ApiOperation("更新简答题详情信息")
    @PutMapping
    public Result update(@RequestBody Answer answer) {
        return answerService.update(answer);
    }
    @ApiOperation("查询简答题详情信息")
    @GetMapping("/{id}")
    public Result view(@PathVariable String id) {
        Answer entity = answerService.getById(id);
        return Result.ok(entity);
    }
}
