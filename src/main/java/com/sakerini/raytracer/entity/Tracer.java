package com.sakerini.raytracer.entity;

import com.sakerini.raytracer.geometry.Vector3D;
import com.sakerini.raytracer.utils.graphics.Display;
import lombok.Getter;

@Getter
public class Tracer {

    private Camera _camera;

    public Tracer() {
        _camera = new Camera(new Vector3D(-1.0f, 1.0f, 0.2f), 0.005f, 0.1f);
    }

    public void update(float dt)
    {
        _camera.update(dt);
    }

    public void render(Display display) {
        //TODO
    }

}
