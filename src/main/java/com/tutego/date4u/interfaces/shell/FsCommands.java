package com.tutego.date4u.interfaces.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.unit.DataSize;

import com.tutego.date4u.core.FileSystem;

@ShellComponent
public class FsCommands {

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
        return minimumFreeDiskSpace;
    }
}
