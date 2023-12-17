package org.fosu.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.fosu.workflow.entities.StudentLeave;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.req.AttendREQ;
import org.fosu.workflow.req.courseManagerREQ;

import java.util.ArrayList;

public interface StudentLeaveMapper extends BaseMapper<StudentLeave> {
    IPage<courseManager> getStudentLeaveList(IPage<StudentLeave> page, @Param("req") AttendREQ req);

    ArrayList<StudentLeave> viewById(String id);
}
