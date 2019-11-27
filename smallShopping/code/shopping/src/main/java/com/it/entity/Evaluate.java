package com.it.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 评价实体类<br>
 *
 * @author
 * @create 2018/11/10 15:25
 * @since 1.0.0
 */
@Data
@TableName("gm_evaluate")
public class Evaluate implements Serializable {
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
     * 商品Id
     */
    private String productId;
    /**
     * 时间
     */
    private String time;
    /**
     * 内容
     */
    private String content;
    /**
     * user
     */
    @TableField(exist = false)
    private User user;
}