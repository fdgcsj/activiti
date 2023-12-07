package org.fosu.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.req.courseManagerREQ;
import org.fosu.workflow.utils.Result;

public interface courseManagerService extends IService<courseManager> {
    Result add(courseManager CourseManager);
    Result listPage(courseManagerREQ req);
    Result update(courseManager CourseManager);
    Result listClassInfoPage(courseManagerREQ req);
}
