package com.sakerini.raytracer.utils.graphics;

import com.sakerini.raytracer.entity.geometry.Vector3D;
import com.sakerini.raytracer.utils.shader.Shader;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class Display extends Canvas {

    private final int DISPLAY_FILL_COLOR = 0x000000;

    @Setter
    private String title;
    private int width;
    private int height;
    private int scale;
    private int[] pixels;
    private BufferedImage _image;
    private Dimension _dimension;
    private JFrame _jFrame;
    private BufferStrategy _bufferStrategy;

    public Display(int width, int height, int scale, String title) {
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.title = title;

        _dimension = new Dimension(width * scale, height * scale);
        setPreferredSize(_dimension);

        _image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) _image.getRaster().getDataBuffer()).getData();
        clear();

        _jFrame = new JFrame();
        _jFrame.setResizable(false);
        _jFrame.setTitle(this.title);
        _jFrame.add(this);
        _jFrame.pack();
        _jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _jFrame.setLocationRelativeTo(null); // This centres the Location of the Window when its opened
        _jFrame.setVisible(true);

        createBufferStrategy(3);
        _bufferStrategy = getBufferStrategy();
    }

    public void drawPixelVector3d(int x, int y, Vector3D color) {

        if (x < 0 || x > width || y < 0 || y > height) {
            return;
        }

        color = Shader.smoothStepVector3d(Shader.clampVector(color, 0.0f, 1.0f), 0.0f, 1.0f);

        long red = (long) (color.x * 255.0f);
        long green = (long) (color.y * 255.0f);
        long blue = (long) (color.z * 255.0f);
        long hex_value = ((red << 16) | (green << 8) | blue);

        int index = x + y * width;
        pixels[index] = (int) hex_value;
    }


    public void render() {
        Graphics graphics = _bufferStrategy.getDrawGraphics();
        graphics.drawImage(_image, 0, 0, width * scale, height * scale, null);
        graphics.dispose();
        _bufferStrategy.show();
    }

    public void clear() {
        Arrays.fill(pixels, DISPLAY_FILL_COLOR);
    }
}