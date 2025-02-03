package com.example.bootcamp.controller;

import com.example.bootcamp.domain.exception.ImageSendException;
import com.example.bootcamp.domain.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/images")
public class ImageController {
    @GetMapping("/{imagePath}")
    public void getProfileImage(@PathVariable String imagePath, HttpServletResponse response) {
        try {

            InputStream fileInputStream = new FileInputStream("images/" + imagePath);
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(fileInputStream, response.getOutputStream());

        } catch (FileNotFoundException e) {
            throw new ResourceNotFoundException("Изображение с путём " + imagePath + " не найдено!");
        } catch (IOException e) {
            throw new ImageSendException("Произошла ошибка при отправке изображения!");
        }
    }
}
