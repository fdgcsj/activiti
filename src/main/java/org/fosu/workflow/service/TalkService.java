package org.fosu.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.fosu.workflow.entities.Talk;
import org.fosu.workflow.req.TalkREQ;
import org.fosu.workflow.utils.Result;

public interface TalkService extends IService<Talk> {
    Result add(Talk talk);
    Result listPage(TalkREQ req);
    Result update(Talk talk);
}
