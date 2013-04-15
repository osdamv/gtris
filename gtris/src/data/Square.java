package data;

import java.awt.Image;
import java.io.Serializable;

/**
 * Square object, is an square in the game
 * 
 * @author dvelazquez
 * @since 11/04/2013
 */
public class Square implements Serializable, Comparable<Square> {

    private static final long serialVersionUID = -2097311988269411366L;
    private Config config = Config.getInstance();
    private Color color = null;
    protected int posX = 0;
    protected int posY = config.getCanvasHeight() - 1;
    private boolean falling = false;
    private int coordY = 0;
    private int coordX = 0;
    private boolean swaping = false;
    private boolean deletable = false;

    public int getPosX() {
	return posX;
    }

    protected int pixelMovment() {

	return 2 * config.getDropSpeed();
    }

    public Square setPosX(int posX) {
	this.posX = posX;
	return this;
    }

    public int getPosY() {
	return posY;
    }

    public void decreasePosY() {
	posY--;
    }

    public void setPosY(int posY) {
	this.posY = posY;
	falling = true;
    }

    public Color getColor() {
	return color;
    }

    public void setColor(Color color) {
	this.color = color;
    }

    public int getCoordX() {
	int target = posX * config.getSquareWidthPx();
	swaping = true;
	int dif = target - coordX;
	if (dif == 0)
	    swaping = false;
	else if (Math.abs(dif) < pixelMovment())
	    setInFinallCoordX();
	else if ((target - coordX) < 0)
	    coordX -= pixelMovment();
	else
	    coordX += pixelMovment();
	return coordX;

    }

    public int getCoordY() {
	int target = config.getCanvasHeightPx() - ((posY + 1) * config.getSquareHeightPx());
	falling = true;
	int dif = target - coordY;
	if (dif == 0)
	    falling = false;
	else if (Math.abs(dif) < pixelMovment())
	    setInFinallCoordY();
	else if ((target - coordY) < 0)
	    coordY -= pixelMovment();
	else
	    coordY += pixelMovment();
	return coordY;

    }

    public void setInFinallCoordY() {
	coordY = config.getCanvasHeightPx() - ((posY + 1) * config.getSquareHeightPx());
    }

    public void setInFinallCoordX() {
	coordX = posX * config.getSquareWidthPx();
    }

    public boolean isInPosition(int posx, int posy) {
	return posx == posX && posy == posY;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((color == null) ? 0 : color.hashCode());
	result = prime * result + posX;
	result = prime * result + posY;
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Square other = (Square) obj;
	if (color != other.color)
	    return false;
	if (posX != other.posX)
	    return false;
	if (posY != other.posY)
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Square [color=" + color + ", posX=" + posX + ", posY=" + posY + "]";
    }

    public boolean isFalling() {
	return falling;
    }

    public void setFalling(boolean falling) {
	this.falling = falling;
    }

    @Override
    public int compareTo(Square o) {
	if (equals(o))
	    return 0;
	if (hashCode() > o.hashCode())
	    return 1;
	return -1;
    }

    public Image getImage() {
	return color.getImage();
    }

    public boolean isSwaping() {
	return swaping;
    }

    public void setSwaping(boolean swaping) {
	this.swaping = swaping;
    }

    public boolean isDeletable() {
	return deletable;
    }

    public void setDeletable(boolean deletable) {
	this.deletable = deletable;
    }

    public void decreasePosY(Square[][] squares) {
	squares[posX][posY] = null;
	squares[posX][posY - 1] = this;
	decreasePosY();

    }

}
