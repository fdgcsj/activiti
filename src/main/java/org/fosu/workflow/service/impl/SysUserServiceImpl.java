package org.fosu.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.fosu.workflow.entities.SysUser;
import org.fosu.workflow.mapper.SysUserMapper;
import org.fosu.workflow.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    /** 通过用户名获取用户信息 */
    @Override
    public SysUser findByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }

        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);

        // baseMapper对应的就是SysUserMapper
        return baseMapper.selectOne(wrapper);
    }

    /** 获取全部用户信息 */
    @Override
    public List<SysUser> getAllUsers() {
        return baseMapper.getAllUsers();
    }
}
