package com.tutego.date4u.interfaces.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.unit.DataSize;

import com.tutego.date4u.core.FileSystem;

@ShellComponent
public class FsCommands {

    // private final FileSystem fs = new FileSystem();

    private final FileSystem fs;

    public FsCommands(FileSystem fs) {
        this.fs = fs;
    }

    @ShellMethod("Display free disk space")
    public String freeDiskSpace() {
        return DataSize.ofBytes(fs.getFreeDiskSpace()).toGigabytes() + "GB";
    }
}
