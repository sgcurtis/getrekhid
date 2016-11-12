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
    private SourceDataLine outLine;
    private AudioInputStream bgMusicStream;

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

                AudioFormat format = createFormat(file.getStream().getFormat());
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
                AudioInputStream dataIn = AudioSystem.getAudioInputStream(format, file.getStream());

                try {
                    SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
                    line.open();
                    line.start();
                    outLine = line;
                    bgMusicStream = dataIn;
                } catch (LineUnavailableException e) {
                    logger.error("couldn't open", e);
                }
                dirtyBackground = false;
            }

            if (bgMusicStream != null ) {
                try {
                    playBg(outLine,(AudioFile) resourceManager.requestResource(getBackgroundMusic()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // http://docs.oracle.com/javase/tutorial/sound/playing.html
            // TODO: pop off queue and play requests
        }
    }

    private void playBg(SourceDataLine line, AudioFile file) throws IOException {
        byte[] buffer = new byte[4096];
        int nBytesRead = 0, nBytesWritten = 0;

        nBytesRead = bgMusicStream.read(buffer, 0, buffer.length);
        if (nBytesRead != -1) {
            nBytesWritten = line.write(buffer, 0, nBytesRead);
        }
        else {
            bgMusicStream.close();
            bgMusicStream = AudioSystem.getAudioInputStream(createFormat(file.getStream().getFormat()), file.getStream());
        }

    }

    private AudioFormat createFormat(AudioFormat baseFormat) {
        return new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                            baseFormat.getSampleRate(),
                            16,
                            baseFormat.getChannels(),
                            baseFormat.getChannels() * 2,
                            baseFormat.getSampleRate(),
                            false
                    );
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
