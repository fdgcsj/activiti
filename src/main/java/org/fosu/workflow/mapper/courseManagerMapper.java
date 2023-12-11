package org.fosu.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.req.courseManagerREQ;

import java.util.ArrayList;

public interface courseManagerMapper extends BaseMapper<courseManager> {
    IPage<courseManager> getcourseManagerAndStatusList(IPage<courseManager> page, @Param("req") courseManagerREQ req);

    IPage<courseManager> getallCourseNameAndTeacherlist(IPage<courseManager> page, @Param("req") courseManagerREQ req);

    IPage<courseManager> getClassCourseNameAndTeacherlist(Page page, courseManagerREQ req);

    IPage<courseManager> getClassInfoPage(IPage<courseManager> page, @Param("req") courseManagerREQ req);

    IPage<courseManager> getelectiveCourse(IPage<courseManager> page, @Param("req") courseManagerREQ req);

    ArrayList<courseManager> viewById(String id);

    void selectElectiveCourse(String id, String username);

    void deleteElectiveCourse(String id, String username);

}
