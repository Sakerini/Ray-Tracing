package com.sakerini.raytracer.entity.lightsource;

import com.sakerini.raytracer.entity.geometry.Vector3D;
import com.sakerini.raytracer.utils.Configuration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointLight extends BaseLight {
    private float constant;
    private float linear;
    private float exponent;


    public PointLight(Vector3D position, Vector3D color, float intensity, float constant, float linear, float exponent) {
        super(position, color, intensity);
        this.setLightType(Configuration.lightType.POINT);
        this.constant = constant;
        this.linear = linear;
        this.exponent = exponent;
    }
}
