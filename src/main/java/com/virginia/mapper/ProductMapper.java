package com.virginia.mapper;

import com.virginia.annotation.DataFilterByRegionAnnotation;
import com.virginia.pojo.Product;
import com.virginia.query.DataFilterQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    // Get all products with id, name and region only
    @DataFilterByRegionAnnotation(tableAlias = "tp", tableField = "region")
    List<Product> selectAllProducts(DataFilterQuery query);
}