package com.it.service;

import com.it.entity.Role;

import java.util.List;

public interface RoleService {
    /**
     * 通用查询
     *
     * @param role
     * @return
     */
    List<Role> selectSysRole(Role role);

    /**
     * 根据主键id查询
     *
     * @param id
     * @return
     */
    Role selectByPrimaryKey(String id);

    /**
     * 新增
     *
     * @param role
     * @return
     */
    boolean insert(Role role);

    /**
     * 修改
     *
     * @param role
     * @return
     */
    boolean updateByPrimaryKey(Role role);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    boolean deleteByPrimaryKey(int id);

    /**
     * 分配角色
     *
     * @param userIds
     * @param roleId
     * @return
     */
    boolean allotRole(String userIds, String roleId);

    /**
     * 删除角色下的用户
     *
     * @param userId
     * @param roleId
     * @return
     */
    boolean delUser(String userId, String roleId);

    /**
     * 得到所有角色,没有条件
     *
     * @return
     */
    List<Role> getRoleList();

}
