package org.fosu.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.fosu.workflow.entities.Attend;
import org.fosu.workflow.entities.AttendList;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.req.AttendREQ;

import java.util.ArrayList;
import java.util.List;

public interface AttendListMapper extends BaseMapper<AttendList> {

    int insertAttendList(@Param("list") List<AttendList> list);

    ArrayList<AttendList> getAttendList(String id);

}
