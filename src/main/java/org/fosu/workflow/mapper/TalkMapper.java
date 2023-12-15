package org.fosu.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.fosu.workflow.entities.Talk;
import org.fosu.workflow.req.LeaveREQ;
import org.fosu.workflow.req.TalkREQ;

public interface TalkMapper extends BaseMapper<Talk> {
    IPage<Talk> getTalkList(IPage page, @Param("req") TalkREQ req);
}
