package com.sakerini.raytracer.entity;

import com.sakerini.raytracer.entity.geometry.Plane;
import com.sakerini.raytracer.entity.geometry.Primitive;
import com.sakerini.raytracer.entity.geometry.Sphere;
import com.sakerini.raytracer.entity.geometry.Vector3D;
import com.sakerini.raytracer.entity.material.BaseMaterial;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SceneObject {

    private List<Primitive> _primitives;
    private BaseMaterial material;

    public SceneObject() {
        _primitives = new ArrayList<>();
    }

    public SceneObject(List<Primitive> primitives, BaseMaterial material) {
        this._primitives = primitives;
        this.material = material;
    }

    public static SceneObject buildPlane(Vector3D position, Vector3D normal, BaseMaterial material) {
        Plane plane = new Plane(position, normal);
        SceneObject object = new SceneObject();
        object._primitives.add(plane);
        object.material = material;
        return object;
    }

    public static SceneObject buildSphere(Vector3D position, float radius, BaseMaterial material) {
        Sphere sphere = new Sphere(position, radius);
        SceneObject object = new SceneObject();
        object.material = material;
        object._primitives.add(sphere);
        return object;
    }
}