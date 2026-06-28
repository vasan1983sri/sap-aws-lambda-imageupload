package org.sap.sap_lambda_imageupload.service;


import org.sap.sap_lambda_imageupload.data.dto.ImageDetailsDto;
import org.sap.sap_lambda_imageupload.data.repository.ImageDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ImageProcessing {

    @Autowired
    ImageDetailsRepository imageDetailsRepository;

    public String saveImage(MultipartFile image, String category) {
        String fileName = image.getOriginalFilename();
        String contentType = image.getContentType();
        ImageDetailsDto e = populateImageDetails(image, category, fileName, contentType);
        imageDetailsRepository.save(e);
        return e.toString();
    }

    public List<ImageDetailsDto> getImagesByCategory(String category) {
        return imageDetailsRepository.findImageByCategory(category.toLowerCase());
    }

    private ImageDetailsDto populateImageDetails(MultipartFile image, String category, String fileName, String contentType) {
        ImageDetailsDto imageDetails = new ImageDetailsDto();
        try {
            imageDetails.setFileName(fileName);
            imageDetails.setFileType(contentType);
            byte[] imageBytes = image.getBytes();
            imageDetails.setImageData(imageBytes);
            if(contentType != null) {
                imageDetails.setFileCategory(category);
            } else {
                imageDetails.setFileCategory("general");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to process the image: " + e.getMessage());
        }
        return imageDetails;
    }
}
