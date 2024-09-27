package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 通用接口
 */
@RestController
@RequestMapping("/admin/common")
@Api(tags="通用接口")
@Slf4j
public class CommonController {

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file)  {
        log.info("文件上传：{}",file);
        String originalFilename=file.getOriginalFilename();
        //构造唯一文件名 uuid
        String type=originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        String newFilename = UUID.randomUUID().toString().replace("-","")+"."+type;
        try{
            file.transferTo(new File("F:\\Studies\\Graduates\\JavaStudies\\sky-take-out\\PictureResources\\"+newFilename));
        }catch (Exception e){
            log.error("文件上传失败：{}",e.toString());
        }
        return Result.success("PictureResources\\"+newFilename);
        //TODO 修改此处代码，使得可以显示图片

    }
}
