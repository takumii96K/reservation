package org.example.reservation.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.reservation.entity.Image;
import org.example.reservation.repository.JpaImageRepository;
import org.example.reservation.service.spec.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final JpaImageRepository repository;
    private final String uploadDir = "/staitc/images/"; // ローカルの画像保存ディレクトリ

    @Override
    public Image saveImage(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String filePath = Paths.get(uploadDir, fileName).toString();

        // ローカルに画像を保存
        File dest = new File(filePath);
        file.transferTo(dest);

        // データベースに保存する画像のメタデータを作成
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setPath(filePath); // ローカルファイルパスをURLフィールドに保存
        return repository.save(image);
    }
//    public Image saveImage(MultipartFile file) {
//        // 画像ファイルを処理してデータベースに保存
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        try {
//            if(fileName.contains("..")) {
//                // ファイル名の検証（セキュリティ対策）
//                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//            }
//
//            Image newImage = new Image(fileName, file.getContentType(), file.getBytes());
//            return imageRepository.save(newImage); // 新しいImageエンティティを保存
//        } catch (IOException ex) {
//            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
//        }
//    }

    @Override
    public Image getImage(Long id) {
        return repository.findById(id).orElse(null);
    }


    @Override
    public Image updateImage(Long id, MultipartFile newImage) throws IOException {
        Optional<Image> existingImageOpt = repository.findById(id);

        if (existingImageOpt.isPresent()) {
            Image existingImage = existingImageOpt.get();
            String oldFilePath = existingImage.getPath();

            // 既存の画像ファイルを削除
            try {
                Files.deleteIfExists(Paths.get(oldFilePath));
            } catch (IOException e) {
                // ファイルの削除に失敗した場合のエラーハンドリング
                e.printStackTrace();
            }

            // 新しい画像を保存
            String fileName = UUID.randomUUID().toString() + "_" + newImage.getOriginalFilename();
            String filePath = Paths.get(uploadDir, fileName).toString();
            File dest = new File(filePath);
            newImage.transferTo(dest);

            // データベースレコードを更新
            existingImage.setName(newImage.getOriginalFilename());
            existingImage.setPath(filePath); // 新しい画像のパスを設定
            return repository.save(existingImage);
        } else {
            // 指定されたIDの画像が見つからない場合の処理
            return null; // 実際にはここでカスタム例外を投げることを検討してください
        }
    }


    @Override
    public void deleteImage(Long id) {
        repository.deleteById(id);
    }
}
