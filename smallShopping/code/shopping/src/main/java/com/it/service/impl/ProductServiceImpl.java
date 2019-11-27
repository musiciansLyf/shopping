package com.it.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Cart;
import com.it.entity.Product;
import com.it.entity.User;
import com.it.mapper.CartMapper;
import com.it.mapper.ProductMapper;
import com.it.service.ProductService;
import com.it.util.DateUtil;
import com.it.util.ItdragonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 王钰尧
 * @create 2018/11/10 15:38
 * @since 1.0.0
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;
    @Resource
    private ItdragonUtils itdragonUtils;
    @Resource
    private CartMapper cartMapper;

    @Override
    public Page<Product> selectPage(Product product, int page, int limit) {
        EntityWrapper<Product> searchInfo = new EntityWrapper<>();
        Page<Product> pageInfo = new Page<>(page, limit);
        if (product.getName() != null && !"".equals(product.getName())) {
            searchInfo.like("name", product.getName());
        }
        if (product.getCategoryId() != null && !"".equals(product.getCategoryId())) {
            searchInfo.eq("categoryId", product.getCategoryId());
        }
        if (product.getOrderParam() != null && !"".equals(product.getOrderParam())) {
            searchInfo.orderBy(product.getOrderParam() + " desc");
        }
        List<Product> resultList = productMapper.selectPage(pageInfo, searchInfo);
        if (!resultList.isEmpty()) {
            pageInfo.setRecords(resultList);
        }
        return pageInfo;
    }

    @Override
    public Product selectByPrimaryKey(String id) {
        return productMapper.selectById(id);
    }

    @Override
    public boolean insert(Product product) {
        product.setCreatedTime(DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
        product.setSales(0);
        product.setUserId(itdragonUtils.getSessionUser().getId());
        int result = productMapper.insert(product);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(Product product) {
        int result = productMapper.updateById(product);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByPrimaryKey(String ids) {
        int result = productMapper.deleteById(ids);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Product> getProductListByCategoryId(String categoryId) {
        EntityWrapper<Product> searchInfo = new EntityWrapper<>();
        searchInfo.eq("categoryId", categoryId);
        List<Product> productList = productMapper.selectList(searchInfo);
        if (!productList.isEmpty()) {
            return productList;
        }
        return null;
    }

    @Override
    public Page<Product> getProductListForFront(int page, int limit, String categoryId, String orderBy, String name, String ptCategoryId) {
        EntityWrapper<Product> searchInfo = new EntityWrapper<>();
        Page<Product> pageInfo = new Page<>(page, limit);
        if (ItdragonUtils.stringIsNotBlack(categoryId)) {
            searchInfo.eq("categoryId", categoryId);
        }
        if (ItdragonUtils.stringIsNotBlack(name)) {
            searchInfo.like("name", name);
        }
        if (ItdragonUtils.stringIsNotBlack(ptCategoryId)) {
            searchInfo.like("ptCategoryId", ptCategoryId);
        }
        if (ItdragonUtils.stringIsNotBlack(orderBy)) {
            searchInfo.orderBy(orderBy);
        }
        List<Product> resultList = productMapper.selectPage(pageInfo, searchInfo);
        if (!resultList.isEmpty()) {
            pageInfo.setRecords(resultList);
        }
        return pageInfo;
    }

    @Override
    public List<Product> getProductByName(String name) {
        EntityWrapper<Product> searchInfo = new EntityWrapper<>();
        searchInfo.like("name", name);
        List<Product> productList = productMapper.selectList(searchInfo);
        if (!productList.isEmpty()) {
            return productList;
        }
        return null;
    }

    @Override
    public boolean inCart(Cart cart) {
        User user = (User) itdragonUtils.getShiroSession().getAttribute("userInfo");
        cart.setUserId(user.getId());
        int result = cartMapper.insert(cart);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Cart> getCartListByUserId(String userId) {
        EntityWrapper<Cart> searchInfo = new EntityWrapper<>();
        searchInfo.eq("userId", userId);
        return cartMapper.selectList(searchInfo);
    }

    @Override
    public boolean deleteCart(String id) {
        int result = cartMapper.deleteById(id);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String zunNumber() {
        return productMapper.selectList(null).size() + "";
    }

    @Override
    public List<Product> getListRoule(int i, String param) {
        EntityWrapper<Product> wrapper = new EntityWrapper<>();
        switch (param) {
            case "时间":
                wrapper.orderBy("createdTime desc");
                break;
            case "销量":
                wrapper.orderBy("sales desc");
                break;
            default:
        }
        List<Product> productList = productMapper.selectList(wrapper);
        List<Product> result = new ArrayList<>();
        if (productList.size() > i) {
            for (int a = 0; a < i; a++) {
                result.add(productList.get(a));
            }
            return result;
        }

        return productList;
    }
}