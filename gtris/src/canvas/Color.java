package canvas;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * Enumerated with the available colors
 * 
 * @author dvelazquez
 * @since 11/04/2013
 */
public enum Color {
    BLUE, GREEN, PINK, RED, YELLOW;
    /**
     * Get a color from a integer
     * 
     * @param value
     *            integer between 0 and 4
     * @return a color or null if the value is not between 0-4
     */
    public static Color valueOf(int value) {

	switch (value) {
	case 1:
	    return BLUE;
	case 2:
	    return GREEN;
	case 3:
	    return PINK;
	case 4:
	    return RED;
	case 0:
	    return YELLOW;
	}
	return null;
    }

    private static final Image IMAGEBLUE;
    private static final Image IMAGEGREEN;
    private static final Image IMAGEPINK;
    private static final Image IMAGERED;
    private static final Image IMAGEYELLOW;
    static {
	IMAGEBLUE = initImage("blue.png");
	IMAGEGREEN = initImage("green.png");
	IMAGEPINK = initImage("pink.png");
	IMAGERED = initImage("red.png");
	IMAGEYELLOW = initImage("yellow.png");
    }

    private static final Image initImage(String name) {
	URL url = ClassLoader.getSystemClassLoader().getResource(name);
	try {
	    return ImageIO.read(url);
	} catch (IOException e) {
	    e.printStackTrace();
	    return null;
	}
    }

    /**
     * Get representing image of the current color
     * 
     * @return an Image
     */
    public Image getImage() {
	switch (this) {
	case BLUE:
	    return IMAGEBLUE;
	case GREEN:
	    return IMAGEGREEN;
	case PINK:
	    return IMAGEPINK;
	case RED:
	    return IMAGERED;
	case YELLOW:
	    return IMAGEYELLOW;
	}
	return null;
    }

}