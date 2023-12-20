package org.fosu.workflow.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.fosu.workflow.entities.Speak;
import org.fosu.workflow.entities.Talk;
import org.fosu.workflow.mapper.SpeakMapper;
import org.fosu.workflow.req.SpeakREQ;
import org.fosu.workflow.service.SpeakService;
import org.fosu.workflow.utils.Result;
import org.fosu.workflow.utils.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
@Service
public class SpeakServiceImpl extends ServiceImpl<SpeakMapper, Speak> implements SpeakService {
    @Override
    @Transactional
    public Result add(Speak speak) {
        speak.setSpeaker(UserUtils.getUsername());
        speak.setCreateDate(new Date());
        if(baseMapper.insert(speak) == 1)
            return Result.ok();
        return Result.error("添加新发言失败");
    }

    @Override
    public Result listPage(SpeakREQ req) {
        IPage<Speak> page = baseMapper.getSpeakList(req.getPage(), req);
        return Result.ok(page);
    }

    @Override
    public Result update(Speak speak) {
        if (speak == null || StringUtils.isEmpty(speak.getId())) {
            return Result.error("数据不合法");
        }
        //查询元数据
        Speak entity = baseMapper.selectById(speak.getId());
        // 拷贝新数据
        BeanUtils.copyProperties(speak, entity);
        baseMapper.updateById(entity);
        return Result.ok();
    }
}
