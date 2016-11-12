package com.huskygames.rekhid.slugger.sound;

import com.huskygames.rekhid.slugger.resource.AudioFile;
import com.huskygames.rekhid.slugger.resource.Resource;
import com.huskygames.rekhid.slugger.resource.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sound.sampled.*;
import java.io.IOException;
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

    private final ConcurrentLinkedQueue<ClipRequest> queue = new ConcurrentLinkedQueue<>();
    private final ResourceManager resourceManager;
    private volatile boolean dirtyBackground = false;
    private volatile Resource backgroundMusic;
    private Mixer outputMixer;

    public Mixer getOutputMixer() {
        return outputMixer;
    }

    public void setOutputMixer(Mixer outputMixer) {
        this.outputMixer = outputMixer;
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

        if (outputMixer == null) {
            logger.warn("Unable to find suitable output, muted.");
            return;
        }
        else {
            logger.info("Sound thread started.");
        }

        while (true) {

            if(dirtyBackground) {
                AudioFile file = (AudioFile) resourceManager.requestResource(getBackgroundMusic());
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, file.getFormat());
                logger.info(file.getFormat());
                logger.info(info);
                try {
                    SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
                    logger.info(line);
                    line.open();
                    line.start();
                    byte[] buffer = new byte[2048];
                    int nBytesRead = 0, nBytesWritten = 0;
                    while (nBytesRead != -1) {
                        nBytesRead = file.getStream().read(buffer, 0, buffer.length);
                        if (nBytesRead != -1) {
                            nBytesWritten = line.write(buffer, 0, nBytesRead);
                        }
                    }
                    line.drain();
                    line.stop();
                    line.close();

                } catch (LineUnavailableException e) {
                    logger.error("couldn't open", e);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // http://docs.oracle.com/javase/tutorial/sound/playing.html
            // TODO: pop off queue and play requests
        }
    }

    public SoundThread() {
        resourceManager = new ResourceManager();
    }

    public static SoundThread getInstance() {
        return instance;
    }

    public void requestSound(ClipRequest request) {
        queue.add(request);
    }
}
