package com.game.dropper;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameManager extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    public GameManager(Context context) {
        super(context);
        getHolder().addCallback(this);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void update() {
    }

    public void draw(Canvas canvas){

    }
}
