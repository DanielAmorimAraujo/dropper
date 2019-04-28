package com.game.dropper;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Player class used to generate the player, in the form of a Rect
 */
public class Player implements GameObject {

    private Rect player;
    int color;

    public Player(Rect player, int color) {
        this.player = player;
        this.color = color;
    }

    /**
     * moves the player down y pixels
     *
     * @param y number of pixels the player moves
     */
    public void playerDrop(float y) {
        player.top -= y;
        player.bottom -= y;
    }


    /**
     * moves the player to the right x pixels
     *
     * @param x number of pixels the player moves
     */
    public void playerSlide(float x) {
        player.right += x;
        player.left += x;
    }

    /**
     * @return returns the player's rect
     */
    public Rect getRect() {
        return player;
    }

    @Override
    public void update() {

    }

    /**
     * draws the player onto the canvas
     *
     * @param canvas in which the game is drawn on
     */
    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(player, paint);
    }
}
