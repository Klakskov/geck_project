package com.gecktest.gecktest.domains.business;

import com.gecktest.gecktest.domains.request.ImageResizeRequest;
import com.gecktest.gecktest.services.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.image.BufferedImage;
import java.nio.file.Path;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ImageResizeWorkerTest {

    @Mock
    private FileService fileService;
    @InjectMocks
    private ImageResizeWorker worker;

    private ImageResizeRequest request;

    @BeforeEach
    public void setUp() {
        request = new ImageResizeRequest();
        request.setImageName("testImage.jpg");
        request.setWidth(100);
        request.setHeight(100);

        worker = new ImageResizeWorker(request, fileService);
    }

    @Test
    public void testRun() throws Exception {
        // Mocking
        BufferedImage mockBufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        when(fileService.findByPath(any(Path.class))).thenReturn(mockBufferedImage);

        doNothing().when(fileService).saveImage(
                any(BufferedImage.class), any(Path.class), any(String.class));

        // Execute the method
        worker.run();

        // Verify
        verify(fileService).findByPath(any(Path.class));
        verify(fileService).saveImage(any(BufferedImage.class),
                                      any(Path.class),
                                      eq("jpg"));
    }


}
