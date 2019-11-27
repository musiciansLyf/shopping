package com.it.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.it.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据访问层
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
