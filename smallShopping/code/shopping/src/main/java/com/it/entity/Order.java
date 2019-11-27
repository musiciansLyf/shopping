package com.it.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 实体类
 *
 * @author itdragon
 */
@Data
@TableName("gm_order")
public class Order implements Serializable {
    /**
     * 自增长主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    private String userId;
    private String addressId;
    private String productId;
    /**
     * 订单编号
     */
    private String orderUuid;
    /**
     * 订单生成时间
     */
    private String orderTime;
    /**
     * 商品数目
     */
    private Integer num;
    /**
     * 总价
     */
    private Float zumPrice;
    /**
     * 图片地址
     */
    private String imgUrl;
    /**
     * 订单状态
     */
    private String status;
    // @TableField(exist = false)
}