package me.wavever.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import me.waveverht.library.R;

/**
 * Created by wavever on 2016/4/19.
 */
public class TagCornerImageView extends ImageView {

    private static final int DEFAULT_TEXT_SIZE = 18;
    private static final int DEFAULT_BG_ALPHA = 255;

    private Paint mTextPaint;
    private Paint mTagPaint;

    private Paint.FontMetrics mFontMetrics;

    private Rect mTextBound;

    private Path mPath;

    private int mTagBgColor;
    private int mTagBgAlpha;

    private String mTagText;
    private int mTagTextColor;
    private int mTagTextSize;
    private int mTagGravity;
    private int mTagType;

    private Bitmap mTagIcon;
    private boolean mTagIconTurn;

    private int mImgWidth;
    private int mImgHeight;


    private enum Type {
        TRIANGLE,
        RECT,
        ROUND_RECT
    }

    private enum Gravity {
        LEFT_TOP,
        RIGHT_TOP,
        RIGHT_BOTTOM,
        LEFT_BOTTOM
    }

    public TagCornerImageView(Context context) {
        this(context, null);
    }

    public TagCornerImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagCornerImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.TagCornerImageView, defStyleAttr, 0);
        mTagBgColor = a.getColor(R.styleable.TagCornerImageView_tag_background_color, Color.BLUE);
        mTagBgAlpha = a.getInt(R.styleable.TagCornerImageView_tag_background_alpha, DEFAULT_BG_ALPHA);
        if (mTagBgAlpha != DEFAULT_BG_ALPHA) {
            setTagBgAlpha(mTagBgAlpha);
        }
        mTagText = a.getString(R.styleable.TagCornerImageView_tag_text);
        mTagTextColor = a.getColor(R.styleable.TagCornerImageView_tag_text_color, Color.WHITE);
        mTagTextSize = a.getDimensionPixelSize(R.styleable.TagCornerImageView_tag_text_size,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TEXT_SIZE, getResources().getDisplayMetrics()));
        mTagGravity = a.getInt(R.styleable.TagCornerImageView_tag_gravity, 0);
        mTagType = a.getInt(R.styleable.TagCornerImageView_tag_type, 0);
        mTagIcon = BitmapFactory.decodeResource(getResources(), a.getResourceId(R.styleable.TagCornerImageView_tag_icon, 0));
        mTagIconTurn = a.getBoolean(R.styleable.TagCornerImageView_tag_icon_turn, true);

        a.recycle();
        init();
    }

    private void init() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextBound = new Rect();
        mTextPaint.setColor(mTagTextColor);
        mTextPaint.setTextSize(mTagTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mFontMetrics = mTextPaint.getFontMetrics();
        if (mTagText != null) {
            mTextPaint.getTextBounds(mTagText, 0, mTagText.length(), mTextBound);
        }

        mTagPaint = new Paint();
        mTagPaint.setStyle(Paint.Style.FILL);
        mTagPaint.setColor(mTagBgColor);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mTagType == Type.TRIANGLE.ordinal()) {
            drawTagTriangle(canvas);
        } else if (mTagType == Type.RECT.ordinal()) {
            drawTagRect(canvas);
        } else if (mTagType == Type.ROUND_RECT.ordinal()) {
            drawTagRoundRect(canvas);
        } else {
            drawTagRect(canvas);
        }
    }

    private void drawTagTriangle(Canvas canvas) {
        int width = getMeasuredWidth() / 4;
        if (mTagGravity == Gravity.RIGHT_TOP.ordinal()) {
            drawTagTriangleRightTop(canvas, width);
        } else if (mTagGravity == Gravity.RIGHT_BOTTOM.ordinal()) {
            drawTagTriangleRightBottom(canvas,width);
        } else if (mTagGravity == Gravity.LEFT_BOTTOM.ordinal()) {
           drawTagTriangleLeftBottom(canvas,width);
        } else {
            drawTagTriangleLeftTop(canvas, width);
        }
    }

    private void drawTagTriangleLeftTop(Canvas canvas,int width){
        int iconWidth = 0;
        int iconHeight = 0;
        canvas.save();
        mPath.lineTo(width, 0);
        mPath.lineTo(0, width);
        mPath.lineTo(0, 0);
        mPath.close();
        canvas.drawPath(mPath, mTagPaint);
        canvas.restore();

        canvas.save();
        if (mTagIcon != null) {
            canvas.drawBitmap(mTagIcon, iconWidth, iconHeight, null);
        }
        canvas.restore();
    }

    private void drawTagTriangleRightTop(Canvas canvas,int width){
        int iconWidth = getMeasuredWidth() - width / 2;
        int iconHeight = 0;
        canvas.save();
        mPath.moveTo(getMeasuredWidth() - width, 0);
        mPath.lineTo(getMeasuredWidth(), width);
        mPath.lineTo(getMeasuredWidth(), 0);
        canvas.drawPath(mPath,mTagPaint);
        canvas.restore();
        canvas.save();


        if(mTagIcon != null){
            canvas.drawBitmap(mTagIcon,iconWidth,iconHeight,null);
        }
        canvas.restore();
    }

    private void drawTagTriangleRightBottom(Canvas canvas,int width){
        int iconWidth = getMeasuredWidth() - width / 2;
        int iconHeight = getMeasuredHeight() - width / 2;
        canvas.save();
        mPath.moveTo(getMeasuredWidth(), getMeasuredHeight() - width);
        mPath.lineTo(getMeasuredWidth() - width, getMeasuredHeight());
        mPath.lineTo(getMeasuredWidth(), getMeasuredHeight());
        canvas.drawPath(mPath, mTagPaint);
        canvas.restore();

        canvas.save();
        if (mTagIcon != null) {
            canvas.drawBitmap(mTagIcon, iconWidth, iconHeight, null);
        }
        canvas.restore();
    }

    private void drawTagTriangleLeftBottom(Canvas canvas,int width){
        int iconWidth = 0;
        int iconHeight = getMeasuredHeight() - width / 2;
        canvas.save();
        mPath.moveTo(0, getMeasuredHeight() - width);
        mPath.lineTo(width, getMeasuredHeight());
        mPath.lineTo(0, getMeasuredHeight());
        canvas.drawPath(mPath, mTagPaint);
        canvas.restore();

        canvas.save();
        if (mTagIcon != null) {
            canvas.drawBitmap(mTagIcon, iconWidth, iconHeight, null);
        }
        canvas.restore();
    }

    /**
     * RoundRect Tag
     * @param canvas
     */
    private void drawTagRoundRect(Canvas canvas) {
        canvas.save();
        canvas.drawCircle(getMeasuredHeight() / 3, getMeasuredHeight() / 3, 200, mTagPaint);
        canvas.restore();
    }

    private void drawTagRect(Canvas canvas) {
        int width = getMeasuredWidth() / 6;
        if (mTagGravity == Gravity.RIGHT_TOP.ordinal()) {
            drawTagRectRightTop(canvas,width);
        } else if (mTagGravity == Gravity.RIGHT_BOTTOM.ordinal()) {
           drawTagRectRightBottom(canvas,width);
        } else if (mTagGravity == Gravity.LEFT_BOTTOM.ordinal()) {
           drawTagRectLeftBottom(canvas,width);
        } else {
            drawTagRectLeftTop(canvas, width);
        }
    }

    /**
     * Rect Tag
     * @param canvas
     * @param width
     */
    private void drawTagRectLeftTop(Canvas canvas, int width) {
        canvas.save();
        mPath.moveTo(width, 0);
        mPath.lineTo(2 * width, 0);
        mPath.lineTo(0, 2 * width);
        mPath.lineTo(0, width);
        mPath.close();
        canvas.drawPath(mPath, mTagPaint);
        canvas.restore();

        canvas.save();
        int x = 3 * width / 4;
        int y = 3 * width / 4;
        canvas.rotate(-45, x, y);
        if (mTagText != null) {
            canvas.drawText(mTagText, x, Math.abs(mFontMetrics.top) / 2 + y, mTextPaint);
        }
        canvas.restore();
    }

    private void drawTagRectRightTop(Canvas canvas,int width){
        canvas.save();
        mPath.moveTo(getMeasuredWidth() - width, 0);
        mPath.lineTo(getMeasuredWidth(), width);
        mPath.lineTo(getMeasuredWidth(), 2 * width);
        mPath.lineTo(getMeasuredWidth() - 2 * width, 0);
        mPath.close();
        canvas.drawPath(mPath, mTagPaint);
        canvas.restore();

        canvas.save();
        int x =  getMeasuredWidth() - (3 * width / 4);
        int y = 3 * width / 4;
        canvas.rotate(45, x, y);
        if (mTagText != null) {
            canvas.drawText(mTagText, x, Math.abs(mFontMetrics.top) / 2 + y, mTextPaint);
        }
        canvas.restore();
    }

    private void drawTagRectRightBottom(Canvas canvas,int width){
        canvas.save();
        mPath.moveTo(getMeasuredWidth(), getMeasuredHeight() - 2 * width);
        mPath.lineTo(getMeasuredWidth(), getMeasuredHeight() - width);
        mPath.lineTo(getMeasuredWidth() - width, getMeasuredHeight());
        mPath.lineTo(getMeasuredWidth() - 2 * width, getMeasuredHeight());
        mPath.close();
        canvas.drawPath(mPath, mTagPaint);
        canvas.restore();

        canvas.save();
        int x = getMeasuredWidth() - (3 * width / 4);
        int y = getMeasuredHeight() - (3 * width / 4);
        canvas.rotate(-45, x, y);
        if (mTagText != null) {
            canvas.drawText(mTagText, x, Math.abs(mFontMetrics.top) / 2 + y, mTextPaint);
        }
        canvas.restore();
    }

    private void drawTagRectLeftBottom(Canvas canvas,int width){
        canvas.save();
        mPath.moveTo(0, getMeasuredHeight() - 2 * width);
        mPath.lineTo(0, getMeasuredHeight() - width);
        mPath.lineTo(width, getMeasuredHeight());
        mPath.lineTo(2 * width, getMeasuredHeight());
        mPath.close();
        canvas.drawPath(mPath, mTagPaint);
        canvas.restore();

        canvas.save();
        int x = 3 * width / 4;
        int y = getMeasuredHeight() - (3 * width / 4);
        canvas.rotate(45, x, y);
        if (mTagText != null) {
            canvas.drawText(mTagText, x, Math.abs(mFontMetrics.top) / 2 + y, mTextPaint);
        }
        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mImgWidth = w;
        mImgHeight = h;
    }

    public void setTagBgAlpha(int alpha) {
        alpha &= 0xFF;          // keep it legal
        if (alpha != mTagBgAlpha) {
            mTagBgAlpha = alpha;
            invalidate();
        }
    }

}