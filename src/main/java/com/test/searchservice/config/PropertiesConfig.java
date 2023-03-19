package com.test.searchservice.config;

import com.test.searchservice.config.properties.KakaoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties(value = {KakaoProperties.class})
@PropertySource(value = {"classpath:properties/kakao.properties"})
public class PropertiesConfig {
}
