package util;

import java.awt.*;

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
}
