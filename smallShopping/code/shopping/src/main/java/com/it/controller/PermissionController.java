package com.it.controller;

import com.it.entity.Permission;
import com.it.service.PermissionService;
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
 * 权限接口
 *
 * @author
 * @create 2018/10/11 8:35
 * @since 1.0.0
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/permissionByRoleId.do")
    @ResponseBody
    public TableResultResponse getPermissionListByRoleId(String roleId) {
        List<Map<String, Object>> listInfo = new ArrayList<>();
        List<Permission> permissionList = permissionService.getPermissionListByRoleId(roleId);
        for (Permission permission : permissionList) {
            Map<String, Object> permissionMap = new HashMap(16);
            permissionMap.put("id", permission.getPermissionId());
            permissionMap.put("permissionName", permission.getPermissionName());
            permissionMap.put("permissionType", permission.getPermissionType());
            permissionMap.put("url", permission.getUrl());
            permissionMap.put("parentId", permission.getParentId());
            listInfo.add(permissionMap);
        }
        return Result.tableResule(0, listInfo);
    }

    /**
     * 权限管理中,为角色新增权限的弹窗url地址
     *
     * @param mv
     * @param roleId
     * @return
     */
    @GetMapping("/addPermissionForRole.do")
    public ModelAndView addPermissionForRole(ModelAndView mv, String roleId) {
        mv.addObject("roleId", roleId);
        mv.setViewName("/systemSet/addPermissionForRole");
        return mv;
    }

    /**
     * 权限管理中的弹窗权限列表
     *
     * @param permissionType
     * @param permissionName
     * @param roleId
     * @return
     */
    @ResponseBody
    @GetMapping("permissionForTable.do")
    public TableResultResponse userTablesForRole(String permissionType, String permissionName, String roleId) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        List<Permission> permissionList = permissionService.getPermissionListForRole(permissionType, permissionName, roleId);
        for (Permission permissionEntity : permissionList) {
            Map<String, Object> permissionMap = new HashMap<>(16);
            permissionMap.put("id", permissionEntity.getPermissionId());
            permissionMap.put("permissionName", permissionEntity.getPermissionName());
            permissionMap.put("permissionType", permissionEntity.getPermissionType());
            permissionMap.put("url", permissionEntity.getUrl());
            infoList.add(permissionMap);
        }
        return Result.tableResule(0, infoList);
    }

    /**
     * 分配权限
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @PostMapping("/addPermission.do")
    public ResultResponse addPermission(String ids, String roleId) {
        boolean result = permissionService.allotPermission(ids, roleId);
        if (!result) {
            return Result.resuleError("添加失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 删除角色下的权限
     *
     * @param permissionId
     * @param roleId
     * @return
     */
    @ResponseBody
    @DeleteMapping("/delPermission.do")
    public ResultResponse delPermission(String permissionId, String roleId) {
        boolean result = permissionService.delPermission(permissionId, roleId);
        if (!result) {
            return Result.resuleError("删除失败");
        }
        return Result.resuleSuccess();
    }

}