package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public enum Shape {

    LINE(Color.GRAY),
    ANGLE(Color.darkGray),
    THREE_LINE(Color.YELLOW);

    Color color;

    Shape(Color color) {
        this.color = color;
    }

    public Color getClolor() {
        return color;
    }


    public static class ImageFactory {


        public static BufferedImage getImage(Shape shape) {
            switch (shape) {
                case LINE:
                    try {
                        return ImageIO.read(new File("resources/line.png"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                case ANGLE:
                    try {
                        return ImageIO.read(new File("resources/angle.png"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                case THREE_LINE:
                    try {
                        return ImageIO.read(new File("resources/three_line.png"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                default:
                    return null;
            }
        }
    }
}
