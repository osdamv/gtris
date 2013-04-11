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

    /**
     * Get representing image of the current color
     * 
     * @return an Image
     */
    public Image getImage() {
	URL path = ClassLoader.getSystemClassLoader().getResource(this.name().toLowerCase() + ".png");
	try {
	    return ImageIO.read(path);
	} catch (IOException e) {
	    e.printStackTrace();
	    return null;
	}
    }

}