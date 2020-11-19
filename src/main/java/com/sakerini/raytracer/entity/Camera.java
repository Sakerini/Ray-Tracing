package com.sakerini.raytracer.entity;

import com.sakerini.raytracer.entity.geometry.Vector3D;
import com.sakerini.raytracer.entity.geometry.Quaternion;
import com.sakerini.raytracer.utils.service.InputListener;
import lombok.Getter;

public class Camera {

    @Getter
    private Vector3D position;
    @Getter
    private Quaternion rotateQuaternion;
    private float speed;
    private float sensitivity;

    public Camera(Vector3D pos, float speed, float sensitivity) {
        position = pos;
        rotateQuaternion = new Quaternion(0, 0, 0, 0);
        this.speed = speed;
        this.sensitivity = sensitivity;
    }

    public void update(float dt) {
        // Camera movement
        if (InputListener.getKeyboardKeys()[InputListener.KEY_W]) {
            move(getForwardVector(), speed * dt);
        } else if (InputListener.getKeyboardKeys()[InputListener.KEY_S]) {
            move(getForwardVector().negate(), speed * dt);
        }
        if (InputListener.getKeyboardKeys()[InputListener.KEY_A]) {
            move(getRightVector().negate(), speed * dt);
        } else if (InputListener.getKeyboardKeys()[InputListener.KEY_D]) {
            move(getRightVector(), speed * dt);
        }
        if (InputListener.getKeyboardKeys()[InputListener.KEY_R]) {
            move(getUpVector(), speed * dt);
        } else if (InputListener.getKeyboardKeys()[InputListener.KEY_F]) {
            move(getUpVector().negate(), speed * dt);
        }

        // Camera rotation
        if (InputListener.getKeyboardKeys()[InputListener.KEY_RIGHT])
            rotate(getUpVector(), sensitivity * dt);
        if (InputListener.getKeyboardKeys()[InputListener.KEY_LEFT])
            rotate(getUpVector(), -sensitivity * dt);
        if (InputListener.getKeyboardKeys()[InputListener.KEY_UP])
            rotate(getRightVector(), -sensitivity * dt);
        if (InputListener.getKeyboardKeys()[InputListener.KEY_DOWN])
            rotate(getRightVector(), sensitivity * dt);
        if (InputListener.getKeyboardKeys()[InputListener.KEY_E])
            rotate(getForwardVector(), sensitivity * dt);
        if (InputListener.getKeyboardKeys()[InputListener.KEY_Q])
            rotate(getForwardVector(), -sensitivity * dt);
    }

    public void move(Vector3D direction, float amount) {
        position.setVector(position.add(direction.scale(amount)));
    }


    public void rotate(Vector3D axis, float theta) {
        Quaternion rotation = new Quaternion().createFromAxisAngle(axis.x, axis.y, axis.z, theta);
        rotateQuaternion = rotation.mul(rotateQuaternion).normalize();
    }

    public Vector3D getUpVector() {
        return rotateQuaternion.getUpVector();
    }

    public Vector3D getRightVector() {
        return rotateQuaternion.getRightVector();
    }

    public Vector3D getForwardVector() {
        return rotateQuaternion.getForwardVector();
    }
}
