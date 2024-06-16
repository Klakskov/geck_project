package com.gecktest.gecktest.domains.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ImageResizeRequest {
    @NotNull
    @NotBlank
    private String imageName;
    @Positive
    private int width;
    @Positive
    private int height;
}
