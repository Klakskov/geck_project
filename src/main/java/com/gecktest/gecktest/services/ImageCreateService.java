package com.gecktest.gecktest.services;

import com.gecktest.gecktest.domains.request.ImageCreateRequest;

public interface ImageCreateService {
    void save(ImageCreateRequest request);
}
