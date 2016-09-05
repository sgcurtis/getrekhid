package com.huskygames.rekhid;

public class Main {

    public enum GameState {
        STARTING,
        MENU,
        CHARACTER_SELECT,
        MATCH,
        QUITTING
    }

    private static final int TARGET_FPS = 60;
    private static final float TARGET_TIME = 1000f / TARGET_FPS;

    private GameState state;
    private long tickCount;

    private void runGameLoop() throws Exception {
        long lastTick = -1;

        while (true) {
            // game loop

            switch (state) {
                case STARTING:
                case MENU:
                case CHARACTER_SELECT:
                case MATCH:
                case QUITTING:
                    return;
            }

            long currentTime = System.currentTimeMillis();

            long tickTime = currentTime - lastTick;

            if (tickTime < (long) TARGET_TIME) {
                Thread.sleep(((long) TARGET_TIME) - tickTime);
            }
            else {
                // TODO: log about slow tick
            }

            lastTick = currentTime;

            // end game loop
        }
    }

    public static void main(String[] args) {
        Main game = new Main();

        while (game.state != GameState.QUITTING) {
            game.state = GameState.STARTING;
            try {
                game.runGameLoop();
            } catch (Exception e) {
                // TODO: log exception here
            }
        }
        // cleanup here

    }
}
