package org.fosu.workflow.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.fosu.workflow.entities.Answer;
import org.fosu.workflow.entities.ChoiceQuestion;
import org.fosu.workflow.entities.Paper;
import org.fosu.workflow.req.AnswerREQ;
import org.fosu.workflow.req.QuestionREQ;
import org.fosu.workflow.service.AnswerService;
import org.fosu.workflow.service.ChoiceQuestionService;
import org.fosu.workflow.service.PaperService;
import org.fosu.workflow.service.StudentService;
import org.fosu.workflow.utils.Result;
import org.fosu.workflow.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api("答题申请控制层")
@ResponseBody
@RestController
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    AnswerService answerService;
    @Autowired
    ChoiceQuestionService choiceService;
    @Autowired
    PaperService paperService;
    @Autowired
    StudentService studentService;
    @ApiOperation("新增答题申请")
    @PostMapping
    public Result add(@RequestBody Answer answer) {
        if(isTimeEnd(answer))
            return Result.error("试卷已经截止");
        UpdateScore(answer);
        return answerService.add(answer);
    }
    @ApiOperation("查询答题请列表")
    @PostMapping("/list")
    public Result listPage(@RequestBody AnswerREQ req) {
        return answerService.listPage(req);
    }
    @ApiOperation("更新答题详情信息")
    @PutMapping
    public Result update(@RequestBody Answer answer) {
        if(isTimeEnd(answer)&&studentService.isStudent(UserUtils.getUsername()))
            return Result.error("试卷已经截止,学生不可再修改，仅允许老师修改");
        UpdateScore(answer);
        return answerService.update(answer);
    }
    @ApiOperation("查询答题详情信息")
    @GetMapping("/{id}")
    public Result view(@PathVariable String id) {
        Answer entity = answerService.getById(id);
        return Result.ok(entity);
    }
    void UpdateScore(Answer answer)
    {
        if(answer == null)
            return;
        int intAnswer = -1;
        try
        {
            intAnswer = Integer.parseInt(answer.getAnswer());
        }
        catch (Exception e) {
            intAnswer = -1;
        }
        ChoiceQuestion choiceQuestion = choiceService.getById(answer.getQuestionId());//判断题自动批改
        if(choiceQuestion != null)
        {
            if(intAnswer == choiceQuestion.getAnswer())
                answer.setScore(choiceQuestion.getScore());
            else
                answer.setScore(0);
        }
        else
        {
            //判断题批改
        }
    }
    boolean isTimeEnd(Answer answer)
    {
        if(answer != null)
        {
            Paper paper = paperService.getById(answer.getPaperId());
            if(paper != null)
            {
                if(paper.getEndDate() == null)
                    return false;
                return new Date().after(paper.getEndDate());
            }
        }
        return true;
    }
    @ApiOperation("获取某人在某卷的得分")
    @GetMapping("/score")
    public Result getScore(@RequestBody AnswerREQ req) {
        return Result.ok(answerService.getScore(req));
    }
}
