package com.ditraacademy.travelagency.core.upload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadResponseModel {
    private String url;
}
