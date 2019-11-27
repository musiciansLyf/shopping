package com.it.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Category;
import com.it.entity.PtCategory;
import com.it.service.CategoryService;
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
 * controller
 *
 * @author 王钰尧
 * @create 2018/10/9 17:02
 * @since 1.0.0
 */
@RequestMapping("/category")
@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 界面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/index.do")
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("/category/categoryIndex");
        return mv;
    }

    /**
     * 父分类列表
     *
     * @param ptCategory
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @GetMapping("ptCategory.do")
    public TableResultResponse ptCategoryTables(PtCategory ptCategory, int page, int limit) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<PtCategory> pageInfo = categoryService.selectPtCategoryPage(ptCategory, page, limit);
        for (PtCategory record : pageInfo.getRecords()) {
            Map<String, Object> resultMap = new HashMap<>(16);
            resultMap.put("id", record.getId());
            resultMap.put("title", record.getTitle());
            resultMap.put("time", record.getTime() == null ? "" : record.getTime().substring(0, 19));
            infoList.add(resultMap);
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
    }

    /**
     * 父分类列表
     *
     * @param category
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @GetMapping("category.do")
    public TableResultResponse categoryTables(Category category, int page, int limit) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<Category> pageInfo = categoryService.selectCategoryPage(category, page, limit);
        for (Category record : pageInfo.getRecords()) {
            Map<String, Object> resultMap = new HashMap<>(16);
            resultMap.put("id", record.getId());
            resultMap.put("parentId", record.getParentId());
            resultMap.put("title", record.getTitle());
            resultMap.put("time", record.getTime() == null ? "" : record.getTime().substring(0, 19));
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
    @RequestMapping("/addPtIndex")
    public ModelAndView addIndex(ModelAndView mv) {
        mv.setViewName("category/ptCategoryAdd");
        return mv;
    }

    /**
     * 新增跳转界面
     *
     * @param mv
     * @return
     */
    @RequestMapping("/addIndex")
    public ModelAndView addIndex1(ModelAndView mv, String parentId) {
        mv.addObject("parentId", parentId);
        mv.setViewName("category/categoryAdd");
        return mv;
    }

    /**
     * 新增
     *
     * @param ptCategory
     * @return
     */
    @ResponseBody
    @PostMapping("/ptCategory.do")
    public ResultResponse add(PtCategory ptCategory) {
        boolean result = categoryService.insertPtCategory(ptCategory);
        if (!result) {
            return Result.resuleError("操作失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 新增
     *
     * @param category
     * @return
     */
    @ResponseBody
    @PostMapping("/category.do")
    public ResultResponse add(Category category) {
        boolean result = categoryService.insertCategory(category);
        if (!result) {
            return Result.resuleError("操作失败");
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
    @DeleteMapping("/ptCategory.do")
    public ResultResponse del(String ids) {
        boolean result = categoryService.deletePtCategoryByPrimaryKey(ids);
        if (!result) {
            return Result.resuleError("操作失败");
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
    @DeleteMapping("/category.do")
    public ResultResponse del1(String ids) {
        boolean result = categoryService.deleteCategoryByPrimaryKey(ids);
        if (!result) {
            return Result.resuleError("操作失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 修改跳转界面
     *
     * @param mv
     * @return
     */
    @RequestMapping("/editPtIndex.do")
    public ModelAndView editIndex(ModelAndView mv, String id) {
        PtCategory ptCategory = categoryService.selecPtCategory(id);
        mv.addObject("ptCategory", ptCategory);
        mv.setViewName("category/ptCategoryEdit");
        return mv;
    }

    /**
     * 修改跳转界面
     *
     * @param mv
     * @return
     */
    @RequestMapping("/editIndex.do")
    public ModelAndView editIndex1(ModelAndView mv, String id) {
        Category category = categoryService.selecCategory(id);
        mv.addObject("category", category);
        mv.setViewName("category/categoryEdit");
        return mv;
    }

    /**
     * 修改
     *
     * @param category
     * @return
     */
    @ResponseBody
    @PutMapping("/category.do")
    public ResultResponse editUser(Category category) {
        boolean result = categoryService.updateCategoryByPrimaryKey(category);
        if (!result) {
            return Result.resuleError("操作失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 修改
     *
     * @param ptCategory
     * @return
     */
    @ResponseBody
    @PutMapping("/ptCategory.do")
    public ResultResponse editUser(PtCategory ptCategory) {
        boolean result = categoryService.updatePtCategoryByPrimaryKey(ptCategory);
        if (!result) {
            return Result.resuleError("操作失败");
        }
        return Result.resuleSuccess();
    }
}