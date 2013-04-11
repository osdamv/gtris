package data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.concurrent.ConcurrentSkipListSet;

import shape.Pair;
import canvas.Square;

public class GtrisModel implements Serializable {

	private static final long serialVersionUID = -5847873452769942837L;
	public ConcurrentSkipListSet<Square> data = new ConcurrentSkipListSet<Square>();

	public ConcurrentSkipListSet<Square> getData() {
		return data;
	}

	public void fallSquares() {
		for (Square s : data) {
			dropSquare(s);
		}
	}

	public  boolean dropSquare(Square square) {
		Square nextSquare = findNextSquare(square.getPosX(),
				square.getPosY() - 1);
		if (square.getPosY() == 0 || nextSquare != null) {
			square.setFalling(false);
			return false;
		}
		square.decreasePosY();
		return true;
	}

	private Square findNextSquare(int posx, int posy) {
		for (Square s : data) {
			if (s.isInPosition(posx, posy))
				return s;
		}
		return null;
	}

	public synchronized  void  add(Square square) {

		data.add(square);
	}

	public void add(Pair pair) {
		add(pair.getLeft());
		add(pair.getRight());
		dropSquare(pair.getLeft());
		dropSquare(pair.getRight());

	}

}
