package com.game.dropper;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * SingleFloor class with declaration and relevant functions
 */
public class SingleFloor implements GameObject {

    private Rect rect1;
    private Rect rect2;
    private Rect rect3;
    private int height;
    private int floorHeight;
    private int color;

    /**
     * SingleFloor function used to generate a single floor level
     * Note: a single floor is made up of 2 or 3 rectangles
     *
     * @param length1     length of first rectangle
     * @param length2     length of second rectangle
     * @param height      height the floor starts at
     * @param floorHeight height of the floor
     * @param playerGap   gap between adjacent rectangles
     * @param color       color of the floor
     */
    public SingleFloor(int length1, int length2, int height, int floorHeight, int playerGap, int color) {
        this.color = color;
        this.height = height;
        this.floorHeight = floorHeight;

        rect1 = new Rect(0, height, length1, floorHeight + height);
        rect2 = new Rect(length1 + playerGap, height, length1 + playerGap + length2, floorHeight + height);
        rect3 = new Rect(length1 + 2 * playerGap + length2, height, Constants.SCREEN_WIDTH, floorHeight + height);
    }

    /**
     * @return the bottom of the floor
     */
    public int getHeight() {

        return rect1.bottom;
    }

    /**
     * moves the floor up y pixels
     *
     * @param y number of pixels the floor moves up
     */
    public void floorMove(float y) {
        rect1.top -= y;
        rect1.bottom -= y;
        rect2.top -= y;
        rect2.bottom -= y;
        rect3.top -= y;
        rect3.bottom -= y;
    }

    /**
     * determines if the player is on top of a floor
     *
     * @param player the current player
     * @return true if the player is on top of a floor, false otherwise
     */
    public boolean playerCollideF(Rect player) {
        return (Rect.intersects(rect1, player) || Rect.intersects(rect2, player) || Rect.intersects(rect3, player)) && !rightBlockF(player) && !leftBlockF(player);
    }

    /**
     * determines if the player is being blocked by the right-side of a floor
     *
     * @param player the current player
     * @return true if being blocked, false otherwise
     */
    public boolean rightBlockF(Rect player) {
        return (player.top <= rect1.bottom) && (player.bottom >= rect1.top) && ((player.right == rect2.left) || (player.right == rect3.left) ||
                (player.right == rect2.left + 1) || (player.right == rect3.left + 1));
    }

    /**
     * determines if the player is being blocked by the left-side of a floor
     *
     * @param player the current player
     * @return true if being blocked, false otherwise
     */
    public boolean leftBlockF(Rect player) {
        return (player.top <= rect1.bottom) && (player.bottom >= rect1.top) && ((player.left == rect1.right) || (player.left == rect2.right) ||
                (player.left == rect1.right - 1) || (player.left == rect2.right - 1));
    }

    /**
     * update function for SingleFloor
     */
    @Override
    public void update() {

    }

    /**
     * draws the floor onto the canvas
     *
     * @param canvas in which the game is drawn on
     */
    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rect1, paint);
        canvas.drawRect(rect2, paint);
        canvas.drawRect(rect3, paint);
    }
}
