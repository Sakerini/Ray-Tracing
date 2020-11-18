package com.sakerini.raytracer.utils;

import com.sakerini.raytracer.entity.Tracer;
import com.sakerini.raytracer.utils.graphics.Display;
import com.sakerini.raytracer.utils.service.TimeUtil;

public class Engine implements Runnable {

    private boolean isRunning;
    private Display _display;
    private Tracer _tracer;

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
            TimeUtil.update();
            update((float) TimeUtil.getDeltatTime());
            render();
        }
    }

    public void render() {
        _display.render();
    }

    public void update(float dt)
    {
        _tracer.update(dt);
    }
}
