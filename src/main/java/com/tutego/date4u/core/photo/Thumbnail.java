package com.tutego.date4u.core.photo;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;


public interface Thumbnail {
    @Async
    Future<byte[]> thumbnail(byte[] imageBytes);
}
