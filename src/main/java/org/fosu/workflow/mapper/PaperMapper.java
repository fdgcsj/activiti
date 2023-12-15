package org.fosu.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.fosu.workflow.entities.Paper;
import org.fosu.workflow.req.PaperREQ;

public interface PaperMapper extends BaseMapper<Paper> {
    IPage<Paper> getPaperList(IPage page, @Param("req") PaperREQ req);
}
