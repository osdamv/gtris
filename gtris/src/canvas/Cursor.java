package canvas;

import java.awt.Image;

import data.Config;
import data.Images;
import data.Square;
/**
 * Cursor class
 * @author dvelazquez
 * @since 15/04/2013
 */
public class Cursor extends Square {

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
    /**
     * the animation speed of the cursor is different
     */
    @Override
    protected int pixelMovment() {
	return 16;
    }

    public Square getSelectedSquare() {
	return selectedSquare;
    }

    public void setSelectedSquare(Square selectedSquare) {
	this.selectedSquare = selectedSquare;
    }
    /**
     * Check if is possible perform a swap
     * 
     */
    public boolean isValidSwap() {
	if (selectedSquare == null || selectedSquare.isFalling() || selectedSquare.isSwapping() || selectedSquare.isDeletable())
	    return false;
	int gapX = Math.abs(posX - selectedSquare.getPosX());
	int gapY = Math.abs(posY - selectedSquare.getPosY()) ;

	return (gapX==1 && gapY==0) || (gapX==0 && gapY==1);
    }

}
