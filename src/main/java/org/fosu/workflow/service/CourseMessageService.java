package org.fosu.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.fosu.workflow.entities.CourseMessage;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.req.CourseMessageREQ;
import org.fosu.workflow.utils.Result;

public interface CourseMessageService extends IService<CourseMessage> {
    Result add(CourseMessage courseMessage);

    Result update(CourseMessage courseMessage);

    Object viewById(String id);

    Result listPage(CourseMessageREQ req);

    Result classMessageList(CourseMessageREQ req);
}
