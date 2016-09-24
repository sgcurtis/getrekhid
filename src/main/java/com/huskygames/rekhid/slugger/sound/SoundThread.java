package com.huskygames.rekhid.slugger.sound;

import com.huskygames.rekhid.slugger.resource.Resource;
import com.huskygames.rekhid.slugger.resource.ResourceManager;
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

    static {
        instance = new SoundThread();
    }

    private volatile boolean dirtyBackground = true;

    private final ConcurrentLinkedQueue<ClipRequest> queue = new ConcurrentLinkedQueue<>();
    private final ResourceManager resourceManager;
    private volatile Resource backgroundMusic;

    public SoundThread() {
        resourceManager = new ResourceManager();
    }

    public static SoundThread getInstance() {
        return instance;
    }

    public Resource getBackgroundMusic() {
        return backgroundMusic;
    }

    public void setBackgroundMusic(Resource backgroundMusic) {
        if (this.backgroundMusic != backgroundMusic) {
            this.backgroundMusic = backgroundMusic;
            resourceManager.suggestLoad(backgroundMusic);
            dirtyBackground = true;
        }
    }

    @Override
    public synchronized void run() {
        Thread.currentThread().setName("AudioThread");
        Thread.currentThread().setPriority(MIN_PRIORITY + 1);
        while (true) {

            // http://docs.oracle.com/javase/tutorial/sound/playing.html
            // TODO: pop off queue and play requests
        }
    }

    public void requestSound(ClipRequest request) {
        queue.add(request);
    }
}
