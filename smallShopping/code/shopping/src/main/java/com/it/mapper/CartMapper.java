package com.it.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.it.entity.Cart;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户数据访问层
 */
@Mapper
public interface CartMapper extends BaseMapper<Cart> {

}
