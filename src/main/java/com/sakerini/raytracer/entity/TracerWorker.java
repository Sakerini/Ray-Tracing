package com.sakerini.raytracer.entity;

import com.sakerini.raytracer.entity.geometry.Intersection;
import com.sakerini.raytracer.entity.geometry.Primitive;
import com.sakerini.raytracer.entity.geometry.Vector3D;
import com.sakerini.raytracer.entity.lightsource.BaseLight;
import com.sakerini.raytracer.utils.Configuration;
import com.sakerini.raytracer.utils.graphics.Display;
import com.sakerini.raytracer.utils.graphics.Tracer;
import lombok.Getter;
import lombok.Setter;

public class TracerWorker implements Runnable {

    private static final int RECURSION_DEPTH = 0;

    @Setter
    private Display _display;
    private Tracer _tracer;

    @Getter
    private int width;
    @Getter
    private int height;
    @Getter
    private int xOffset;
    @Getter
    private int yOffset;
    @Getter
    private int id;
    @Getter
    private boolean isFinished;

    public TracerWorker(Tracer _tracer, int width, int height, int xOffset, int yOffset, int id) {
        this._tracer = _tracer;
        this.width = width;
        this.height = height;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.id = id;
        this.isFinished = true;
    }

    @Override
    public void run() {
        isFinished = false;
        if (_tracer != null && _display != null) {
            int width = Configuration.displayWidth;
            int height = Configuration.displayHeight;
            Ray cameraRay;
            float aspectRatio = (float) _display.getWidth() / (float) _display.getHeight();
            for (int y = yOffset; y < (yOffset + height); y++)
                for (int x = xOffset; x < (xOffset + width); x++) {
                    cameraRay = Ray.calculateCameraRay(_tracer.get_camera(), width, height, aspectRatio, x, y);
                    _display.drawPixelVector3d(x, y, traceColor(cameraRay, _tracer.get_scene(), RECURSION_DEPTH));
                }
        }
        isFinished = true;
    }

    public static Vector3D traceColor(Ray ray, Scene scene, int recursionDepth) {
        if (recursionDepth > Configuration.maxRecursion)
            return new Vector3D(); // Return no color

        // Init intersect data
        Intersection xInit = null, xFinal = null;
        SceneObject xObj = null;
        float tNear = Float.MAX_VALUE; // Distance to the intersect object

        // Find nearest intersection point
        for (SceneObject object : scene.getSceneObjects()) {
            for (Primitive primitive : object.get_primitives()) {
                xInit = primitive.intersect(ray);
                if (xInit != null && xInit.getT() < tNear) {
                    xFinal = xInit;
                    tNear = xFinal.getT();
                    xObj = object;
                }
            }
        }

        // If did not hit anything return background color
        if (xFinal == null)
            return new Vector3D();

        // Init color to return
        Vector3D colorResult = new Vector3D();

        // Shade surface point against lights
        for (BaseLight light: scene.getLights()) {
            //TODO ADD SHADER RESULT colorResult.setVector(colorResult.add())

            Ray shadowRay = null;

            if (xObj.getMaterial().getReflectivity() != 1.0f) {
                //TODO TRACE SHADOW FROM LIGHT SOURCES
            }
        }

        return null;
    }
}
