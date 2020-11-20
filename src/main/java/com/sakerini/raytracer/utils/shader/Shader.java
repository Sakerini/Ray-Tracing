package com.sakerini.raytracer.utils.shader;

import com.sakerini.raytracer.entity.Ray;
import com.sakerini.raytracer.entity.geometry.Intersection;
import com.sakerini.raytracer.entity.geometry.Vector3D;
import com.sakerini.raytracer.entity.lightsource.BaseLight;
import com.sakerini.raytracer.entity.lightsource.DirectionalLight;
import com.sakerini.raytracer.entity.lightsource.PointLight;
import com.sakerini.raytracer.entity.material.BaseMaterial;
import com.sakerini.raytracer.entity.material.CooktorranceMaterial;
import com.sakerini.raytracer.entity.material.PhongMaterial;
import com.sakerini.raytracer.utils.Configuration;

public class Shader {

    public static Vector3D shade(Ray ray, Intersection x, BaseLight light, BaseMaterial material) {
        Vector3D C, L, V, H;
        Vector3D N = x.getNormal();
        float NdotL, NdotV, NdotH, VdotH, lambertian, specular, roughness, lLength, A = 1.0f;

        if (light.getLightType() == Configuration.lightType.DIRECTIONAL) {
            L = ((DirectionalLight) light).getDirection().negate().normalize();
            V = ((DirectionalLight) light).getDirection().negate();
            H = V.add(L).normalize();
        } else if (light.getLightType() == Configuration.lightType.POINT) {
            L = ((PointLight) light).getPosition().sub(x.getPosition());
            lLength = L.length();
            L = L.normalize();
            V = ray.getDirection().negate();
            H = V.add(L).normalize();
            A = ((PointLight) light).getConstant() + ((PointLight) light).getLinear() +
                    + ((PointLight) light).getExponent() * lLength * lLength + Configuration.epsilon;
        } else {
            return new Vector3D();
        }

        // Calculate the dot products for shading
        NdotL = Math.min(N.dot(L), 1.0f);
        NdotV = Math.min(N.dot(V), 1.0f);
        NdotH = Math.min(N.dot(H), 1.0f);
        VdotH = Math.min(V.dot(H), 1.0f);

        if (material.getModel() == Configuration.materialModel.PHONG) {
            C = new Vector3D();

            //Calculate the lambertian term
            lambertian = Math.min(NdotL, 1.0f);

            //Calculate the specular term
            if (((PhongMaterial) material).getShininess() > 0.0f) {
                specular = (float) Math.pow(NdotH, ((PhongMaterial) material).getShininess()); // Specular
            } else {
                specular = 0.0f;
            }

            // ADD ALL  TERMS TOGETHER TO C
            C.setVector(C.add(light.getColor()
                                    .scale(((PhongMaterial) material).getDiffuseColor())
                                    .scale(lambertian)
                                    .scale(light.getIntensity())));
            C.setVector(C.add(material.getSpecularColor().scale(specular).scale(light.getIntensity())));
        } else if (material.getModel() == Configuration.materialModel.COOKTORRANCE) {
            C = new Vector3D();
            //Return Null if the surface normal and light direction are facing opposite directions
            if (NdotL < Configuration.epsilon) {
                return new Vector3D();
            }

            // Get Surface Props
            float R = ((CooktorranceMaterial) material).getRoughness();
            float F = ((CooktorranceMaterial) material).getFresnel();
            float K = ((CooktorranceMaterial) material).getDensity();

            //Evaluate the geom terms
            float geoNumerator = 2.0f * NdotH;
            float geoDenomerator = VdotH;
            float geoA = (geoNumerator * NdotV) / geoDenomerator;
            float geoB = (geoNumerator * NdotL) / geoDenomerator;
            lambertian = Math.min(1.0f, Math.min(geoA, geoB));

            //Evaluate roughness
            float roughnessA = 1.0f / (4.0f * R * R * (float) Math.pow(NdotH, 4));
            float roughnessB = NdotH * NdotH - 1.0f;
            float roughnessC = R * R * NdotH * NdotH;
            roughness = roughnessA * (float) Math.exp(roughnessB / roughnessC);

            // Evaluate the fresnel term
            specular = (float) Math.pow(1.0f - VdotH, 5);
            specular *= 1.0f - F;
            specular += F;

            // Put all the terms together
            float rsNumerator = lambertian * roughness * specular;
            float rsDenominator = Math.max(NdotV * NdotL, Configuration.epsilon);
            float rs = rsNumerator / rsDenominator;

            // Add all the terms to C
            Vector3D resultA = light.getColor().scale(NdotL).scale(light.getIntensity());
            Vector3D resultB = material.getDiffuseColor().scale(K).add(material.getSpecularColor().scale(rs * (1.0f - K)));
            C.setVector(resultA.scale(resultB));
        } else {
            return new Vector3D();
        }

        return C.divide(A);
    }

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
