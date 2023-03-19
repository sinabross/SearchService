package com.test.searchservice.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties
public class KakaoProperties {

    private String restapi;
}
