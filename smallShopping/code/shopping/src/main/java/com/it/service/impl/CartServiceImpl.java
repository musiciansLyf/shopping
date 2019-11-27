package com.it.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.it.entity.Cart;
import com.it.mapper.CartMapper;
import com.it.service.CartService;
import com.it.util.ItdragonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author
 * @create 2019/2/15 17:53
 * @since 1.0.0
 */
@Service
public class CartServiceImpl implements CartService {
    @Resource
    private CartMapper CartMapper;
    @Resource
    private ItdragonUtils itdragonUtils;

    @Override
    public Cart selecCart(String id) {
        return CartMapper.selectById(id);
    }

    @Override
    public boolean insertCart(Cart Cart) {
        Cart.setUserId(itdragonUtils.getSessionUser().getId());
        Integer insert = CartMapper.insert(Cart);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCartByPrimaryKey(String ids) {
        Integer delete = CartMapper.deleteById(ids);
        if (delete > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Cart> getCategoryByUserId(String userId) {
        EntityWrapper<Cart> searchInfo = new EntityWrapper<>();
        if (ItdragonUtils.stringIsNotBlack(userId)) {
            searchInfo.eq("userId", userId);
        }
        return CartMapper.selectList(searchInfo);
    }
}