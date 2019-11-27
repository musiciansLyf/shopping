package com.it.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.it.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * 角色数据访问层
 */
public interface NoticeMapper extends BaseMapper<Notice> {

}
