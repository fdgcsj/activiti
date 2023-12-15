package org.fosu.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.req.courseManagerREQ;
import org.fosu.workflow.utils.Result;
import org.springframework.web.bind.annotation.PathVariable;

public interface courseManagerService extends IService<courseManager> {
    Result add(courseManager CourseManager);

    Result listPage(courseManagerREQ req);

    Result getAllStudents(String courseName,String userName);

    Result allCourseNameAndTeacherlist(courseManagerREQ req);

    Result classCourseNameAndTeacherlist(courseManagerREQ req);

    Result update(courseManager CourseManager);

    Result viewById(String id);

    Result listClassInfoPage(courseManagerREQ req);

    Result listelectiveCourse(courseManagerREQ req);

    Result selectElectiveCourse(String id, String username);

    Result deleteElectiveCourse(String id, String username);

    Result allCourseNameAndTeacherAndClasslist(courseManagerREQ req);

    Result classCourseNameAndTeacherAndClasslist(courseManagerREQ req);
}
