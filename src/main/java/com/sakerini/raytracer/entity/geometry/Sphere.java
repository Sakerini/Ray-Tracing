package com.sakerini.raytracer.entity.geometry;

import com.sakerini.raytracer.entity.Ray;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sphere extends Primitive {

    private float radius;

    public Sphere(Vector3D position, float radius) {
        super(position);
        this.radius = radius;
    }

    @Override
    public Intersection intersect(Ray ray) {
        //TODO
        return null;
    }
}
