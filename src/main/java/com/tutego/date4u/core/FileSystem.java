package com.tutego.date4u.core;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

@Service
public class FileSystem {
    private final Path root = Paths.get(System.getProperty("user.home")).resolve("fs");
    public FileSystem(){
        try{
            if(!Files.isDirectory(root)){
                Files.createDirectory(root);
            }
        }catch(IOException e){
            throw new UncheckedIOException(e);
        }
    }
    public boolean exists(Path path){
        return Files.exists(path);
    }
    public long getFreeDiskSpace(){
        return root.toFile().getFreeSpace();
    }

    public byte[] load(String fileName){
        try{
            Path path = resolve(fileName);
            return Files.readAllBytes(path);
        }catch(IOException e){
            throw new UncheckedIOException(e);
        }
    }
    public void store(String filename, byte[] bytes){
        try{
            Path path = resolve(filename);
            Files.write(path, bytes);
        }catch(IOException e){
            throw new UncheckedIOException(e);
        }
    }
    private Path resolve(String filename){
        Path path = root.resolve(filename).toAbsolutePath().normalize();
        if(!path.startsWith(root)){
            throw new SecurityException("Access to "+path+" denied");
        }
        return path;
    }
}
