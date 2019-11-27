package com.it.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.it.entity.*;
import com.it.service.CategoryService;
import com.it.service.PermissionService;
import com.it.service.UserService;
import com.it.util.ItdragonUtils;
import com.it.util.Result;
import com.it.util.ResultResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @version:
 * @Description: 内容
 * @author: zhao shi jie
 * @date: 2018年8月16日 上午10:09:18
 */
@Controller
public class IndexController {
    private static final transient Logger log = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private ItdragonUtils itdragonUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/indexWbe")
    public ModelAndView index(ModelAndView mv, Model model) {
        User user = userService.selectByPrimaryKey(itdragonUtils.getSessionUser().getId());
        List<SubSystemInfo> sysList = permissionService.getSubSystemInfoByUserId(user.getId());
        Map<String, LinkedHashMap<String, List<Menu>>> menuMap = permissionService.getMenuInfoByUserId(user.getId());
        JSONObject allMenu = new JSONObject();
        for (String key : menuMap.keySet()) {
            JSONArray sysJson = new JSONArray();
            for (String inKey : menuMap.get(key).keySet()) {
                List<Menu> menuList = menuMap.get(key).get(inKey);
                JSONObject menuJson = new JSONObject();
                JSONArray jsonArray = new JSONArray();
                menuJson.put("title", inKey.split(":")[0]);
                menuJson.put("icon", "");
                menuJson.put("href", "");
                menuJson.put("spread", false);
                for (Menu menu : menuList) {
                    JSONObject json = new JSONObject();
                    json.put("title", menu.getMenuName());
                    json.put("href", menu.getMenuURL());
                    json.put("spread", false);
                    json.put("target", "");
                    jsonArray.add(json);
                }
                menuJson.put("children", jsonArray);
                sysJson.add(menuJson);
            }
            allMenu.put(key, sysJson);
        }
        model.addAttribute("menuInfo", allMenu);
        model.addAttribute("subSysInfo", sysList);
        User userByUserName = userService.findUserByUserName(user.getUserName());
        List<Role> roleList = userByUserName.getRoleList();
        if (roleList.isEmpty()) {
            model.addAttribute("role", "暂无身份");
        } else {
            model.addAttribute("role", roleList.get(0).getRole());
        }
        model.addAttribute("user", user);
        mv.setViewName("content/index");
        return mv;
    }

    /**
     * 登录页面
     *
     * @param mv
     * @return
     */
    @RequestMapping("/loginWbe")
    public ModelAndView login(ModelAndView mv) {
        //拿到父分类标签集合
        List<PtCategory> ptCategoryList = categoryService.getPtCategory();
        mv.addObject("ptCategoryList", ptCategoryList);
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            mv.setViewName("/content/index");
            return mv;
        } else {
            mv.setViewName("/login");
            return mv;
        }

    }

    /**
     * Shiro登录跳转地址,重定向到登录页面
     *
     * @param mv
     * @return
     */
    @RequestMapping("/loginShiro")
    public String loginShiro(ModelAndView mv) {
        return "forward:/loginWbe";
    }

    /**
     * Shiro登录成功后index跳转地址,重定向到indexWbe页面
     *
     * @param mv
     * @return
     */
    @RequestMapping("/trnk")
    public String trnk(ModelAndView mv) {
        if (userService.isAdmin(itdragonUtils.getSessionUser().getId())) {
            return "forward:/indexWbe";
        } else {
            return "forward:/front/index.do";
        }
    }

    @RequestMapping("/indexShiro")
    public String indexShiro(ModelAndView mv) {
        return "forward:/indexWbe";
    }

    /**
     * 修改密码页面跳转
     *
     * @param mv
     * @return
     */
    @GetMapping("/changePwd")
    public ModelAndView changePwd(ModelAndView mv) {
        User user = (User) itdragonUtils.getShiroSession().getAttribute("loginUserInfo");
        mv.addObject("user", user);
        mv.setViewName("content/changePwd");
        return mv;
    }

    /**
     * 用户修改密码
     *
     * @param newPas
     * @param oldPas
     * @param userName
     * @return
     */
    @ResponseBody
    @PostMapping("/updatePas")
    public ResultResponse updatePassword(String newPas, String oldPas, String userName) {
        User user = (User) itdragonUtils.getShiroSession().getAttribute("loginUserInfo");
        if (oldPas.equals(user.getPlainPassword())) {
            boolean result = userService.changePass(newPas, userName);
            if (result) {
                user.setPlainPassword(newPas);
                return Result.resuleSuccess();
            } else {
                return Result.resuleError("修改失败;原密码错误");
            }

        }
        return Result.resuleError("原密码错误,请重新输入");


    }

    /**
     * 用户中心
     *
     * @param mv
     * @return
     */
    @GetMapping("/userInfo")
    public ModelAndView userInfo(ModelAndView mv) {
        User user = userService.selectByPrimaryKey(itdragonUtils.getSessionUser().getId());
        if (!ItdragonUtils.stringIsNotBlack(user.getImgUrl())) {
            user.setImgUrl("/resource/image/default.png");
        }
        mv.addObject("user", user);
        mv.setViewName("content/userInfo");
        return mv;
    }

    /**
     * 首页
     *
     * @param mv
     * @return
     */
    @GetMapping("/main")
    public ModelAndView main(ModelAndView mv) {
        mv.setViewName("content/main");
        return mv;
    }

    /**
     * 404页面
     *
     * @param mv
     * @return
     */
    @GetMapping("/404")
    public ModelAndView silingsi(ModelAndView mv) {
        mv.setViewName("content/404");
        return mv;
    }

    /**
     * 获取锁屏密码
     */
    @ResponseBody
    @GetMapping("/lockPas")
    public String lockPas() {
        User user = (User) itdragonUtils.getShiroSession().getAttribute("loginUserInfo");
        String userName = user.getUserName();
        Map<String, String> map = (Map<String, String>) itdragonUtils.getShiroSession().getAttribute("lockInfo");
        String lockPas = "123";
        if (!map.isEmpty()) {
            lockPas = map.get(userName);
        }
        return lockPas;
    }

    /**
     * 安全设置
     *
     * @param mv
     * @return
     */
    @RequestMapping("/PersonalCenter/safety")
    public ModelAndView message(ModelAndView mv) {
        mv.setViewName("PersonalCenter/safety");
        return mv;
    }

    /**
     * 设置锁屏密码
     */
    @ResponseBody
    @PostMapping("/setLockPas")
    public ResultResponse lockPas(String lockPas) {
        User user = (User) itdragonUtils.getShiroSession().getAttribute("loginUserInfo");
        String userName = user.getUserName();
        Map<String, String> lockInfo = new HashMap<String, String>(16);
        lockInfo.put(userName, lockPas);
        itdragonUtils.getShiroSession().setAttribute("lockInfo", lockInfo);
        return Result.resuleSuccess();
    }

    /**
     * 注册页面跳转
     */
    @RequestMapping("/reg")
    public ModelAndView reg(ModelAndView mv) {
        mv.setViewName("reg");
        return mv;
    }

    /**
     * 登录页面跳转
     */
    @RequestMapping("/login")
    public ModelAndView login1(ModelAndView mv) {
        mv.setViewName("login");
        return mv;
    }
}
