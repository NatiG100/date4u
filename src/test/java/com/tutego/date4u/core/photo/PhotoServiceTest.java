package com.tutego.date4u.core.photo;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.Base64;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.tutego.date4u.core.FileSystem;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@SpringBootTest({"spring.shell.interactive.enabled=false","spring.shell.interactive.enabled=false"})
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
    @Nested
    class Validator{
        @Test
        void photo_is_valid(){
            Photo photo = new Photo(1L,1L,"fillmorespic",false, LocalDateTime.MIN);
            assertThatCode(()->photoService.download(photo)).doesNotThrowAnyException();
        }
        @Test void photo_has_invalid_created_date(){
            LocalDateTime future = LocalDateTime.of(2500,1,1,0,0,0);
            Photo photo = new Photo(1L,1L, "fillmorespic",false, future);
            assertThatThrownBy(()->photoService.download(photo))
                .isInstanceOf(ConstraintViolationException.class)
                .extracting(
                        throwable -> ((ConstraintViolationException) throwable).getConstraintViolations(),
                        as(InstanceOfAssertFactories.collection(ConstraintViolation.class))
                )
                    .hasSize(1)
                    .first(InstanceOfAssertFactories.type(ConstraintViolation.class))
                    .satisfies(
                            vio ->{
                                assertThat(vio.getRootBeanClass()).isSameAs(PhotoService.class);
                                assertThat(vio.getLeafBean()).isExactlyInstanceOf(Photo.class);
                                assertThat(vio.getPropertyPath()).hasToString("download.photo.created");
                                assertThat(vio.getInvalidValue()).isEqualTo(future);
                            }
                    );
        }
    }
}
