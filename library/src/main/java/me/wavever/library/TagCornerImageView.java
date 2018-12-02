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
    private ShapeGravity mTagGravity;
    private ShapeType mTagType;

    private Bitmap mTagIcon;
    private boolean mTagIconTurn;
    private int mTagIconPadding;

    private int mImgWidth;
    private int mImgHeight;

    private CornerTag mCornerTag;

    public TagCornerImageView(Context context) {
        this(context, null);
    }

    public TagCornerImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagCornerImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TagCornerImageView, defStyleAttr, 0);
        mTagBgColor = a.getColor(R.styleable.TagCornerImageView_tag_color, Color.RED);
        if (a.hasValue(R.styleable.TagCornerImageView_tag_alpha)) {
            mTagBgAlpha = a.getInt(R.styleable.TagCornerImageView_tag_alpha, DEFAULT_BG_ALPHA);
            if (mTagBgAlpha != DEFAULT_BG_ALPHA) {
                setTagBgAlpha(mTagBgAlpha);
            }
        }
        mTagText = a.getString(R.styleable.TagCornerImageView_tag_text);
        mTagTextColor = a.getColor(R.styleable.TagCornerImageView_tag_text_color, Color.WHITE);
        mTagTextSize = a.getDimensionPixelSize(R.styleable.TagCornerImageView_tag_text_size,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, DEFAULT_TEXT_SIZE, getResources().getDisplayMetrics()));
        mTagGravity = ShapeGravity.getGravity(a.getInt(R.styleable.TagCornerImageView_tag_gravity, 0));
        mTagType = ShapeType.getType(a.getInt(R.styleable.TagCornerImageView_tag_type, 0));
        mTagIcon = BitmapFactory.decodeResource(getResources(), a.getResourceId(R.styleable.TagCornerImageView_tag_icon, 0));
        mTagIconTurn = a.getBoolean(R.styleable.TagCornerImageView_tag_icon_turn, true);
        mTagIconPadding = a.getDimensionPixelSize(R.styleable.TagCornerImageView_tag_icon_padding,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()));
        a.recycle();
        mCornerTag = new CornerTag.Builder(this)
                .backgroundColor(mTagBgColor)
                .color(mTagTextColor)
                .type(mTagType)
                .build();
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
        if (mTagType == ShapeType.TRIANGLE) {
            drawTagTriangle(canvas);
        } else if (mTagType == ShapeType.RECTANGLE) {
            drawTagRect(canvas);
        }
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);

    }

    private void drawTagTriangle(Canvas canvas) {
        int width = getMeasuredWidth() >= getMeasuredHeight() ? getMeasuredWidth() / 4 : getMeasuredHeight() / 4;
        if (mTagGravity == ShapeGravity.RIGHT_TOP) {
            drawTagTriangleRightTop(canvas, width);
        } else if (mTagGravity == ShapeGravity.RIGHT_BOTTOM) {
            drawTagTriangleRightBottom(canvas, width);
        } else if (mTagGravity == ShapeGravity.LEFT_BOTTOM) {
            drawTagTriangleLeftBottom(canvas, width);
        } else {
            drawTagTriangleLeftTop(canvas, width);
        }
    }

    private void drawTagTriangleLeftTop(Canvas canvas, int width) {
        int iconLeft = width / 2 - mTagIcon.getWidth(); //icon左上角的x坐标
        int iconTop = width / 2 - mTagIcon.getHeight(); //icon左上角的y坐标
        canvas.save();
        mPath.lineTo(width, 0);
        mPath.lineTo(0, width);
        mPath.lineTo(0, 0);
        mPath.close();
        canvas.drawPath(mPath, mTagPaint);
        canvas.restore();

        canvas.save();
        if (mTagIcon != null) {
            canvas.drawBitmap(mTagIcon, iconLeft, iconTop, null);
        }
        canvas.restore();
    }

    private void drawTagTriangleRightTop(Canvas canvas, int width) {
        int iconLeft = getMeasuredWidth() - width / 2; //icon左上角x坐标 W-w+w/2
        int iconTop = width / 2 - mTagIcon.getHeight();
        canvas.save();
        mPath.moveTo(getMeasuredWidth() - width, 0);
        mPath.lineTo(getMeasuredWidth(), width);
        mPath.lineTo(getMeasuredWidth(), 0);
        canvas.drawPath(mPath, mTagPaint);
        canvas.restore();
        canvas.save();


        if (mTagIcon != null) {
            canvas.drawBitmap(mTagIcon, iconLeft, iconTop, null);
        }
        canvas.restore();
    }

    private void drawTagTriangleRightBottom(Canvas canvas, int width) {
        int iconLeft = getMeasuredWidth() - width / 2;
        int iconTop = getMeasuredHeight() - width / 2;
        canvas.save();
        mPath.moveTo(getMeasuredWidth(), getMeasuredHeight() - width);
        mPath.lineTo(getMeasuredWidth() - width, getMeasuredHeight());
        mPath.lineTo(getMeasuredWidth(), getMeasuredHeight());
        canvas.drawPath(mPath, mTagPaint);
        canvas.restore();

        canvas.save();
        if (mTagIcon != null) {
            canvas.drawBitmap(mTagIcon, iconLeft, iconTop, null);
        }
        canvas.restore();
    }

    private void drawTagTriangleLeftBottom(Canvas canvas, int width) {
        int iconLeft = width / 2 - mTagIcon.getWidth(); //icon左上角x坐标
        int iconTop = getMeasuredHeight() - width / 2;
        canvas.save();
        mPath.moveTo(0, getMeasuredHeight() - width);
        mPath.lineTo(width, getMeasuredHeight());
        mPath.lineTo(0, getMeasuredHeight());
        canvas.drawPath(mPath, mTagPaint);
        canvas.restore();

        canvas.save();
        if (mTagIcon != null) {
            canvas.drawBitmap(mTagIcon, iconLeft, iconTop, null);
        }
        canvas.restore();
    }

    private void drawTagRect(Canvas canvas) {
        int width = getMeasuredWidth() / 6;
        if (mTagGravity == ShapeGravity.RIGHT_TOP) {
            drawTagRectRightTop(canvas, width);
        } else if (mTagGravity == ShapeGravity.RIGHT_BOTTOM) {
            drawTagRectRightBottom(canvas, width);
        } else if (mTagGravity == ShapeGravity.LEFT_BOTTOM) {
            drawTagRectLeftBottom(canvas, width);
        } else {
            drawTagRectLeftTop(canvas, width);
        }
    }

    /**
     * Rect Tag
     *
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

    private void drawTagRectRightTop(Canvas canvas, int width) {
        canvas.save();
        mPath.moveTo(getMeasuredWidth() - width, 0);
        mPath.lineTo(getMeasuredWidth(), width);
        mPath.lineTo(getMeasuredWidth(), 2 * width);
        mPath.lineTo(getMeasuredWidth() - 2 * width, 0);
        mPath.close();
        canvas.drawPath(mPath, mTagPaint);
        canvas.restore();

        canvas.save();
        int x = getMeasuredWidth() - (3 * width / 4);
        int y = 3 * width / 4;
        canvas.rotate(45, x, y);
        if (mTagText != null) {
            canvas.drawText(mTagText, x, Math.abs(mFontMetrics.top) / 2 + y, mTextPaint);
        }
        canvas.restore();
    }

    private void drawTagRectRightBottom(Canvas canvas, int width) {
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

    private void drawTagRectLeftBottom(Canvas canvas, int width) {
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
        alpha &= 0xFF; // keep it legal
        if (alpha != mTagBgAlpha) {
            mTagBgAlpha = alpha;
            invalidate();
        }
    }

}
