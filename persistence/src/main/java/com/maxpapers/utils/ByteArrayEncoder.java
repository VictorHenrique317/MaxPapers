package com.maxpapers.utils;

import lombok.NonNull;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public final class ByteArrayEncoder {
    // Encodes byte array to base64 and then to a String
    @NonNull
    public static String toBase64String(@NonNull byte[] bytes) {
        return new String(Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8);
    }
}
