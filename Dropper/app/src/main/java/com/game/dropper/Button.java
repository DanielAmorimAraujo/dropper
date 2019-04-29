package com.game.dropper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Button classed used to generate two buttons
 */
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

    /**
     * @return cx - circle centre's x
     */
    public int getCx() {
        return cx;
    }

    /**
     * @return cy - circle centre's y
     */
    public int getCy() {
        return cy;
    }

    /**
     * determines if the point is on the button
     * @param mX user input x value
     * @param mY user input y value
     * @return boolean if the point intersects
     */
    public boolean buttonClick(int mX, int mY) {
        return Math.pow(mX - cx, 2) + Math.pow(mY - cy, 2) <= Math.pow(radius, 2);
    }

    @Override
    public void update() {
        if (color == Color.BLUE) {
            color = Color.GREEN;
        } else if (color == Color.GREEN) {
            color = Color.BLUE;
        }
    }

    /**
     * draws the button onto the canvas
     *
     * @param canvas in which the game is drawn on
     */
    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setAlpha(100);
        canvas.drawCircle(cx, cy, radius, paint);
    }
}
