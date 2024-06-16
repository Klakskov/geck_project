package com.gecktest.gecktest.domains.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageCreateRequest {

    private String fileName;
    @NotNull(message = "image is needed")
    private MultipartFile image;

    public String getFileName() {
        return fileName == null ? image.getOriginalFilename() : fileName + ".png";
    }
}
