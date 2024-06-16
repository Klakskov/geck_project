package com.gecktest.gecktest.services.impl;

import com.gecktest.gecktest.domains.request.ImageDeleteRequest;
import com.gecktest.gecktest.services.DeleteImageService;
import com.gecktest.gecktest.services.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.gecktest.gecktest.Utils.getPathname;

@Service
@AllArgsConstructor
@Slf4j
public class DeleteImageServiceImpl implements DeleteImageService {
    private final FileService fileService;

    @Override
    public void deleteByName(ImageDeleteRequest request) {
        fileService.deleteByName(getPathname(request.getImageName()));
    }
}
