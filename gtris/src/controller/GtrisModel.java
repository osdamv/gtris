package controller;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import canvas.Cursor;
import controller.shape.Pair;
import controller.shape.ShapeFinder;
import data.Config;
import data.Square;
import data.Tuple;

/**
 * Data model where is stored the information of the game
 * 
 * @author dvelazquez
 * @since 11/04/2013
 */
@SuppressWarnings("unchecked")
public class GtrisModel implements Serializable {

    private static final long serialVersionUID = -5847873452769942837L;
    public Set<Square> squares = Collections.newSetFromMap(new ConcurrentHashMap<Square,Boolean>());
    private Cursor cursor = new Cursor();
    private long points = 0;
    private long initTime = System.currentTimeMillis();

    /**
     * Retrieve the data
     * 
     * @return a Set of Data, the order doesn't matter in this case
     */
    public Set<Square> getSquares() {
	return squares;
    }

    /**
     * Try to fall al squares
     */
    public void fallSquares() {
	for (Square s : squares) {
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
	Square nextSquare = findNextSquare(square.getPosX(), square.getPosY() - 1, squares);
	if (square.getPosY() == 0 || nextSquare != null)
	    return false;
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
    public static final Square findNextSquare(int posx, int posy, Set<Square> data) {
	for (Square s : data) {
	    if (s.isInPosition(posx, posy))
		return s;
	}
	return null;
    }

    private static final HashMap<String, Tuple<Integer, Integer>[]> shapes = new HashMap<String, Tuple<Integer, Integer>[]>();
    private static final HashMap<String, Integer> pointsTable = new HashMap<String, Integer>();
    static {
	Tuple<Integer, Integer>[] cube = new Tuple[3];
	Tuple<Integer, Integer>[] lineh = new Tuple[3];
	Tuple<Integer, Integer>[] linev = new Tuple[3];
	Tuple<Integer, Integer>[] blockb = new Tuple[3];
	Tuple<Integer, Integer>[] blockb90 = new Tuple[3];
	Tuple<Integer, Integer>[] blockb180 = new Tuple[3];
	Tuple<Integer, Integer>[] blockb240 = new Tuple[3];
	Tuple<Integer, Integer>[] blockd = new Tuple[3];
	Tuple<Integer, Integer>[] blockd90 = new Tuple[3];
	Tuple<Integer, Integer>[] blockd240 = new Tuple[3];
	Tuple<Integer, Integer>[] blockzi = new Tuple[3];
	Tuple<Integer, Integer>[] blockzh = new Tuple[3];
	Tuple<Integer, Integer>[] blockzih = new Tuple[3];
	Tuple<Integer, Integer>[] blockz = new Tuple[3];
	Tuple<Integer, Integer>[] blockd180 = new Tuple[3];

	/**
	 * ## ##
	 */
	cube[0] = new Tuple<Integer, Integer>(1, 0);
	cube[1] = new Tuple<Integer, Integer>(0, 1);
	cube[2] = new Tuple<Integer, Integer>(-1, 0);
	/**
	 * ####
	 */
	lineh[0] = new Tuple<Integer, Integer>(1, 0);
	lineh[1] = new Tuple<Integer, Integer>(1, 0);
	lineh[2] = new Tuple<Integer, Integer>(1, 0);
	/**
	 * # # # #
	 */
	linev[0] = new Tuple<Integer, Integer>(0, 1);
	linev[1] = new Tuple<Integer, Integer>(0, 1);
	linev[2] = new Tuple<Integer, Integer>(0, 1);
	/**
	 * # # ##
	 */
	blockb[0] = new Tuple<Integer, Integer>(-1, 0);
	blockb[1] = new Tuple<Integer, Integer>(0, 1);
	blockb[2] = new Tuple<Integer, Integer>(0, 1);
	/**
	 * ### #
	 */
	blockb90[0] = new Tuple<Integer, Integer>(0, 1);
	blockb90[1] = new Tuple<Integer, Integer>(1, 0);
	blockb90[2] = new Tuple<Integer, Integer>(1, 0);
	/**
	 * ## # #
	 */
	blockb180[0] = new Tuple<Integer, Integer>(1, 0);
	blockb180[1] = new Tuple<Integer, Integer>(0, -1);
	blockb180[2] = new Tuple<Integer, Integer>(0, -1);
	/**
	 * # ###
	 */
	blockb240[0] = new Tuple<Integer, Integer>(1, 0);
	blockb240[1] = new Tuple<Integer, Integer>(1, 0);
	blockb240[2] = new Tuple<Integer, Integer>(0, 1);
	/**
	 * # # ##
	 */
	blockd[0] = new Tuple<Integer, Integer>(1, 0);
	blockd[1] = new Tuple<Integer, Integer>(0, 1);
	blockd[2] = new Tuple<Integer, Integer>(0, 1);
	/**
	 * # ###
	 */
	blockd90[0] = new Tuple<Integer, Integer>(0, -1);
	blockd90[1] = new Tuple<Integer, Integer>(1, 0);
	blockd90[2] = new Tuple<Integer, Integer>(1, 0);
	/**
	 * ## # #
	 */
	blockd180[0] = new Tuple<Integer, Integer>(-1, 0);
	blockd180[1] = new Tuple<Integer, Integer>(0, -1);
	blockd180[2] = new Tuple<Integer, Integer>(0, -1);
	/**
	 * ### #
	 */
	blockd240[0] = new Tuple<Integer, Integer>(1, 0);
	blockd240[1] = new Tuple<Integer, Integer>(1, 0);
	blockd240[2] = new Tuple<Integer, Integer>(0, -1);

	/**
	 * # ## #
	 */
	blockz[0] = new Tuple<Integer, Integer>(0, 1);
	blockz[1] = new Tuple<Integer, Integer>(1, 0);
	blockz[2] = new Tuple<Integer, Integer>(0, 1);
	/**
	 * ## ##
	 */
	blockzi[0] = new Tuple<Integer, Integer>(1, 0);
	blockzi[1] = new Tuple<Integer, Integer>(0, -1);
	blockzi[2] = new Tuple<Integer, Integer>(1, 0);
	/**
	 * # ## #
	 */
	blockzh[0] = new Tuple<Integer, Integer>(0, 1);
	blockzh[1] = new Tuple<Integer, Integer>(-1, 0);
	blockzh[2] = new Tuple<Integer, Integer>(0, 1);
	/**
	 * ## ##
	 */
	blockzih[0] = new Tuple<Integer, Integer>(1, 0);
	blockzih[1] = new Tuple<Integer, Integer>(0, 1);
	blockzih[2] = new Tuple<Integer, Integer>(1, 0);
	shapes.put("cube", cube);
	shapes.put("lineh", lineh);
	shapes.put("linev", linev);
	shapes.put("blockb", blockb);
	shapes.put("blockb90", blockb90);
	shapes.put("blockb180", blockb180);
	shapes.put("blockb240", blockb240);
	shapes.put("blockd", blockd);
	shapes.put("blockd90", blockd90);
	shapes.put("blockd180", blockd180);
	shapes.put("blockd240", blockd240);
	shapes.put("blockz", blockz);
	shapes.put("blockzh", blockzh);
	shapes.put("blockzi", blockzi);
	shapes.put("blockzih", blockzih);

	pointsTable.put("cube", 10);
	pointsTable.put("lineh", 8);
	pointsTable.put("linev", 7);
	pointsTable.put("blockb", 9);
	pointsTable.put("blockb90", 10);
	pointsTable.put("blockb180", 11);
	pointsTable.put("blockb240", 12);
	pointsTable.put("blockd", 6);
	pointsTable.put("blockd90", 7);
	pointsTable.put("blockd180", 8);
	pointsTable.put("blockd240", 10);
	pointsTable.put("blockz", 15);
	pointsTable.put("blockzh", 16);
	pointsTable.put("blockzi", 15);
	pointsTable.put("blockzih", 16);

    }

    public synchronized void markDeletable() {
	HashSet<Square> toBeErased = new HashSet<Square>();
	for (Square s : squares) {
	    for (String tupleKey : shapes.keySet()) {
		ShapeFinder shape = new ShapeFinder(s, squares, shapes.get(tupleKey));
		toBeErased.addAll(shape.getFound());
		if (!toBeErased.isEmpty()) {
		    for (Square e : toBeErased) {
			e.setDeletable(true);
		    }
		    calculatePunctuation(tupleKey);
		    break;
		}
	    }
	    if (!toBeErased.isEmpty())
		break;
	}
	System.err.println(points);

    }

    private void calculatePunctuation(String shade) {
	points += pointsTable.get(shade);
	long diffTime = (System.currentTimeMillis() - initTime) / 10000;
	points += diffTime * 20;
	initTime = System.currentTimeMillis();
    }

    /**
     * Add squares to the data model
     * 
     * @param square
     *            to be added
     */
    public synchronized void add(Square square) {

	squares.add(square);
    }

    public synchronized void add(Cursor cursor) {
	this.setCursor(cursor);
    }

    /**
     * add a Pair shade
     * 
     * @param pair
     */
    public boolean add(Pair pair) {
	Square l = findNextSquare(pair.getLeft().getPosX(), pair.getLeft().getPosY(), squares);
	Square r = findNextSquare(pair.getRight().getPosX(), pair.getRight().getPosY(), squares);
	if ((l == null && r == null)) {
	    add(pair.getLeft());
	    add(pair.getRight());
	    dropSquare(pair.getLeft());
	    dropSquare(pair.getRight());
	    return true;
	} else {
	    for (int x = 0; x < Config.getInstance().getCanvasWidth(); x += 2) {
		l = findNextSquare(x, pair.getLeft().getPosY(), squares);
		r = findNextSquare(x + 1, pair.getRight().getPosY(), squares);
		if (l == null && r == null) {
		    dropSquare(pair.getLeft().setPosX(x));
		    dropSquare(pair.getRight().setPosX(x+1));
		    return true;
		}

	    }
	    return false;
	}

    }

    public Cursor getCursor() {
	return cursor;
    }

    public void setCursor(Cursor cursor) {
	this.cursor = cursor;
    }

    public synchronized void swapSquare() {
	Square currentSquare = findNextSquare(cursor.getPosX(), cursor.getPosY(), squares);
	if (cursor.isValidSwap() && currentSquare != null) {
	    Square selected = cursor.getSelectedSquare();
	    int sx = selected.getPosX();
	    int sy = selected.getPosY();
	    selected.setPosX(currentSquare.getPosX());
	    selected.setPosY(currentSquare.getPosY());
	    currentSquare.setPosX(sx);
	    currentSquare.setPosY(sy);
	    selected.setSwaping(true);
	    currentSquare.setSwaping(true);
	    cursor.setSelectedSquare(null);
	    add(currentSquare);
	    add(selected);
	} else {
	    cursor.setSelectedSquare(currentSquare);
	}
    }

    public synchronized void performDelete() {
	Iterator<Square> itera = squares.iterator();
	while (itera.hasNext()) {
	    if (itera.next().isDeletable())
		itera.remove();
	}

    }

    public long getPoints() {
	return points;
    }

}
