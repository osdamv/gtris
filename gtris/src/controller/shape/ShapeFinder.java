package controller.shape;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import canvas.Square;
import data.GtrisModel;

public class ShapeFinder {
    private HashSet<Square> hashFound = new HashSet<Square>(4);
    private Set<Square> data;

    public ShapeFinder(Square startSquare, Set<Square> data, HashMap<Integer, Integer> figureInc) {
	this.data = data;
	hashFound.add(startSquare);
	Square buff = startSquare;
	for (int x : figureInc.keySet()) {
	    buff = findSquare(buff.getPosX() + x, buff.getPosY() + figureInc.get(x));
	    if (isInValid(buff, startSquare))
		break;
	    hashFound.add(buff);
	}

    }

    private boolean isInValid(Square buff, Square startSquare) {
	return buff == null || buff.getColor() != startSquare.getColor();
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
