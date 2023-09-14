package org.fosu.workflow.service;
import com.baomidou.mybatisplus.extension.service.IService;
import org.fosu.workflow.entities.Loan;
import org.fosu.workflow.req.LoanREQ;
import org.fosu.workflow.utils.Result;

public interface LoanService extends IService<Loan> {
    Result add(Loan loan);
    Result listPage(LoanREQ req);
    Result update(Loan loan);
}