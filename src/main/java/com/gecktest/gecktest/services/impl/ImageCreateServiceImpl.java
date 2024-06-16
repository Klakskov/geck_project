package com.gecktest.gecktest.services.impl;

import com.gecktest.gecktest.domains.exceptions.BusinessException;
import com.gecktest.gecktest.domains.request.ImageCreateRequest;
import com.gecktest.gecktest.services.FileService;
import com.gecktest.gecktest.services.ImageCreateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.gecktest.gecktest.Utils.getPathname;

@Service
@AllArgsConstructor
@Slf4j
public class ImageCreateServiceImpl implements ImageCreateService {

    private FileService fileService;

    @Override
    public void save(ImageCreateRequest request) {
        try {
            fileService.save(getPathname(request.getFileName()), request.getImage().getBytes());
        } catch (IOException e) {
            log.error("error when trying to getData {} ", e.getMessage());
            throw new BusinessException("error when trying to get fileData");
        }

    }
}
