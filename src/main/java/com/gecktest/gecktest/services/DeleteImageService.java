package com.gecktest.gecktest.services;

import com.gecktest.gecktest.domains.request.ImageDeleteRequest;

public interface DeleteImageService {
    void deleteByName(ImageDeleteRequest request);
}
