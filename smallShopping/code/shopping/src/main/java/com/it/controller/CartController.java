package com.it.controller;

import com.it.entity.Cart;
import com.it.service.CartService;
import com.it.service.UserService;
import com.it.util.ItdragonUtils;
import com.it.util.Result;
import com.it.util.ResultResponse;
import com.it.util.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private ItdragonUtils itdragonUtils;

    /**
     * 购物车列表加载
     *
     * @return
     */
    @ResponseBody
    @GetMapping("cart.do")
    public TableResultResponse enlistmentTables() {
        List<Map<String, Object>> infoList = new ArrayList<>();
        List<Cart> cartList = cartService.getCategoryByUserId(itdragonUtils.getSessionUser().getId());
        for (Cart record : cartList) {
            Map<String, Object> resultMap = new HashMap<>(16);
            resultMap.put("id", record.getId());
            resultMap.put("avatar", record.getImgUrl());
            resultMap.put("price", "￥" + record.getProPrice());
            resultMap.put("original", "￥" + record.getPrice());
            resultMap.put("proPrice", record.getProPrice());
            resultMap.put("color", record.getParameter());
            resultMap.put("wattage", record.getSpecification());
            resultMap.put("number", record.getNum());
            resultMap.put("name", record.getProductName());
            resultMap.put("productId", record.getProductId());
            infoList.add(resultMap);
        }
        return Result.tableResule(0, infoList);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @ResponseBody
    @DeleteMapping("/cart.do")
    public ResultResponse delEn(String id) {
        boolean result = cartService.deleteCartByPrimaryKey(id);
        if (!result) {
            return Result.resuleError("删除失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @DeleteMapping("/delCart.do")
    public ResultResponse delCart(String ids) {
        String[] split = ids.split(",");
        boolean result = false;
        for (String s : split) {
            result = cartService.deleteCartByPrimaryKey(s);
        }
        if (!result) {
            return Result.resuleError("删除失败");
        }
        return Result.resuleSuccess();
    }
}