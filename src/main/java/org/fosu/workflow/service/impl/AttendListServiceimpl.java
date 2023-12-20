package org.fosu.workflow.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.fosu.workflow.entities.Attend;
import org.fosu.workflow.entities.AttendList;
import org.fosu.workflow.mapper.AttendListMapper;
import org.fosu.workflow.mapper.AttendMapper;
import org.fosu.workflow.req.AttendREQ;
import org.fosu.workflow.service.AttendListService;
import org.fosu.workflow.service.AttendService;
import org.fosu.workflow.utils.Result;
import org.fosu.workflow.utils.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Service
public class AttendListServiceimpl extends ServiceImpl<AttendListMapper, AttendList> implements AttendListService {
    @Override
    public Result add(AttendList[] attendList){
        baseMapper.insertAttendList(Arrays.asList(attendList));
        return Result.ok();
    };
    @Override
    public Result getAttendList(String id) {
        ArrayList<AttendList> list=baseMapper.getAttendList(id);
        return Result.ok(list);
    }
    @Override
    public Result update(AttendList[] attendListArray) {
        if (attendListArray == null) {
            return Result.error("数据不合法");
        }
        for (AttendList attendList : attendListArray) {
            if (StringUtils.isEmpty(attendList.getId())) {
                return Result.error("数据不合法");
            }
            // 查询原数据
            AttendList entity = baseMapper.selectById(attendList.getId());
            // 拷贝新数据
            BeanUtils.copyProperties(attendList, entity);
            baseMapper.updateById(entity);
        }
        return Result.ok();
    }


}
