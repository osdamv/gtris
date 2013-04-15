package controller.shape;

import java.util.HashSet;

import controller.GtrisModel;
import data.Square;
import data.Tuple;

public class ShapeFinder {
    private HashSet<Square> hashFound = new HashSet<Square>(4);
    private Square[][] data;

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

    private boolean isInValid(Square buff, Square startSquare) {
	return buff == null || buff.getColor() != startSquare.getColor() || buff.isFalling() || buff.isSwaping()
		|| startSquare.isFalling() || buff.isFalling() || buff.isDeletable() || startSquare.isDeletable();
    }

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
