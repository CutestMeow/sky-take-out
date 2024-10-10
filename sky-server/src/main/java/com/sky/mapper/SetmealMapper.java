package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    /**
     * 修改套餐
     * @param setmeal
     * @return
     */
    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);

    /**
     * 新增套餐
     * @params setmeal
     * @return
     */
    @AutoFill(OperationType.INSERT)
    void save(Setmeal setmeal);

    /**
     * 分页查询套餐
     * @params setmealPageQueryDTO
     * @return
     */
    Page<SetmealVO> page(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 根据id查询套餐
     * @params setmealId
     * @return
     */
    @Select("select * from setmeal where id=#{setmealId}")
    Setmeal getSetmealBySetmealId(Long setmealId);

    /**
     * 根据id删除套餐
     * @params ids
     * @return
     */
    void delete(List<Long> ids);

    /**
     * 根据id查询状态
     * @params id
     * @return
     */
    @Select("select status from setmeal where id=#{id}")
    Integer statusCheck(Long id);
}
