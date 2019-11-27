package com.it.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品实体类<br>
 *
 * @author
 * @create 2018/11/10 15:25
 * @since 1.0.0
 */
@Data
@TableName("gm_product")
public class Product implements Serializable {
    /**
     * 自增长主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 促销价
     */
    private Float proPrice;
    /**
     * 原价
     */
    private Float price;
    /**
     * 销量
     */
    private Integer sales;
    /**
     * 规格
     */
    private String specification;
    /**
     * 产品参数
     */
    private String parameter;
    /**
     * 商品图
     */
    private String imgUrl;
    /**
     * 详情图
     */
    private String dasImg;
    /**
     * 创建时间
     */
    private String createdTime;
    /**
     * 分类id
     */
    private String categoryId;
    /**
     * 发布人id
     */
    private String userId;
    /**
     * 季节
     */
    private String season;
    private String ptCategoryId;
    /**
     * 排序方式
     */
    @TableField(exist = false)
    private String orderParam;
}