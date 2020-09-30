package com.maxpapers.constants;

import lombok.NonNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ImageConverter {
    @NonNull
    public static byte[] toByteArray(@NonNull Path path){
        if (!Files.exists(path)) throw new IllegalArgumentException("File doesn't exist");

        BufferedImage bufferedImage;
        byte[] bytes = null;
        try{
            bufferedImage = ImageIO.read(path.toFile());
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
            bytes = outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
