package com.yl.crazy.mydongtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import static android.R.attr.width;

/**
 * Created by Administrator on 2017/11/9.
 */

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);

        Path path = new Path();
        path.moveTo(0,50);
        path.quadTo(0,0,50,0);
        path.lineTo(width -50,0);
        path.quadTo(width,0,  width,50);
        path.lineTo(width,height);
        path.lineTo(0,height);
        path.close();

        canvas.drawPath(path,paint);

    }
}
