package com.example.bootcamp.domain.exception;

public class ImageSendException extends RuntimeException {
    public ImageSendException(String message) {
        // Вызывается при ошибке во время отправки изображения.
        super(message);
    }
}
