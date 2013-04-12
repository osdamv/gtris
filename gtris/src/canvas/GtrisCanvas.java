package canvas;

import java.awt.Dimension;
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
		.getCanvasHeightPx());
	setPreferredSize(dimension);
	setMaximumSize(dimension);
	setIgnoreRepaint(true);
	setFocusable(true);
	setRequestFocusEnabled(true);
    }
    private static final Image img=Images.getImage("delete.png");
    /**
     * draw of the squares represented in the model
     */
    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	Cursor cursor = model.getCursor();
	for (Square s : model.getSquares()) {
	    if (!s.isSwaping())
		s.setInFinallCoordX();
	    g.drawImage(s.getImage(), s.getCoordX(), s.getCoordY(), this);
	    if(s.isDeletable()){
		g.drawImage(img, s.getCoordX(), s.getCoordY(), this);
	    }
	}
	g.drawImage(cursor.getImage(), cursor.getCoordX(), cursor.getCoordY(), this);

    }
}
