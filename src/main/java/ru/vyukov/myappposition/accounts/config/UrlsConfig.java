package ru.vyukov.myappposition.accounts.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.stereotype.Component;

import java.beans.ConstructorProperties;

/**
 * @author gelo
 */
@Data
@Component
@ConfigurationProperties("urls")
public class UrlsConfig {
    private String panelUrl;
}
