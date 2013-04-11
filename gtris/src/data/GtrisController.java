package data;

import java.util.Random;

import javax.swing.JFrame;

import shape.Pair;
import canvas.Color;
import canvas.Square;

/**
 * Canvas and model controller
 * 
 * @author dvelazquez
 * @since 11/04/2013
 */
public class GtrisController {
    private GtrisModel model;

    /**
     * Constructor, initialize the model and threads(Timers)
     * 
     * @param model
     *            to be controlled
     * @param window
     *            to be repainted
     */
    public GtrisController(GtrisModel model, final JFrame window) {

	this.model = model;
	for (int y = 0; y < 5; y++)
	    for (int x = 0; x < Config.getInstance().getCanvasHeight(); x++) {
		if (getRandomBoolean())
		    continue;
		Square square = new Square();
		square.setColor(getRandomColor());
		square.setPosX(x);
		model.add(square);
		while (model.dropSquare(square))
		    ;
	    }

	new DecreasingThread(5000, -100) {

	    @Override
	    public void run() {
		GtrisController.this.model.add(new Pair());

	    }
	};

	new DecreasingThread(500, -1) {

	    @Override
	    public void run() {
		GtrisController.this.model.fallSquares();
		window.repaint();

	    }
	};

    }

    /**
     * get a range random
     * 
     * @param bottom
     *            limit
     * @param top
     *            limit
     * @return a random number in the given range
     */
    public static final int getRandom(int bottom, int top) {
	Random rnd = new Random(System.nanoTime());
	int value = rnd.nextInt(top);

	return value;
    }

    /**
     * gets a random color
     * 
     * @return a Color
     */
    public static final Color getRandomColor() {
	return Color.valueOf((getRandom(0, 500) / 100));
    }

    /**
     * gets a random boolean
     * 
     * @return a random boolean
     */
    public static final boolean getRandomBoolean() {
	return getRandom(0, 500) > 250;
    }
}
