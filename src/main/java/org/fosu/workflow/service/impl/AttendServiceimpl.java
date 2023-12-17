package org.fosu.workflow.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.fosu.workflow.entities.Attend;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.mapper.AttendMapper;
import org.fosu.workflow.mapper.courseManagerMapper;
import org.fosu.workflow.req.AttendREQ;
import org.fosu.workflow.req.courseManagerREQ;
import org.fosu.workflow.service.AttendService;
import org.fosu.workflow.service.BusinessStatusService;
import org.fosu.workflow.service.courseManagerService;
import org.fosu.workflow.utils.Result;
import org.fosu.workflow.utils.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AttendServiceimpl extends ServiceImpl<AttendMapper, Attend> implements AttendService {
    @Override
    public Result add(Attend attend){
        attend.setTchId(UserUtils.getUsername());
        baseMapper.insert(attend);
        return Result.ok();
    };
    @Override
    public Result getAttendList(AttendREQ req) {
        if (StringUtils.isEmpty(req.getUsername())) {
            req.setUsername(UserUtils.getUsername());
        }
        IPage<Attend> page = baseMapper.getAttendList(req.getPage(), req);
        return Result.ok(page);
    }
    @Override
    public Result update(Attend attend) {
        if (attend == null || StringUtils.isEmpty(attend.getId())) {
            return Result.error("数据不合法");
        }
        // 查询原数据
        Attend entity = baseMapper.selectById(attend.getId());
        // 拷贝新数据
        BeanUtils.copyProperties(attend, entity);
        entity.setUpdateDate(new Date());
        baseMapper.updateById(entity);
        return Result.ok();
    }

}
