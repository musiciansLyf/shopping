package com.it.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Notice;

import java.util.List;

public interface NoticeService {
    /**
     * 分页查询
     *
     * @param notice
     * @param page
     * @param limit
     * @return
     */
    Page<Notice> selectPage(Notice notice, int page, int limit);

    /**
     * 新增
     *
     * @param notice
     * @return
     */
    boolean insert(Notice notice);

    /**
     * 删除
     */
    boolean delById(String id);

    /**
     * 修改
     */
    boolean updateById(Notice notice);

    /**
     * 得到单个对象
     */
    Notice getOneById(String id);

    /**
     * 得到所有集合
     */
    List<Notice> getListByType(String type);

}
