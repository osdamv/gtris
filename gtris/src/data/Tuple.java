package data;
/**
 * Tuple helping class 
 * @author dvelazquez
 * @since 16/04/2013
 * @param <X>
 * @param <Y>
 */
public class Tuple<X, Y> {
    private X x;
    private Y y;

    public Tuple() {	
    }

    public Tuple(X x, Y y) {
	super();
	this.x = x;
	this.y = y;
    }

    public X getX() {
	return x;
    }

    public void setX(X x) {
	this.x = x;
    }

    public Y getY() {
	return y;
    }

    public void setY(Y y) {
	this.y = y;
    }
}
