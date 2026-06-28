package org.sap.sap_lambda_imageupload.data.repository;


import org.sap.sap_lambda_imageupload.data.dto.ImageDetailsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageDetailsRepository extends JpaRepository<ImageDetailsDto, Long> {

   ImageDetailsDto save(ImageDetailsDto imageDetailsDto);

   @Query(value = "SELECT p.* FROM SAP_IMAGE.IMAGE_STORE p WHERE FILE_CATEGORY = :fileCategory", nativeQuery = true)
   List<ImageDetailsDto> findImageByCategory(String fileCategory);

}
