package org.fosu.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.fosu.workflow.entities.Paper;
import org.fosu.workflow.entities.Speak;
import org.fosu.workflow.req.PaperREQ;
import org.fosu.workflow.req.SpeakREQ;
import org.fosu.workflow.utils.Result;

public interface PaperService extends IService<Paper> {
    Result add(Paper paper);
    Result listPage(PaperREQ req);
    Result update(Paper paper);
}
