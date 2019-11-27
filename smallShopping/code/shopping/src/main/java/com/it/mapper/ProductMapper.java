package com.it.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.it.entity.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品mapper
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}
