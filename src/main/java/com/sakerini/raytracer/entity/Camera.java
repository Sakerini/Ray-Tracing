package com.sakerini.raytracer.entity;

import com.sakerini.raytracer.geometry.Vector3D;
import com.sakerini.raytracer.geometry.Quaternion;
import com.sakerini.raytracer.utils.InputListener;
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
            move(getForward(), speed * dt);
        } else if (InputListener.getKeyboardKeys()[InputListener.KEY_S]) {
            move(getForward().negate(), speed * dt);
        }
        if (InputListener.getKeyboardKeys()[InputListener.KEY_A]) {
            move(getRight().negate(), speed * dt);
        } else if (InputListener.getKeyboardKeys()[InputListener.KEY_D]) {
            move(getRight(), speed * dt);
        }
        if (InputListener.getKeyboardKeys()[InputListener.KEY_R]) {
            move(getUp(), speed * dt);
        } else if (InputListener.getKeyboardKeys()[InputListener.KEY_F]) {
            move(getUp().negate(), speed * dt);
        }

        // Camera rotation
        if (InputListener.getKeyboardKeys()[InputListener.KEY_RIGHT])
            rotate(getUp(), sensitivity * dt);
        if (InputListener.getKeyboardKeys()[InputListener.KEY_LEFT])
            rotate(getUp(), -sensitivity * dt);
        if (InputListener.getKeyboardKeys()[InputListener.KEY_UP])
            rotate(getRight(), -sensitivity * dt);
        if (InputListener.getKeyboardKeys()[InputListener.KEY_DOWN])
            rotate(getRight(), sensitivity * dt);
        if (InputListener.getKeyboardKeys()[InputListener.KEY_E])
            rotate(getForward(), sensitivity * dt);
        if (InputListener.getKeyboardKeys()[InputListener.KEY_Q])
            rotate(getForward(), -sensitivity * dt);
    }

    public void move(Vector3D direction, float amount) {
        position.setVector(position.add(direction.scale(amount)));
    }


    public void rotate(Vector3D axis, float theta) {
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
