package com.tutego.date4u.core.photo;

import org.springframework.stereotype.Service;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import javax.imageio.ImageIO;

@Service("qualityThumbnailRenderer")
public class AwtBicubicThumbnail implements Thumbnail{
    private static BufferedImage create(BufferedImage source, int width, int height) {
        double thumbRatio = (double) width / height;
        double imageRatio = (double) source.getWidth() / source.getHeight();
        if (thumbRatio < imageRatio)
            height = (int) (width / imageRatio);
        else
            width = (int) (height * imageRatio);
        BufferedImage thumb = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = thumb.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.drawImage(source, 0, 0, width, height, null);
        g2.dispose();
        return thumb;
    }

    @Override
    public Future<byte[]> thumbnail(byte[] imageBytes) {
        try (InputStream is = new ByteArrayInputStream(imageBytes);
                ByteArrayOutputStream boas = new ByteArrayOutputStream()) {
            BufferedImage thumbnail = create(ImageIO.read(is), 200, 200);
            ImageIO.write(thumbnail, "jpg", boas);
            return CompletableFuture.completedFuture(boas.toByteArray()) ;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
