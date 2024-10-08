package com.sky.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SetmealMapper {

    /**
     * 根据分类id查询套餐的数量
     * @param id
     * @return
     */
    @Select("select count(id) from setmeal where category_id = #{categoryId}")
    Integer countByCategoryId(Long id);

    /**
     * 根据菜品id删除含有该菜品的套餐
     * @param dishId
     * @return
     */
    @Delete("delete from setmeal where id in (select setmeal_id from setmeal_dish where dish_id=#{dishId})")
    void deleteSetmealByDishId(Long dishId);
}
