package com.sakerini.raytracer.entity.geometry;

import com.sakerini.raytracer.entity.Ray;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Plane extends Primitive {

    private Vector3D normal;

    public Plane(Vector3D position, Vector3D normal) {
        super(position);
        this.normal = normal;
    }

    @Override
    public Intersection intersect(Ray ray) {
        //TODO
        return null;
    }
}
