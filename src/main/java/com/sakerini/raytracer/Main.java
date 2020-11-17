package com.sakerini.raytracer;

import com.sakerini.raytracer.utils.Configuration;
import com.sakerini.raytracer.utils.Engine;
import com.sakerini.raytracer.utils.graphics.Display;

public class Main {
    public static void main(String[] args) {

        Configuration.load();
        Display display = new Display(
                Configuration.displayWidth,
                Configuration.displayHeight,
                Configuration.displayScale,
                Configuration.name);
        Engine engine = new Engine(display);
        engine.start();
    }
}
