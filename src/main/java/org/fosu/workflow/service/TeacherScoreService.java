package org.fosu.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.fosu.workflow.entities.TeacherScore;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.mapper.TeacherScoreMapper;
import org.fosu.workflow.mapper.courseManagerMapper;
import org.fosu.workflow.req.courseManagerREQ;
import org.fosu.workflow.service.impl.TeacherScoreServiceimpl;
import org.fosu.workflow.utils.Result;

public interface TeacherScoreService extends IService<TeacherScore>{
    Result add(TeacherScore teacherScore);

    Result listPage(courseManagerREQ req);
}
