package com.game.dropper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
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

    private Player player;
    private Map map;
    //private Floors floors;
    private Button rightButton;
    private Button leftButton;
    //private CoinMap coins;
    private int movePointX;
    private int movePointY;
    private boolean moveRight;
    //private boolean rightBlock;
    //private boolean leftBlock;
    private boolean moveLeft;
    private boolean gameOver = false;

    public GameManager(Context context) {
        super(context);
        getHolder().addCallback(this);

        player = new Player(new Rect(Constants.SCREEN_WIDTH / 2 - 30, 200, Constants.SCREEN_WIDTH / 2 + 50, 280), Color.RED);
        map = new Map();
        //floors = new Floors(30, 150, 200, Color.GRAY);
        rightButton = new Button(Constants.SCREEN_WIDTH / 6 * 5, Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT / 10, Constants.SCREEN_WIDTH / 9, Color.BLUE);
        leftButton = new Button(Constants.SCREEN_WIDTH / 6, Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT / 10, Constants.SCREEN_WIDTH / 9, Color.BLUE);
        //coins = new CoinMap();
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
     * moves the player horizontally in the direction that the user touches the screen
     *
     * @param event touch event
     * @return always returns true
     */
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (MotionEvent.ACTION_DOWN == action) {
            movePointX = (int) event.getX();
            movePointY = (int) event.getY();
            if (rightButton.buttonClick(movePointX, movePointY)) {
                moveRight = true;
                moveLeft = false;
                rightButton.update();
            } else if (leftButton.buttonClick(movePointX, movePointY)) {
                moveLeft = true;
                moveRight = false;
                leftButton.update();
            }
        } else if (MotionEvent.ACTION_UP == action) {
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
     * updates the specific GameObjects
     */
    public void update() {
        gameOver = player.onScreen();
        if (!gameOver) {
            map.update(player, moveRight, moveLeft);
        }
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
        //floors.draw(canvas);
        rightButton.draw(canvas);
        leftButton.draw(canvas);
        //coins.draw(canvas);
    }
}
