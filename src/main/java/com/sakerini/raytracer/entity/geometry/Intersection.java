package com.sakerini.raytracer.entity.geometry;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Intersection {

    private Vector3D position;
    private Vector3D normal;
    private float t;

    public Intersection(Vector3D position, Vector3D normal, float t) {
        this.position = position;
        this.normal = normal;
        this.t = t;
    }

    public Intersection() {
        this.position = new Vector3D(); // Initializes vector with zeros
        this.normal = new Vector3D(); // Initializes vector with zeros
        this.t = 0.0f;
    }
}
