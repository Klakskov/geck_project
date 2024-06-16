package com.gecktest.gecktest.controllers;

import com.gecktest.gecktest.domains.request.ImageCreateRequest;
import com.gecktest.gecktest.domains.request.ImageDeleteRequest;
import com.gecktest.gecktest.domains.request.ImageResizeRequest;
import com.gecktest.gecktest.services.DeleteImageService;
import com.gecktest.gecktest.services.ImageCreateService;
import com.gecktest.gecktest.services.ImageListService;
import com.gecktest.gecktest.services.ImageResizeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/images")
@AllArgsConstructor
public class ImageControllerImpl implements ImageController {

    private final ImageCreateService imageCreateService;
    private final ImageListService imageListService;
    private final DeleteImageService deleteImageService;
    private final ImageResizeService imageResizeService;

    @Override
    @PostMapping()
    public ResponseEntity<String> uploadImage(
            ImageCreateRequest request) {
        imageCreateService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }

    @Override
    public ResponseEntity<List<String>> getAllFiles() {
        return ResponseEntity.ok(
                imageListService.getAll()
        );
    }


    @Override
    public ResponseEntity<String> deleteByName(ImageDeleteRequest request) {
        deleteImageService.deleteByName(request);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");

    }

    @Override
    public ResponseEntity<String> resize(ImageResizeRequest request) {
        imageResizeService.sendToProccess(request);
        return ResponseEntity.status(HttpStatus.OK).body("On process");
    }


}
