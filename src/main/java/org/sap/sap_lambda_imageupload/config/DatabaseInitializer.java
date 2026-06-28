package org.sap.sap_lambda_imageupload.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        File targetFile = new File("/tmp/testdb.mv.db");


        // Only extract if the database file does not already exist on the host machine
        if (!targetFile.exists()) {
            ClassPathResource resource = new ClassPathResource("/tmp/testdb.mv.db");
            try (InputStream in = resource.getInputStream()) {
                Files.copy(in, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Database successfully extracted from JAR to: " + targetFile.getAbsolutePath());
            }
        }
    }
}