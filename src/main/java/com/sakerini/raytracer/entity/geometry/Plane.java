package com.sakerini.raytracer.entity.geometry;

import com.sakerini.raytracer.entity.Ray;
import com.sakerini.raytracer.utils.Configuration;
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
        Vector3D P;
        float d, t;

        P = vertices[0].sub(ray.getPosition());
        d = normal.dot(ray.getDirection());

        if (d > 0.0f)
            return null;

        t = P.dot(normal) / d;

        if (t < Configuration.epsilon)
            return null;

        Intersection x = new Intersection();
        x.setPosition(ray.getPosition().add(ray.getDirection().scale(t)));
        x.setNormal(normal.normalize());
        x.setT(t);

        return x;
    }
}
