package com.game.dropper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Map class with declarations and relevant functions
 */
public class Map {

    public Floors floors;
    public CoinMap coins;
    private boolean rightBlock;
    private boolean leftBlock;
    public int score = 0;

    /**
     * Map definition stores a Floors and CoinMap
     */
    public Map() {
        floors = new Floors(30, 150, 200, Color.GRAY);
        coins = new CoinMap();
    }

    /**
     * updates the different GameObjects on the Map
     *
     * @param player    the current player
     * @param moveRight whether the player is blocked on the right
     * @param moveLeft  whether the player is blocked on the left
     */
    public void update(Player player, boolean moveRight, boolean moveLeft) {
        for (int i = 0; i < 3; ++i) {
            if (floors.playerCollide(player)) {
                player.playerDrop(1); // moves the player up with the floor if the player is on the floor
            } else {
                player.playerDrop(-5); // moves the player down if it isn't blocked
            }
            int index = coins.touchCoin(player); // determines the index of the coin that the player is touching (if it exists)
            if (index != -1) {
                coins.remove(index);
                ++score; // increases score if coin is collected by player
            }
            rightBlock = floors.rightBlock(player); // determines if the player is blocked on the right
            leftBlock = floors.leftBlock(player); // determines if the player is blocked on the left
            if (moveRight && !rightBlock) {
                player.playerSlide(2); // moves player to the right if possible
            } else if (moveLeft && !leftBlock) {
                player.playerSlide(-2); // moves player to the left if possible
            }
        }
        floors.fullUpdate(coins); // updates floors
    }

    /**
     * draws the map components onto the canvas
     *
     * @param canvas in which the game is drawn on
     */
    public void draw(Canvas canvas) {
        floors.draw(canvas);
        coins.draw(canvas);


        Paint paint = new Paint();

        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        canvas.drawText("Score: " + score, 10, 50, paint);
    }
}
