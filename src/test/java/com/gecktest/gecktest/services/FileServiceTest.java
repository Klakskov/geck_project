package com.gecktest.gecktest.services;

import com.gecktest.gecktest.services.impl.FileServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileServiceTest {

    private FileService fileService;

    @BeforeEach
    public void setUp() {
        fileService = new FileServiceImpl();
    }

    @Test
    public void testSaveImage(@TempDir Path tempDir) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

        Path imagePath = tempDir.resolve("testImage.jpg");
        
        fileService.saveImage(bufferedImage, imagePath, "jpg");

        File savedFile = new File(imagePath.toString());
        assertTrue(savedFile.exists(), "The image file should exist.");

        BufferedImage savedImage = ImageIO.read(savedFile);
        assertNotNull(savedImage, "The saved image should not be null.");
        assertEquals(100, savedImage.getWidth(), "The width of the saved image should be 100.");
        assertEquals(100, savedImage.getHeight(), "The height of the saved image should be 100.");
    }


    @Test
    public void testGetAllImages(@TempDir Path tempDir) throws IOException {
        Files.createFile(tempDir.resolve("image1.jpg"));
        Files.createFile(tempDir.resolve("image2.png"));
        Files.createFile(tempDir.resolve("image3.gif"));

        Files.createDirectory(tempDir.resolve("subdir"));

        List<String> images = fileService.getAllImages(tempDir);

        assertEquals(3, images.size(), "There should be 3 files in the directory.");
        assertTrue(images.contains("image1.jpg"), "The list should contain 'image1.jpg'.");
        assertTrue(images.contains("image2.png"), "The list should contain 'image2.png'.");
        assertTrue(images.contains("image3.gif"), "The list should contain 'image3.gif'.");
    }

    @Test
    public void testDeleteByName_Success(@TempDir Path tempDir) throws IOException {
        // Create a dummy file in the temporary directory
        Path filePath = Files.createFile(tempDir.resolve("testFile.txt"));

        // Call the method
        fileService.deleteByName(filePath);

        // Verify that the file is deleted
        assertFalse(Files.exists(filePath), "The file should be deleted.");

    }

    @Test
    public void testDeleteByName_NoSuchFile(@TempDir Path tempDir) {
        // Define a path to a non-existent file
        Path filePath = tempDir.resolve("nonExistentFile.txt");

        // Call the method
        fileService.deleteByName(filePath);

    }

    @Test
    public void testDeleteByName_DirectoryNotEmpty(@TempDir Path tempDir) throws IOException {
        // Create a directory and a file inside it
        Path dirPath = Files.createDirectory(tempDir.resolve("nonEmptyDir"));
        Files.createFile(dirPath.resolve("fileInDir.txt"));

        // Call the method and verify the exception
        fileService.deleteByName(dirPath);

    }


}