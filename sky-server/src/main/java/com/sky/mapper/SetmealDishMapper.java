package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /**
     * 根据dishId查询菜品所属套餐
     * @param dishId
     * @return
     */
    @Select("select setmeal_id from setmeal_dish where dish_id = #{dishId}")
    List<Long> getSetmealById(Long dishId);

    /**
     * 根据dishIds批量查询菜品所属套餐
     * @param dishIds
     * @return
     */

    List<Long> getSetmealsByDishIds(List<Long> dishIds);
}
