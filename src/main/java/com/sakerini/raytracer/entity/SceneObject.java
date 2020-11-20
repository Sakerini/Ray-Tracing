package com.sakerini.raytracer.entity;

import com.sakerini.raytracer.entity.geometry.Primitive;
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
}