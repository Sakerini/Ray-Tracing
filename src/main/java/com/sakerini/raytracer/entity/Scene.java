package com.sakerini.raytracer.entity;

import com.sakerini.raytracer.entity.geometry.Vector3D;
import com.sakerini.raytracer.entity.lightsource.BaseLight;
import com.sakerini.raytracer.entity.lightsource.PointLight;
import com.sakerini.raytracer.entity.material.CooktorranceMaterial;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Scene {

    private List<SceneObject> sceneObjects;
    private List<BaseLight> lights;
    private MaterialDict materials = new MaterialDict();

    public Scene() {
        sceneObjects = new ArrayList<>();
        lights = new ArrayList<>();

        lights.add(new PointLight(new Vector3D(0.0f, 4.0f, -5.0f), new Vector3D(1.0f), 1.0f, 0.0f, 0.0f, 0.1f));
        sceneObjects.add(SceneObject.buildPlane(
                new Vector3D(0.0f, 0.0f, 0.0f),
                new Vector3D(0.0f, 1.0f, 0.0f),
                materials.getMaterial("diffuse_sand")));
        sceneObjects.add(SceneObject.buildPlane(
                new Vector3D(0.0f, 0.0f, -10.0f),
                new Vector3D(0.0f, 0.0f, 1.0f),
                materials.getMaterial("diffuse_sky"))); // front wall
        sceneObjects.add(SceneObject.buildSphere(
                new Vector3D(1.0f, 0.75f, -4.0f),
                0.75f,
                materials.getMaterial("reflective_green")));
    }

    public void update(float dt) {

    }
}
