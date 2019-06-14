package audio;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * The AudioLoader provides capabilities for playing a wav file.
 *
 * @author Daniël Barenholz
 */
public class AudioLoader {

    private final static ConcurrentHashMap<String, Clip> audiofiles = new ConcurrentHashMap<>();

    // @return true if audio is playing
    private static boolean isPlaying(String audio) {
        return audiofiles.contains(audio);
    }

    private static Clip loadAudioClip(String audio) {
        Clip c = null;

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(audio));
            c = AudioSystem.getClip();
            c.open(audioStream);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }

        return c;
    }

    /**
     * Plays the audio denoted by the name {@code audio}.
     *
     * @param audio the name of the audiofile to play.
     */
    public static void play(String audio) {
        // If it is playing, reset the frameposition to restart.
        if (isPlaying(audio)) {
            audiofiles.get(audio).setFramePosition(0);
        } else {
            Clip c = loadAudioClip(audio);

            c.addLineListener((LineEvent evt) -> {
                if (evt.getType() == LineEvent.Type.STOP) {
                    audiofiles.remove(audio);
                }

                if (evt.getType() == LineEvent.Type.START) {
                    c.setFramePosition(0);
                }
            });

            c.start();

            audiofiles.put(audio, c);
        }
    }

    /**
     * Stops audio denoted with name {@code audio} from playing.
     *
     * @param audio the name of the audiofile to stop.
     */
    public static void stop(String audio) {
        if (isPlaying(audio)) {
            audiofiles.get(audio).stop();
        }
    }
}
