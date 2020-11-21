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

    public Scene() {
        sceneObjects = new ArrayList<>();
        lights = new ArrayList<>();

        CooktorranceMaterial m_diffuse_sky = new CooktorranceMaterial(
                new Vector3D(0.01f),
                new Vector3D(0.0f, 0.5f, 1.0f),
                new Vector3D(1.0f),
                0.0f, 0, 0, 0.375f, 0.5f, 0.9f);
        CooktorranceMaterial m_diffuse_sand = new CooktorranceMaterial(
                new Vector3D(0.01f),
                new Vector3D(1.0f, 1.0f, 0.5f),
                new Vector3D(1.0f),
                0.0f, 0, 0, 0.375f, 0.5f, 0.9f);

        CooktorranceMaterial m_reflective_green = new CooktorranceMaterial(
                new Vector3D(0.01f),
                new Vector3D(0.0f, 1.0f, 0.0f),
                new Vector3D(1.0f),
                0.50f, 0, 0, 0.05f, 1.0f, 0.5f);

        lights.add(new PointLight(new Vector3D(0.0f, 4.0f, -5.0f), new Vector3D(1.0f), 1.0f, 0.0f, 0.0f, 0.1f));
        sceneObjects.add(SceneObject.buildPlane(new Vector3D(0.0f, 0.0f, 0.0f), new Vector3D(0.0f, 1.0f, 0.0f), m_diffuse_sand));
        sceneObjects.add(SceneObject.buildPlane(new Vector3D(0.0f, 0.0f, -10.0f), new Vector3D(0.0f, 0.0f, 1.0f), m_diffuse_sky)); // front wall
        sceneObjects.add(SceneObject.buildSphere(new Vector3D(1.0f, 0.75f, -4.0f), 0.75f, m_reflective_green));
    }

    public void update(float dt) {

    }
}
