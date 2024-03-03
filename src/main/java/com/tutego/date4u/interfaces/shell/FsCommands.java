package com.tutego.date4u.interfaces.shell;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.unit.DataSize;

import com.tutego.date4u.core.FileSystem;
import com.tutego.date4u.core.configuration.Date4uApplicationConfig;


@ShellComponent
public class FsCommands {

    @Autowired
    Date4uApplicationConfig date4uConfig;
    @Value("${date4u.filesystem.minimum-free-disk-space}")
    private long minimumFreeDiskSpace;
    @Autowired
    private Environment env;
    private final FileSystem fs;

    public FsCommands(FileSystem fs) {
        this.fs = fs;

    }

    @ShellMethod("Display user home")
    public String userHome() {
        return env.getProperty("user.home");
    }

    @ShellMethod("Display free disk space")
    public String freeDiskSpace() {
        return DataSize.ofBytes(fs.getFreeDiskSpace()).toGigabytes() + "GB";
    }

    @ShellMethod("Display required free disk space")
    public long minimumFreeDiskSpace() {
        return date4uConfig.getFilesystem().getMinimumFreeDiskSpace();
    }
    @ShellMethod("Display if a path exists")
    public String exists(Path path){
        boolean exists = fs.exists(path);
        return String.format("Path to '%s' %s exists",path, exists?"does":"doesn't");
    }
}
