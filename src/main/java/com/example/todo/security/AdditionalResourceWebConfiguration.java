package com.example.todo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // 💡 Spring Boot の設定クラスであることを示す
public class AdditionalResourceWebConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(@SuppressWarnings("null") ResourceHandlerRegistry registry) {
        // 💡 `/images/` へのリクエストを、プロジェクト直下の `images/` フォルダへマッピング
        registry.addResourceHandler("/images/**").addResourceLocations("file:images/");
    }
}
