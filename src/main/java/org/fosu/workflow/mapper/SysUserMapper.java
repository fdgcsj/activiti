package org.fosu.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.fosu.workflow.entities.SysUser;

import java.util.List;

public interface SysUserMapper extends BaseMapper<SysUser> {
    @Select("SELECT sys_user.* FROM sys_user JOIN mxg_teacher ON sys_user.id = mxg_teacher.id")
    List<SysUser> getAllUsers();
}
