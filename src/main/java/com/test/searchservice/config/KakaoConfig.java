package com.test.searchservice.config;

import com.test.searchservice.config.properties.KakaoProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class KakaoConfig {

    private final KakaoProperties kakaoProperties;

    @Bean
    public WebClient kakaoWebClient(){
        return WebClient.builder().baseUrl("https://dapi.kakao.com")
                .defaultHeader("Authorization", "KakaoAK " + kakaoProperties.getRestapi()).build();
    }
}
