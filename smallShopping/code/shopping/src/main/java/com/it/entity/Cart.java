package com.it.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 购物车实体类
 *
 * @author itdragon
 */
@Data
@TableName("gm_cart")
public class Cart implements Serializable {
    /**
     * 自增长主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 商品Id
     */
    private String productId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 规格
     */
    private String parameter;
    /**
     * 参数
     */
    private String specification;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 原价
     */
    private Float price;
    /**
     * 打折价
     */
    private Float proPrice;
    /**
     * 数量
     */
    private Integer num;
    /**
     * 图片地址
     */
    private String imgUrl;
}