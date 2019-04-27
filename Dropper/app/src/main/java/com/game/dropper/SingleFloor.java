package com.game.dropper;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

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

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rect1, paint);
        canvas.drawRect(rect2, paint);
        canvas.drawRect(rect3, paint);
    }
}
