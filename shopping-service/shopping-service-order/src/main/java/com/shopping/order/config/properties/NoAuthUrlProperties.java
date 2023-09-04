package com.shopping.order.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashSet;

@Data
@ConfigurationProperties(prefix = "order.auth")
public class NoAuthUrlProperties {
    private LinkedHashSet<String> shouldSkipUrls;
}
