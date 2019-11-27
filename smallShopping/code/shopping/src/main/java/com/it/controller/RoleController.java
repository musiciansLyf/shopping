package com.it.controller;

import com.it.service.RoleService;
import com.it.util.Result;
import com.it.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 角色管理接口
 *
 * @author
 * @create 2018/10/12 9:52
 * @since 1.0.0
 */

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 权限管理中,为角色新增用户的弹窗url地址
     *
     * @param mv
     * @param roleId
     * @return
     */
    @GetMapping("/addUserForRole.do")
    public ModelAndView addUserForRole(ModelAndView mv, String roleId) {
        mv.addObject("roleId", roleId);
        mv.setViewName("/systemSet/addUserForRole");
        return mv;
    }

    /**
     * 分配角色
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @PostMapping("/addUser.do")
    public ResultResponse addUser(String ids, String roleId) {
        boolean result = roleService.allotRole(ids, roleId);
        if (!result) {
            return Result.resuleError("添加失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 删除角色下的用户
     * @param userId
     * @param roleId
     * @return
     */
    @ResponseBody
    @DeleteMapping("/delUser.do")
    public ResultResponse delUser(String userId, String roleId) {
        boolean result = roleService.delUser(userId, roleId);
        if (!result) {
            return Result.resuleError("删除失败");
        }
        return Result.resuleSuccess();
    }
}