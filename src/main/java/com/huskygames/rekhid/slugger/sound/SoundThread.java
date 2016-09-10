package com.huskygames.rekhid.slugger.sound;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This thread will own all sound resources, and is responsible for playing sounds and music.
 * Accepts queued requests to play sounds, and can be used to change background music.
 */
public class SoundThread extends Thread {

    private static final SoundThread instance;

    private final static Logger logger = LogManager.getLogger(SoundThread.class);

    private final ConcurrentLinkedQueue<ClipRequest> queue = new ConcurrentLinkedQueue<>();

    static {
        instance = new SoundThread();
    }

    public static SoundThread getInstance() {
        return instance;
    }

    @Override
    public synchronized void run() {
        while (true) {
            // TODO: pop off queue and play requests
        }
    }

    public void requestSound(ClipRequest request) {
        queue.add(request);
    }
}
