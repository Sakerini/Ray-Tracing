package com.sakerini.raytracer.utils.service;

import lombok.Getter;

public class TimeUtil {

    public static final long SECONDS  = 1000000L;
    public static final double SMOOTHING = 0.9;

    @Getter
    private static long currentTime = 0;
    @Getter
    private static long lastTime = getTime();
    @Getter
    private static double deltatTime = 0;


    public static void update() {
        currentTime = getTime();
        deltatTime = deltatTime * SMOOTHING + (double) (currentTime - lastTime);
        lastTime = currentTime;
    }

    public static long getTime()
    {
        return System.nanoTime() / SECONDS;
    }
}
