package com.sakerini.raytracer.entity;

import com.sakerini.raytracer.utils.graphics.Display;
import lombok.Getter;
import lombok.Setter;

public class TracerWorker implements Runnable {

    @Setter
    private Display _display;
    private Tracer _tracer;

    @Getter
    private int width;
    @Getter
    private int height;
    @Getter
    private int xOffset;
    @Getter
    private int yOffset;
    @Getter
    private int id;
    @Getter
    private boolean isFinished;

    public TracerWorker(Tracer _tracer, int width, int height, int xOffset, int yOffset, int id) {
        this._tracer = _tracer;
        this.width = width;
        this.height = height;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.id = id;
        this.isFinished = true;
    }

    @Override
    public void run() {
        isFinished = false;
        //TODO PARALLEL RAY TRACING WORK DONE HERE
        isFinished = true;
    }
}
