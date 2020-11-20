package com.sakerini.raytracer.entity.material;

import com.sakerini.raytracer.entity.geometry.Vector3D;
import com.sakerini.raytracer.entity.material.BaseMaterial;
import com.sakerini.raytracer.utils.Configuration;
import lombok.Getter;
import lombok.Setter;

public class PhongMaterial extends BaseMaterial {
    @Getter
    @Setter
    private float shininess;
    public PhongMaterial(Vector3D ambientColor, Vector3D diffuseColor, Vector3D specularColor,
                         float reflectivity, float refractivity, float ior, float shininess) {
        super(ambientColor, diffuseColor, specularColor, reflectivity, refractivity, ior);
        this.setModel(Configuration.materialModel.PHONG);
        this.shininess = shininess;
    }
}
