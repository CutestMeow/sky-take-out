package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    /**
     * 新增菜品
     * @param dishDTO
     * @return
     */
    void saveWithFlavor(DishDTO dishDTO);

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);
    /**
     * 批量删除菜品
     * @param ids
     * @return
     */
    void delete(List<Long> ids);
    /**
     * 修改菜品
     * @param dishDTO
     * @return
     */
    void update(DishDTO dishDTO);
    /**
     * 根据id查询菜品及口味
     * @param id
     * @return
     */
    DishVO getByIdWithFlavor(Long id);
    /**
     * 菜品起售、停售
     * @param status,id
     * @return
     */
    void statusChange(Integer status,Long id);
}
