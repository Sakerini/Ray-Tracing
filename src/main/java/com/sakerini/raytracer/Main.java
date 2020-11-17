package com.sakerini.raytracer;

import com.sakerini.raytracer.utils.Engine;
import com.sakerini.raytracer.utils.graphics.Display;

public class Main {
    public static void main(String[] args) {

        Display display = new Display(768, 768, 1,"Ray Tracer");
        Engine engine = new Engine(display);
        engine.start();
    }
}
