package com.sakerini.raytracer;

import com.sakerini.raytracer.entity.Tracer;
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
        Tracer tracer = new Tracer();
        Engine engine = new Engine(display, tracer);
        engine.start();
    }
}
