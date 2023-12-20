package org.fosu.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.fosu.workflow.entities.Answer;
import org.fosu.workflow.entities.ChoiceQuestion;
import org.fosu.workflow.req.AnswerREQ;
import org.fosu.workflow.req.QuestionREQ;
import org.fosu.workflow.utils.Result;

public interface AnswerService extends IService<Answer> {
    Result add(Answer answer);
    Result listPage(AnswerREQ req);
    Result update(Answer answer);
    int getScore(AnswerREQ req);
}
