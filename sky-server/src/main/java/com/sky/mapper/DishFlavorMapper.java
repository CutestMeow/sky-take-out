package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishFlavorMapper {
    /**
     * 新增菜品口味
     * @param dishFlavor
     * @return
     */
    void saveFlavor(DishFlavor dishFlavor);
}
