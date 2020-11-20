package com.sakerini.raytracer.entity.lightsource;

import com.sakerini.raytracer.entity.geometry.Vector3D;

public class DirectionalLight extends BaseLight {

    public DirectionalLight(Vector3D color, float intensity) {
        super(color, intensity);
    }
}
