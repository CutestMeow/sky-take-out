package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.utils.HuaweiObsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
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
    @Autowired
    private HuaweiObsUtil huaweiObsUtil;
    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file) {
        log.info("文件上传：{}",file);
        String originalFilename=file.getOriginalFilename();
        //构造唯一文件名 uuiddish
        String type=originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        String newFilename = UUID.randomUUID().toString().replace("-","")+"."+type;

//        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
//        String pre = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath() +
//                "\\src\\main\\resources\\static\\PictureResources\\";
//        String path = pre + newFilename;
        String filePath="";
        try{
            filePath=huaweiObsUtil.upload(file.getBytes(),newFilename);
//            System.out.println("文件上传成功");
        }catch (Exception e){
            log.error("文件上传失败：{}",e.toString());
        }


//        System.out.println("开始查询图片");
//        String dest = "F:\\Studies\\Graduates\\JavaStudies\\nginx-1.20.2\\html\\sky\\img\\PictureResources\\"+newFilename;
//        String dest2= "/img/logo.38b01728.png";
        System.out.println(filePath+"/"+newFilename);
        return Result.success(filePath+"/"+newFilename);
        //TODO 修改此处代码，使得可以本地存储图片

    }
}
