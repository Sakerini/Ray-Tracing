package com.sakerini.raytracer.utils;

import com.sakerini.raytracer.utils.service.ResourceFileLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;

public class Configuration {

    private static final String FILE_RESOURCE = "config.cfg";

    public static String name;
    public static int displayWidth;
    public static int displayHeight;
    public static int displayScale;
    public static int maxRecursion;
    public static int screenDivider;
    public static float epsilon;
    public static boolean debug;


    public static void load() {
        Properties properties = new Properties();
        InputStream istream = null;
        try {
            istream = new FileInputStream(getResourceAsFile(FILE_RESOURCE));

            properties.load(istream);

            name = String.valueOf(properties.getProperty("name"));
            displayWidth = Integer.valueOf(properties.getProperty("display_width"));
            displayHeight = Integer.valueOf(properties.getProperty("display_height"));
            displayScale = Integer.valueOf(properties.getProperty("display_scale"));
            maxRecursion = Integer.valueOf(properties.getProperty("recursion_max"));
            screenDivider = Integer.valueOf(properties.getProperty("thread_amount"));
            epsilon = Float.valueOf(properties.getProperty("epsilon"));
            debug = Boolean.valueOf(properties.getProperty("debug"));

            istream.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.exit(1);
        }
    }

    private static File getResourceAsFile(String resource) {
        File file = null;
        try {
            file = ResourceFileLoader.loadFile(resource);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return file;
    }

    public static enum materialModel {
        COOKTORRANCE, PHONG
    }

}
