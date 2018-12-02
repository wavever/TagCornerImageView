package me.wavever.library;

public enum ShapeGravity {
    LEFT_TOP(0),
    RIGHT_TOP(1),
    RIGHT_BOTTOM(2),
    LEFT_BOTTOM(3);

    public final int gravityId;

    ShapeGravity(int gravityId) {
        this.gravityId = gravityId;
    }

    public static ShapeGravity getGravity(int gravityId) {
        ShapeGravity gravity;
        switch (gravityId) {
            case 0:
                gravity = LEFT_TOP;
                break;
            case 1:
                gravity = RIGHT_TOP;
                break;
            case 2:
                gravity = RIGHT_BOTTOM;
                break;
            default:
                gravity = LEFT_BOTTOM;
                break;
        }
        return gravity;
    }
}
