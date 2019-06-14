import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import audio.AudioLoader;
import image.ImageLoader;

/**
 * A quick class for a clickable image that plays an audiofile when clicked.
 *
 * @author Daniël Barenholz
 */
public class IMG extends JPanel {
    private final String audio;
    private final String image;

    /**
     * Constructor. Creates an IMG object with audio file denoted by {@code audio}
     * and image denoted by {@code image}.
     */
    IMG(String audio, String image) {
        super(null);

        this.audio = GlobalState.getAudioSrc() + audio;
        System.out.println(this.audio);
        this.image = GlobalState.getImgSrc() + image;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                AudioLoader.play(IMG.this.audio);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(ImageLoader.getImage(image), 20, 20, null);
    }
}
