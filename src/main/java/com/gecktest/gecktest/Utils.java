package com.gecktest.gecktest;

import java.nio.file.Path;

public class Utils {

    public static final String UPLOAD_DIRECTORY = "src/main/resources/images";


    public static Path getPathname(String... filename) {
        return Path.of(UPLOAD_DIRECTORY, filename);
    }

}
