package org.fosu.workflow.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.fosu.workflow.entities.ChoiceQuestion;
import org.fosu.workflow.entities.Paper;
import org.fosu.workflow.mapper.ChoiceQuestionMapper;
import org.fosu.workflow.req.PaperREQ;
import org.fosu.workflow.req.QuestionREQ;
import org.fosu.workflow.service.ChoiceQuestionService;
import org.fosu.workflow.utils.Result;
import org.fosu.workflow.utils.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ChoiceQuestionServiceImpl extends ServiceImpl<ChoiceQuestionMapper, ChoiceQuestion> implements ChoiceQuestionService {
    @Override
    @Transactional
    public Result add(ChoiceQuestion question) {
        question.setCreateDate(new Date());
        if(baseMapper.insert(question) == 1)
            return Result.ok();
        return Result.error("添加新选择题失败");
    }

    @Override
    public Result listPage(QuestionREQ req) {
        IPage<ChoiceQuestion> page = baseMapper.getQuestionList(req.getPage(), req);
        return Result.ok(page);
    }

    @Override
    public Result update(ChoiceQuestion question) {
        if (question == null || StringUtils.isEmpty(question.getId())) {
            return Result.error("数据不合法");
        }
        //查询元数据
        ChoiceQuestion entity = baseMapper.selectById(question.getId());
        // 拷贝新数据
        BeanUtils.copyProperties(question, entity);
        baseMapper.updateById(entity);
        return Result.ok();
    }
}
