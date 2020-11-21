package com.sakerini.raytracer.entity;

import com.sakerini.raytracer.entity.geometry.Vector3D;
import com.sakerini.raytracer.entity.material.BaseMaterial;
import com.sakerini.raytracer.entity.material.CooktorranceMaterial;

import java.util.HashMap;
import java.util.Map;

public class MaterialDict {

    private Map<String, BaseMaterial> dict = new HashMap();

    MaterialDict() {
        CooktorranceMaterial diffuse_sky = new CooktorranceMaterial(
                new Vector3D(0.01f),
                new Vector3D(0.0f, 0.5f, 1.0f),
                new Vector3D(1.0f),
                0.0f, 0, 0, 0.375f, 0.5f, 0.9f);

        dict.put("diffuse_sky", diffuse_sky);
        CooktorranceMaterial diffuse_sand = new CooktorranceMaterial(
                new Vector3D(0.01f),
                new Vector3D(1.0f, 1.0f, 0.5f),
                new Vector3D(1.0f),
                0.0f, 0, 0, 0.375f, 0.5f, 0.9f);
        dict.put("diffuse_sand", diffuse_sand);

        CooktorranceMaterial reflective_green = new CooktorranceMaterial(
                new Vector3D(0.01f),
                new Vector3D(0.0f, 1.0f, 0.0f),
                new Vector3D(1.0f),
                0.50f, 0, 0, 0.05f, 1.0f, 0.5f);
        dict.put("reflective_green", reflective_green);
    }

    public BaseMaterial getMaterial(String materialName) {
        return dict.get(materialName);
    }
}
