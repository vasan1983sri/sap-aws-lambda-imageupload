package org.sap.sap_lambda_imageupload;

import org.sap.sap_lambda_imageupload.controller.PingController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@SpringBootApplication
// We use direct @Import instead of @ComponentScan to speed up cold starts
// @ComponentScan(basePackages = "org.sap.controller")
@Import({ PingController.class })
public class SapLambdaImageUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(SapLambdaImageUploadApplication.class, args);
    }
}