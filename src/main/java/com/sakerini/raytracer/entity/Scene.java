package com.sakerini.raytracer.entity;

import com.sakerini.raytracer.entity.geometry.Vector3D;
import com.sakerini.raytracer.entity.lightsource.BaseLight;
import com.sakerini.raytracer.entity.lightsource.DirectionalLight;
import com.sakerini.raytracer.entity.lightsource.PointLight;
import com.sakerini.raytracer.entity.material.CooktorranceMaterial;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Scene {

    private List<SceneObject> sceneObjects = new ArrayList<>();
    private List<BaseLight> lights = new ArrayList<>();
    private MaterialDict materials = new MaterialDict();

    public Scene() {
        secondScene();
    }

    public void firstScene() {

        lights.add(new PointLight(new Vector3D(0.0f, 20.0f, 10.0f), new Vector3D(1.0f), 25.0f, 0.0f, 0.0f, 0.1f));
        sceneObjects.add(SceneObject.buildPlane(
                new Vector3D(0.0f, 0.0f, 0.0f),
                new Vector3D(0.0f, 1.0f, 0.0f),
                materials.getMaterial("diffuse_green")));
        sceneObjects.add(SceneObject.buildPlane(
                new Vector3D(0.0f, 0.0f, -10.0f),
                new Vector3D(0.0f, 0.0f, 1.0f),
                materials.getMaterial("diffuse_sky"))); // front wall
        sceneObjects.add(SceneObject.buildSphere(
                new Vector3D(1.0f, 5f, -4.0f),
                5f,
                materials.getMaterial("skin_color")));
        sceneObjects.add(SceneObject.buildSphere(
                new Vector3D(0.0f, 6f, 0.30f),
                1f,
                materials.getMaterial("diffuse_white")));

        sceneObjects.add(SceneObject.buildSphere(
                new Vector3D(2.0f, 6f, 0.30f),
                1f,
                materials.getMaterial("diffuse_white")));

        sceneObjects.add(SceneObject.buildSphere(
                new Vector3D(2.0f, 6f, 0.85f),
                0.5f,
                materials.getMaterial("reflective_metal")));


        sceneObjects.add(SceneObject.buildSphere(
                new Vector3D(0.0f, 6f, 0.85f),
                0.5f,
                materials.getMaterial("reflective_metal")));


        sceneObjects.add(SceneObject.buildSphere(
                new Vector3D(0.0f, 6f, 0.85f),
                0.5f,
                materials.getMaterial("reflective_metal")));

        sceneObjects.add(SceneObject.buildSphere(
                new Vector3D(3.0f, 1.75f, 5f),
                1.5f,
                materials.getMaterial("reflective_purple")));

        sceneObjects.add(SceneObject.buildSphere(
                new Vector3D(-3.0f, 1.50f, 3.5f),
                1.25f,
                materials.getMaterial("black_color")));
    }

    public void secondScene() {
        lights.add(new PointLight(
                new Vector3D(0.0f, 4.0f, -5.0f),
                new Vector3D(1.0f),
                1.0f, 0.0f, 0.0f, 0.1f)
        );

        sceneObjects.add(SceneObject.buildPlane(
                new Vector3D(0.0f, 0.0f, 0.0f),
                new Vector3D(0.0f, 1.0f, 0.0f),
                materials.getMaterial("diffuse_white")
        ));

        sceneObjects.add(SceneObject.buildPlane(
                new Vector3D(0.0f, 5.0f, 0.0f),
                new Vector3D(0.0f, -1.0f, 0.0f),
                materials.getMaterial("diffuse_white")
        ));

        sceneObjects.add(SceneObject.buildPlane(
                new Vector3D(0.0f, 0.0f, -10.0f),
                new Vector3D(0.0f, 0.0f, 1.0f),
                materials.getMaterial("diffuse_white")
        ));

        sceneObjects.add(SceneObject.buildPlane(
                new Vector3D(4.0f, 0.0f, 0.0f),
                new Vector3D(-1.0f, 0.0f, 0.0f),
                materials.getMaterial("diffuse_white")
        ));

        sceneObjects.add(SceneObject.buildPlane(
                new Vector3D(-4.0f, 0.0f, 0.0f),
                new Vector3D(1.0f, 0.0f, 0.0f),
                materials.getMaterial("diffuse_white")
        ));

        sceneObjects.add(SceneObject.buildSphere(
                new Vector3D(2.0f, 0.75f, -5.0f), 0.75f, materials.getMaterial("diffuse_red")
        ));
        sceneObjects.add(SceneObject.buildSphere(
                new Vector3D(0.0f, 0.75f, -5.0f), 0.75f, materials.getMaterial("reflective_metal")
        ));
        sceneObjects.add(SceneObject.buildSphere(
                new Vector3D(-2.0f, 0.75f, -5.0f), 0.75f, materials.getMaterial("refractive_glass")
        ));
    }

    public void update(float dt) {

    }
}
