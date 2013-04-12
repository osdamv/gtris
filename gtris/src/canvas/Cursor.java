package canvas;

import java.awt.Image;

import data.Config;
import data.Images;
import data.Square;

public class Cursor extends Square {

    private static final long serialVersionUID = 6830138528385728405L;
    private static final Image img = Images.getImage("cursor.png");
    private Config config = Config.getInstance();
    private Square selectedSquare;

    public Cursor() {
	posY = 0;
    }

    public Image getImage() {
	return img;
    }

    public void up() {
	if (posY < config.getCanvasHeight() - 1)
	    posY++;
    }

    public void left() {
	if (posX > 0)
	    posX--;
    }

    public void right() {
	if (posX < config.getCanvasWidth() - 1)
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

    public Square getSelectedSquare() {
	return selectedSquare;
    }

    public void setSelectedSquare(Square selectedSquare) {
	this.selectedSquare = selectedSquare;
    }

    public boolean isValidSwap() {
	if (selectedSquare == null || selectedSquare.isFalling() || selectedSquare.isSwaping())
	    return false;
	boolean gapX = Math.abs(posX - selectedSquare.getPosX()) == 1 ? true : false;
	boolean gapY = Math.abs(posY - selectedSquare.getPosY()) == 1 ? true : false;

	return (gapX && !gapY) || (!gapX && gapY);
    }

}
