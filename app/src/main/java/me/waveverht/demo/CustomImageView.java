package me.waveverht.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.ImageView;

import me.wavever.library.CornerTag;

public class CustomImageView extends android.support.v7.widget.AppCompatImageView {

    private CornerTag cornerTag;

    public CustomImageView(Context context) {
        super(context);
        cornerTag = new CornerTag.Builder(this)
                .backgroundColor(1)
                .color(1)
                .build();
        cornerTag.setEnable(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        cornerTag.draw(canvas);
    }

}
