package com.sakerini.raytracer.entity.geometry;

public class Vector3D {

    public float x;
    public float y;
    public float z;

    public Vector3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D(float value) {
        this.x = value;
        this.y = value;
        this.z = value;
    }

    public Vector3D() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }

    public boolean equals(Vector3D vector3D)
    {
        if (x == vector3D.x && y == vector3D.y && z == vector3D.z)
            return true;
        else
            return false;
    }

    public Vector3D setVector(Vector3D vector3D) {
        x = vector3D.x;
        y = vector3D.y;
        z = vector3D.z;
        return this;
    }

    public Vector3D add(Vector3D vector3D)
    {
        return new Vector3D(x + vector3D.x, y + vector3D.y, z + vector3D.z);
    }
    public Vector3D add(float v)
    {
        return new Vector3D(x + v, y + v, z + v);
    }
    public Vector3D sub(Vector3D vector3D)
    {
        return new Vector3D(x - vector3D.x, y - vector3D.y, z - vector3D.z);
    }
    public Vector3D sub(float v)
    {
        return new Vector3D(x - v, y - v, z - v);
    }
    public Vector3D scale(Vector3D vector3D)
    {
        return new Vector3D(x * vector3D.x, y * vector3D.y, z * vector3D.z);
    }
    public Vector3D scale(float v)
    {
        return new Vector3D(x * v, y * v, z * v);
    }
    public Vector3D divide(Vector3D vector3D)
    {
        return new Vector3D(x / vector3D.x, y / vector3D.y, z / vector3D.z);
    }
    public Vector3D divide(float v)
    {
        return new Vector3D(x / v, y / v, z / v);
    }
    public float dot(Vector3D vector3D)
    {
        return x * vector3D.x + y * vector3D.y + z * vector3D.z;
    }

    public float length()
    {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3D normalize()
    {
        float length = length();
        return new Vector3D(x / length, y / length, z / length);
    }

    public Vector3D negate()
    {
        return new Vector3D(-x, -y, -z);
    }
    public Vector3D reflect(Vector3D N)
    {
        return this.sub(N.scale(N.dot(this)).scale(2.0f));
    }

    public Vector3D refract(Vector3D N, float n, float NdotI, float cos_t)
    {
        cos_t = (float) Math.sqrt(1.0 - cos_t);

        if (cos_t < 1.0f)
            return scale(n).add(N.scale(n * NdotI - cos_t));
        else
            return reflect(N);
    }

    public Vector3D mul(Quaternion q)
    {
        Quaternion q_inv = q.conjugate();

        Quaternion w = q.mul(this).mul(q_inv);

        return new Vector3D(w.x, w.y, w.z);
    }

    @Override
    public String toString() {
        return "Point3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

}
