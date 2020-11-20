package com.sakerini.raytracer.entity;

import com.sakerini.raytracer.entity.geometry.Vector3D;
import com.sakerini.raytracer.utils.Configuration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CooktorranceMaterial extends BaseMaterial {

    private float roughness;
    private float fresnel;
    private float density;

    public CooktorranceMaterial(
            Vector3D ambientColor, Vector3D diffuseColor, Vector3D specularColor,
            float reflectivity, float refractivity, float ior,
            float roughness, float fresnel, float density) {
        super(ambientColor, diffuseColor, specularColor, reflectivity, refractivity, ior);
        this.setModel(Configuration.materialModel.COOKTORRANCE);
        this.roughness = roughness;
        this.fresnel = fresnel;
        this.density = density;
    }
}
