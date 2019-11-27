package com.it.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Log;

public interface LogService {
    /**
     * 分页查询
     *
     * @param Log
     * @param page
     * @param limit
     * @return
     */
    Page<Log> selectPage(Log Log, int page, int limit);

    /**
     * 新增
     *
     * @param operation
     * @return
     */
    boolean insert(String operation);

    /**
     * 删除
     */
    boolean delById(String id);


}
