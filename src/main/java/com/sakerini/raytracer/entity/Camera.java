package com.sakerini.raytracer.entity;

import com.sakerini.raytracer.geometry.Vector3D;
import com.sakerini.raytracer.geometry.Quaternion;
import lombok.Getter;

public class Camera {

    @Getter
    private Vector3D position;
    @Getter
    private Quaternion rotateQuaternion;
    private float speed;
    private float sensitivity;

    public Camera(Vector3D pos, float speed, float sensitivity)
    {
        position = pos;
        rotateQuaternion = new Quaternion(0, 0, 0, 0);
        this.speed = speed;
        this.sensitivity = sensitivity;
    }

    public void update() {
        //TODO UPDATE CAMERA WITH INPUT LISTENERS
    }

    public void move(Vector3D direction, float amount)
    {
        position.setVector(position.add(direction.scale(amount)));
    }


    public void rotate(Vector3D axis, float theta)
    {
        Quaternion rotation = new Quaternion().createFromAxisAngle(axis.x, axis.y, axis.z, theta);
        rotateQuaternion = rotation.mul(rotateQuaternion).normalize();
    }

    public Vector3D getUp() {
        return rotateQuaternion.getUpVector();
    }
    public Vector3D getRight() {
        return rotateQuaternion.getRightVector();
    }
    public Vector3D getForward() {
        return rotateQuaternion.getForwardVector();
    }
}
