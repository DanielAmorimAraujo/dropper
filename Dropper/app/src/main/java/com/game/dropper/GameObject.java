package com.game.dropper;

import android.graphics.Canvas;

/**
 * general functions used for GameObjects
 */
public interface GameObject {
    public void update();

    public void draw(Canvas canvas);
}
