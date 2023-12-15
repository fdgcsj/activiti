package org.fosu.workflow.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.fosu.workflow.entities.Loan;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.mapper.LoanMapper;
import org.fosu.workflow.mapper.courseManagerMapper;
import org.fosu.workflow.req.LoanREQ;
import org.fosu.workflow.req.courseManagerREQ;
import org.fosu.workflow.service.BusinessStatusService;
import org.fosu.workflow.service.LoanService;
import org.fosu.workflow.service.courseManagerService;
import org.fosu.workflow.utils.Result;
import org.fosu.workflow.utils.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class courseManagerServiceimpl extends ServiceImpl<courseManagerMapper, courseManager> implements courseManagerService {
    @Autowired
    private BusinessStatusService businessStatusService;

    @Override
    public Result add(courseManager CourseManager) {
        CourseManager.setTchId(UserUtils.getUsername());
        int size = baseMapper.insert(CourseManager);
        if (size == 1) {
            businessStatusService.add(CourseManager.getId());
        }
        return Result.ok();
    }

    @Override
    public Result listPage(courseManagerREQ req) {
        if (StringUtils.isEmpty(req.getUsername())) {
            req.setUsername(UserUtils.getUsername());
        }
        IPage<courseManager> page = baseMapper.getcourseManagerAndStatusList(req.getPage(), req);
        return Result.ok(page);
    }

    @Override
    public Result allCourseNameAndTeacherlist(courseManagerREQ req) {
        if (StringUtils.isEmpty(req.getUsername())) {
            req.setUsername(UserUtils.getUsername());
        }
        IPage<courseManager> page = baseMapper.getallCourseNameAndTeacherlist(req.getPage(), req);
        return Result.ok(page);
    }

    @Override
    public Result classCourseNameAndTeacherlist(courseManagerREQ req) {
        if (StringUtils.isEmpty(req.getUsername())) {
            req.setUsername(UserUtils.getUsername());
        }
        IPage<courseManager> page = baseMapper.getClassCourseNameAndTeacherlist(req.getPage(), req);
        return Result.ok(page);
    }

    @Override
    public Result update(courseManager CourseManager) {
        if (CourseManager == null || StringUtils.isEmpty(CourseManager.getId())) {
            return Result.error("数据不合法");
        }
        // 查询原数据
        courseManager entity = baseMapper.selectById(CourseManager.getId());
        // 拷贝新数据
        BeanUtils.copyProperties(CourseManager, entity);
        entity.setUpdateDate(new Date());
        baseMapper.updateById(entity);
        return Result.ok();
    }

    @Override
    public Result viewById(String id) {
        ArrayList<courseManager> list = baseMapper.viewById(id);
        return Result.ok(list);
    }

    @Override
    public Result listClassInfoPage(courseManagerREQ req) {
        if (StringUtils.isEmpty(req.getUsername())) {
            req.setUsername(UserUtils.getUsername());
        }
        IPage<courseManager> page = baseMapper.getClassInfoPage(req.getPage(), req);
        return Result.ok(page);
    }


    @Override
    public Result listelectiveCourse(courseManagerREQ req) {
        if (StringUtils.isEmpty(req.getUsername())) {
            req.setUsername(UserUtils.getUsername());
        }
        IPage<courseManager> page = baseMapper.getelectiveCourse(req.getPage(), req);
        return Result.ok(page);
    }

    @Override
    public Result selectElectiveCourse(String id, String username) {
        baseMapper.selectElectiveCourse(id, username);
        return Result.ok();
    }

    @Override
    public Result deleteElectiveCourse(String id, String username) {
        baseMapper.deleteElectiveCourse(id, username);
        return Result.ok();
    }

}
