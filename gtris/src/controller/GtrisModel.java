package controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

import canvas.Cursor;
import controller.shape.Pair;
import controller.shape.ShapeFinder;
import data.Config;
import data.Sound;
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
    private Config config = Config.getInstance();
    public Square[][] squares = new Square[config.getCanvasWidth()][config.getCanvasHeight()];
    private Cursor cursor = new Cursor();
    private long points = 0;
    private long initTime = System.currentTimeMillis();

    /**
     * Retrieve the data
     * 
     * @return a Set of Data, the order doesn't matter in this case
     */
    public Square[][] getSquares() {
	return squares;
    }

    /**
     * Try to fall al squares
     */
    public void fallSquares() {
	for (Square[] l : squares)
	    for (Square s : l)
		if (s != null)
		    dropSquare(s);
    }

    /**
     * Drop a square a positon in y axis
     * 
     * @param Square
     *            to be drop
     * @return false if cannot be dropped anymore, true otherwise
     */
    public boolean dropSquare(Square square) {
	Square nextSquare = findSquare(square.getPosX(), square.getPosY() - 1, squares);
	if (square.getPosY() == 0 || nextSquare != null)
	    return false;
	square.decreasePosY(squares);

	return true;
    }

    /**
     * Find an square
     * 
     * @param posx
     *            coordinate in x axis
     * @param posy
     *            coordinate in y axis
     * @return an square if found something, null otherwise
     */
    public static final Square findSquare(int posx, int posy, Square[][] data) {
	try {
	    return data[posx][posy];
	} catch (IndexOutOfBoundsException e) {
	    return null;
	}
    }

    /**
     * figures and points map
     */
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
    /**
     * mark elements who can be deleted, and update the game points 
     */
    public synchronized void markDeletable() {
	HashSet<Square> toBeErased = new HashSet<Square>();
	for (Square[] l : squares) {
	    for (Square s : l)
		if (s != null)
		    for (String tupleKey : shapes.keySet()) {
			ShapeFinder shape = new ShapeFinder(s, squares, shapes.get(tupleKey));
			toBeErased.addAll(shape.getFound());
			if (!toBeErased.isEmpty()) {
			    for (Square e : toBeErased) {
				e.setDeletable(true);
			    }
			    calculatePoints(tupleKey);
			    break;
			}
		    }
	    if (!toBeErased.isEmpty())
		break;
	}
    }
    /**
     * get the current game points
     * 
     */
    private void calculatePoints(String shade) {
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

	squares[square.getPosX()][square.getPosY()] = square;
    }

    public synchronized void add(Cursor cursor) {
	this.cursor = cursor;
    }

    /**
     * add a Pair shade
     * 
     * @param pair
     */
    public boolean add(Pair pair) {
	if (isAvailable(pair.getLeft()) && isAvailable(pair.getRight())) {
	    add(pair.getLeft());
	    add(pair.getRight());
	    return true;
	}
	for (int x = 0; x < config.getCanvasWidth() - 1; x++) {
	    if (isAvailable(x, config.getCanvasHeight() - 1) && isAvailable(x + 1, config.getCanvasHeight() - 1)) {
		pair.getLeft().setPosX(x);
		pair.getRight().setPosX(x + 1);
		add(pair.getLeft());
		add(pair.getRight());
		return true;
	    }
	}

	return false;
    }
    /**
     * check if the given location is available  
     * 
     */
    private boolean isAvailable(Square s) {
	return isAvailable(s.getPosX(), s.getPosY());
    }
    /**
     * check if the given location is available  
     * 
     */
    private boolean isAvailable(int posx, int posy) {
	return findSquare(posx, posy, squares) == null;
    }
    
    public Cursor getCursor() {
	return cursor;
    }
    /**
     * perform the swap action 
     */
    public synchronized void swapSquare() {
	Square currentSquare = findSquare(cursor.getPosX(), cursor.getPosY(), squares);
	if (cursor.isValidSwap() && currentSquare != null && !currentSquare.isDeletable()) {
	    Square selected = cursor.getSelectedSquare();
	    int sx = selected.getPosX();
	    int sy = selected.getPosY();
	    selected.setPosX(currentSquare.getPosX());
	    selected.setPosY(currentSquare.getPosY());
	    currentSquare.setPosX(sx);
	    currentSquare.setPosY(sy);
	    selected.setSwapping(true);
	    currentSquare.setSwapping(true);
	    add(currentSquare);
	    add(selected);
	} else {
	    cursor.setSelectedSquare(currentSquare);
	}
    }
    /**
     * delete elements marked as deletable 
     */
    public synchronized void performDelete() {
	for (Square[] l : squares)
	    for (Square s : l)
		if (s != null && s.isDeletable()) {
		    squares[s.getPosX()][s.getPosY()] = null;
		    Sound.playPuff();
		}

    }

    public long getPoints() {
	return points;
    }
    
    /**
     * set the cursor position in the given coordinates
     * 
     */
    public void setCursorPosition(int coordX, int coordY) {
	int posx = coordX / config.getSquareWidthPx();
	int posy = (config.getCanvasHeightPx() - coordY) / config.getSquareHeightPx();
	cursor.setPosX(posx);
	cursor.setPosY(posy);

    }

}
