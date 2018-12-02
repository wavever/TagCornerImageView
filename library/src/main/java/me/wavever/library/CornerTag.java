package me.wavever.library;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class CornerTag {

    private boolean mEnable;
    private boolean mNeedClear;

    private View mHostView;

    private ShapeType mType;

    private int mBackgroundColor;
    private int mColor;
    private int[] mGradientColor;

    private String mText;
    private int mTextSize;

    private Bitmap mIcon;

    private ShapeGravity mGravity;

    private Paint mTagPaint;
    private Paint mTextPaint;
    private Path mPath;

    public CornerTag(View view) {
        mHostView = view;
        mType = ShapeType.RECTANGLE;
        mBackgroundColor = Color.RED;
        mColor = Color.WHITE;
        mGradientColor = new int[]{-1, -1, -1};
        mGravity = ShapeGravity.LEFT_TOP;
        mPath = new Path();
        initPaint();
    }

    private void initPaint() {
        mTagPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTagPaint.setColor(mBackgroundColor);
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mColor);
    }

    public void setEnable(boolean enable) {
        mEnable = enable;
        invalidate();
    }

    public void draw(Canvas canvas) {
        if (!mEnable) return;
        if (mNeedClear) {
            mNeedClear = false;
            return;
        }
        switch (mType) {
            case TRIANGLE:
                drawTriangle(canvas);
                break;
            case RECTANGLE:
                drawRectangle(canvas);
                break;
            case CIRCLE:
                drawCircle(canvas);
                break;
            default:
                break;
        }
    }

    private void drawTriangle(Canvas canvas) {
    }

    private void drawRectangle(Canvas canvas) {

    }

    private void drawCircle(Canvas canvas) {
    }

    public void clear() {
        mNeedClear = true;
        invalidate();
    }

    private void invalidate() {
        assert mHostView != null;
        mHostView.invalidate();
    }

    public static class Builder {

        private CornerTag mCornerTag;

        public Builder(View view) {
            mCornerTag = new CornerTag(view);
        }

        public Builder type(ShapeType type) {
            mCornerTag.mType = type;
            return this;
        }

        public Builder backgroundColor(int color) {
            mCornerTag.mBackgroundColor = color;
            return this;
        }

        public Builder color(int color) {
            mCornerTag.mColor = color;
            return this;
        }

        public Builder gravity(ShapeGravity gravity) {
            mCornerTag.mGravity = gravity;
            return this;
        }

        public Builder gradientColors(int... colors) {
            if (colors == null) return this;
            int size = colors.length;
            for (int i = 0; i < size; i++) {
                if (i > 2) break;
                mCornerTag.mGradientColor[i] = colors[i];
            }
            return this;
        }

        public CornerTag build() {
            return mCornerTag;
        }
    }
}
