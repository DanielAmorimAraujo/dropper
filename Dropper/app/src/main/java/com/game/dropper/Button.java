package com.game.dropper;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class Button implements GameObject {

    private int cx;
    private int cy;
    private int radius;
    int color;

    public Button(int cx, int cy, int radius, int color) {
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawCircle(cx, cy, radius, paint);
    }
}
