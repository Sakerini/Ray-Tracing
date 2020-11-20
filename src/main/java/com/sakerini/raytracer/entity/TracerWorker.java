package com.sakerini.raytracer.entity;

import com.sakerini.raytracer.entity.geometry.Intersection;
import com.sakerini.raytracer.entity.geometry.Primitive;
import com.sakerini.raytracer.entity.geometry.Vector3D;
import com.sakerini.raytracer.entity.lightsource.BaseLight;
import com.sakerini.raytracer.entity.lightsource.DirectionalLight;
import com.sakerini.raytracer.utils.Configuration;
import com.sakerini.raytracer.utils.graphics.Display;
import com.sakerini.raytracer.utils.graphics.Tracer;
import com.sakerini.raytracer.utils.shader.Shader;
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
            for (int y = yOffset; y < (yOffset + this.height); y++)
                for (int x = xOffset; x < (xOffset + this.width); x++) {
                    cameraRay = Ray.calculateCameraRay(_tracer.get_camera(), width, height, aspectRatio, x, y);
                    _display.drawPixelVector3d(x, y, traceColor(cameraRay, _tracer.get_scene(), RECURSION_DEPTH));
                }
        }
        isFinished = true;
    }

    public static Vector3D traceColor(Ray ray, Scene scene, int recursionDepth) {
        if (recursionDepth > Configuration.maxRecursion)
            return new Vector3D(0f); // Return no color

        // Init intersect data
        Intersection xInit, xFinal = null;
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
            colorResult.setVector(colorResult.add(Shader.shade(ray, xFinal, light, xObj.getMaterial())));

            Ray shadowRay = null;

            if (xObj.getMaterial().getReflectivity() != 1.0f) {
                Vector3D lVector = light.getPosition().sub(xFinal.getPosition());
                float lLength = lVector.length();
                if (light.getLightType() == Configuration.lightType.DIRECTIONAL) {
                    shadowRay = new Ray(xFinal.getPosition(), ((DirectionalLight) light).getDirection().negate());
                    lLength = Float.MAX_VALUE;
                } else if (light.getLightType() == Configuration.lightType.POINT) {
                    shadowRay = new Ray(xFinal.getPosition(), lVector);
                }

                if (shadowRay != null) {
                    colorResult.setVector(colorResult.scale(Math.min(traceShadow(shadowRay, scene, xObj, lLength)
                    + xObj.getMaterial().getReflectivity(), 1.0f)));
                }
            }
        }

        if (xObj.getMaterial().getReflectivity() > 0.0f) {
            Ray reflectedRay = new Ray(xFinal.getPosition(), ray.getDirection().reflect(xFinal.getNormal()));
            colorResult.setVector(colorResult.add(traceColor(reflectedRay, scene, recursionDepth + 1)
                                    .scale(xObj.getMaterial().getReflectivity())));
        }

        if (xObj.getMaterial().getRefractivity() > 0.0f) {
            Ray refractedRay;
            Vector3D N = xFinal.getNormal();
            float NdotI = ray.getDirection().dot(N), ior, n1, n2, cosT;

            if (NdotI > 0.0) {
                n1 = ray.getIor();
                n2 = xObj.getMaterial().getIor();
                N = N.negate();
            } else {
                n1 = xObj.getMaterial().getIor();
                n2 = ray.getIor();
                NdotI = -NdotI;
            }

            ior = n2 / n1;
            cosT = ior * ior * (1.0f - NdotI * NdotI);

            refractedRay = new Ray(xFinal.getPosition(), ray.getDirection().refract(N, ior, NdotI, cosT), 1.0f);
            colorResult.setVector(colorResult.add(traceColor(refractedRay, scene, recursionDepth + 1)
                                        .scale(xObj.getMaterial().getRefractivity())));
        }

        colorResult.setVector(colorResult.add(xObj.getMaterial().getAmbientColor()));

        return Shader.clampVector(colorResult, 0.0f, 1.0f);
    }

    private static float traceShadow(Ray ray, Scene scene, SceneObject object, float lLength) {

        Intersection xInit = null;
        Intersection xFinal = null;
        SceneObject xObj = null;
        float tNear = Float.MAX_VALUE;
        float weight = 1.0f;

        for (SceneObject obj: scene.getSceneObjects()) {
            if (obj.equals(object)) {
                continue;
            }

            for (Primitive primitive: obj.get_primitives()) {
                xInit = primitive.intersect(ray);
                if (xInit != null && xInit.getT() < tNear && xInit.getT() < lLength) {
                    xFinal = xInit;
                    tNear = xFinal.getT();
                    xObj = obj;
                }
            }
        }

        if (xFinal == null) {
            return 1.0f;
        }

        if (xObj.getMaterial().getReflectivity() > 0.0f) {
            weight -= xObj.getMaterial().getReflectivity();
        }

        if (xObj.getMaterial().getRefractivity() > 0.0f) {
            weight *= xObj.getMaterial().getRefractivity();
        }

        return weight;
    }
}
