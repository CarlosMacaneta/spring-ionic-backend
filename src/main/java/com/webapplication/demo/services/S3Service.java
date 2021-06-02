package com.webapplication.demo.services;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    
    public URI uploadFile(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            InputStream is = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();
            
            return uploadFile(is, fileName, contentType);
            
            
        } catch (IOException e) {
            throw new RuntimeException("IOException: "+ e.getMessage());
        }
    }
    
    public URI uploadFile(InputStream is, String fileName, String contentType) {
        try {
            ObjectMetadata om = new ObjectMetadata();
            om.setContentType(contentType);
            
            log.info("Iniciando upload");
            s3Client.putObject(bucketName, fileName, is, om);
            log.info("Upload terminado");
            
            return s3Client.getUrl(bucketName, fileName).toURI();
        } catch (SdkClientException | URISyntaxException e) {
           throw new RuntimeException(e.getMessage());
        } 
    }
}
