package me.wavever.library;

/**
 * The TagView's Shape.
 * <p>
 * Created by wavever on 2018/12/01.
 */
public enum ShapeType {

    TRIANGLE(0),

    RECTANGLE(1),

    CIRCLE(2);

    public final int typeId;

    ShapeType(int typeId) {
        this.typeId = typeId;
    }

    public static ShapeType getType(int typeId) {
        ShapeType type;
        switch (typeId) {
            case 0:
                type = TRIANGLE;
                break;
            case 1:
                type = RECTANGLE;
                break;
            case 2:
                type = CIRCLE;
                break;
            default:
                type = TRIANGLE;
                break;
        }
        return type;
    }
}
