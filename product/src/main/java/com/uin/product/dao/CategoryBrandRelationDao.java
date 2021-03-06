package com.uin.product.dao;

import com.uin.product.entity.CategoryBrandRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 品牌分类关联
 *
 * @author wanglufei
 * @email 1634060836@qq.com
 * @date 2022-04-18 18:05:16
 */
@Mapper
public interface CategoryBrandRelationDao extends BaseMapper<CategoryBrandRelationEntity> {


    void updateCategory(@Param("catId") Long catId,@Param("name") String name);
}
