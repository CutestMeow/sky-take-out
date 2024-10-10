package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/setmeal")
@RestController
@Api(tags="套餐相关接口")
@Slf4j

public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    /**
     * 新增套餐
     * @params setmealDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增套餐")
    public Result<String> save(@RequestBody SetmealDTO setmealDTO){
        log.info("新增套餐：{}",setmealDTO);
        setmealService.save(setmealDTO);
        return Result.success();
    }

    /**
     * 分页查询套餐
     * @params setmealPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分页查询套餐")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("分页查询套餐：{}",setmealPageQueryDTO);
        PageResult pageResult=setmealService.page(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 修改套餐
     * @params setmealDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改套餐")
    public Result<String> update(@RequestBody SetmealDTO setmealDTO){
        log.info("修改菜品{}",setmealDTO);
        setmealService.update(setmealDTO);
        return Result.success();
    }
    /**
     * 根据id查询套餐
     * @params id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询套餐")
    public Result<SetmealVO> getById(@PathVariable Long id){
        log.info("根据id查询套餐：{}",id);
        SetmealVO setmealVO = setmealService.getById(id);
        return Result.success(setmealVO);
    }
    /**
     * 套餐起售、停售
     * @params status,id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("套餐起售、停售")
    public Result<String> statusChange(@PathVariable Integer status,Long id){
        if(status==1){
            log.info("套餐起售，id为{}",id);
        }else{
            log.info("套餐停售，id为{}",id);
        }
        setmealService.statusChange(status,id);
        return Result.success();
    }
    /**
     * 批量删除套餐
     * @params ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("批量删除套餐")
    public Result<String> delete(@RequestParam List<Long> ids){
        log.info("批量删除套餐：{}",ids);
        setmealService.delete(ids);
        return Result.success();
    }
}
