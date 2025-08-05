package com.dujiaqi0725.seckill.dao;

import com.dujiaqi0725.seckill.dataobject.ProductDO;
import com.dujiaqi0725.seckill.param.BasePageParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductDAO {

    int add(ProductDO productDO);

    int delete(@Param("id") Long id);

    int update(ProductDO productDO);

    ProductDO selectById(@Param("id") Long id);

    ProductDO selectByName(@Param("name") String name);

    List<ProductDO> selectAllByName(@Param("name") String name);


    List<ProductDO> pageQuery(BasePageParam param);

    List<ProductDO> selectAll();

    int selectCount();

}
