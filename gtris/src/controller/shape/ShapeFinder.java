package controller.shape;

import java.util.HashSet;

import canvas.Square;

public abstract class ShapeFinder {
    private HashSet<Square> hashFound = new HashSet<Square>(4);

    public ShapeFinder(Square startSquare) {
	hashFound.add(startSquare);
	Square buff = findSecond(startSquare);
	if (isInValid(buff, startSquare))
	    return;
	hashFound.add(buff);
	buff = findThird(buff);
	if (isInValid(buff, startSquare))
	    return;
	hashFound.add(buff);
	buff = findFourth(buff);
	if (isInValid(buff, startSquare))
	    return;
	hashFound.add(buff);

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

    protected abstract Square findSecond(Square square);

    protected abstract Square findThird(Square square);

    protected abstract Square findFourth(Square square);
}
