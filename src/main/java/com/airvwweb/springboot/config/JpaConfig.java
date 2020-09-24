package com.airvwweb.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
// JPA Auditiong 활성화 p221
@EnableJpaAuditing
public class JpaConfig {
}
