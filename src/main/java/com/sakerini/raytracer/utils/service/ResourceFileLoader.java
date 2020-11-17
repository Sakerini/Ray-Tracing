package com.sakerini.raytracer.utils.service;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class ResourceFileLoader {

    public static File loadFile(String filePath) throws URISyntaxException {
        ClassLoader classLoader = ResourceFileLoader.class.getClassLoader();
        URL resource = classLoader.getResource(filePath);
        if (resource == null) {
            throw new IllegalArgumentException("File not found" + filePath);
        } else {
            return new File(resource.toURI());
        }
    }
}
