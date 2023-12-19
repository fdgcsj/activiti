package org.fosu.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.fosu.workflow.entities.ChoiceQuestion;
import org.fosu.workflow.req.PaperREQ;
import org.fosu.workflow.req.QuestionREQ;
import org.fosu.workflow.utils.Result;

public interface ChoiceQuestionService extends IService<ChoiceQuestion> {
    Result add(ChoiceQuestion question);
    Result listPage(QuestionREQ req);
    Result update(ChoiceQuestion question);
    int getTotalScore(String paperId);
}
