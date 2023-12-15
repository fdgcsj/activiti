package org.fosu.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.fosu.workflow.entities.Speak;
import org.fosu.workflow.req.SpeakREQ;
import org.fosu.workflow.utils.Result;

public interface SpeakService extends IService<Speak> {
    Result add(Speak speak);
    Result listPage(SpeakREQ req);
    Result update(Speak speak);
}
