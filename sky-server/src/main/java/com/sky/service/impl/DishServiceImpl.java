package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Override
    @Transactional
    public void saveWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dish.setStatus(StatusConstant.ENABLE);

        dishMapper.saveDish(dish);
        Long id = dish.getId();
        List<DishFlavor> dishFlavorList = dishDTO.getFlavors();
        if(dishFlavorList!=null&&dishFlavorList.size()>0){
            for(DishFlavor df : dishFlavorList) {
                df.setDishId(id);
                dishFlavorMapper.saveFlavor(df);
            }
        }
        //TODO 改为SQL批量查询
    }

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO){
        PageHelper.startPage(dishPageQueryDTO.getPage(),dishPageQueryDTO.getPageSize());

        Page<DishVO> page=dishMapper.pageQuery(dishPageQueryDTO);
        PageResult pageResult=new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }

    /**
     * 批量删除菜品
     * @param ids
     * @return
     */

    @Transactional
    @Override
    public void delete(List<Long> ids){
        for (Long id : ids) {
            Dish dish = dishMapper.getById(id);
            if(dish.getStatus().equals(StatusConstant.ENABLE)){
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }

            List<Long> setmealIdList=setmealDishMapper.getSetmealById(id);
            if(setmealIdList!=null&&setmealIdList.size()>0){
                throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
            }
        }
        dishMapper.deleteByIds(ids);
        dishFlavorMapper.deleteFlavorByDishIds(ids);
    }

    /**
     * 修改菜品
     * @param dishDTO
     * @return
     */
    @Override
    public void update(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.update(dish);


        dishFlavorMapper.deleteFlavorByDishIds(Collections.singletonList(dishDTO.getId()));

        List<DishFlavor> dishFlavorList = dishDTO.getFlavors();
        if(dishFlavorList!=null&&dishFlavorList.size()>0){
            for(DishFlavor df : dishFlavorList) {
                df.setDishId(dishDTO.getId());
                dishFlavorMapper.saveFlavor(df);
            }
        }
        //TODO 改为SQL批量查询
    }
    /**
     * 根据id查询菜品及口味
     * @param id
     * @return
     */
    @Override
    public DishVO getByIdWithFlavor(Long id) {
        Dish dish = dishMapper.getById(id);
        DishVO dishVO=new DishVO();
        BeanUtils.copyProperties(dish, dishVO);
        List<DishFlavor> dishFlavorList=dishFlavorMapper.getDishFlavorsByDishId(id);
        dishVO.setFlavors(dishFlavorList);
        return dishVO;
    }
    /**
     * 菜品起售、停售
     * @param status,id
     * @return
     */
    public void statusChange(Integer status,Long id){
        Dish dish = dishMapper.getById(id);
        dish.setStatus(status);
        dishMapper.update(dish);
    }
}
