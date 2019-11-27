package com.it.entity;

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
@TableName("gm_address")
public class Address implements Serializable {
    /**
     * 自增长主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区县
     */
    private String county;
    /**
     * 详细
     */
    private String particular;
    /**
     * 联系方式
     */
    private String iphone;
    /**
     * 收货人
     */
    private String consignee;
    /**
     * 是否为默认
     */
    private String status;
}