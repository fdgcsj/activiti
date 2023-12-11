package org.fosu.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.fosu.workflow.entities.TeacherScore;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.req.courseManagerREQ;

public interface TeacherScoreMapper extends BaseMapper<TeacherScore> {

    IPage<TeacherScore> listPage(IPage<TeacherScore> page, @Param("req") courseManagerREQ req);
}
