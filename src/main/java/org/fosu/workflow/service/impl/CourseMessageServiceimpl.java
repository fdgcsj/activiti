package org.fosu.workflow.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.fosu.workflow.entities.CourseMessage;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.mapper.CourseMessageMapper;
import org.fosu.workflow.mapper.courseManagerMapper;
import org.fosu.workflow.req.CourseMessageREQ;
import org.fosu.workflow.req.courseManagerREQ;
import org.fosu.workflow.service.CourseMessageService;
import org.fosu.workflow.service.courseManagerService;
import org.fosu.workflow.utils.Result;
import org.fosu.workflow.utils.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class CourseMessageServiceimpl extends ServiceImpl<CourseMessageMapper, CourseMessage> implements CourseMessageService {
    @Override
    public Result add(CourseMessage courseMessage){
        courseMessage.setTeacher_name(UserUtils.getUsername());
        baseMapper.insert(courseMessage);
        return Result.ok();
    }
    @Override
    public Result update(CourseMessage courseMessage) {
        if (courseMessage == null || StringUtils.isEmpty(courseMessage.getId())) {
            return Result.error("数据不合法");
        }
        // 查询原数据
        CourseMessage entity = baseMapper.selectById(courseMessage.getId());
        // 拷贝新数据
        BeanUtils.copyProperties(courseMessage, entity);
        baseMapper.updateById(entity);
        return Result.ok();
    }
    @Override
    public Result viewById(String id) {
        ArrayList<CourseMessage> list=baseMapper.viewById(id);
        return Result.ok(list);
    }
    @Override
    public Result listPage(CourseMessageREQ req) {
        if (StringUtils.isEmpty(req.getUsername())) {
            req.setUsername(UserUtils.getUsername());
        }
        IPage<courseManager> page = baseMapper.getCourseMessageList(req.getPage(), req);
        return Result.ok(page);
    }
    @Override
    public Result classMessageList(CourseMessageREQ req) {
        if (StringUtils.isEmpty(req.getUsername())) {
            req.setUsername(UserUtils.getUsername());
        }
        IPage<courseManager> page = baseMapper.classMessageList(req.getPage(), req);
        return Result.ok(page);
    }
}
