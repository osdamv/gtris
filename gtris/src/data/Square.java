package data;

import java.io.Serializable;



/**
 * Square object, is an square in the game
 * 
 * @author dvelazquez
 * @since 11/04/2013
 */
public class Square implements Serializable, Comparable<Square> {

    private static final long serialVersionUID = -2097311988269411366L;

    private Color color = null;
    private int posX = 0;
    private int posY = Config.getInstance().getCanvasHeight() - 1;
    private boolean falling = false;
    private int coordY = 0;

    public int getPosX() {
	return posX;
    }

    public void setPosX(int posX) {
	this.posX = posX;
    }

    public int getPosY() {
	return posY;
    }

    public void decreasePosY() {
	posY--;
    }

    public void setPosY(int posY) {
	this.posY = posY;
    }

    public Color getColor() {
	return color;
    }

    public void setColor(Color color) {
	this.color = color;
    }

    public int getCoordX() {
	return posX * Config.getInstance().getSquareWidthPx();
    }

    public int getCoordY() {
	int target = Config.getInstance().getCanvasHeightPx() - ((posY + 1) * Config.getInstance().getSquareHeightPx());
	falling=true;
	if ((target - coordY) == 0)
	    falling=false;
	else if ((target - coordY) < 0)
	    coordY-=2;
	else
	    coordY+=2;
	return coordY;

    }

    public void setInFinallCoordY() {
	coordY = Config.getInstance().getCanvasHeightPx() - ((posY + 1) * Config.getInstance().getSquareHeightPx());
    }

    public boolean isInPosition(int posx, int posy) {
	return posx == posX && posy == posY;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
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

}
