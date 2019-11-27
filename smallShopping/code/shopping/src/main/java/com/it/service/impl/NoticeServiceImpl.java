package com.it.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Notice;
import com.it.mapper.NoticeMapper;
import com.it.service.NoticeService;
import com.it.util.DateUtil;
import com.it.util.ItdragonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author
 * @create 2019/2/15 17:53
 * @since 1.0.0
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public Page<Notice> selectPage(Notice notice, int page, int limit) {
        EntityWrapper<Notice> searchInfo = new EntityWrapper<>();
        Page<Notice> pageInfo = new Page<>(page, limit);
        if (ItdragonUtils.stringIsNotBlack(notice.getType())) {
            searchInfo.eq("type", notice.getType());
        }
        searchInfo.orderBy("time desc");
        List<Notice> resultList = noticeMapper.selectPage(pageInfo, searchInfo);
        if (!resultList.isEmpty()) {
            pageInfo.setRecords(resultList);
        }
        return pageInfo;
    }

    @Override
    public boolean insert(Notice notice) {
        notice.setTime(DateUtil.getNowDateSS());
        Integer insert = noticeMapper.insert(notice);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delById(String id) {
        Integer delete = noticeMapper.deleteById(id);
        if (delete > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateById(Notice notice) {
        Integer update = noticeMapper.updateById(notice);
        if (update > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Notice getOneById(String id) {
        return noticeMapper.selectById(id);
    }

    @Override
    public List<Notice> getListByType(String type) {
        EntityWrapper<Notice> searchInfo = new EntityWrapper<>();
        if (ItdragonUtils.stringIsNotBlack(type)) {
            searchInfo.eq("type", type);
        }
        return noticeMapper.selectList(searchInfo);
    }
}