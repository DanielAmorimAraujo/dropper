package com.game.dropper;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * SingleFloor class used to generate a single floor level
 */
public class SingleFloor implements GameObject {

    private Rect rect1;
    private Rect rect2;
    private Rect rect3;
    private int height;
    private int floorHeight;
    private int color;


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
     * moves the floor down y pixels
     *
     * @param y number of pixels the floor moves down
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
     * determines if the player intersects with the floor
     *
     * @param player the current player
     * @return if it intersects or not
     */
    // it  can only intersect at the top, have it continue falling when it touches the size
    public boolean playerCollideF(Player player) {
        return Rect.intersects(rect1, player.getRect()) || Rect.intersects(rect2, player.getRect()) || Rect.intersects(rect3, player.getRect());
    }

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
