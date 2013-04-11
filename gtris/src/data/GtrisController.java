package data;

import java.util.Random;

import javax.swing.JFrame;

import shape.Pair;
import canvas.Color;
import canvas.Square;

public class GtrisController {
    private GtrisModel model;

    public GtrisController(final JFrame window) {

	model = new GtrisModel();
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
	model.add(new Pair());

	new DecreasingThread(500, 0) {

	    @Override
	    public void run() {
		model.fallSquares();
		window.repaint();

	    }
	};

    }

    public GtrisModel getModel() {
	return model;
    }

    public static final int getRandom(int bottom, int top) {
	Random rnd = new Random(System.nanoTime());
	int value = rnd.nextInt(top);

	return value;
    }

    public static final Color getRandomColor() {
	return Color.valueOf((getRandom(0, 500) / 100));
    }

    public static final boolean getRandomBoolean() {
	return getRandom(0, 500) > 250;
    }
}
