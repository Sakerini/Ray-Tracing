package com.sakerini.raytracer.utils.graphics;
import com.sakerini.raytracer.entity.Camera;
import com.sakerini.raytracer.entity.Scene;
import com.sakerini.raytracer.entity.TracerWorker;
import com.sakerini.raytracer.entity.geometry.Vector3D;
import com.sakerini.raytracer.utils.Configuration;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Tracer {

    private Camera _camera;
    private Scene _scene = new Scene();
    private List<TracerWorker> _workers = new ArrayList<TracerWorker>();

    public Tracer() {
        _camera = new Camera(new Vector3D(-1.0f, 1.0f, 0.2f), 0.005f, 0.1f);
        prepareWorkers(Configuration.amoutOfThreads);
    }

    public void prepareWorkers(int quantity) {
        if (quantity <= 0)
            quantity = Runtime.getRuntime().availableProcessors();

        _workers.clear();

        int width = Configuration.displayWidth;
        int height = Configuration.displayHeight;

        if (quantity > 1) {
            // TODO CREATE WORKER FOR CERTAIN PART
        } else {
            _workers.add(new TracerWorker(this, width, height, 0,0, 0));
        }

    }

    public boolean isWorkerDone() {
        for (TracerWorker worker: _workers)
            if (!worker.isFinished())
                return false;
        return true;
    }

    public void update(float dt)
    {
        _camera.update(dt);
    }

    public void render(Display display) {
        if (isWorkerDone()) {
            for(TracerWorker worker : _workers) {
                worker.set_display(display);
                Thread tracerWorker = new Thread(worker, "Worker: " + worker.getId());
                tracerWorker.start();
            }
        }
    }

}