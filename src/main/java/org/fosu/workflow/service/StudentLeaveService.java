package org.fosu.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.fosu.workflow.entities.StudentLeave;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.req.AttendREQ;
import org.fosu.workflow.req.courseManagerREQ;
import org.fosu.workflow.utils.Result;
import org.springframework.web.bind.annotation.RequestBody;

public interface StudentLeaveService extends IService<StudentLeave> {
    Result add(StudentLeave studentLeave);

    Result listPage(AttendREQ req);

    Result update(StudentLeave studentLeave);

    Result viewById(String id);

}
