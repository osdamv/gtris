package canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import controller.GtrisModel;

import data.Config;
import data.Square;

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
	setBackground(new Color(120, 120, 120));
	setPreferredSize(dimension);
	setMaximumSize(dimension);
	setIgnoreRepaint(true);	
	setFocusable(true);
	setRequestFocusEnabled(true);
    }

    /**
     * draw of the squares represented in the model
     */
    @Override
    protected void paintComponent(Graphics g) {
 	super.paintComponent(g);
	for (Square s : model.getData()) {	    
	    g.drawImage(s.getColor().getImage(), s.getCoordX(), s.getCoordY(), null);
	}
 
    }
}
