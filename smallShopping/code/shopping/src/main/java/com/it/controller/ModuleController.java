package com.it.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Role;
import com.it.entity.User;
import com.it.service.RoleService;
import com.it.service.UserService;
import com.it.util.Result;
import com.it.util.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单管理接口
 *
 * @author 王钰尧
 * @create 2018/10/10 16:38
 * @since 1.0.0
 */
@Controller
@RequestMapping("/module")
public class ModuleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    /**
     * 权限管理首页页面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/moduleManagerHouse.do")
    public ModelAndView moduleManagerHouse(ModelAndView mv) {
        //获取角色列表
        List<Role> roleList = roleService.getRoleList();
        List<Map<String, String>> resultList = new ArrayList<>();
        for (Role role : roleList) {
            Map<String, String> roleMap = new HashMap<>();
            roleMap.put("id", role.getId());
            roleMap.put("name", role.getRole());
            roleMap.put("description", role.getDescription());
            resultList.add(roleMap);
        }
        mv.addObject("roleList", resultList);
        mv.setViewName("/systemSet/moduleManager");
        return mv;
    }

    @ResponseBody
    @GetMapping("user.do")
    public TableResultResponse userTables(String roleId, int page, int limit) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<User> pageInfo = userService.getUserListByRoleId(roleId, page, limit);
        int i = (page - 1) * limit + 1;
        for (User userEntity : pageInfo.getRecords()) {
            Map<String, Object> userMap = new HashMap<>(16);
            userMap.put("index", i + "");
            userMap.put("id", userEntity.getId());
            userMap.put("userName", userEntity.getUserName());
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
}