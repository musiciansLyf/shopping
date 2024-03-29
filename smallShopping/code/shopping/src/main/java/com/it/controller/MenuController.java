package com.it.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Permission;
import com.it.entity.SubSystemInfo;
import com.it.service.PermissionService;
import com.it.util.Result;
import com.it.util.ResultResponse;
import com.it.util.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资源管理
 *
 * @author 王钰尧
 * @create 2018/10/21 10:50
 * @since 1.0.0
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 资源管理首页页面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/menuManagerHouse.do")
    public ModelAndView menuManagerHouse(ModelAndView mv) {
        //获取子系统列表
        List<SubSystemInfo> sysList = permissionService.getSubSystemInfo();
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (SubSystemInfo sys : sysList) {
            Map<String, Object> sysMap = new HashMap<>();
            sysMap.put("name", sys.getSubSystemName());
            sysMap.put("id", sys.getSubSystemId());
            List<Map<String, Object>> mapList = permissionService.getMenuByParentId(sys.getSubSystemId());
            sysMap.put("children", mapList);
            resultList.add(sysMap);
        }
        mv.addObject("sysList", resultList);
        mv.setViewName("/systemSet/menuManager");
        return mv;
    }

    /**
     * 资源管理界面列表
     *
     * @param permissionId
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @GetMapping("menu.do")
    public TableResultResponse userTables(int page, int limit, String permissionId) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<Permission> pageInfo = permissionService.getPermissionByParentId(permissionId, page, limit);
        int i = (page - 1) * limit + 1;
        for (Permission permissionEntity : pageInfo.getRecords()) {
            Map<String, Object> permissionMap = new HashMap<>(16);
            permissionMap.put("index", i + "");
            permissionMap.put("id", permissionEntity.getPermissionId());
            permissionMap.put("parentId", permissionEntity.getParentId());
            permissionMap.put("permissionName", permissionEntity.getPermissionName());
            permissionMap.put("url", permissionEntity.getUrl());
            permissionMap.put("permissionMark", permissionEntity.getPermissionMark());
            permissionMap.put("permissionType", permissionEntity.getPermissionType());
            infoList.add(permissionMap);
            i++;
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
    }

    /**
     * 新增资源页面弹窗
     *
     * @param parentId
     * @return
     */
    @RequestMapping("/addMenuHouser")
    public ModelAndView addMenuHouser(String parentId, ModelAndView mv) {
        Permission permission = permissionService.getPermissionById(parentId);
        mv.addObject("permission", permission);
        mv.setViewName("/systemSet/addMenu");
        return mv;
    }

    /**
     * 新增资源
     *
     * @param permission
     * @return
     */
    @PostMapping("/addMenu")
    @ResponseBody
    public ResultResponse addMenu(Permission permission) {
        String oldChar = permission.getPermissionMark().split(":")[0];
        String newChar = permission.getPermissionType();
        String newMark = permission.getPermissionMark().replace(oldChar, newChar);
        permission.setPermissionMark(newMark);
        boolean result = permissionService.insert(permission);
        if (!result) {
            return Result.resuleError("新增失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 删除资源
     *
     * @param id
     * @return
     */
    @PostMapping("/delMenu")
    @ResponseBody
    public ResultResponse addMenu(String id) {
        boolean result = permissionService.delete(id);
        if (!result) {
            return Result.resuleError("删除成功");
        }
        return Result.resuleSuccess();
    }
}