package com.game.dropper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * GameManager class manages the game by generating the specific GameObjects and updating them
 * appropriately
 * Also generates the canvas in which the game is drawn on
 */
public class GameManager extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    private Player player; // stores current player
    private Map map; // stores the game map
    private Button rightButton; // stores right button
    private Button leftButton; // stores left button
    private int movePointX; // stores x value of user click
    private int movePointY; // stores y value of user click
    private boolean moveRight; // stores if user is trying to move to the right
    private boolean moveLeft; // stores if user is trying to move to the left
    private boolean gameOver = false; // true of game is over

    /**
     * starts the game and declares important game components
     *
     * @param context
     */
    public GameManager(Context context) {
        super(context);
        getHolder().addCallback(this);

        player = new Player(Constants.SCREEN_WIDTH / 2 - 30, 200, Constants.SCREEN_WIDTH / 2 + 50, 280, Color.RED);
        map = new Map();
        rightButton = new Button(Constants.SCREEN_WIDTH / 6 * 5, Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT / 10, Constants.SCREEN_WIDTH / 9, Color.BLUE);
        leftButton = new Button(Constants.SCREEN_WIDTH / 6, Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT / 10, Constants.SCREEN_WIDTH / 9, Color.BLUE);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

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
     * reads user touch event and determines what direction the user is trying to move in
     *
     * @param event touch event
     * @return always returns true (assume touch event was successful)
     */
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (MotionEvent.ACTION_DOWN == action) {
            if (!gameOver) {
                movePointX = (int) event.getX();
                movePointY = (int) event.getY();
                if (rightButton.buttonClick(movePointX, movePointY)) { // determines if clicking on right button
                    moveRight = true;
                    moveLeft = false;
                    rightButton.update();
                } else if (leftButton.buttonClick(movePointX, movePointY)) { // determines if clicking on left button
                    moveLeft = true;
                    moveRight = false;
                    leftButton.update();
                }
            } else {
                resetGame();
            }
        } else if (MotionEvent.ACTION_UP == action) { // once player stops clicking (returns buttons to default)
            if (moveRight) {
                rightButton.update();
            }
            if (moveLeft) {
                leftButton.update();
            }
            moveRight = false;
            moveLeft = false;
        }
        return true;
    }

    /**
     * updates the specific GameObjects if the game is not over
     */
    public void update() {
        gameOver = player.onScreen();
        if (!gameOver) {
            map.update(player, moveRight, moveLeft);
        }
    }

    public void gameOver(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(100);
        canvas.drawText("Game Over", Constants.SCREEN_WIDTH / 4, Constants.SCREEN_HEIGHT / 2, paint);
    }

    public void resetGame() {
        player = new Player(Constants.SCREEN_WIDTH / 2 - 30, 200, Constants.SCREEN_WIDTH / 2 + 50, 280, Color.RED);
        map = new Map();
        gameOver = false;
    }

    /**
     * draws the canvas and all necessary GameObjects
     *
     * @param canvas in which the game is drawn on
     */
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);

        map.draw(canvas);
        player.draw(canvas);
        rightButton.draw(canvas);
        leftButton.draw(canvas);

        if (gameOver) {
            gameOver(canvas);
        }
    }
}