package org.fosu.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.fosu.workflow.entities.Attend;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.req.AttendREQ;
import org.fosu.workflow.req.courseManagerREQ;

public interface AttendMapper extends BaseMapper<Attend> {
    IPage<Attend> getAttendList(IPage<Attend> page, @Param("req") AttendREQ req);

}
