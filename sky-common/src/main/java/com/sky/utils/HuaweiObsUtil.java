package com.sky.utils;

import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import com.obs.services.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Data
@AllArgsConstructor
@Slf4j
public class HuaweiObsUtil {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    /**
     * 文件上传
     *
     * @param bytes
     * @param objectName
     * @return
     */
    public String upload(byte[] bytes, String objectName) throws IOException {
        endpoint="obs.cn-north-4.myhuaweicloud.com";
        accessKeyId="ZHFNNHUHP6JEWIGKE5EV";
        accessKeySecret="m8kOywWTSlGtm852HRqznVqwRouUQjyWPJLcpbcP";
        bucketName="skymeow";
        //要把权限改成所有用户均可访问，不然读不出来
        // 创建OBSClient实例。
        ObsClient obsClient = new ObsClient(accessKeyId, accessKeySecret,endpoint);

        try {
            // 创建PutObject请求。
            InputStream inputStream = new ByteArrayInputStream(bytes);

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
            PutObjectResult putObjectResult = obsClient.putObject(putObjectRequest);
        } catch (ObsException oe) {
            System.out.println("Caught an ObsException, which means your request made it to OBS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
        } catch (Exception e) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + e.getMessage());
        } finally {
            if (obsClient != null) {
                obsClient.close();
            }
        }

        //文件访问路径规则 https://BucketName.Endpoint/ObjectName
        StringBuilder stringBuilder = new StringBuilder("https://");
        stringBuilder
                .append(bucketName)
                .append(".")
                .append(endpoint)
                .append("/")
                .append(objectName);
        String uploadPath="https://skymeow.obs.cn-north-4.myhuaweicloud.com";
        log.info("文件上传到:{}", uploadPath);

//        return stringBuilder.toString();
        return uploadPath;
    }
}
