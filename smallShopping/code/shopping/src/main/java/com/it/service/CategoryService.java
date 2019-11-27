package com.it.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Category;
import com.it.entity.PtCategory;

import java.util.List;

public interface CategoryService {

    /**
     * 子分类分页查询
     *
     * @param category
     * @param page
     * @param limit
     * @return
     */
    Page<Category> selectCategoryPage(Category category, int page, int limit);

    /**
     * 父分类分页查询
     *
     * @param ptCategory
     * @param page
     * @param limit
     * @return
     */
    Page<PtCategory> selectPtCategoryPage(PtCategory ptCategory, int page, int limit);

    /**
     * 根据主键id查询
     *
     * @param id
     * @return
     */
    Category selecCategory(String id);

    PtCategory selecPtCategory(String id);

    /**
     * 新增子分类
     *
     * @param category
     * @return
     */
    boolean insertCategory(Category category);

    /**
     * 新增父分类
     *
     * @param ptCategory
     * @return
     */
    boolean insertPtCategory(PtCategory ptCategory);

    /**
     * 修改
     *
     * @param category
     * @return
     */
    boolean updateCategoryByPrimaryKey(Category category);

    /**
     * 修改
     *
     * @param ptCategory
     * @return
     */
    boolean updatePtCategoryByPrimaryKey(PtCategory ptCategory);

    /**
     * 删除子分类
     *
     * @param ids
     * @return
     */
    boolean deleteCategoryByPrimaryKey(String ids);

    /**
     * 删除父分了
     *
     * @param ids
     * @return
     */
    boolean deletePtCategoryByPrimaryKey(String ids);

    /**
     * 得到父分类和其下的子分类
     *
     * @return
     */
    List<PtCategory> getPtCategory();

    /**
     * 得到所有子分类
     *
     * @return
     */
    List<Category> getCategory();

    /**
     * 根据父类id得到所有子类集合
     *
     * @param ptCategoryId
     * @return
     */
    List<Category> getCategoryByPtCategoryId(String ptCategoryId);

}
