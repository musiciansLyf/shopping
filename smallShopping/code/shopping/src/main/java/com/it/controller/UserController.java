package com.it.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.User;
import com.it.service.LogService;
import com.it.service.UserService;
import com.it.util.Result;
import com.it.util.ResultResponse;
import com.it.util.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户接口
 *
 * @author
 * @create 2018/10/9 17:02
 * @since 1.0.0
 */
@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;

    /**
     * 用户管理界面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/userManagerHouse.do")
    public ModelAndView userManagerHouse(ModelAndView mv) {
        mv.setViewName("systemSet/userManager");
        return mv;
    }

    /**
     * 用户管理界面列表
     *
     * @param user
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @GetMapping("user.do")
    public TableResultResponse userTables(User user, int page, int limit) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<User> pageInfo = userService.getUserList(user, page, limit);
        int i = (page - 1) * limit + 1;
        for (User userEntity : pageInfo.getRecords()) {
            Map<String, Object> userMap = new HashMap<>(16);
            userMap.put("index", i + "");
            userMap.put("id", userEntity.getId());
            userMap.put("userName", userEntity.getUserName());
            userMap.put("address", userEntity.getAddress());
            userMap.put("sex", userEntity.getSex());
            userMap.put("iphone", userEntity.getIphone());
            userMap.put("email", userEntity.getEmail());
            userMap.put("createdDate", userEntity.getCreatedDate() == null ? "" : userEntity.getCreatedDate().substring(0, 19));
            userMap.put("updateDate", userEntity.getUpdatedDate() == null ? "" : userEntity.getUpdatedDate().substring(0, 19));
            if (userEntity.getStatus() == 1) {
                userMap.put("status", "启用");
            } else if (userEntity.getStatus() == 0) {
                userMap.put("status", "禁用");
            }
            infoList.add(userMap);
            i++;
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
    }

    /**
     * 权限管理中的用户列表
     *
     * @param user
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @GetMapping("userForRole.do")
    public TableResultResponse userTablesForRole(User user, int page, int limit, String roleId) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<User> pageInfo = userService.getUserListForRole(user, page, limit, roleId);
        int i = (page - 1) * limit + 1;
        for (User userEntity : pageInfo.getRecords()) {
            Map<String, Object> userMap = new HashMap<>(16);
            userMap.put("index", i + "");
            userMap.put("id", userEntity.getId());
            userMap.put("userName", userEntity.getUserName());
            userMap.put("createdDate", userEntity.getCreatedDate() == null ? "" : userEntity.getCreatedDate().substring(0, 19));
            if (userEntity.getStatus() == 1) {
                userMap.put("status", "启用");
            } else if (userEntity.getStatus() == 0) {
                userMap.put("status", "禁用");
            }
            infoList.add(userMap);
            i++;
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
    }

    /**
     * 新增用户跳转界面
     *
     * @param mv
     * @return
     */
    @RequestMapping("/addUserHouser.do")
    public ModelAndView addUserHouser(ModelAndView mv) {
        mv.setViewName("systemSet/addUser");
        return mv;
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("/user.do")
    public ResultResponse addUser(User user) {
        User checkUser = userService.getUserByUserName(user.getUserName());
        if (checkUser != null) {
            return Result.resuleError("用户名已存在");
        }
        boolean result = userService.insert(user);
        if (!result) {
            return Result.resuleError("新增失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 删除用户,批量删除,单个删除通用
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @DeleteMapping("/user.do")
    public ResultResponse delUser(String ids) {
        boolean result = userService.deleteByPrimaryKey(ids);
        if (!result) {
            return Result.resuleError("删除失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 更改用户状态
     *
     * @param id
     * @param status
     * @return
     */
    @ResponseBody
    @PostMapping("status.do")
    public ResultResponse updateUserStatus(String id, Integer status) {
        boolean result = userService.updateUserStatus(id, status);
        if (!result) {
            return Result.resuleError("更改失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 修改用户跳转界面
     *
     * @param mv
     * @return
     */
    @RequestMapping("/editUserHouser.do")
    public ModelAndView editUserHouser(ModelAndView mv, String id) {
        User user = userService.selectByPrimaryKey(id);
        mv.addObject("user", user);
        mv.setViewName("systemSet/editUser");
        return mv;
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @ResponseBody
    @PutMapping("/user.do")
    public ResultResponse editUser(User user) {
        boolean result = userService.updateByPrimaryKey(user);
        if (!result) {
            return Result.resuleError("修改失败,未知错误");
        }
        logService.insert("修改个人信息");
        return Result.resuleSuccess();
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("/userRegister.do")
    public ResultResponse userRegister(User user) {
        System.out.println(user);
        User checkUser = userService.getUserByUserName(user.getUserName());
        if (checkUser != null) {
            return Result.resuleError("用户名已存在");
        }
        boolean result = userService.insert(user);
        if (!result) {
            return Result.resuleError("新增失败");
        }
        return Result.resuleSuccess();
    }
}