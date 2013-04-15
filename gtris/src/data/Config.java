package data;

/**
 * Singleton configuration of the game
 * 
 * @author dvelazquez
 * @since 11/04/2013
 */
public final class Config {
    private int squareHeightPx = 32;
    private int squareWidthPx = 32;
    /**
     * this data is in game units, no pixels
     */
    private int canvasHeight = 20;
    private int canvasWidth = 20;
    private long drawTime = 120000;
    private int initialFill = 9;
    private int dropSpeed = 1;

    private static final Config config = new Config();

    private Config() {
    }

    public int getCanvasHeightPx() {
	return squareHeightPx * canvasHeight;
    }

    public int getCanvasWidthPx() {
	return squareWidthPx * canvasWidth;
    }

    public static final Config getInstance() {
	return config;
    }

    public int getSquareHeightPx() {
	return squareHeightPx;
    }

    public void setSquareHeightPx(int squareHeightPx) {
	this.squareHeightPx = squareHeightPx;
    }

    public int getSquareWidthPx() {
	return squareWidthPx;
    }

    public void setSquareWidthPx(int squareWidthPx) {
	this.squareWidthPx = squareWidthPx;
    }

    public int getCanvasHeight() {
	return canvasHeight;
    }

    public void setCanvasHeight(int canvasHeight) {
	this.canvasHeight = canvasHeight;
    }

    public int getCanvasWidth() {
	return canvasWidth;
    }

    public int getCanvasArea() {
	return canvasHeight * canvasWidth;
    }

    public void setCanvasWidth(int canvasWidth) {
	this.canvasWidth = canvasWidth;
    }

    public long getDrawTime() {
	return drawTime;
    }

    public void setDrawTime(long drawTime) {
	this.drawTime = drawTime;
    }

    public int getInitialFill() {
	return initialFill;
    }

    public void setInitialFill(int initialFill) {
	this.initialFill = initialFill;
    }

    public int getDropSpeed() {
	return dropSpeed;
    }

    
    public void speedUp(int multipler) {
	if(multipler==0)
	    multipler=1;
	if(multipler>3)
	    return;
	if(dropSpeed==8)
	    return;
	dropSpeed=(int) Math.pow(2, multipler);
    }

}
