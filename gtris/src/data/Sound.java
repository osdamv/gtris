package data;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public final class Sound {

    private static final Clip SONG;
    private static final Clip PUFF;

    static {
	Clip song = null, puff = null;

	try {
	    String path = ClassLoader.getSystemClassLoader().getResource("song.wav").getFile();
	    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
	    song = AudioSystem.getClip();
	    song.open(audioInputStream);
	    path = ClassLoader.getSystemClassLoader().getResource("puff.wav").getFile();
	    audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
	    puff = AudioSystem.getClip();
	    puff.open(audioInputStream);
	} catch (UnsupportedAudioFileException | IOException e) {
	    e.printStackTrace();
	} catch (LineUnavailableException e) {
	    e.printStackTrace();
	}
	SONG = song;
	PUFF = puff;
    }

    private Sound() {

    }

    public static void playSong() {
	SONG.loop(Clip.LOOP_CONTINUOUSLY);
	SONG.start();
    }

    public static void playPuff() {
	if (!PUFF.isRunning()) {
	    PUFF.setFramePosition(0);
	    PUFF.start();
	}
    }
}
