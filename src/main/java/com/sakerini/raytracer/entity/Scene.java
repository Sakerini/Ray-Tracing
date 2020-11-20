package com.sakerini.raytracer.entity;

import com.sakerini.raytracer.entity.lightsource.BaseLight;
import lombok.Getter;

import java.util.List;

@Getter
public class Scene {

    private List<SceneObject> sceneObjects;
    private List<BaseLight> lights;

    public Scene() {
        //TODO IN CONSTRUCTOR SHOULD BE CONSTRUCTED SCENE INSTANCED OBJECTS
    }
}
