package com.gecktest.gecktest.controllers;

import com.gecktest.gecktest.domains.request.ImageCreateRequest;
import com.gecktest.gecktest.domains.request.ImageDeleteRequest;
import com.gecktest.gecktest.domains.request.ImageResizeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ImageController {
    @PostMapping()
    ResponseEntity<String> uploadImage(
            @ModelAttribute @Validated ImageCreateRequest request);

    @GetMapping()
    ResponseEntity<List<String>> getAllFiles();

    @DeleteMapping
    ResponseEntity<String> deleteByName(@Validated @RequestBody ImageDeleteRequest request);

    @PostMapping("/resize")
    ResponseEntity<String> resize(@Validated @RequestBody ImageResizeRequest request);
}
