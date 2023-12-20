package org.fosu.workflow.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.fosu.workflow.entities.TeacherScore;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.mapper.TeacherScoreMapper;
import org.fosu.workflow.req.courseManagerREQ;
import org.fosu.workflow.service.TeacherScoreService;
import org.fosu.workflow.utils.Result;
import org.fosu.workflow.utils.UserUtils;
import org.springframework.stereotype.Service;

@Service
public class TeacherScoreServiceimpl extends ServiceImpl<TeacherScoreMapper, TeacherScore> implements TeacherScoreService {

    @Override
    public Result add(TeacherScore teacherScore){
        teacherScore.setTch_id(UserUtils.getUsername());
        baseMapper.insert(teacherScore);
        return Result.ok();
    }
    @Override
    public Result listPage(courseManagerREQ req){
        if (StringUtils.isEmpty(req.getUsername())) {
            req.setUsername(UserUtils.getUsername());
        }
        IPage<TeacherScore> page = baseMapper.listPage(req.getPage(), req);
        return Result.ok(page);
    };

}
