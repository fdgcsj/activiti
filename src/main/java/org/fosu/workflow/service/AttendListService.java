package org.fosu.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.fosu.workflow.entities.Attend;
import org.fosu.workflow.entities.AttendList;
import org.fosu.workflow.req.AttendREQ;
import org.fosu.workflow.utils.Result;
import org.springframework.stereotype.Service;

public interface AttendListService extends IService<AttendList> {
    Result add(AttendList[] attendList);
    Result getAttendList(String id);
    Result update(AttendList[] attendList);


}
