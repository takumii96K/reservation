package org.example.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.example.reservation.entity.Image;
import org.example.reservation.service.spec.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            Image savedImage = imageService.saveImage(file);
            return new ResponseEntity<>(savedImage, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/images/{id}")
//    public ResponseEntity<?> getImage(@PathVariable Long id) {
//        Image image = imageService.getImage(id);
//        // image.getPath() が /images/4 のような相対パスを返すことを想定
//        String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path(image.getPath())
//                .toUriString();
//
//        return ResponseEntity
//                .ok()
//                .body(Collections.singletonMap("url", imageUrl));
//    }
}

