package com.samruddhi.fit_buy.service.image;

import com.samruddhi.fit_buy.dto.ImageDto;
import com.samruddhi.fit_buy.model.Image;
import com.samruddhi.fit_buy.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    List<ImageDto> addImage(List<MultipartFile> files, Long productId);
    void updateImage(MultipartFile file, Long imageId);
    void deleteImageById(Long id);
}
