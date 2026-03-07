package org.nuoyue.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class AWSS3Operator {
    private final String bucketName;
    private final Region region;

    public AWSS3Operator(AWSS3Properties awss3Properties) {
        this.bucketName = awss3Properties.getBucketName();
        this.region = Region.of(awss3Properties.getRegion());
    }
    public String upload(byte[] content, String originalFilename) {
        // create S3 client
        S3Client s3Client = S3Client.builder()
                .region(region)
                .build();

        // yyyy/MM directory
        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));

        // generate new file name
        String newFileName = UUID.randomUUID() +
                originalFilename.substring(originalFilename.lastIndexOf("."));

        String objectName = dir + "/" + newFileName;

        try {

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(objectName)
                    .build();

            s3Client.putObject(
                    putObjectRequest,
                    RequestBody.fromBytes(content)
            );

        } catch (S3Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            s3Client.close();
        }
        return "https://" + bucketName + ".s3." + region.id() + ".amazonaws.com/" + objectName;
    }
}