import javax.swing.*;

/**
 * A very, very quick java application mimicking what a soundboard should do. It
 * contains one main screen, with 6 images. When clicking on an image, the
 * respective soundfile will play.
 *
 * @author Daniël Barenholz
 */
public class Soundboard extends JFrame {

    private IMG[][] view = new IMG[GlobalState.getW()][GlobalState.getH()];

    private Soundboard(String title) {
        super(title);

        this.setLayout(null);
        this.setSize(500, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        initGUI();
        this.repaint();
    }

    private void initGUI() {
        for (int w = 0; w < GlobalState.getW(); w++) {
            for (int h = 0; h < GlobalState.getH(); h++) {

                String audio = "" + w + "_" + h + ".wav";
                String image = "" + w + "_" + h + ".jpg";

                view[w][h] = new IMG(audio, image);

                view[w][h].setSize(this.getWidth() / GlobalState.getW(), this.getHeight() / GlobalState.getH());
                view[w][h].setLocation((w * (this.getWidth())) / GlobalState.getW(), (h * (this.getHeight())) / GlobalState.getH());

                this.add(view[w][h]);
            }
        }
    }

    // Runner
    public static void main(String[] args) {
        new Soundboard("My amazing soundboard!");
    }
}