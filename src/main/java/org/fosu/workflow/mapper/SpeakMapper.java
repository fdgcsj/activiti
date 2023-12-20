package org.fosu.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.fosu.workflow.entities.Speak;
import org.fosu.workflow.req.SpeakREQ;

public interface SpeakMapper extends BaseMapper<Speak> {
    IPage<Speak> getSpeakList(IPage page, @Param("req") SpeakREQ req);
}
