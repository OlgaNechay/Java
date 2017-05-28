package com.company;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;


public class Main {
    public static void main(String[] args) {
        try {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            Mat source = Imgcodecs.imread("y_1pl1.png",
                    Imgcodecs.CV_LOAD_IMAGE_COLOR);
            Mat destination = new Mat(source.rows(), source.cols(), source.type());
            //Размытие с Гауссовым фильтром
            Imgproc.GaussianBlur(source, destination, new Size(7, 7), 0);
            Imgcodecs.imwrite("Gaussian45.png", destination);
            BufferedImage image = ImageIO.read(new File("Gaussian45.png"));


            int w = image.getWidth();
            int h = image.getHeight();

            Color[][] colors = new Color[h][w];
            Color purple = new Color(210, 40, 38);
            int PURPLE = purple.getRGB();

            double max = 0;
            double[][] lum = new double[h][w];
            for (int j = 0; j < h; j++) {

                for (int k = 0; k < w; k++) {

                    colors[j][k]= new Color(image.getRGB(k,j));
                    double luminance = 0.2126 * colors[j][k].getRed() + 0.7152 * colors[j][k].getGreen() + 0.0722 * colors[j][k].getBlue();

                    lum[j][k] = luminance;
                    if(lum[j][k]>max){
                        max=lum[j][k];
                    }
                }
            }
            for (int j = 0; j < h; j++){
                for(int k = 0; k < w; k++){
                    if(lum[j][k]>110){
                        image.setRGB(k, j , PURPLE);
                    }

                }

            }
            System.out.println(max);
            File output = new File("some oth.png");
            ImageIO.write(image, "png", output);
        } catch (IOException e) {

        }
    }
}




