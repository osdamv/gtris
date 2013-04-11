package data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import shape.Pair;
import shape.ShapeFinder;
import canvas.Square;

/**
 * Data model where is stored the information of the game
 * 
 * @author dvelazquez
 * @since 11/04/2013
 */
public class GtrisModel implements Serializable {

    private static final long serialVersionUID = -5847873452769942837L;
    public Set<Square> data = new ConcurrentSkipListSet<Square>();

    /**
     * Retrive the data
     * 
     * @return a Set of Data, the order doesn't matter in this case
     */
    public Set<Square> getData() {
	return data;
    }

    /**
     * Try to fall al squares
     */
    public void fallSquares() {
	for (Square s : data) {
	    dropSquare(s);
	}
    }

    /**
     * Drop a square a positon in y axis
     * 
     * @param Square
     *            to be drop
     * @return false if cannot be dropped anymore, true otherwise
     */
    public boolean dropSquare(Square square) {
	Square nextSquare = findNextSquare(square.getPosX(), square.getPosY() - 1);
	if (square.getPosY() == 0 || nextSquare != null) {
	    square.setFalling(false);
	    return false;
	}
	square.setFalling(true);
	square.decreasePosY();
	return true;
    }

    /**
     * Find the consecutive bottom square, of given coordinates
     * 
     * @param posx
     *            coordinate in x axis
     * @param posy
     *            coordinate in y axis
     * @return an square if found something, null otherwise
     */
    private Square findNextSquare(int posx, int posy) {
	for (Square s : data) {
	    if (s.isInPosition(posx, posy))
		return s;
	}
	return null;
    }

    class CubeFinder extends ShapeFinder {

	public CubeFinder(Square startSquare) {
	    super(startSquare);
	}

	@Override
	protected Square findSecond(Square square) {
	    return findNextSquare(square.getPosX() + 1, square.getPosY());
	}

	@Override
	protected Square findThird(Square square) {
	    return findNextSquare(square.getPosX(), square.getPosY() + 1);
	}

	@Override
	protected Square findFourth(Square square) {
	    return findNextSquare(square.getPosX() - 1, square.getPosY());
	}

    }

    public void searchCubes() {
	HashSet<Square> toBeErased = new HashSet<Square>();
	for (Square s : data) {
	    CubeFinder c = new CubeFinder(s);
	    toBeErased.addAll(c.getFound());
	}

	data.removeAll(toBeErased);
    }

    /**
     * Add squares to the data model
     * 
     * @param square
     *            to be added
     */
    public synchronized void add(Square square) {

	data.add(square);
    }

    /**
     * add a Pair shade
     * 
     * @param pair
     */
    public void add(Pair pair) {
	add(pair.getLeft());
	add(pair.getRight());
	dropSquare(pair.getLeft());
	dropSquare(pair.getRight());

    }

}
