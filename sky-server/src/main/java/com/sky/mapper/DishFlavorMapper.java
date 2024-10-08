package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /**
     * 新增菜品口味
     * @param dishFlavor
     * @return
     */
    void saveFlavor(DishFlavor dishFlavor);

    /**
     * 根据菜品id删除口味信息
     * @param dishIds
     * @return
     */
    void deleteFlavorByDishIds(List<Long> dishIds);

    /**
     * 根据菜品id获取口味信息
     * @param dishId
     * @return
     */
    List<DishFlavor> getDishFlavorsByDishId(Long dishId);
}
