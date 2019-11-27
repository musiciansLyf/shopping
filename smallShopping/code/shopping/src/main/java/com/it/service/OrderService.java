package com.it.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Order;

import java.util.List;

public interface OrderService {

    /**
     * 分页查询
     *
     * @param order
     * @param page
     * @param limit
     * @return
     */
    Page<Order> selectPage(Order order, int page, int limit);

    /**
     * 根据主键id查询
     *
     * @param id
     * @return
     */
    Order selectByPrimaryKey(String id);

    /**
     * 新增
     *
     * @param order
     * @return
     */
    boolean insert(Order order);

    /**
     * 修改
     *
     * @param order
     * @return
     */
    boolean updateByPrimaryKey(Order order);

    /**
     * 删除,单个删除批量删除通用
     *
     * @param ids
     * @return
     */
    boolean deleteByPrimaryKey(String ids);

    /**
     * 得到订单列表通过用户id
     *
     * @param userId
     * @return
     */
    List<Order> getOrderListByUserId(String userId);

    /**
     * 得到待评价订单列表通过用户id
     *
     * @param userId
     * @return
     */
    List<Order> getOrderListByDaiPingJia(String userId, Integer status);

    String zunNumber();
}
