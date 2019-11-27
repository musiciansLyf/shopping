package com.it.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Cart;
import com.it.entity.Product;

import java.util.List;

public interface ProductService {

    /**
     * 分页查询
     *
     * @param product
     * @param page
     * @param limit
     * @return
     */
    Page<Product> selectPage(Product product, int page, int limit);

    /**
     * 根据主键id查询
     *
     * @param id
     * @return
     */
    Product selectByPrimaryKey(String id);

    /**
     * 新增
     *
     * @param product
     * @return
     */
    boolean insert(Product product);

    /**
     * 修改
     *
     * @param product
     * @return
     */
    boolean updateByPrimaryKey(Product product);

    /**
     * 删除,单个删除批量删除通用
     *
     * @param ids
     * @return
     */
    boolean deleteByPrimaryKey(String ids);

    /**
     * 获得分类下的所有物品
     *
     * @param categoryId
     * @return
     */
    List<Product> getProductListByCategoryId(String categoryId);

    /**
     * 商品列表ajax加载的方法
     *
     * @param page
     * @param limit
     * @param categoryId
     * @param orderBy
     * @return
     */
    Page<Product> getProductListForFront(int page, int limit, String categoryId, String orderBy, String name,String ptCategoryId);

    /**
     * 根据商品名称模糊查询
     *
     * @param name
     * @return
     */
    List<Product> getProductByName(String name);

    /**
     * 存入购物车操作
     *
     * @param cart
     * @return
     */
    boolean inCart(Cart cart);

    /**
     * 根据用户得到所有的购物车数据
     *
     * @param userId
     * @return
     */
    List<Cart> getCartListByUserId(String userId);

    /**
     * 删除购物车的某个商品
     *
     * @param id
     * @return
     */
    boolean deleteCart(String id);

    String zunNumber();

    List<Product> getListRoule(int i, String param);
}
