package canvas;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public enum Color {
    BLUE, GREEN, PINK, RED, YELLOW;

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