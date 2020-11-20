package com.sakerini.raytracer.utils.shader;

import com.sakerini.raytracer.entity.geometry.Vector3D;

public class Shader {
    public static Vector3D smoothStepVector3d(Vector3D vector, float edge1, float edge2) {
        float x = smoothStep(vector.x, edge1, edge2);
        float y = smoothStep(vector.y, edge1, edge2);
        float z = smoothStep(vector.z, edge1, edge2);

        return new Vector3D(x, y, z);
    }

    public static float smoothStep(float x, float edge1, float edge2) {
        x = clamp((x - edge1) / (edge2 - edge1), 0.0f, 1.0f);
        return x;
    }

    public static float clamp(float x, float lowerLimit, float upperLimit) {
        return Math.max(lowerLimit, Math.min(x, upperLimit));
    }

    public static Vector3D clampVector(Vector3D vector3D, float lowerLimit, float upperLimit) {
        float x = clamp(vector3D.x, lowerLimit, upperLimit);
        float y = clamp(vector3D.y, lowerLimit, upperLimit);
        float z = clamp(vector3D.z, lowerLimit, upperLimit);

        return new Vector3D(x, y, z);
    }
}
