package com.ditraacademy.travelagency.core.upload;


import com.ditraacademy.travelagency.utility.ErrorResponseModel;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
public class UploadController {

    @Autowired
    UploadService uploadService ;

   @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file){
       return  uploadService.uploadFile(file);
   }
}
