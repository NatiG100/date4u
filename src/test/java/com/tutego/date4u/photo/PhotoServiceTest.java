package com.tutego.date4u.photo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Base64;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.tutego.date4u.core.FileSystem;
import com.tutego.date4u.core.photo.AwtBicubicThumbnail;
import com.tutego.date4u.core.photo.PhotoService;

// @SpringBootTest()
@ExtendWith(MockitoExtension.class)
public class PhotoServiceTest {
    private static final byte[] MINIMAL_JPG = Base64.getDecoder().decode(
            "/9j/4AAQSkZJRgABAQEASABIAAD/2wBDAP////////////////////////////////////"
                    + "//////////////////////////////////////////////////wgALCAABAAEBAREA/8QA"
                    + "FBABAAAAAAAAAAAAAAAAAAAAAP/aAAgBAQABPxA=");

    @Mock
    private FileSystem fileSystem;
    @Spy
    private AwtBicubicThumbnail thumbnail;
    @InjectMocks
    private PhotoService photoService;

    @Test
    void successful_photo_uplad() {
        String imageName = photoService.upload(MINIMAL_JPG);
        assertThat(imageName).isNotEmpty();
        verify(fileSystem, times(2)).store(anyString(), any(byte[].class));
        verify(thumbnail).thumbnail(any(byte[].class));
    }
}
