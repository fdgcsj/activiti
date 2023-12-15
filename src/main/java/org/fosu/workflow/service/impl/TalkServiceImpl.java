package org.fosu.workflow.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.fosu.workflow.entities.Leave;
import org.fosu.workflow.entities.Talk;
import org.fosu.workflow.mapper.TalkMapper;
import org.fosu.workflow.req.TalkREQ;
import org.fosu.workflow.service.TalkService;
import org.fosu.workflow.utils.Result;
import org.fosu.workflow.utils.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TalkServiceImpl extends ServiceImpl<TalkMapper, Talk> implements TalkService {
    @Override
    @Transactional
    public Result add(Talk talk) {
        talk.setCreator(UserUtils.getUsername());
        talk.setCreateDate(new Date());
        if(baseMapper.insert(talk) == 1)
            return Result.ok();
        return Result.error("添加新话题失败");
    }

    @Override
    public Result listPage(TalkREQ req) {
        IPage<Talk> page = baseMapper.getTalkList(req.getPage(), req);
        return Result.ok(page);
    }

    @Override
    public Result update(Talk talk) {
        if (talk == null || StringUtils.isEmpty(talk.getId())) {
            return Result.error("数据不合法");
        }
        //查询元数据
        Talk entity = baseMapper.selectById(talk.getId());
        // 拷贝新数据
        BeanUtils.copyProperties(talk, entity);
        baseMapper.updateById(entity);
        return Result.ok();
    }
}
