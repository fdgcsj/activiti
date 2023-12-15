package org.fosu.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.fosu.workflow.entities.Student;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.req.courseManagerREQ;

import java.util.ArrayList;

public interface courseManagerMapper extends BaseMapper<courseManager> {
    ArrayList<courseManager> getAllStudents(String courseName, String userName);

    IPage<courseManager> getcourseManagerAndStatusList(IPage<courseManager> page, @Param("req") courseManagerREQ req);

    IPage<courseManager> getallCourseNameAndTeacherlist(IPage<courseManager> page, @Param("req") courseManagerREQ req);

    IPage<courseManager> getClassCourseNameAndTeacherlist(IPage<courseManager> page, @Param("req") courseManagerREQ req);

    IPage<courseManager> getClassInfoPage(IPage<courseManager> page, @Param("req") courseManagerREQ req);

    IPage<courseManager> getelectiveCourse(IPage<courseManager> page, @Param("req") courseManagerREQ req);

    ArrayList<courseManager> viewById(String id);

    void selectElectiveCourse(String id, String username);

    void deleteElectiveCourse(String id, String username);

    IPage<courseManager> allCourseNameAndTeacherAndClasslist(IPage<courseManager> page, @Param("req") courseManagerREQ req);

    IPage<courseManager> classCourseNameAndTeacherAndClasslist(IPage<courseManager> page, @Param("req") courseManagerREQ req);
}
