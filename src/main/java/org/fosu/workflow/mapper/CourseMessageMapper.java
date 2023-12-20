package org.fosu.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.fosu.workflow.entities.CourseMessage;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.req.CourseMessageREQ;

import java.util.ArrayList;

public interface CourseMessageMapper extends BaseMapper<CourseMessage> {
    ArrayList<CourseMessage> viewById(String id);

    IPage<courseManager> getCourseMessageList(IPage<courseManager> page, @Param("req") CourseMessageREQ req);

    IPage<courseManager> classMessageList(IPage<courseManager> page, @Param("req") CourseMessageREQ req);
}
