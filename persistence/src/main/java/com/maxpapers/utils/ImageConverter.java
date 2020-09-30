package com.maxpapers.utils;

import lombok.NonNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public final class ImageConverter {
    public static byte[] toByteArray(@NonNull Path path){
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
        if (bytes == null || bytes.length == 0){
            throw new NullPointerException("byte array is empty");
        }
        return bytes;
    }
}
