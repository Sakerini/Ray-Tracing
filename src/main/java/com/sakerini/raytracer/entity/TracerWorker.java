package com.sakerini.raytracer.entity;

import com.sakerini.raytracer.utils.Configuration;
import com.sakerini.raytracer.utils.graphics.Display;
import com.sakerini.raytracer.utils.graphics.Tracer;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

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

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("Worker: " + id + " Starts Work");
        isFinished = false;
        if (_tracer != null && _display != null) {
            int width = Configuration.displayWidth;
            int height = Configuration.displayHeight;
            Ray cameraRay;
            float aspectRatio = (float) _display.getWidth() / (float) _display.getHeight();
            for (int y = yOffset; y < (yOffset + height); y++)
                for (int x = xOffset; x < (xOffset + width); x++) {
                    cameraRay = Ray.calculateCameraRay(_tracer.get_camera(), width, height, aspectRatio, x, y);
                    //TODO DRAW PIXEL VECTOR3D

                }
        }
        TimeUnit.SECONDS.sleep(2);
        isFinished = true;
        System.out.println("Worker: " + id + " Finishes Work");
    }
}
