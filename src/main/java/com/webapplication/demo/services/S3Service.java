package com.webapplication.demo.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author CarlosMacaneta
 */
@Service
public class S3Service {
    
    private Logger log = LoggerFactory.getLogger(S3Service.class);
    
    @Autowired
    private AmazonS3 s3Client;
    
    @Value("${s3.bucket}")
    private String bucketName;
    
    public void uploadFile(String localFilePath) {
        try {
            File file = new File(localFilePath);
            log.info("Iniciando upload");
            s3Client.putObject(bucketName, "teste.png", file);
            log.info("Upload terminado");
        } catch (AmazonServiceException e) {
            log.info("AmazonServiceException: "+ e.getErrorMessage());
            log.info("Status code: "+ e.getErrorCode());
        } catch (AmazonClientException e2) {
            log.info("AmazonClientException: "+ e2.getMessage());
        }
    }
}
