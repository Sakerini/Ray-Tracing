package com.sakerini.raytracer.geometry;

public class Quaternion {

    public float w;
    public float x;
    public float y;
    public float z;

    public Quaternion(float w, float x, float y, float z)
    {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Quaternion()
    {
        w = 0.0f;
        x = 0.0f;
        y = 0.0f;
        z = 0.0f;
    }

    public boolean equals(Quaternion q)
    {
        if (this.w == q.w && this.x == q.x && this.y == q.y && this.z == q.z)
            return true;
        else
            return false;
    }

    public Quaternion createFromAxisAngle(float x, float y, float z, float theta)
    {
        theta = (float) Math.toRadians(theta);

        this.w = (float) Math.cos(theta / 2.0);
        this.x = x * (float) Math.sin(theta / 2.0);
        this.y = y * (float) Math.sin(theta / 2.0);
        this.z = z * (float) Math.sin(theta / 2.0);

        return this;
    }

    public Quaternion mul(Quaternion q)
    {
        Quaternion r = new Quaternion();

        r.w = w * q.w - x * q.x - y * q.y - z * q.z;
        r.x = w * q.x + x * q.w + y * q.z - z * q.y;
        r.y = w * q.y - x * q.z + y * q.w + z * q.x;
        r.z = w * q.z + x * q.y - y * q.x + z * q.w;

        return r;
    }

    public Quaternion mul(Vector3D vector3D)
    {
        Quaternion r = new Quaternion();

        r.w = -x * vector3D.x - y * vector3D.y - z * vector3D.z;
        r.x = w * vector3D.x + y * vector3D.z - z * vector3D.y;
        r.y = w * vector3D.y + z * vector3D.x - x * vector3D.z;
        r.z = w * vector3D.z + x * vector3D.y - y * vector3D.x;

        return r;
    }

    public Quaternion conjugate()
    {
        return new Quaternion(w, -x, -y, -z);
    }

    public Quaternion normalize()
    {
        float length = length();
        return new Quaternion(w / length, x / length, y / length, z / length);
    }

    public float length()
    {
        return (float) Math.sqrt(w * w + x * x + y * y + z * z);
    }

    public Vector3D getForwardVector()
    {
        return new Vector3D(0, 0, 1).mul(this);
    }
    public Vector3D getRightVector()
    {
        return new Vector3D(1, 0, 0).mul(this);
    }
    public Vector3D getUpVector()
    {
        return new Vector3D(0, 1, 0).mul(this);
    }


}
