package com.it.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 股票实体类
 *
 * @author itdragon
 */
@Data
@TableName("gm_notice")
public class Notice implements Serializable {
    /**
     * 自增长主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 公告类型
     */
    private String type;
    /**
     * 公告名称
     */
    private String name;
    /**
     * 发布时间
     */
    private String time;
    /**
     * 公告内容
     */
    private String content;
    /**
     * 图片
     */
    private String imgUrl;
}