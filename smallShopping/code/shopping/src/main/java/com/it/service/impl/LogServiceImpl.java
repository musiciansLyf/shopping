package com.it.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Log;
import com.it.mapper.LogMapper;
import com.it.service.LogService;
import com.it.util.DateUtil;
import com.it.util.ItdragonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 王钰尧
 * @create 2019/2/15 17:53
 * @since 1.0.0
 */
@Service
public class LogServiceImpl implements LogService {
    @Resource
    private LogMapper LogMapper;
    @Resource
    private ItdragonUtils itdragonUtils;

    @Override
    public Page<Log> selectPage(Log Log, int page, int limit) {
        EntityWrapper<Log> searchInfo = new EntityWrapper<>();
        Page<Log> pageInfo = new Page<>(page, limit);
        if (ItdragonUtils.stringIsNotBlack(Log.getUserName())) {
            searchInfo.eq("type", Log.getUserName());
        }
        searchInfo.orderBy("time desc");
        List<Log> resultList = LogMapper.selectPage(pageInfo, searchInfo);
        if (!resultList.isEmpty()) {
            pageInfo.setRecords(resultList);
        }
        return pageInfo;
    }

    @Override
    public boolean insert(String operation) {
        Log log = new Log();
        log.setOperation(operation);
        log.setTime(DateUtil.getNowDateSS());
        log.setUserName(itdragonUtils.getSessionUser().getUserName());
        Integer insert = LogMapper.insert(log);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delById(String id) {
        Integer delete = LogMapper.deleteById(id);
        if (delete > 0) {
            return true;
        }
        return false;
    }
}