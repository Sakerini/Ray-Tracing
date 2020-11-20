package com.sakerini.raytracer.entity.lightsource;

import com.sakerini.raytracer.entity.geometry.Vector3D;
import com.sakerini.raytracer.utils.Configuration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DirectionalLight extends BaseLight {

    private Vector3D direction;

    public DirectionalLight(Vector3D position, Vector3D direction, Vector3D color, float intensity) {
        super(position, color, intensity);
        this.setLightType(Configuration.lightType.DIRECTIONAL);
        this.direction = direction;
    }
}
