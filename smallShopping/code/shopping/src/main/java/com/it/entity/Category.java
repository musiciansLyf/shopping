package com.it.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 子商品分类实体类
 *
 * @author
 * @create 2018/11/9 11:15
 * @since 1.0.0
 */
@Data
@TableName("gm_category")
public class Category implements Serializable {
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
     * 父分类id
     */
    private String parentId;
    /**
     * 商品列表中默认选择标识
     */
    @TableField(exist = false)
    private String selected;
}