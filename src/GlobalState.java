/**
 * A Java class containing all global variables of the application.
 */
public class GlobalState {
    /**
     * Contains the amount of images that you see in the width
     */
    final private static int W = 2;

    /**
     * Contains the amount of images that you see in the height
     */
    final private static int H = 3;

    // Contains the element(s) for a file separator
    final private static String FS = System.getProperty("file.separator");

    // C:\Users\s165839\Documents\IdeaProjects\SoundBoard\src\image
    final private static String IMG_SRC = System.getProperty("user.dir") + getFS() + "src" + getFS() + "images" + getFS();

    // C:\Users\s165839\Documents\IdeaProjects\SoundBoard\src\audio
    final private static String AUDIO_SRC = System.getProperty("user.dir") + getFS() + "src" + getFS() + "audio" + getFS();


    public static int getW() {
        return W;
    }

    public static int getH() {
        return H;
    }

    public static String getFS() {
        return FS;
    }

    public static String getImgSrc() {
        return IMG_SRC;
    }

    public static String getAudioSrc() {
        return AUDIO_SRC;
    }
}