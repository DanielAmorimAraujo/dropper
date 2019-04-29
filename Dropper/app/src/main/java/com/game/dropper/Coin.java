package com.game.dropper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Coin implements GameObject {

    private int cx;
    private int cy;
    private int radius;

    public Coin(int cx, int cy, int radius) {
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
    }

    public void coinMove(int y) {
        cy -= y;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(cx, cy, radius, paint);
    }
}
