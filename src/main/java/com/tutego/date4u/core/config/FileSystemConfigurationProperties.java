package com.tutego.date4u.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("date4u.filesystem")
public record FileSystemConfigurationProperties(
        String homepage, int numberOfSeminars, long minimumFreeDiskSpace) {
}
