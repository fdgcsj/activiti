package org.fosu.workflow.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.fosu.workflow.entities.Answer;
import org.fosu.workflow.entities.Paper;
import org.fosu.workflow.mapper.AnswerMapper;
import org.fosu.workflow.req.AnswerREQ;
import org.fosu.workflow.service.AnswerService;
import org.fosu.workflow.utils.Result;

import org.fosu.workflow.utils.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements AnswerService {
    @Override
    @Transactional
    public Result add(Answer answer) {
        answer.setCreator(UserUtils.getUsername());
        if(baseMapper.insert(answer) == 1)
            return Result.ok();
        return Result.error("答题失败");
    }

    @Override
    public Result listPage(AnswerREQ req) {
        IPage<Answer> page = baseMapper.getAnswerList(req.getPage(), req);
        return Result.ok(page);
    }

    @Override
    public Result update(Answer answer) {
        if (answer == null || StringUtils.isEmpty(answer.getId())) {
            return Result.error("数据不合法");
        }
        //查询元数据
        Answer entity = baseMapper.selectById(answer.getId());
        // 拷贝新数据
        BeanUtils.copyProperties(answer, entity);
        baseMapper.updateById(entity);
        return Result.ok();
    }
}
