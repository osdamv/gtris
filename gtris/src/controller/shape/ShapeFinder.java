package controller.shape;

import java.util.HashSet;

import controller.GtrisModel;
import data.Square;
import data.Tuple;
/**
 * Find shapes in the squares data 
 * @author dvelazquez
 * @since 15/04/2013
 * 
 * 
 */
public class ShapeFinder {
    private HashSet<Square> hashFound = new HashSet<Square>(4);
    private Square[][] data;
    /**
     * Find an given figure from the start square 
     * @param startSquare where the search start
     * @param squares data 
     * @param figureInc the coordinates  of the figure 
     */
    public ShapeFinder(Square startSquare, Square[][] squares, Tuple<Integer, Integer>[] figureInc) {
	this.data = squares;
	hashFound.add(startSquare);
	Square buff = startSquare;
	for (Tuple<Integer, Integer> t : figureInc) {
	    buff = findSquare(buff.getPosX() + t.getX(), buff.getPosY() + t.getY());
	    if (isInValid(buff, startSquare))
		break;
	    hashFound.add(buff);
	}

    } 
    /**
     * check if the squares are valid to be marked as figure 
     */
    private boolean isInValid(Square buff, Square startSquare) {
	return buff == null || buff.getColor() != startSquare.getColor() || buff.isFalling() || buff.isSwapping()
		|| startSquare.isFalling() || buff.isFalling() || buff.isDeletable() || startSquare.isDeletable();
    }
    /**
     * gets  the figure found 
     * 
     */
    public HashSet<Square> getFound() {
	if (hashFound.size() != 4) {
	    hashFound.clear();
	}
	return hashFound;
    }
    
    protected Square findSquare(int posx, int posy) {
	return GtrisModel.findSquare(posx, posy, data);
    }

}
