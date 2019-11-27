package com.it.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Order;
import com.it.mapper.OrderMapper;
import com.it.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 王钰尧
 * @create 2018/11/17 16:21
 * @since 1.0.0
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Override
    public Page<Order> selectPage(Order order, int page, int limit) {
        EntityWrapper<Order> searchInfo = new EntityWrapper<>();
        Page<Order> pageInfo = new Page<>(page, limit);
        if (order.getUserId() != null && !"".equals(order.getUserId())) {
            searchInfo.eq("userId", order.getUserId());
        }
        if (order.getProductId() != null && !"".equals(order.getProductId())) {
            searchInfo.eq("productId", order.getProductId());
        }
        if (order.getStatus() != null && !"".equals(order.getStatus())) {
            searchInfo.eq("status", order.getStatus());
        }
        if (order.getOrderTime() != null && !"".equals(order.getOrderTime())) {
            String[] split = order.getOrderTime().split(" - ");
            searchInfo.between("orderTime", split[0] + " 00:00:00", split[1] + " 23:59:59");
        }
        List<Order> resultList = orderMapper.selectPage(pageInfo, searchInfo);
        if (!resultList.isEmpty()) {
            pageInfo.setRecords(resultList);
        }
        return pageInfo;
    }

    @Override
    public Order selectByPrimaryKey(String id) {
        return orderMapper.selectById(id);
    }

    @Override
    public boolean insert(Order order) {
        int result = orderMapper.insert(order);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateByPrimaryKey(Order order) {
        int result = orderMapper.updateById(order);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteByPrimaryKey(String ids) {
        String[] idList = ids.split(",");
        int result = 0;
        for (String s : idList) {
            result = orderMapper.deleteById(s);
        }
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Order> getOrderListByUserId(String userId) {
        EntityWrapper<Order> searchInfo = new EntityWrapper<>();
        searchInfo.eq("userId", userId);
        return orderMapper.selectList(searchInfo);
    }

    @Override
    public List<Order> getOrderListByDaiPingJia(String userId, Integer status) {
        EntityWrapper<Order> searchInfo = new EntityWrapper<>();
        searchInfo.eq("userId", userId);
        searchInfo.eq("status", status);
        return orderMapper.selectList(searchInfo);
    }

    @Override
    public String zunNumber() {
        return orderMapper.selectList(null).size() + "";
    }
}