package org.fosu.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.fosu.workflow.entities.Leave;
import org.fosu.workflow.req.LeaveREQ;
import org.fosu.workflow.utils.Result;

public interface LeaveService extends IService<Leave> {

    Result add(Leave leave);
    Result listPage(LeaveREQ req);
    Result update(Leave leave);
}