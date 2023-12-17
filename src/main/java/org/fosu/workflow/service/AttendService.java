package org.fosu.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.fosu.workflow.entities.Attend;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.req.AttendREQ;
import org.fosu.workflow.req.courseManagerREQ;
import org.fosu.workflow.utils.Result;

public interface AttendService extends IService<Attend> {
    Result add(Attend attend);
    Result getAttendList(AttendREQ req);
    Result update(Attend attend);


}
