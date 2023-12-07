package org.fosu.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.fosu.workflow.entities.Loan;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.req.LoanREQ;
import org.fosu.workflow.req.courseManagerREQ;

public interface courseManagerMapper extends BaseMapper<courseManager> {
    IPage<courseManager> getcourseManagerAndStatusList(IPage<courseManager> page, @Param("req") courseManagerREQ req);

    IPage<courseManager> getClassInfoPage(IPage<courseManager> page, @Param("req") courseManagerREQ req);

    IPage<courseManager> getelectiveCourse(IPage<courseManager> page, @Param("req") courseManagerREQ req);

    void selectElectiveCourse(String id, String username);

    void deleteElectiveCourse(String id, String username);
}
