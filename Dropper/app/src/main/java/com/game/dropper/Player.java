package com.game.dropper;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Player implements GameObject {

    private Rect player;
    int color;

    public Player(Rect player, int color) {
        this.player = player;
        this.color = color;
    }

    public void playerDrop(float y) {
        player.top -= y;
        player.bottom -= y;
    }

    public void playerSlide(float x) {
        player.right += x;
        player.left += x;
    }

    public Rect getRect() {
        return player;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(player, paint);
    }
}
