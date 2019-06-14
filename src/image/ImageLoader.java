package image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;

/**
 * The ImageLoader is a <b>static</b> class providing tools to dynamically load images.
 *
 * @author Daniël Barenholz
 */
public class ImageLoader {

    // empty constructor for static class creation
    private ImageLoader() {
    }

    /**
     * Contains a mapping from a the name of an image (which is actually a path) to
     * the actual image.
     */
    private final static ConcurrentHashMap<String, BufferedImage> images = new ConcurrentHashMap<>();

    /**
     * Checks if {@code imageName} is contained within {@code images}.
     *
     * @param imageName the name of an image
     * @return {@code true} if image described by {@code imageName} is loaded in
     * memory.
     */
    private static boolean cachedImage(String imageName) {
        return images.contains(imageName);
    }

    /**
     * Adds an image with.
     */
    private static boolean addImage(String imageName) {
        // Check if image is cached
        if (cachedImage(imageName)) {
            return true;
        }

        // Try loading the image
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(imageName));
        } catch (IOException e) {
            return false;
        }

        // Store the image with imageName as name
        images.put(imageName, image);
        return true;
    }

    /**
     * Removes the image with name {@code imageName} from {@code images}.
     *
     * @param imageName the name of the image to remove.
     */
    public static void removeImage(String imageName) {
        images.remove(imageName);
    }

    /**
     * Gets the image stored under the name {@code imageName}.
     *
     * @param imageName the image to get.
     * @return the image stored under the name {@code imageName} or {@code null} if
     * there is no image with that name.
     */
    public static BufferedImage getImage(String imageName) {
        if (!cachedImage(imageName)) {
            addImage(imageName);
        }

        return images.get(imageName);
    }

    /**
     * Clears all images stored in {@code images}.
     */
    public static void clearImages() {
        images.clear();
    }
}
