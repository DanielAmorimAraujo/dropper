package com.game.dropper;

import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * CoinMap class with declaration and relevant functions
 */
public class CoinMap implements GameObject {

    private ArrayList<Coin> coins; // stores coins in an array

    /**
     * CoinMap function generates a CoinMap
     */
    public CoinMap() {
        coins = new ArrayList<>();
    }

    /**
     * @return returns size of CoinMap array
     */
    public int getSize() {
        return coins.size();
    }

    /**
     * adds coins at a random position on the bottom SingleFloor
     */
    public void addCoins() {
        coins.add(0, new Coin((int) Math.floor(Math.random() * Constants.SCREEN_WIDTH), Constants.SCREEN_HEIGHT - 30, 20));
        for (Coin c : coins) {
            if (c.getHeight() <= 0) {
                coins.remove(c);
            }
        }
    }

    /**
     * removes coin at index
     *
     * @param index index at which coin is removed
     */
    public void remove(int index) {
        coins.remove(index);
    }

    /**
     * determines the index of the coin the player is touching
     *
     * @param player the current player
     * @return index of the coin, -1 otherwise
     */
    public int touchCoin(Player player) {
        for (int i = 0; i < coins.size(); ++i) {
            if (coins.get(i).touchCoin(player.getRect())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * update function for CoinMap
     */
    @Override
    public void update() {
        for (Coin c : coins) {
            c.coinMove(3);
        }
    }

    /**
     * draws the CoinMap components onto the canvas
     *
     * @param canvas in which the game is drawn on
     */
    @Override
    public void draw(Canvas canvas) {
        for (Coin c : coins) {
            c.draw(canvas);
        }
    }
}
