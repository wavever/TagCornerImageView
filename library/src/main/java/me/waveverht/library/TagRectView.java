package me.waveverht.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by waveverht on 2016/4/29.
 */
public class TagRectView extends View {

    private Paint mTextPaint;
    private String mText;
    private int mImgWidth;
    private int mImgHeight;

    private enum Gravity {
        LEFT_TOP,
        RIGHT_TOP,
        RIGHT_BOTTOM,
        LEFT_BOTTOM
    }

    public TagRectView(Context context) {
        this(context,null);
    }

    public TagRectView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TagRectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
