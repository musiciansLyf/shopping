package com.it.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 通过UserName查询到User对象,并且将User所拥有的角色也查出来,并将角色下拥有的权限也查出来
     *
     * @param userName
     * @return
     */
    User findUserByUserName(String userName);

    /**
     * 仅仅得到User对象
     *
     * @param userName
     * @return
     */
    User getUserByUserName(String userName);

    /**
     * 通过User对象查找列表
     *
     * @param user
     * @param page
     * @param limit
     * @return
     */
    Page<User> getUserList(User user, int page, int limit);

    /**
     * 得到角色下没有的用户列表
     *
     * @param user
     * @param page
     * @param limit
     * @return
     */
    Page<User> getUserListForRole(User user, int page, int limit, String roleId);

    /**
     * 通过角色id得到下面的用户
     *
     * @param roleId
     * @param page
     * @param limit
     * @return
     */
    Page<User> getUserListByRoleId(String roleId, int page, int limit);

    /**
     * 修改密码
     *
     * @param newPas
     * @return
     */
    boolean changePass(String newPas, String userName);

    /**
     * 修改用户状态
     *
     * @param id
     * @param status
     * @return
     */
    boolean updateUserStatus(String id, Integer status);

    /**
     * 根据主键id查询
     *
     * @param id
     * @return
     */
    User selectByPrimaryKey(String id);

    /**
     * 新增
     *
     * @param user
     * @return
     */
    boolean insert(User user);

    /**
     * 修改
     *
     * @param user
     * @return
     */
    boolean updateByPrimaryKey(User user);

    /**
     * 删除,单个删除批量删除通用
     *
     * @param ids
     * @return
     */
    boolean deleteByPrimaryKey(String ids);

    /**
     * 修改登陆时间
     *
     * @param userName
     * @param time
     * @return
     */
    void updateLoginTime(String userName, String time);

    List<User> selectList();

    boolean isAdmin(String userId);

    /**
     * 统计用户总户数
     */
    String zumNumber();
}
