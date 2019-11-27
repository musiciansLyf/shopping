package com.it.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.it.entity.Role;
import com.it.mapper.RoleMapper;
import com.it.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author
 * @create 2018/10/12 9:55
 * @since 1.0.0
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> selectSysRole(Role role) {
        return null;
    }

    @Override
    public Role selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public boolean insert(Role role) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(Role role) {
        return false;
    }

    @Override
    public boolean deleteByPrimaryKey(int id) {
        return false;
    }

    @Override
    public boolean allotRole(String userIds, String roleId) {
        String[] idList = userIds.split(",");
        int result = 0;
        for (String s : idList) {
            result = roleMapper.allotRole(roleId, s);
        }
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delUser(String userId, String roleId) {
        int result = roleMapper.delUser(userId, roleId);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Role> getRoleList() {
        EntityWrapper<Role> searchInfo = new EntityWrapper<>();
        searchInfo.ne("role", "超级管理员");
        List<Role> roleList = roleMapper.selectList(searchInfo);
        return roleList;
    }
}