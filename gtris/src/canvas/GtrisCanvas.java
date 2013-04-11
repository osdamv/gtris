package canvas;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import data.Config;
import data.GtrisModel;

/**
 * Canvas where the draw is performed
 * 
 * @author dvelazquez
 * @since 11/04/2013
 */
public class GtrisCanvas extends JPanel {
    private static final long serialVersionUID = -1503661829765315435L;

    private GtrisModel model;

    /**
     * Constructor
     * 
     * @param GtrisModel
     *            where the data is stored
     */
    public GtrisCanvas(GtrisModel model) {
	this.model = model;
	Dimension dimension = new Dimension(Config.getInstance().getCanvasWidthPx(), Config.getInstance()
		.getCanvasHeightPx());
	setPreferredSize(dimension);
	setMaximumSize(dimension);
    }

    /**
     * draw of the squares represented in the model
     */
    @Override
    public void paintComponent(Graphics g) {

	for (Square s : model.getData()) {
	    g.drawImage(s.getColor().getImage(), s.getCoordX(), s.getCoordY(), this);
	}

    }
}
