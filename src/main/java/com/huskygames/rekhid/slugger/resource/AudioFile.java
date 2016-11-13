package com.huskygames.rekhid.slugger.resource;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

public class AudioFile extends LoadedResource {
    private final AudioInputStream stream;
    private AudioFormat format;

    public AudioFile(String path, AudioInputStream stream) {
        super(path);
        this.stream = stream;
    }

    public AudioInputStream getStream() {
        return stream;
    }

}
