package data;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;
/**
 * create a cache of images, speeding up all the game 
 * @author dvelazquez
 * @since 16/04/2013
 */
public class Images {
    private static final HashMap<String, Image> IMAGES = new HashMap<String, Image>();

    public static final Image getImage(String name) {
	Image img = IMAGES.get(name);
	if (img == null) {
	    URL url = ClassLoader.getSystemClassLoader().getResource(name);
	    try {
		img = ImageIO.read(url);
	    } catch (IOException e) {
		e.printStackTrace();
		img = null;
	    }
	    IMAGES.put(name, img);
	}

	return img;

    }
}
