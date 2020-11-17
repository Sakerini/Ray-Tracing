package com.sakerini.raytracer.utils;

public class Engine implements Runnable {

    private boolean isRunning;

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

    }
}
