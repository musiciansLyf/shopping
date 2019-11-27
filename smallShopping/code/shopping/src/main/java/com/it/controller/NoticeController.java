package com.it.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Notice;
import com.it.service.NoticeService;
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
 * 〈〉<br>
 *
 * @author
 * @create 2019/1/16 16:00
 * @since 1.0.0
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private UserService userService;

    /**
     * 管理界面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/index.do")
    public ModelAndView enIndex(ModelAndView mv) {
        mv.setViewName("notice/index");
        return mv;
    }

    /**
     * 管理界面列表
     *
     * @param notice
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @GetMapping("notice.do")
    public TableResultResponse enlistmentTables(Notice notice, int page, int limit) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<Notice> pageInfo = noticeService.selectPage(notice, page, limit);
        for (Notice record : pageInfo.getRecords()) {
            Map<String, Object> resultMap = new HashMap<>(16);
            resultMap.put("id", record.getId());
            resultMap.put("content", record.getContent());
            resultMap.put("name", record.getName());
            resultMap.put("type", record.getType());
            resultMap.put("imgUrl", record.getImgUrl());
            resultMap.put("time", record.getTime() == null ? "" : record.getTime().substring(0, 11));
            infoList.add(resultMap);
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
    }

    /**
     * 新增跳转界面
     *
     * @param mv
     * @return
     */
    @RequestMapping("/add.do")
    public ModelAndView addIndex(ModelAndView mv) {
        mv.setViewName("notice/add");
        return mv;
    }

    /**
     * 新增
     *
     * @param notice
     * @return
     */
    @ResponseBody
    @PostMapping("/notice.do")
    public ResultResponse add(Notice notice) {
        boolean result = noticeService.insert(notice);
        if (!result) {
            return Result.resuleError("新增失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @ResponseBody
    @DeleteMapping("/notice.do")
    public ResultResponse delEn(String id) {
        boolean result = noticeService.delById(id);
        if (!result) {
            return Result.resuleError("删除失败");
        }
        return Result.resuleSuccess();
    }


    /**
     * 修改跳转界面
     *
     * @param mv
     * @return
     */
    @RequestMapping("/edit.do")
    public ModelAndView editEnIndex(ModelAndView mv, String id) {
        Notice notice = noticeService.getOneById(id);
        mv.addObject("notice", notice);
        mv.setViewName("Notice/edit");
        return mv;
    }

    /**
     * 修改
     *
     * @param notice
     * @return
     */
    @ResponseBody
    @PutMapping("/notice.do")
    public ResultResponse edit(Notice notice) {
        boolean result = noticeService.updateById(notice);
        if (!result) {
            return Result.resuleError("修改失败,未知错误");
        }
        return Result.resuleSuccess();
    }
}