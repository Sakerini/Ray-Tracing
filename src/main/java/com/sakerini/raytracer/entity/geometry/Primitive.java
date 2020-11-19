package com.sakerini.raytracer.entity.geometry;

import lombok.Getter;

public abstract class Primitive {

    @Getter
    protected Vector3D[] vertices;

    protected Primitive(Vector3D[] vertices) {
        this.vertices = vertices;
    }
    protected Primitive(Vector3D position) {
        vertices = new Vector3D[1];
        vertices[0] = position;
    }

    //TODO Intersection with RAY
    public void intersect() {}

}
