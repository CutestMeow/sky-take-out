package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /**
     * 根据id查询菜品所属套餐
     * @param id
     * @return
     */
    @Select("select setmeal_id from setmeal_dish where dish_id = #{id}")
    List<Long> getSetmealById(Long id);
}
