package com.gecktest.gecktest.services.impl;

import com.gecktest.gecktest.domains.request.ImageResizeRequest;
import com.gecktest.gecktest.services.FileService;
import com.gecktest.gecktest.services.ImageResizeService;
import com.gecktest.gecktest.services.workers.ImageResizeWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Service
@Slf4j
public class ResizeServiceImpl implements ImageResizeService {

    private final ThreadPoolExecutor executor;
    private final FileService fileService;

    @Autowired
    public ResizeServiceImpl(
            @Value("${image.resize.service.threads}") int qtdThreads,
            FileService fileService) {
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(qtdThreads);
        this.fileService = fileService;
    }


    @Override
    public void sendToProccess(ImageResizeRequest request) {
        log.info("sending to process {}", request);
        executor.execute(ImageResizeWorker.builder()
                                 .request(request)
                                 .fileService(fileService)
                                 .build());
    }
}
