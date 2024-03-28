package org.example.reservation.service.spec;

import org.example.reservation.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    Image saveImage(MultipartFile file) throws IOException;
    Image getImage(Long id);
    Image updateImage(Long id, MultipartFile newImage) throws IOException;
    void deleteImage(Long id);
}

