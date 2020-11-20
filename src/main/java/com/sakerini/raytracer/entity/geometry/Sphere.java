package com.sakerini.raytracer.entity.geometry;

import com.sakerini.raytracer.entity.Ray;
import com.sakerini.raytracer.utils.Configuration;
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
        Vector3D sp;
        float t, b, d;

        sp = vertices[0].sub(ray.getPosition());
        b = sp.dot(ray.getDirection());
        d = b * b - sp.dot(sp) + radius * radius;

        if (d < 0.0f) {
            return null;
        }

        d = (float) Math.sqrt(d);
        t = (t = b - d) > Configuration.epsilon ? t : ((t = b + d) > Configuration.epsilon ? t : -1.0f);

        if (t == -1.0f)
            return null;

        Intersection x = new Intersection();
        x.setPosition(ray.getPosition().add(ray.getDirection().scale(t)));
        x.setNormal(x.getPosition().sub(vertices[0]).divide(radius));
        x.setT(t);

        return x;
    }
}
