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

    private Color color = null;
    protected int posX = 0;
    protected int posY = Config.getInstance().getCanvasHeight() - 1;
    private boolean falling = false;
    private int coordY = 0;
    private int coordX = 0;
    
    
     
    public int getPosX() {
	return posX;
    }
    protected int pixelMovment(){
	return 2;
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
	int target = posX * Config.getInstance().getSquareWidthPx();
	falling=true;
	if ((target - coordX) == 0)
	    ;
	else if ((target - coordX) < 0)
	    coordX-=pixelMovment();
	else
	    coordX+=pixelMovment();
	return coordX;
	
	
    }

    public int getCoordY() {
	int target = Config.getInstance().getCanvasHeightPx() - ((posY + 1) * Config.getInstance().getSquareHeightPx());
	falling=true;
	if ((target - coordY) == 0)
	    falling=false;
	else if ((target - coordY) < 0)
	    coordY-=pixelMovment();
	else
	    coordY+=pixelMovment();
	return coordY;

    }

    public void setInFinallCoordY() {
	coordY = Config.getInstance().getCanvasHeightPx() - ((posY + 1) * Config.getInstance().getSquareHeightPx());
    }
    public void setInFinallCoordX(){
	coordX=posX * Config.getInstance().getSquareWidthPx();
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

    public Image getImage() {
	return color.getImage();
    }

}
