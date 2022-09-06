package org.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.example.bean.Product;

public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 减扣商品库存
     */
    int updateProductStockById(@Param("count") Integer count,@Param("id") Long id);
}
