package com.it.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Category;
import com.it.entity.Product;
import com.it.entity.PtCategory;
import com.it.service.CategoryService;
import com.it.service.ProductService;
import com.it.util.ItdragonUtils;
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
 * 商品相关操作
 *
 * @author 王钰尧
 * @create 2018/11/10 15:51
 * @since 1.0.0
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ItdragonUtils itdragonUtils;

    /**
     * 界面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/index.do")
    public ModelAndView index(ModelAndView mv) {
        List<Category> categories = categoryService.getCategory();
        mv.addObject("categorieList", categories);
        mv.setViewName("product/productIndex");
        return mv;
    }

    /**
     * 管理界面列表
     *
     * @param product
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @GetMapping("product.do")
    public TableResultResponse Tables(Product product, int page, int limit) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<Product> pageInfo = productService.selectPage(product, page, limit);
        for (Product record : pageInfo.getRecords()) {
            Map<String, Object> resultMap = new HashMap<>(16);
            resultMap.put("id", record.getId());
            resultMap.put("name", record.getName());
            resultMap.put("specification", record.getSpecification());
            resultMap.put("parameter", record.getParameter());
            resultMap.put("price", record.getPrice());
            resultMap.put("proPrice", record.getProPrice());
            resultMap.put("sales", record.getSales());
            resultMap.put("createdTime", record.getCreatedTime() == null ? "" : record.getCreatedTime().substring(0, 19));
            //图片展示json
            Map<String, String> imgUrlMap = new HashMap<>();
            imgUrlMap.put("alt", "商品展示图");
            imgUrlMap.put("pid", "666");
            imgUrlMap.put("src", record.getImgUrl());
            imgUrlMap.put("thumb", record.getImgUrl());
            //放入列表中
            resultMap.put("imgUrl", imgUrlMap);
            resultMap.put("imgUrlLayer", "  <span style=\"color:#2ab7ff;cursor:pointer\">查看图片</span>");

            //图片详情json
            if (record.getDasImg() != null && !"".equals(record.getDasImg())) {
                String[] dasImg = record.getDasImg().split(";");
                List<Map<String, String>> dasImgUrlList = new ArrayList<>();
                for (String s : dasImg) {
                    Map<String, String> dasImgUrlMap = new HashMap<>();
                    if (!"".equals(s)) {
                        dasImgUrlMap.put("alt", "商品详情图");
                        dasImgUrlMap.put("pid", "6");
                        dasImgUrlMap.put("src", s);
                        dasImgUrlMap.put("thumb", s);
                        dasImgUrlList.add(dasImgUrlMap);
                    }
                }
                resultMap.put("dasImg", dasImgUrlList);
            }

            resultMap.put("dasImgLayer", "  <span style=\"color:#2ab7ff;cursor:pointer\">查看图片</span>");
            resultMap.put("categoryId", record.getCategoryId());
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
    @RequestMapping("/addIndex.do")
    public ModelAndView addIndex(ModelAndView mv) {
        List<Category> categories = categoryService.getCategory();
        mv.addObject("categorieList", categories);
        List<PtCategory> ptCategoryList = categoryService.getPtCategory();
        mv.addObject("ptCategorieList", ptCategoryList);
        mv.setViewName("product/productAdd");
        return mv;
    }

    /**
     * 新增
     *
     * @param product
     * @return
     */
    @ResponseBody
    @PostMapping("/product.do")
    public ResultResponse add(Product product) {
        boolean result = productService.insert(product);
        if (!result) {
            return Result.resuleError("操作失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 修改界面
     *
     * @param mv
     * @return
     */
    @RequestMapping("/editIndex.do")
    public ModelAndView editIndex(ModelAndView mv, String id) {
        mv.addObject("product", productService.selectByPrimaryKey(id));
        mv.setViewName("product/productEdit");
        return mv;
    }

    /**
     * 新增
     *
     * @param product
     * @return
     */
    @ResponseBody
    @PutMapping("/product.do")
    public ResultResponse edit(Product product) {
        boolean result = productService.updateByPrimaryKey(product);
        if (!result) {
            return Result.resuleError("操作失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @DeleteMapping("/product.do")
    public ResultResponse del(String ids) {
        boolean result = productService.deleteByPrimaryKey(ids);
        if (!result) {
            return Result.resuleError("操作失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 商品列表ajax加载的方法
     *
     * @param page
     * @param limit
     * @param categoryId
     * @param orderBy
     * @return
     */
    @ResponseBody
    @PostMapping("/productList.do")
    public ResultResponse productList(int page, int limit, String categoryId, String orderBy, String name, String ptCategoryId) {
        Page<Product> productPage = productService.getProductListForFront(page, limit, categoryId, orderBy, name, ptCategoryId);
        if (productPage.getRecords() != null) {
            return Result.resuleSuccess(productPage.getRecords());
        }
        return Result.resuleError("暂无商品");
    }
}