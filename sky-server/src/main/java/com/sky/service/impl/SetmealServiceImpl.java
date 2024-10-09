package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.exception.SetmealEnableFailedException;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;
    @Autowired
    private DishMapper dishMapper;

    /**
     * 新增套餐
     * @params setmealDTO
     * @return
     */
    @Override
    @Transactional
    public void save(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
//        System.out.println(setmeal);
        List<SetmealDish> setmealDishList = setmealDTO.getSetmealDishes();
        setmealMapper.save(setmeal);
        for (SetmealDish setmealDish : setmealDishList) {
            setmealDish.setSetmealId(setmeal.getId());
        }
        setmealDishMapper.save(setmealDishList);
    }
    /**
     * 分页查询套餐
     * @params setmealPageQueryDTO
     * @return
     */
    @Override
    public PageResult page(SetmealPageQueryDTO setmealPageQueryDTO){
        PageHelper.startPage(setmealPageQueryDTO.getPage(),setmealPageQueryDTO.getPageSize());
        Page<SetmealVO> page=setmealMapper.page(setmealPageQueryDTO);
        PageResult pageResult=new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }
    /**
     * 修改套餐
     * @params setmealDTO
     * @return
     */
    @Override
    public void update(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmealMapper.update(setmeal);
    }
    /**
     * 根据id查询套餐
     * @params id
     * @return
     */
    @Override
    public SetmealVO getById(Long id){
        SetmealVO setmealVO=new SetmealVO();
        Setmeal setmeal=setmealMapper.getSetmealBySetmealId(id);//获取setmeal
        BeanUtils.copyProperties(setmeal,setmealVO);

        List<SetmealDish> setmealDishList=setmealDishMapper.getSetmealBySetmealId(id);//获取套餐关联菜品
        setmealVO.setSetmealDishes(setmealDishList);
        return setmealVO;
    }
    /**
     * 套餐起售、停售
     * @params status,id
     * @return
     */
    @Override
    @Transactional
    public void statusChange(Integer status,Long id){
        if(status.equals(StatusConstant.ENABLE)){
            List<Long> dishIdList=setmealDishMapper.getDishListBySetmealId(id);
            if(dishIdList!=null&&dishIdList.size()>0){
                for(Long dishId:dishIdList){
                    Dish dish=dishMapper.getById(dishId);
                    if(dish.getStatus().equals(StatusConstant.DISABLE)){
                        throw new SetmealEnableFailedException(MessageConstant.SETMEAL_ENABLE_FAILED);
                    }
                }
            }
        }

        Setmeal setmeal= Setmeal.builder().status(status).id(id).build();
        setmealMapper.update(setmeal);
    }

}
