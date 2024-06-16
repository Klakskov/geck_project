package com.gecktest.gecktest.services.impl;

import com.gecktest.gecktest.services.FileService;
import com.gecktest.gecktest.services.ImageListService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;

import static com.gecktest.gecktest.Utils.UPLOAD_DIRECTORY;

@Service
@AllArgsConstructor
public class ImageListServiceImpl implements ImageListService {

    private final FileService fileService;

    @Override
    public List<String> getAll() {
        return fileService.getAllImages(
                Path.of(UPLOAD_DIRECTORY));
    }
}
