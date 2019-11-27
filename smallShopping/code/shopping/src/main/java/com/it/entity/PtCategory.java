package com.it.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 父商品分类实体类
 *
 * @author
 * @create 2018/11/9 11:15
 * @since 1.0.0
 */
@Data
@TableName("gm_ptCategory")
public class PtCategory implements Serializable {
    /**
     * 自增长主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 标题名称
     */
    private String title;
    /**
     * 创建时间
     */
    private String time;
    /**
     * 子分类集合
     */
    @TableField(exist = false)
    List<Category> categoryList;
    /**
     *
     */
    @TableField(exist = false)
    private String selected;
}