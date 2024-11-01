package com.mygdx.game.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import com.mygdx.game.StopTheSquirrels;

public class GameManager {

    public static final GameManager INSTANCE = new GameManager();

    private static final String BEST_RESULT = "BEST_RESULT";

    private final Preferences PREFS;

    private int result;
    private int health;
    private boolean hasShield=false;
    public static long powerTime;

    private GameManager() {
        PREFS = Gdx.app.getPreferences(StopTheSquirrels.class.getSimpleName());
    }

    public int getResult() {
        return result;
    }

    public void resetResult() {
        result = 0;
        health = 1;
    }

    public void incResult() {
        result++;
    }

    public boolean isGameOver() {
        return health <= 0;
    }


    public void damage() {
        if(!hasShield) {
            health--;
            if (health == 0) {
                if (result > getBestResult()) setBestResult(result);
            }
        }
    }

    public void setBestResult(int result) {
        PREFS.putInteger(BEST_RESULT, result);
        PREFS.flush();
    }

    public int getBestResult() {
        return PREFS.getInteger(BEST_RESULT, 0);
    }

    public void setShield(){
        hasShield=true;
    }


    public boolean getShield(){return hasShield;}

    public void setTimer(long powTime){powerTime=powTime;}
}
