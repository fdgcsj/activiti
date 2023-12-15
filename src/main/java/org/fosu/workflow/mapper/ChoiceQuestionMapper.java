package org.fosu.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.fosu.workflow.entities.ChoiceQuestion;
import org.fosu.workflow.entities.Paper;
import org.fosu.workflow.req.PaperREQ;
import org.fosu.workflow.req.QuestionREQ;

public interface ChoiceQuestionMapper extends BaseMapper<ChoiceQuestion> {
    IPage<ChoiceQuestion> getQuestionList(IPage page, @Param("req") QuestionREQ req);
}
