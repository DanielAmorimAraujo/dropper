package com.game.dropper;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Player class with declaration and relevant functions
 */
public class Player implements GameObject {

    private Rect player;
    int color;

    /**
     * Player function used to generate the player, in the form of a Rect
     *
     * @param left   left side of player rectangle
     * @param top    top side of player rectangle
     * @param right  right side of player rectangle
     * @param bottom bottom side of player rectangle
     * @param color  player's color
     */
    public Player(int left, int top, int right, int bottom, int color) {
        player = new Rect(left, top, right, bottom);
        this.color = color;
    }

    /**
     * @return returns the player's rect
     */
    public Rect getRect() {
        return player;
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
     * determines if the player is on the screen
     *
     * @return true if player is on the screen
     */
    public boolean onScreen() {
        return player.top > Constants.SCREEN_HEIGHT || player.right < 0 || player.bottom < 0 || player.left > Constants.SCREEN_WIDTH;
    }

    /**
     * update function for Player
     */
    @Override
    public void update() {}

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
