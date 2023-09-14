package org.fosu.workflow.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.fosu.workflow.entities.Loan;
import org.fosu.workflow.req.LoanREQ;

public interface LoanMapper extends BaseMapper<Loan> {
    IPage<Loan> getLoanAndStatusList(IPage<Loan> page, @Param("req") LoanREQ req);
}