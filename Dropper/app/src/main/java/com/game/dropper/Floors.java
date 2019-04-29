package com.game.dropper;

import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Floors class with declaration and relevant functions
 */
public class Floors implements GameObject {

    private ArrayList<SingleFloor> floors; // stores an array of Floors
    private int floorHeight;
    private int playerGap;
    private int color;
    private int floorGap;

    // private long startTime;
    // private long initTime

    /**
     * Floors function used to generate a Floors
     *
     * @param floorHeight the height of a SingleFloor
     * @param playerGap   the gap in the SingleFloor
     * @param floorGap    gap between adjacent SingleFloors
     * @param color       color of the Floors
     */
    public Floors(int floorHeight, int playerGap, int floorGap, int color) {
        this.floorHeight = floorHeight;
        this.playerGap = playerGap;
        this.color = color;
        this.floorGap = floorGap;

        floors = new ArrayList<>();

        addFloors();
    }

    /**
     * adds a couple of SingleFloors at the beginning as a buffer
     */
    public void addFloors() {
        int gaps = (int) Math.floor(Math.random() * 2) + 1;

        if (gaps == 1) {
            int length1 = (int) (Math.random() * (Constants.SCREEN_WIDTH - playerGap));
            int length2 = Constants.SCREEN_WIDTH - length1 - playerGap;

            floors.add(0, new SingleFloor(length1, length2, Constants.SCREEN_HEIGHT, floorHeight, playerGap, color));
        } else {
            int length1 = (int) (Math.random() * (Constants.SCREEN_WIDTH / 2 - playerGap));
            int length2 = (int) (Math.random() * (Constants.SCREEN_WIDTH - length1 - playerGap));

            floors.add(0, new SingleFloor(length1, length2, Constants.SCREEN_HEIGHT, floorHeight, playerGap, color));
        }
    }

    /**
     * determines if player is on top of a SingleFloor
     *
     * @param player the current player
     * @return true if a player is on top, false otherwise
     */
    public boolean playerCollide(Player player) {
        for (SingleFloor fl : floors) {
            if (fl.playerCollideF(player.getRect())) {
                return true;
            }
        }
        return false;
    }

    /**
     * determines if player is being blocked on the right by a SingleFloor
     *
     * @param player current player
     * @return true if player is being blocked, false otherwise
     */
    public boolean rightBlock(Player player) {
        for (SingleFloor fl : floors) {
            if (fl.rightBlockF(player.getRect())) {
                return true;
            }
        }
        return false;
    }

    /**
     * determines if player is being blocked on the left by a SingleFloor
     *
     * @param player current player
     * @return true if player is being blocked, false otherwise
     */
    public boolean leftBlock(Player player) {
        for (SingleFloor fl : floors) {
            if (fl.leftBlockF(player.getRect())) {
                return true;
            }
        }
        return false;
    }

    /**
     * moves the floors up, adding additional ones if necessary
     * generates coins and updates them
     */
    public void fullUpdate(CoinMap coins) {
        for (SingleFloor fl : floors) {
            fl.floorMove(3); // moves all the floors up
        }
        coins.update(); // moves all coins up
        if (floors.get(floors.size() - 1).getHeight() < 0) {
            floors.remove(floors.size() - 1); // removes any floors that are no longer on the screen
        }
        if (floors.get(0).getHeight() < Constants.SCREEN_HEIGHT - floorGap) { // adds more floors if necessary
            int gaps = (int) Math.floor(Math.random() * 2) + 1; // randomly generates how many gaps the new SingeFloor will have

            // adds new SingleFloor with random dimensions
            if (gaps == 1) {
                int length1 = (int) (Math.random() * (Constants.SCREEN_WIDTH - playerGap));
                int length2 = Constants.SCREEN_WIDTH - length1 - playerGap;

                floors.add(0, new SingleFloor(length1, length2, Constants.SCREEN_HEIGHT, floorHeight, playerGap, color));
            } else {
                int length1 = (int) (Math.random() * (Constants.SCREEN_WIDTH / 2 - playerGap));
                int length2 = (int) (Math.random() * (Constants.SCREEN_WIDTH - length1 - playerGap));

                floors.add(0, new SingleFloor(length1, length2, Constants.SCREEN_HEIGHT, floorHeight, playerGap, color));
            }

            // adds coins randomly
            int coinChance = (int) Math.floor(Math.random() * 2);
            if (coinChance == 0) {
                coins.addCoins();
            }
        }
    }

    /**
     * update function for Floors
     */
    @Override
    public void update() {}

    /**
     * draws all the SingleFloors onto the canvas
     *
     * @param canvas in which the game is drawn on
     */
    @Override
    public void draw(Canvas canvas) {
        for (SingleFloor fl : floors) {
            fl.draw(canvas);
        }
    }
}
