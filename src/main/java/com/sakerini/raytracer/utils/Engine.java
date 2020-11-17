package com.sakerini.raytracer.utils;

import com.sakerini.raytracer.utils.graphics.Display;

public class Engine implements Runnable {

    private boolean isRunning;
    private Display _display;

    public Engine(Display display) {
        _display = display;
        isRunning = false;
    }

    public synchronized void start() {
        if (!isRunning) {
            isRunning = true;
            run();
        }
    }

    public synchronized void stop() {
        if (isRunning) {
            isRunning = false;
            System.exit(0);
        }
    }

    @Override
    public void run() {
        while (isRunning && _display != null) {
            render();
        }
    }

    public void render() {
        _display.render();
    }
}
