package com.c_comachi.inused.domain.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.c_comachi.inused.domain.s3.dto.AwsS3;
import com.c_comachi.inused.domain.s3.dto.request.S3RemoveRequestDto;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AwsS3Service {
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Transactional
    public AwsS3 upload(MultipartFile multipartFile, String dirName) throws IOException {
        File file = convertMultipartFileToFile(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File convert fail"));

        return upload(file, dirName);
    }

    private AwsS3 upload(File file, String dirName) {
        String key = randomFileName(file, dirName);
        String path = putS3(file, key);
        removeFile(file);

        return AwsS3
                .builder()
                .key(key)
                .path(path)
                .build();
    }

    private String randomFileName(File file, String dirName) {
        return dirName + "/" + UUID.randomUUID() + file.getName();
    }

    private String putS3(File uploadFile, String fileName) {
        amazonS3.putObject(new PutObjectRequest(bucket, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return getS3(bucket, fileName);
    }

    private String getS3(String bucket, String fileName) {
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    private void removeFile(File file) {
        file.delete();
    }

    @Transactional
    public Optional<File> convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(System.getProperty("user.dir") + "/" + multipartFile.getOriginalFilename());

        if (file.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(file)){
                fos.write(multipartFile.getBytes());
            }
            return Optional.of(file);
        }
        return Optional.empty();
    }

    @Transactional
    public void remove(S3RemoveRequestDto awsS3) {
        if (!amazonS3.doesObjectExist(bucket, awsS3.getKey())) {
            throw new AmazonS3Exception("Object " +awsS3.getKey()+ " does not exist!");
        }
        amazonS3.deleteObject(bucket, awsS3.getKey());
    }


}
