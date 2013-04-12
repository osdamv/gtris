package controller.shape;

import java.util.HashSet;
import java.util.Set;

import controller.GtrisModel;
import data.Square;
import data.Tuple;

public class ShapeFinder {
    private HashSet<Square> hashFound = new HashSet<Square>(4);
    private Set<Square> data;

    public ShapeFinder(Square startSquare, Set<Square> data, Tuple<Integer, Integer> [] figureInc) {
	this.data = data;
	hashFound.add(startSquare);
	Square buff = startSquare;
	for (Tuple<Integer, Integer> x : figureInc) {
	    buff = findSquare(buff.getPosX() + x.getX(), buff.getPosY() + x.getY());
	    if (isInValid(buff, startSquare))
		break;
	    hashFound.add(buff);
	}

    }

    private boolean isInValid(Square buff, Square startSquare) {
	return buff == null || buff.getColor() != startSquare.getColor() || buff.isFalling() || buff.isSwaping();
    }

    public HashSet<Square> getFound() {
	if (hashFound.size() != 4) {
	    hashFound.clear();
	}
	return hashFound;
    }

    protected Square findSquare(int posx, int posy) {
	return GtrisModel.findNextSquare(posx, posy, data);
    }

}
