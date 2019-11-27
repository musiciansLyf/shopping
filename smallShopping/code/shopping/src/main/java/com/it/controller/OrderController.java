package com.it.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Order;
import com.it.service.AddressService;
import com.it.service.OrderService;
import com.it.service.ProductService;
import com.it.service.UserService;
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
 * 〈一句话功能简述〉<br>
 *
 * @author
 * @create 2018/11/17 16:27
 * @since 1.0.0
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ItdragonUtils itdragonUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private AddressService addressService;

    /**
     * 界面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/index.do")
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("order/orderIndex");
        return mv;
    }

    /**
     * 新增订单
     *
     * @param order
     * @return
     */
    @PostMapping("/order.do")
    @ResponseBody
    public ResultResponse addOrder(Order order) {
        boolean result = orderService.insert(order);
        if (result) {
            return Result.resuleSuccess();
        }
        return Result.resuleError("请求失败");
    }

    /**
     * 修改订单状态
     *
     * @param order
     * @return
     */
    @ResponseBody
    @PostMapping("/status.do")
    public ResultResponse updatStatus(Order order) {
        boolean result = orderService.updateByPrimaryKey(order);
        if (result) {
            return Result.resuleSuccess();
        }
        return Result.resuleError("操作失败");
    }

    /**
     * 删除订单
     *
     * @param id
     * @return
     */
    @ResponseBody
    @DeleteMapping("/order.do")
    public ResultResponse delete(String id) {
        boolean result = orderService.deleteByPrimaryKey(id);
        if (result) {
            return Result.resuleSuccess();
        }
        return Result.resuleError("操作失败");
    }

    /**
     * 管理界面列表
     *
     * @param order
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @GetMapping("order.do")
    public TableResultResponse Tables(Order order, int page, int limit) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<Order> pageInfo = orderService.selectPage(order, page, limit);
        for (Order record : pageInfo.getRecords()) {
            Map<String, Object> resultMap = new HashMap<>(16);
            resultMap.put("id", record.getId());
            resultMap.put("address", addressService.selecAddress(record.getAddressId()));
            resultMap.put("productId", record.getProductId());
            if (productService.selectByPrimaryKey(record.getProductId()) != null) {
                resultMap.put("productName", productService.selectByPrimaryKey(record.getProductId()).getName());
            }
            resultMap.put("zumPrice", record.getZumPrice());
            resultMap.put("num", record.getNum());
            resultMap.put("orderUuid", record.getOrderUuid());
            resultMap.put("proPrice", record.getZumPrice());
            resultMap.put("view", "  <span style=\"color:#2ab7ff;cursor:pointer\">查看收货信息</span>");
            resultMap.put("orderTime", record.getOrderTime() == null ? "" : record.getOrderTime().substring(0, 19));
            resultMap.put("status", record.getStatus());
            infoList.add(resultMap);
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
    }

    /**
     * 订单中心列表加载
     *
     * @return
     */
    @ResponseBody
    @GetMapping("orderInfo.do")
    public TableResultResponse orderInfo(String status) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Order order = new Order();
        order.setUserId(itdragonUtils.getSessionUser().getId());
        if (status != null) {
            order.setStatus(status);
        }
        Page<Order> pageInfo = orderService.selectPage(order, 1, 1000);
        for (Order record : pageInfo.getRecords()) {
            Map<String, Object> resultMap = new HashMap<>(16);
            resultMap.put("id", record.getId());
            resultMap.put("addresss", addressService.selecAddress(record.getAddressId()));
            resultMap.put("productId", record.getProductId());
            resultMap.put("price", record.getZumPrice());
            resultMap.put("avatar", record.getImgUrl());
            resultMap.put("number", record.getNum());
            resultMap.put("orderid", record.getOrderUuid());
            resultMap.put("timestamp", record.getOrderTime() == null ? "" : record.getOrderTime().substring(0, 19));
            resultMap.put("state", record.getStatus());
            infoList.add(resultMap);
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
    }
}