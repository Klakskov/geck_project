package com.gecktest.gecktest.services;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.List;

public interface FileService {
    void save(Path fileNameAndPath, byte[] bytes);

    List<String> getAllImages(Path uploadDirectory);

    BufferedImage findByPath(Path string);

    void deleteByName(Path uploadDirectory);

    void saveImage(BufferedImage resizedImage, Path pathname, String jpg);
}
