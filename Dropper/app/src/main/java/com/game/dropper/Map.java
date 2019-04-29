package com.game.dropper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Map {

    private Floors floors;
    private CoinMap coins;
    private boolean rightBlock;
    private boolean leftBlock;
    private int score = 0;

    public Map() {
        floors = new Floors(30, 150, 200, Color.GRAY);
        coins = new CoinMap();

    }

    public void update(Player player, boolean moveRight, boolean moveLeft) {
        for (int i = 0; i < 3; ++i) {
            if (floors.playerCollide(player)) {
                player.playerDrop(1);
            } else {
                player.playerDrop(-5);
            }
            int index = coins.touchCoin(player);
            if (index != -1) {
                coins.remove(index);
                ++score;
            }
            rightBlock = floors.rightBlock(player);
            leftBlock = floors.leftBlock(player);
            if (moveRight && !rightBlock) {
                player.playerSlide(2);
            } else if (moveLeft && !leftBlock) {
                player.playerSlide(-2);
            }
        }
        floors.fullUpdate(coins);
    }

    public void draw(Canvas canvas) {
        floors.draw(canvas);
        coins.draw(canvas);


        Paint paint = new Paint();

        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        canvas.drawText("Score: " + score, 10, 50, paint);
    }
}
