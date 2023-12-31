package org.example;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws AWTException {
        Robot robot = new Robot();
        Rectangle screenRect = new Rectangle(0, 0, 1920, 1080); // Adjust screen dimensions

        while (true) {
            BufferedImage screenshot = robot.createScreenCapture(screenRect);
            boolean isWhiteScreen = isWhiteScreen(screenshot);
            boolean isBlackScreen = isBlackScreen(screenshot);
            if (isWhiteScreen) {
                System.out.println("White screen detected!");
                // TODO: Add code to launch "原神" program
                String filePath = "E:\\Genshin Impact\\Genshin Impact Game\\YuanShen.exe"; // 指定程序的路径
                try {
                    File file = new File(filePath);
                    Desktop.getDesktop().open(file);
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (isBlackScreen) {
                System.out.println("Black screen detected!");
                // TODO: Add code to launch "崩坏:星穹铁道" program
                String filePath = "E:\\Star Rail\\Game\\StarRail.exe"; // 指定程序的路径
                try {
                    File file = new File(filePath);
                    Desktop.getDesktop().open(file);
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(1000); // Adjust the delay time as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean isWhiteScreen(BufferedImage image) {
        int whiteThreshold = 220; // Adjust threshold as needed

        for (int y = 200; y < image.getHeight()-200; y++) {
            for (int x = 0; x < image.getWidth()-200; x++) {
                Color pixelColor = new Color(image.getRGB(x, y));
                int avgColor = (pixelColor.getRed() + pixelColor.getGreen() + pixelColor.getBlue()) / 3;

                if (avgColor < whiteThreshold) {
                    return false; // Not a white screen
                }
            }
        }

        return true; // White screen detected
    }

    private static boolean isBlackScreen(BufferedImage image) {
        int blackThreshold = 35; // 调整阈值，根据实际情况

        for (int y = 200; y < image.getHeight()-200; y++) {
            for (int x = 0; x < image.getWidth()-200; x++) {
                Color pixelColor = new Color(image.getRGB(x, y));
                int avgColor = (pixelColor.getRed() + pixelColor.getGreen() + pixelColor.getBlue()) / 3;

                if (avgColor > blackThreshold) {
                    return false; // Not a black screen
                }
            }
        }

        return true; // Black screen detected
    }

}
