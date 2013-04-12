package canvas;

import java.awt.Image;

import data.Color;
import data.Config;
import data.Square;

public class Cursor extends Square {

    private static final long serialVersionUID = 6830138528385728405L;
    private static final Image img = Color.initImage("cursor.png");
    private Config config = Config.getInstance();

    public Cursor() {
	posY = 0;
    }

    public Image getImage() {
	return img;
    }

    public void up() {
	if (posY < config.getCanvasHeight()-1)
	    posY++;
    }

    public void left() {
	if (posX >0 )
	    posX--;
    }

    public void right() {
	if (posX < config.getCanvasWidth()-1)
	    posX++;
	
    }

    public void down() {
	if (posY > 0)
	    posY--;
	
    }

    @Override
    protected int pixelMovment() {
	return 8;
    }

}
