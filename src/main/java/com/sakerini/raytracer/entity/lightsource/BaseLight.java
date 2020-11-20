package com.sakerini.raytracer.entity.lightsource;

import com.sakerini.raytracer.entity.geometry.Vector3D;
import com.sakerini.raytracer.utils.Configuration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseLight {

    private Enum<Configuration.lightType> lightType;
    private Vector3D position;
    private Vector3D color;
    private float intensity;

    public BaseLight(Vector3D position, Vector3D color, float intensity) {
        this.position = position;
        this.color = color;
        this.intensity = intensity;
    }
}
