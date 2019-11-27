package com.it.service;

import com.it.entity.Evaluate;

import java.util.List;

public interface EvaluateService {


    Evaluate selecEvaluate(String id);

    /**
     * 新增
     *
     * @param Evaluate
     * @return
     */
    boolean insertEvaluate(Evaluate Evaluate);

    /**
     * 修改
     *
     * @param Evaluate
     * @return
     */
    boolean updateEvaluateByPrimaryKey(Evaluate Evaluate);

    /**
     * 删除父分了
     *
     * @param ids
     * @return
     */
    boolean deleteEvaluateByPrimaryKey(String ids);

    /**
     * @param userId
     * @return
     */
    List<Evaluate> getEvaluateByProductId(String productId);


}
