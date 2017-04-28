package com.company;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Main {

    //BufferedImage image;
    public static void main(String[] args) {
        try {
            BufferedImage image = ImageIO.read(new File("y_int2_1_2_.png"));

// Store the color objects in an array
            int w = image.getWidth();
            int h = image.getHeight();


            Color[][] colors = new Color[h][w];
            int i = 0;
            Color purple = new Color(42, 210, 3);
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


              //       System.out.print(lum[j][k]+" ");


                }
             //   System.out.println();
            }
            for (int j = 0; j < h; j++){
                for(int k = 0; k < w; k++){
                    if(lum[j][k]>(max-80)){
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
