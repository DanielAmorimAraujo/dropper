package com.game.dropper;

import android.graphics.Canvas;

import java.util.ArrayList;

public class CoinMap implements GameObject {

    private ArrayList<Coin> coins;

    public CoinMap() {
        coins = new ArrayList<>();
    }

    public int getSize() {
        return coins.size();
    }

    public void moveCoins() {
        for (Coin c : coins) {
            c.coinMove(3);
        }
    }

    public int touchCoin(Player player) {
        for (int i = 0; i < coins.size(); ++i) {
            if (coins.get(i).touchCoin(player.getRect())) {
                return i;
            }
        }
        return -1;
    }

    public void remove(int index) {
        coins.remove(index);
    }

    @Override
    public void update() {
        coins.add(0, new Coin((int) Math.floor(Math.random() * Constants.SCREEN_WIDTH), Constants.SCREEN_HEIGHT - 30, 20));
        for (Coin c : coins) {
            if (c.getHeight() <= 0) {
                coins.remove(c);
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        for (Coin c : coins) {
            c.draw(canvas);
        }
    }
}
