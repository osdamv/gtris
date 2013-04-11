package canvas;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import data.Config;
import data.GtrisController;
import data.GtrisModel;

public class GtrisCanvas extends JPanel {
    private static final long serialVersionUID = -1503661829765315435L;

    private GtrisController controller;

    public GtrisCanvas(GtrisController controller) {
	this.controller = controller;
	Dimension dimension = new Dimension(Config.getInstance().getCanvasWidthPx(), Config.getInstance()
		.getCanvasHeightPx());
	setPreferredSize(dimension);
	setMaximumSize(dimension);
    }

    @Override
    public void paintComponent(Graphics g) {
	GtrisModel model = controller.getModel();
	for (Square s : model.getData()) {
	    g.drawImage(s.getColor().getImage(), s.getCoordX(), s.getCoordY(), this);
	}

    }
}
