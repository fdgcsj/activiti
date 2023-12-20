package org.fosu.workflow.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.fosu.workflow.entities.StudentLeave;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.mapper.StudentLeaveMapper;
import org.fosu.workflow.mapper.courseManagerMapper;
import org.fosu.workflow.req.AttendREQ;
import org.fosu.workflow.req.courseManagerREQ;
import org.fosu.workflow.service.BusinessStatusService;
import org.fosu.workflow.service.StudentLeaveService;
import org.fosu.workflow.service.courseManagerService;
import org.fosu.workflow.utils.Result;
import org.fosu.workflow.utils.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
@Service
public class StudentLeaveServiceimpl extends ServiceImpl<StudentLeaveMapper, StudentLeave> implements StudentLeaveService {
    @Autowired
    private BusinessStatusService businessStatusService;

    @Override
    public Result add(StudentLeave studentLeave) {
        studentLeave.setName(UserUtils.getUsername());
        int size = baseMapper.insert(studentLeave);
        if (size == 1) {
            businessStatusService.add(studentLeave.getId());
        }
        return Result.ok();
    }

    @Override
    public Result listPage(AttendREQ req) {
        if (StringUtils.isEmpty(req.getUsername())) {
            req.setUsername(UserUtils.getUsername());
        }
        IPage<courseManager> page = baseMapper.getStudentLeaveList(req.getPage(), req);
        return Result.ok(page);
    }

    @Override
    public Result update(StudentLeave studentLeave) {
        if (studentLeave == null || StringUtils.isEmpty(studentLeave.getId())) {
            return Result.error("数据不合法");
        }
        // 查询原数据
        StudentLeave entity = baseMapper.selectById(studentLeave.getId());
        // 拷贝新数据
        BeanUtils.copyProperties(studentLeave, entity);
        baseMapper.updateById(entity);
        return Result.ok();
    }
    @Override
    public Result viewById(String id) {
        ArrayList<StudentLeave> list = baseMapper.viewById(id);
        return Result.ok(list);
    }
}
