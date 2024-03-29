package canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;

import controller.GtrisModel;
import data.Config;
import data.Images;
import data.Square;

/**
 * Canvas where the draw is performed
 * 
 * @author dvelazquez
 * @since 11/04/2013
 */
public class GtrisCanvas extends JComponent {
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
		.getCanvasHeightPx() + 5);
	setPreferredSize(dimension);
	setMaximumSize(dimension);
	setIgnoreRepaint(true);
	setFocusable(true);
	setRequestFocusEnabled(true);
	setLayout(new FlowLayout());
    }

    private static final Image img = Images.getImage("delete.png");

    /**
     * draw of the squares, score and cursor
     */
    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	Cursor cursor = model.getCursor();
	for (Square[] l : model.getSquares())
	    for (Square s : l)
		if (s != null) {
		    if (!s.isSwapping())
			s.setInFinallCoordX();
		    g.drawImage(s.getImage(), s.getCoordX(), s.getCoordY(), this);
		    if (s.isDeletable()) {
			g.drawImage(img, s.getCoordX(), s.getCoordY(), this);
		    }
		}
	g.drawImage(cursor.getImage(), cursor.getCoordX(), cursor.getCoordY(), this);
	g.setColor(Color.WHITE);
	g.drawString("Points: " + model.getPoints(), 10, 10);	
	
    }
}
