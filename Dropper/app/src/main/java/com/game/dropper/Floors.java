package com.game.dropper;

import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Floors class used to generate all the SingleFloors
 */
public class Floors implements GameObject {

    private ArrayList<SingleFloor> floors;
    private int floorHeight;
    private int playerGap;
    private int color;
    private int floorGap;

    // private long startTime;
    // private long initTime

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
     * determines if the player intersects with any of the floors
     *
     * @param player the current player
     * @return if the player intersects
     */
    public boolean playerCollide(Player player) {
        for (SingleFloor fl : floors) {
            if (fl.playerCollideF(player)) {
                return true;
            }
        }
        return false;
    }

    /**
     * moves the floors down, adding additional ones if necessary
     */
    @Override
    public void update() {
        for (SingleFloor fl : floors) {
            fl.floorMove(3);
        }
        if (floors.get(floors.size() - 1).getHeight() < 0) {
            floors.remove(floors.size() - 1);
        }
        if (floors.get(0).getHeight() < Constants.SCREEN_HEIGHT - floorGap) {
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
    }

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
