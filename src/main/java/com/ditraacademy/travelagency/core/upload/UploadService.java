package com.ditraacademy.travelagency.core.upload;


import com.ditraacademy.travelagency.utility.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class UploadService {


    @Value("${UPLOAD_DIR}")
    private Path fileStorgePath;

    @Value("${SERVER_HOST}")
    private String serverHost;


    public ResponseEntity<?> uploadFile(MultipartFile file){

        String fileName =file.getOriginalFilename();
        Path targetLocation = fileStorgePath.resolve(fileName);

        try{
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            return new ResponseEntity<>(new ErrorResponseModel(e.getMessage()) , HttpStatus.BAD_REQUEST);
        }

        String fileUrl = serverHost+ fileName ;
        return new ResponseEntity<>( new UploadResponseModel(fileUrl),HttpStatus.OK);
    }
}
