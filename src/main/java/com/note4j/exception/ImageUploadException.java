package com.note4j.exception;

/**
 * Created by changwei on 15/4/21.
 */
@SuppressWarnings("serial")
public class ImageUploadException extends RuntimeException {
    public ImageUploadException(String message) {
        super(message);
    }

    public ImageUploadException(String message, Throwable cause) {
        super(message, cause);
    }
}
