package com.it.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Category;
import com.it.entity.PtCategory;
import com.it.mapper.CategoryMapper;
import com.it.mapper.PtCategoryMapper;
import com.it.service.CategoryService;
import com.it.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <br>商品分类Service
 *
 * @author 王钰尧
 * @create 2018/11/9 11:25
 * @since 1.0.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private PtCategoryMapper ptCategoryMapper;

    @Override
    public Page<Category> selectCategoryPage(Category category, int page, int limit) {
        EntityWrapper<Category> searchInfo = new EntityWrapper<>();
        Page<Category> pageInfo = new Page<>(page, limit);

        if (category.getTitle() != null && !"".equals(category.getTitle())) {
            searchInfo.like("title", category.getTitle());
        }
        if (category.getParentId() != null && !"".equals(category.getParentId())) {
            searchInfo.eq("parentId", category.getParentId());
        }
        List<Category> resultList = categoryMapper.selectPage(pageInfo, searchInfo);
        if (!resultList.isEmpty()) {
            pageInfo.setRecords(resultList);
        }
        return pageInfo;
    }

    @Override
    public Page<PtCategory> selectPtCategoryPage(PtCategory ptCategory, int page, int limit) {
        EntityWrapper<PtCategory> searchInfo = new EntityWrapper<>();
        Page<PtCategory> pageInfo = new Page<>(page, limit);
        if (ptCategory.getTitle() != null && !"".equals(ptCategory.getTitle())) {
            searchInfo.like("title", ptCategory.getTitle());
        }
        List<PtCategory> resultList = ptCategoryMapper.selectPage(pageInfo, searchInfo);
        if (!resultList.isEmpty()) {
            pageInfo.setRecords(resultList);
        }
        return pageInfo;
    }

    @Override
    public Category selecCategory(String id) {
        return categoryMapper.selectById(id);
    }

    @Override
    public PtCategory selecPtCategory(String id) {
        return ptCategoryMapper.selectById(id);
    }

    @Override
    public boolean insertCategory(Category category) {
        category.setTime(DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
        int result = categoryMapper.insert(category);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean insertPtCategory(PtCategory ptCategory) {
        ptCategory.setTime(DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
        int result = ptCategoryMapper.insert(ptCategory);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateCategoryByPrimaryKey(Category category) {
        int result = categoryMapper.updateById(category);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updatePtCategoryByPrimaryKey(PtCategory ptCategory) {
        int result = ptCategoryMapper.updateById(ptCategory);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteCategoryByPrimaryKey(String ids) {
        int result = categoryMapper.deleteById(ids);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deletePtCategoryByPrimaryKey(String ids) {
        int resultPt = ptCategoryMapper.deleteById(ids);
        EntityWrapper<Category> searchInfo = new EntityWrapper<>();
        if (resultPt > 0) {
            searchInfo.eq("parentId", ids);
            categoryMapper.delete(searchInfo);
            return true;
        }
        return false;
    }

    @Override
    public List<PtCategory> getPtCategory() {
        List<PtCategory> ptCategories = ptCategoryMapper.selectList(null);
        for (int i = 0; i < ptCategories.size(); i++) {
            EntityWrapper<Category> searchInfo = new EntityWrapper<>();
            searchInfo.eq("parentId", ptCategories.get(i).getId());
            List<Category> categories = categoryMapper.selectList(searchInfo);
            ptCategories.get(i).setCategoryList(categories);

        }
        return ptCategories;
    }

    @Override
    public List<Category> getCategory() {
        return categoryMapper.selectList(null);
    }

    @Override
    public List<Category> getCategoryByPtCategoryId(String ptCategoryId) {
        EntityWrapper<Category> searchInfo = new EntityWrapper<>();
        searchInfo.eq("parentId", ptCategoryId);
        List<Category> categoryList = categoryMapper.selectList(searchInfo);
        return categoryList;
    }
}