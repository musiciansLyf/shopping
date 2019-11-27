package com.it.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.it.entity.Address;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据访问层
 */
@Mapper
public interface AddressMapper extends BaseMapper<Address> {

}
