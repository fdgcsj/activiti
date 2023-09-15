package org.fosu.workflow.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.fosu.workflow.entities.Leave;
import org.fosu.workflow.entities.Loan;
import org.fosu.workflow.mapper.LoanMapper;
import org.fosu.workflow.req.LoanREQ;
import org.fosu.workflow.service.BusinessStatusService;
import org.fosu.workflow.service.LoanService;
import org.fosu.workflow.utils.Result;
import org.fosu.workflow.utils.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

@Service
public class LoanServiceImpl extends ServiceImpl<LoanMapper, Loan> implements LoanService {
    @Autowired
    private BusinessStatusService businessStatusService;

    @Override
    public Result add(Loan loan) {
        // 1. 新增借款信息
        // 当前登录用户即为申请人
        loan.setUserId(UserUtils.getUsername());
        int size = baseMapper.insert(loan);
        // 2. 新增借款业务状态：待提交
        if (size == 1) {
            businessStatusService.add(loan.getId());
        }
        return Result.ok();
    }

    @Override
    public Result listPage(LoanREQ req) {
        if (StringUtils.isEmpty(req.getUsername())) {
            req.setUsername(UserUtils.getUsername());
        }
        IPage<Loan> page = baseMapper.getLoanAndStatusList(req.getPage(), req);
        return Result.ok(page);
    }

    @Override
    public Result update(Loan loan) {
        if (loan == null || StringUtils.isEmpty(loan.getId())) {
            return Result.error("数据不合法");
        }
        // 查询原数据
        Loan entity = baseMapper.selectById(loan.getId());
        // 拷贝新数据
        BeanUtils.copyProperties(loan, entity);
        entity.setUpdateDate(new Date());
        baseMapper.updateById(entity);
        return Result.ok();
    }
}