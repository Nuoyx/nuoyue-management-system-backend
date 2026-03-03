package org.nuoyue.controller;

import lombok.extern.slf4j.Slf4j;
import org.nuoyue.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
        log.info("Received upload request: name={}, age={}, fileName={}",
                name, age, file.getOriginalFilename());
        // Save the uploaded file to a local directory
        String originalFilename = file.getOriginalFilename();

        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID() + extension;

        file.transferTo(new File("D:/images/" + newFileName));
        return Result.success();
    }
}
