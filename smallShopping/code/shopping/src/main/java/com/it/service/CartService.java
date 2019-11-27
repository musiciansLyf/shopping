package com.it.service;

import com.it.entity.Cart;

import java.util.List;

public interface CartService {


    Cart selecCart(String id);

    /**
     * 新增
     *
     * @param Cart
     * @return
     */
    boolean insertCart(Cart Cart);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    boolean deleteCartByPrimaryKey(String ids);

    /**
     * @param userId
     * @return
     */
    List<Cart> getCategoryByUserId(String userId);


}
