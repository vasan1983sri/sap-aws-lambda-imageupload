package org.sap.sap_lambda_imageupload;


import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class StreamLambdaHandler implements RequestStreamHandler {
    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

    static {
        // Capture the AWS environment variable manually
        String activeProfile = System.getenv("SPRING_PROFILES_ACTIVE");

        if (activeProfile != null) {
            // Force set it as a core JVM system property before Spring boots up
            System.out.println("Setting active Spring profile to: " + activeProfile);
            System.setProperty("spring.profiles.active", activeProfile);
        }
    }
    static {
        try {
            handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(SapLambdaImageUploadApplication.class);
        } catch (ContainerInitializationException e) {
            // if we fail here. We re-throw the exception to force another cold start
            e.printStackTrace();
            throw new RuntimeException("Could not initialize Spring Boot application", e);
        }
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context)
            throws IOException {
        handler.proxyStream(inputStream, outputStream, context);
    }
}