package data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import canvas.Square;
import controller.shape.Pair;
import controller.shape.ShapeFinder;

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
	Square nextSquare = findNextSquare(square.getPosX(), square.getPosY() - 1, data);
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
    public static Square findNextSquare(int posx, int posy, Set<Square> data) {
	for (Square s : data) {
	    if (s.isInPosition(posx, posy))
		return s;
	}
	return null;
    }

    private static final HashMap<Integer, Integer> CUBE = new HashMap<Integer, Integer>();
    private static final HashMap<Integer, Integer> LINEH = new HashMap<Integer, Integer>();
    private static final HashMap<Integer, Integer> LINEV = new HashMap<Integer, Integer>();
    private static final HashMap<Integer, Integer> BLOCKB = new HashMap<Integer, Integer>();
    private static final HashMap<Integer, Integer> BLOCKB90 = new HashMap<Integer, Integer>();
    private static final HashMap<Integer, Integer> BLOCKB180 = new HashMap<Integer, Integer>();
    private static final HashMap<Integer, Integer> BLOCKB240 = new HashMap<Integer, Integer>();
    private static final HashMap<Integer, Integer> BLOCKD = new HashMap<Integer, Integer>();
    private static final HashMap<Integer, Integer> BLOCKD90 = new HashMap<Integer, Integer>();
    private static final HashMap<Integer, Integer> BLOCKD180 = new HashMap<Integer, Integer>();
    private static final HashMap<Integer, Integer> BLOCKD240 = new HashMap<Integer, Integer>();
    private static final HashMap<Integer, Integer> BLOCKZ = new HashMap<Integer, Integer>();
    private static final HashMap<Integer, Integer> BLOCKZH = new HashMap<Integer, Integer>();
    private static final HashMap<Integer, Integer> BLOCKZI = new HashMap<Integer, Integer>();
    private static final HashMap<Integer, Integer> BLOCKZIH = new HashMap<Integer, Integer>();
    static {
	/**
	 * ## 
	 * ##
	 */
	CUBE.put(1, 0);
	CUBE.put(0, 1);
	CUBE.put(-1, 0);
	/**
	 * ####
	 */
	LINEH.put(1, 0);
	LINEH.put(1, 0);
	LINEH.put(1, 0);
	/**
	 * #
	 * # 
	 * # 
	 * #
	 */
	LINEV.put(0, 1);
	LINEV.put(0, 1);
	LINEV.put(0, 1);
	/**
	 * # 
	 * # 
	 * ##
	 */
	BLOCKB.put(-1, 0);
	BLOCKB.put(0, 1);
	BLOCKB.put(0, 1);
	/**
	 * ### 
	 * #
	 */
	BLOCKB90.put(0, 1);
	BLOCKB90.put(1, 0);
	BLOCKB90.put(1, 0);
	/**
	 * ## 
	 *  # 
	 *  #
	 */
	BLOCKB180.put(1, 0);
	BLOCKB180.put(0, -1);
	BLOCKB180.put(0, -1);
	/**
	 *   #
	 * ###	 
	 */
	BLOCKB240.put(0, -1);
	BLOCKB240.put(1, 0);
	BLOCKB240.put(1, 0);
        /**
         *  #
         *  #
         * ##
         */
	BLOCKD.put(1, 0);
	BLOCKD.put(0, 1);
	BLOCKD.put(0, 1);
	/**
	 * #
	 * ###
	 */
	BLOCKD90.put(1, 0);
	BLOCKD90.put(0, 1);
	BLOCKD90.put(-1, 0);
	/**
	 * ##
	 * #
	 * #
	 */
	BLOCKD180.put(1, 0);
	BLOCKD180.put(0, 1);
	BLOCKD180.put(-1, 0);
	/**
	 * ###
	 *   #
	 */
	BLOCKD240.put(1, 0);
	BLOCKD240.put(0, 1);
	BLOCKD240.put(-1, 0);
	/**
	 *  #
	 * ##
	 * #
	 */
	BLOCKZ.put(1, 0);
	BLOCKZ.put(0, 1);
	BLOCKZ.put(-1, 0);
	/**
	 * ##
	 *  ##
	 */
	BLOCKZI.put(1, 0);
	BLOCKZI.put(0, 1);
	BLOCKZI.put(-1, 0);
	/**
	 * #
	 * ##
	 *  #
	 */
	BLOCKZH.put(1, 0);
	BLOCKZH.put(0, 1);
	BLOCKZH.put(-1, 0);
	/**
	 *  ##
	 * ##
	 */
	BLOCKZIH.put(1, 0);
	BLOCKZIH.put(0, 1);
	BLOCKZIH.put(-1, 0);
    }

    public void searchCubes() {
	HashSet<Square> toBeErased = new HashSet<Square>();
	for (Square s : data) {
	    HashMap<Integer, Integer> mov = new HashMap<Integer, Integer>();
	    mov.put(1, 0);
	    mov.put(0, 1);
	    mov.put(-1, 0);

	    ShapeFinder shape = new ShapeFinder(s, data, mov);
	    toBeErased.addAll(shape.getFound());

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
