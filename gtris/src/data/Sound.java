package data;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 * helping class who helps with the sound 
 * @author dvelazquez
 * @since 16/04/2013
 */
public final class Sound {

    private static final Clip SONG;
    private static final Clip PUFF;

    static {
	Clip song = null, puff = null;

	try {
	    InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("song.wav");
	    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(stream));
	    song = AudioSystem.getClip();
	    song.open(audioInputStream);
	    stream = ClassLoader.getSystemClassLoader().getResourceAsStream("puff.wav");
	    audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(stream));
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
    /**
     * start the music theme
     */
    public static void playSong() {
	SONG.loop(Clip.LOOP_CONTINUOUSLY);
	SONG.start();
    }
    /**
     * start the sound fx called  "puff", used when a figure is marked to be deleted 
     */
    public static void playPuff() {
	if (!PUFF.isRunning()) {
	    PUFF.setFramePosition(0);
	    PUFF.start();
	}
    }
}
