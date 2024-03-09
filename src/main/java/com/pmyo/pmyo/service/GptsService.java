package com.pmyo.pmyo.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class GptsService {
    private final AmazonS3 amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String savePic(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());
        amazonS3Client.putObject(bucket, fileName, file.getInputStream(), metadata);
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }
}
