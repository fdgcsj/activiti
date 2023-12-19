package org.fosu.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.fosu.workflow.entities.Answer;
import org.fosu.workflow.entities.Paper;
import org.fosu.workflow.req.AnswerREQ;
import org.fosu.workflow.req.PaperREQ;

import java.util.ArrayList;

public interface AnswerMapper extends BaseMapper<Answer> {
    IPage<Answer> getAnswerList(IPage page, @Param("req") AnswerREQ req);
    ArrayList<Answer> getScore(@Param("req") AnswerREQ req);
}
