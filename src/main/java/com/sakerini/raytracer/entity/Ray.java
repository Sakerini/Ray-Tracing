package com.sakerini.raytracer.entity;

import com.sakerini.raytracer.geometry.Vector3D;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ray {

    private static float PIXEL_SHIFT = 0.5f;

    private Vector3D position;
    private Vector3D direction;
    private float ior; // Index of Refraction

    public Ray(Vector3D position, Vector3D direction, float ior) {
        this.position = position;
        this .direction = direction;
        this.ior = ior;
    }

    public Ray(Vector3D position, Vector3D direction) {
        this.position = position;
        this.direction = direction;
        this.ior = 1.0f;
    }


    public Ray() {
        this.position = new Vector3D(); // Will be initialized by zeros
        this.direction = new Vector3D(); // Will be initialized by zeros
        this.ior = 1.0f;
    }

    public static Ray calculateCameraRay(Camera camera, int width, int height, float aspectRatio, int x, int y) {
        float xNorm = (x - width * PIXEL_SHIFT) / width * aspectRatio;
        float yNorm = (height * PIXEL_SHIFT - y) / height;

        Vector3D forward = camera.getForwardVector();
        Vector3D up = camera.getUpVector();
        Vector3D right = camera.getRightVector();

        Vector3D imagePoint = right.scale(xNorm).add(up.scale(yNorm)).add(camera.getPosition().add(forward));
        Vector3D rayDirection = imagePoint.sub(camera.getPosition());

        return new Ray(camera.getPosition(), rayDirection);
    }
}
