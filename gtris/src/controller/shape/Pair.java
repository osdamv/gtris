package controller.shape;

import controller.GtrisController;
import data.Config;
import data.Square;

public class Pair {
    private Square left;
    private Square right;

    public Pair() {
	left = new Square();
	left.setFalling(true);
	left.setPosY(Config.getInstance().getCanvasHeight());
	left.setColor(GtrisController.getRandomColor());
	left.setPosX(GtrisController.getRandom(0, Config.getInstance().getCanvasWidth() - 1));
	right = new Square();
	right.setFalling(true);
	right.setPosY(Config.getInstance().getCanvasHeight());
	right.setColor(GtrisController.getRandomColor());
	right.setPosX(left.getPosX() + 1);
    }

    public Square getLeft() {
	return left;
    }

    public void setLeft(Square left) {
	this.left = left;
    }

    public Square getRight() {
	return right;
    }

    public void setRight(Square right) {
	this.right = right;
    }
}
