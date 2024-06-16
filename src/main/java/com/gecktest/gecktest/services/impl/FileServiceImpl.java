package com.gecktest.gecktest.services.impl;

import com.gecktest.gecktest.domains.exceptions.BusinessException;
import com.gecktest.gecktest.services.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {

    @Override
    public void save(Path fileNameAndPath, byte[] bytes) {
        try {
            Files.write(fileNameAndPath, bytes);
        } catch (Exception e) {
            log.error("error when trying to save file {} ", e.getMessage());
            throw new BusinessException("error");
        }
    }

    @Override
    public List<String> getAllImages(Path uploadDirectory) {
        File directory = new File(uploadDirectory.toString());

        File[] files = directory.listFiles();

        if (files != null) {
            return Arrays.stream(files)
                    .filter(File::isFile) // Filter only regular files
                    .map(File::getName)
                    .collect(Collectors.toList());

        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public BufferedImage findByPath(Path string) {
        try {
            return ImageIO.read(
                    new File(string.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteByName(Path filePath) {
        try {
            Files.delete(filePath);
            log.error("Arquivo deletado com sucesso: {} ", filePath);
        } catch (NoSuchFileException e) {
            log.error("Arquivo não encontrado: {} ", filePath);
            return;
        } catch (DirectoryNotEmptyException e) {
            log.error("O diretório não está vazio: {} ", filePath);
            return;
        } catch (IOException e) {
            log.error("Erro ao deletar o arquivo: {}, error {} ",
                      filePath, e.getMessage());
            throw new BusinessException("error when trying to delete file");
        }
    }

    @Override
    public void saveImage(BufferedImage resizedImage, Path path, String jpg) {
        try {
            ImageIO.write(resizedImage, "jpg", new File(
                    path.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
