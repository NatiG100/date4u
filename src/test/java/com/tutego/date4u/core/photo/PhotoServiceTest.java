package com.tutego.date4u.core.photo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Base64;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.tutego.date4u.core.FileSystem;

@SpringBootTest("spring.shell.interactive.enabled=false")
public class PhotoServiceTest {
    private static final byte[] MINIMAL_JPG = Base64.getDecoder().decode(
            "/9j/4AAQSkZJRgABAQEASABIAAD/2wBDAP////////////////////////////////////"
                    + "//////////////////////////////////////////////////wgALCAABAAEBAREA/8QA"
                    + "FBABAAAAAAAAAAAAAAAAAAAAAP/aAAgBAQABPxA=");

    @MockBean
    private FileSystem fileSystem;
    @SpyBean
    private Thumbnail thumbnail;
    @Autowired
    private PhotoService photoService;

    @BeforeEach
    void setupFileSystem() {
        given(fileSystem.getFreeDiskSpace()).willReturn(1L);
        given(fileSystem.load(anyString())).willReturn(MINIMAL_JPG);
    }

    @Test
    void successful_photo_uplad() {
        String imageName = photoService.upload(MINIMAL_JPG);
        assertThat(imageName).isNotEmpty();
        verify(fileSystem, times(2)).store(anyString(), any(byte[].class));
        verify(thumbnail).thumbnail(any(byte[].class));
    }
}
