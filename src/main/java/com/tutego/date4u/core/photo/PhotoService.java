package com.tutego.date4u.core.photo;

import java.io.UncheckedIOException;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.tutego.date4u.core.FileSystem;
import com.tutego.date4u.core.core.NewPhotoEvent;

@Service
public class PhotoService {
    private final FileSystem fs;
    private final List<Thumbnail> thumbnails;
    @Autowired
    private ApplicationEventPublisher publisher;

    public PhotoService(FileSystem fs, @Autowired List<Thumbnail> thumbnails) {
        this.fs = fs;
        this.thumbnails = thumbnails;
    }

    public Optional<byte[]> download(String name) {
        try {
            return Optional.of(fs.load(name + ".jpg"));
        } catch (UncheckedIOException e) {
            return Optional.empty();
        }
    }

    public String upload(byte[] imageBytes) {
        String imageName = UUID.randomUUID().toString();
        // First: store original image
        fs.store(imageName + ".jpg", imageBytes);
        // Second: store thumbnail
        byte[] thumbnailBytes = thumbnails.get(0).thumbnail(imageBytes);
        fs.store(imageName + "-thumb.jpg", thumbnailBytes);

        NewPhotoEvent newPhotoEvent = new NewPhotoEvent(imageName, OffsetDateTime.now());
        publisher.publishEvent(newPhotoEvent);
        return imageName;
    }
}
