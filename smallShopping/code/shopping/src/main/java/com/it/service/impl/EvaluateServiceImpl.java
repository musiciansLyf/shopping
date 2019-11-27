package com.it.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.it.entity.Evaluate;
import com.it.mapper.EvaluateMapper;
import com.it.service.EvaluateService;
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
public class EvaluateServiceImpl implements EvaluateService {
    @Resource
    private EvaluateMapper EvaluateMapper;
    @Resource
    private ItdragonUtils itdragonUtils;

    @Override
    public Evaluate selecEvaluate(String id) {
        return EvaluateMapper.selectById(id);
    }

    @Override
    public boolean insertEvaluate(Evaluate Evaluate) {
        Evaluate.setUserId(itdragonUtils.getSessionUser().getId());
        Integer insert = EvaluateMapper.insert(Evaluate);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateEvaluateByPrimaryKey(Evaluate Evaluate) {
        return false;
    }

    @Override
    public boolean deleteEvaluateByPrimaryKey(String ids) {
        Integer delete = EvaluateMapper.deleteById(ids);
        if (delete > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Evaluate> getEvaluateByProductId(String productId) {
        EntityWrapper<Evaluate> searchInfo = new EntityWrapper<>();
        if (ItdragonUtils.stringIsNotBlack(productId)) {
            searchInfo.eq("productId", productId);
        }
        return EvaluateMapper.selectList(searchInfo);
    }

}