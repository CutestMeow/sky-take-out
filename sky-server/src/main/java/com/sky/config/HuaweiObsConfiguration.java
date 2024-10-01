package com.sky.config;

import com.sky.properties.HuaweiObsProperties;
import com.sky.utils.HuaweiObsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class HuaweiObsConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public HuaweiObsUtil huaweiObsUtil(HuaweiObsProperties huaweiObsProperties) {
        return new HuaweiObsUtil(huaweiObsProperties.getEndpoint(),
                huaweiObsProperties.getAccessKeyId(),
                huaweiObsProperties.getAccessKeySecret(),
                huaweiObsProperties.getBucketName());

    }
}
