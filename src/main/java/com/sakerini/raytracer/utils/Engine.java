package com.sakerini.raytracer.utils;

import com.sakerini.raytracer.utils.graphics.Tracer;
import com.sakerini.raytracer.utils.graphics.Display;
import com.sakerini.raytracer.utils.service.TimeUtil;

public class Engine implements Runnable {

    private boolean isRunning;
    private Display _display;
    private Tracer _tracer;

    public Engine(Display display, Tracer tracer) {
        _display = display;
        _tracer = tracer;
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
        _tracer.render(_display);
        _display.render();
    }

    public void update(float dt)
    {
        _tracer.update(dt);
    }
}
