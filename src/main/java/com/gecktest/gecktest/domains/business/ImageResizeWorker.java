package com.gecktest.gecktest.domains.business;

import com.gecktest.gecktest.domains.request.ImageResizeRequest;
import com.gecktest.gecktest.services.FileService;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.gecktest.gecktest.Utils.getPathname;


@Builder
@Slf4j
public record ImageResizeWorker(ImageResizeRequest request, FileService fileService) implements Runnable {

    @Override
    public void run() {
        log.info("being processed {} ", request);
        BufferedImage bufferedImage = fileService.findByPath(getPathname(request.getImageName()));


        Image resultingImage = bufferedImage.getScaledInstance(request.getWidth(), request.getHeight(), Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(request.getWidth(), request.getHeight(), BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);

        fileService.saveImage(outputImage, getPathname(request.getImageName()), "jpg");
        log.info("ended resizing image {} ", request);


    }


}
