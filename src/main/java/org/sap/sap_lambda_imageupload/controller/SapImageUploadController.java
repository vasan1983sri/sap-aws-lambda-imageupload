package org.sap.sap_lambda_imageupload.controller;


import org.sap.sap_lambda_imageupload.data.dto.ImageDetailsDto;
import org.sap.sap_lambda_imageupload.service.ImageProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/spaImage")
public class SapImageUploadController {

    @Autowired
    ImageProcessing imageProcessing;

    @GetMapping(value = "/ImageCategory/byName")
    public List<ImageDetailsDto> getImageByCategory(@RequestParam String category){
        List<ImageDetailsDto> images = imageProcessing.getImagesByCategory(category);
        return images;
    }

    @PostMapping(value = "/saveImage", consumes = "multipart/form-data")
    public String saveImage(@RequestPart("image") MultipartFile image, @RequestParam String category)
    {
        String storedImageInfo = imageProcessing.saveImage(image, category);
        return storedImageInfo;
    }

}
