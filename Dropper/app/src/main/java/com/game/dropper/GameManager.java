package com.game.dropper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * GameManager class manages the game by generating the specific GameObjects and updating them
 * appropriately
 * Also generates the canvas in which the game is drawn on
 */
public class GameManager extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    private Player player;
    private Floors floors;

    public GameManager(Context context) {
        super(context);
        getHolder().addCallback(this);

        player = new Player(new Rect(Constants.SCREEN_WIDTH / 2 - 30, 200, Constants.SCREEN_WIDTH / 2 + 30, 260), Color.BLUE);
        floors = new Floors(30, 150, 200, Color.BLACK);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (true) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    /**
     * updates the specific GameObjects
     */
    public void update() {
        if (floors.playerCollide(player)) {
            player.playerDrop(3);
        } else {
            player.playerDrop(-10);
        }
        floors.update();
    }

    /**
     * draws the canvas and all necessary GameObjects
     *
     * @param canvas in which the game is drawn on
     */
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);

        floors.draw(canvas);
        player.draw(canvas);
    }
}
