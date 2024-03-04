package com.tutego.date4u.core;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileSystemTest {
    @Test
    @DisplayName("Free disk space has to be positive")
    void free_disk_space_has_to_be_positive(){
        //given
        FileSystem fs = new FileSystem();

        //when
        long actual = fs.getFreeDiskSpace();


        //then
        //assert
        assertTrue(actual>0, "Free disk space was not > 0");
    }    
}
