package org.fosu.workflow.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.fosu.workflow.entities.Paper;
import org.fosu.workflow.entities.Speak;
import org.fosu.workflow.mapper.PaperMapper;
import org.fosu.workflow.req.PaperREQ;
import org.fosu.workflow.service.PaperService;
import org.fosu.workflow.utils.Result;
import org.fosu.workflow.utils.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements PaperService {
    @Override
    @Transactional
    public Result add(Paper paper) {
        paper.setCreator(UserUtils.getUsername());
        paper.setCreateDate(new Date());
        if(baseMapper.insert(paper) == 1)
            return Result.ok();
        return Result.error("添加新试卷失败");
    }

    @Override
    public Result listPage(PaperREQ req) {
        IPage<Paper> page = baseMapper.getPaperList(req.getPage(), req);
        return Result.ok(page);
    }

  @Override
    public Result getStudentPaperList(PaperREQ req) {
        IPage<Paper> page = baseMapper.getStudentPaperList(req.getPage(), req);
        return Result.ok(page);
    }

    @Override
    public Result update(Paper paper) {
        if (paper == null || StringUtils.isEmpty(paper.getId())) {
            return Result.error("数据不合法");
        }
        //查询元数据
        Paper entity = baseMapper.selectById(paper.getId());
        // 拷贝新数据
        BeanUtils.copyProperties(paper, entity);
        baseMapper.updateById(entity);
        return Result.ok();
    }
}
