package com.game.dropper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Coin class with declaration and revelant functions
 */
public class Coin implements GameObject {

    private int cx;
    private int cy;
    private int radius;

    /**
     * Coin function used to generate a Coin
     *
     * @param cx     x value of the centre
     * @param cy     y value of the centre
     * @param radius radius
     */
    public Coin(int cx, int cy, int radius) {
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
    }

    /**
     * @return returns bottom height of coin
     */
    public int getHeight() {
        return cy + radius;
    }

    /**
     * moves the coin up y pixels
     *
     * @param y number of pixels coin moves
     */
    public void coinMove(int y) {
        cy -= y;
    }

    /**
     * determines if player is touching the coin
     *
     * @param player the current player
     * @return true if player is touching, false otherwise
     */
    public boolean touchCoin(Rect player) {
        return player.right >= cx - radius && player.left <= cx + radius && player.top <= cy + radius && player.bottom >= cy - radius;
    }

    /**
     * update function for Coin
     */
    @Override
    public void update() {}

    /**
     * draws the coin onto the canvas
     *
     * @param canvas in which the game is drawn on
     */
    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(cx, cy, radius, paint);
    }
}
