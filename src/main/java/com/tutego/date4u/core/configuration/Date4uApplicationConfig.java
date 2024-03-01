package com.tutego.date4u.core.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("date4u")
public class Date4uApplicationConfig {

    record Server(String ip) {
    }

    private List<Server> servers = new ArrayList<>();

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public static class Filesystem {
        private long minimumFreeDiskSpace = 1_000_000;

        public long getMinimumFreeDiskSpace() {
            return minimumFreeDiskSpace;
        }

        public void setMinimumFreeDiskSpace(long minimumFreeDiskSpace) {
            this.minimumFreeDiskSpace = minimumFreeDiskSpace;
        }
    }

    private Filesystem filesystem = new Filesystem();

    public Filesystem getFilesystem() {
        return filesystem;
    }

    public void setFilesystem(Filesystem filesystem) {
        this.filesystem = filesystem;
    }
}
