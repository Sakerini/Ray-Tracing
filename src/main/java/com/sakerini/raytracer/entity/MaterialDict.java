package com.sakerini.raytracer.entity;

import com.sakerini.raytracer.entity.geometry.Vector3D;
import com.sakerini.raytracer.entity.material.BaseMaterial;
import com.sakerini.raytracer.entity.material.CooktorranceMaterial;

import java.util.HashMap;
import java.util.Map;

public class MaterialDict {

    private Map<String, BaseMaterial> dict = new HashMap();

    MaterialDict() {
        //CREATE MATERIALS HERE
        CooktorranceMaterial diffuse_sky = new CooktorranceMaterial(
                new Vector3D(0.01f),
                new Vector3D(0.0f, 0.5f, 1.0f),
                new Vector3D(1.0f),
                0.0f, 0, 0, 0.375f, 0.5f, 0.9f);
        dict.put("diffuse_sky", diffuse_sky);

        CooktorranceMaterial skin_color = new CooktorranceMaterial(
                new Vector3D(0.01f),
                new Vector3D(1.0f, 1.0f, 0.5f),
                new Vector3D(1.0f),
                0.0f, 0.0001f, 0, 0.50f, 1.0f, 1f);
        dict.put("skin_color", skin_color);

        CooktorranceMaterial diffuse_green = new CooktorranceMaterial(
                new Vector3D(0.01f),
                new Vector3D(0.0f, 1.0f, 0.0f),
                new Vector3D(1.0f),
                0.0f, 0, 0, 0.375f, 0.5f, 0.9f);
        dict.put("diffuse_green", diffuse_green);

        CooktorranceMaterial diffuse_white = new CooktorranceMaterial(
                new Vector3D(0.01f),
                new Vector3D(1.0f, 1.0f, 1.0f),
                new Vector3D(1.0f),
                0.0f, 0, 0, 0.375f, 0.5f, 0.9f);
        dict.put("diffuse_white", diffuse_white);

        CooktorranceMaterial reflective_green = new CooktorranceMaterial(
                new Vector3D(0.01f),
                new Vector3D(0.0f, 1.0f, 0.0f),
                new Vector3D(1.0f),
                0.50f, 0, 0, 0.05f, 1.0f, 0.5f);
        dict.put("reflective_green", reflective_green);

        CooktorranceMaterial reflective_metal = new CooktorranceMaterial(
                new Vector3D(0.01f),
                new Vector3D(0.0f, 0.0f, 0.0f),
                new Vector3D(1.0f),
                1.0f, 0, 0, 0.10f, 1.0f, 0.5f);
        dict.put("reflective_metal", reflective_metal);

        CooktorranceMaterial reflective_purple = new CooktorranceMaterial(
                new Vector3D(0.01f),
                new Vector3D(0.5f, 0.0f, 1.0f),
                new Vector3D(1.0f),
                0.25f, 0, 0, 0.50f, 1.0f, 1f);
        dict.put("reflective_purple", reflective_purple);

        CooktorranceMaterial black_color = new CooktorranceMaterial(
                new Vector3D(0.01f),
                new Vector3D(0.2f, 0.2f, 0.2f),
                new Vector3D(1.0f),
                0.0f, 0.0001f, 0, 0.50f, 1.0f, 1f);
        dict.put("black_color", black_color);
    }

    public BaseMaterial getMaterial(String materialName) {
        return dict.get(materialName);
    }
}
