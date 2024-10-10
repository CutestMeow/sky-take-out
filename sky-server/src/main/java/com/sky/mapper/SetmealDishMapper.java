package com.sky.mapper;

import com.sky.entity.Dish;
import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
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

    /**
     * 新增套餐菜品关系
     * @params setmealDishList
     * @return
     */
    void save(List<SetmealDish> setmealDishList);

    /**
     * 根据setmealId查询菜品所属套餐
     * @param setmealId
     * @return
     */
    @Select("select * from setmeal_dish where setmeal_id = #{setmealId}")
    List<SetmealDish> getSetmealBySetmealId(Long setmealId);

    /**
     * 根据setmealId查询所包含菜品
     * @param setmealId
     * @return
     */
    @Select("select dish_id from setmeal_dish where setmeal_id=#{setmealId}")
    List<Long> getDishListBySetmealId(Long setmealId);

    /**
     * 根据setmealId删除套餐与菜品的关联关系
     * @params setmealId
     * @return
     */
    @Delete("delete from setmeal_dish where setmeal_id=#{setmealId}")
    void deleteBySetmealId(Long setmealId);
}
