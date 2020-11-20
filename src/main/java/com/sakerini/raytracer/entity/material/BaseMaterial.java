package com.sakerini.raytracer.entity.material;

import com.sakerini.raytracer.entity.geometry.Vector3D;
import com.sakerini.raytracer.utils.Configuration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseMaterial {
    private Enum<Configuration.materialModel> model;
    private Vector3D ambientColor;
    private Vector3D diffuseColor;
    private Vector3D specularColor;
    private float reflectivity;
    private float refractivity;
    private float ior;

    public BaseMaterial(Vector3D ambientColor, Vector3D diffuseColor, Vector3D specularColor, float reflectivity, float refractivity, float ior) {
        this.ambientColor = ambientColor;
        this.diffuseColor = diffuseColor;
        this.specularColor = specularColor;
        this.reflectivity = reflectivity;
        this.refractivity = refractivity;
        this.ior = ior;
    }
}
