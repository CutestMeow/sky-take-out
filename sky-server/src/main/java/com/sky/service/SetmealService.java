package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

public interface SetmealService {

    /**
     * 新增套餐
     * @params setmealDTO
     * @return
     */
    void save(SetmealDTO setmealDTO);

    /**
     * 分页查询套餐
     * @params setmealPageQueryDTO
     * @return
     */
    PageResult page(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 修改套餐
     * @params setmealDTO
     * @return
     */
    void update(SetmealDTO setmealDTO);

    /**
     * 根据id查询套餐
     * @params id
     * @return
     */
    SetmealVO getById(Long id);

    /**
     * 套餐起售、停售
     * @params status,id
     * @return
     */
    void statusChange(Integer status,Long id);
}
