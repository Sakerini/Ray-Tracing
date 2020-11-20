package com.sakerini.raytracer.entity.lightsource;

import com.sakerini.raytracer.entity.geometry.Vector3D;

public class PointLight extends BaseLight {

    public PointLight(Vector3D color, float intensity) {
        super(color, intensity);
    }
}
