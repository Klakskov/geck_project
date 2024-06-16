package com.gecktest.gecktest.services;

import com.gecktest.gecktest.domains.request.ImageResizeRequest;

public interface ImageResizeService {

    void sendToProccess(ImageResizeRequest request);
}
