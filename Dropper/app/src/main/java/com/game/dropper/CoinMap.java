package com.game.dropper;

import android.graphics.Canvas;

import java.util.ArrayList;

public class CoinMap implements GameObject{

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

    @Override
    public void update() {
        coins.add(0, new Coin((int) Math.floor(Math.random() * Constants.SCREEN_WIDTH), Constants.SCREEN_HEIGHT - 5, 20));
    }

    @Override
    public void draw(Canvas canvas) {
        for (Coin c : coins) {
            c.draw(canvas);
        }
    }
}
