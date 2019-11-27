package com.it.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Menu;
import com.it.entity.Permission;
import com.it.entity.SubSystemInfo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限service接口
 */
public interface PermissionService {
    /**
     * 根据UserId获取菜单信息
     *
     * @param userId
     * @return
     */
    Map<String, LinkedHashMap<String, List<Menu>>> getMenuInfoByUserId(String userId);

    /**
     * 根据userId获取子系统详细信息
     *
     * @param userId
     * @return
     */
    List<SubSystemInfo> getSubSystemInfoByUserId(String userId);

    List<SubSystemInfo> getSubSystemInfo();

    /**
     * 查找所有权限资源
     *
     * @return
     */
    List<Permission> getPermissionList();

    /**
     * 通过角色Id查询资源集合
     *
     * @return
     */
    List<Permission> getPermissionListByRoleId(String roleId);

    /**
     * 得到角色下没有的权限列表
     *
     * @param permissionType
     * @param permissionName
     * @param roleId
     * @return
     */
    List<Permission> getPermissionListForRole(String permissionType, String permissionName, String roleId);

    /**
     * 分配权限
     *
     * @param ids
     * @param roleId
     * @return
     */
    boolean allotPermission(String ids, String roleId);

    /**
     * 删除角色下的权限
     *
     * @param permissionId
     * @param roleId
     * @return
     */
    boolean delPermission(String permissionId, String roleId);

    /**
     * 根据父类id得到子类集合以及子类的子类集合,并封装成前台树型所需的数据结构
     *
     * @param id
     * @return
     */
    List<Map<String, Object>> getMenuByParentId(String id);

    /**
     * 根据父类id得到子类集合
     *
     * @param id
     * @return
     */
    Page<Permission> getPermissionByParentId(String id, int page, int limit);

    /**
     * 通过主键查询
     *
     * @return
     */
    Permission getPermissionById(String id);

    /**
     * 新增资源
     *
     * @param permission
     * @return
     */
    boolean insert(Permission permission);

    /**
     * 删除资源,并删除该资源所对应的所有权限
     *
     * @param id
     * @return
     */
    boolean delete(String id);
}
