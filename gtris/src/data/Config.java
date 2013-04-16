package data;

/**
 * Singleton configuration of the game
 * 
 * @author dvelazquez
 * @since 11/04/2013
 */
public final class Config {
    /**
     * Squares size
     */
    private int squareHeightPx = 32;
    private int squareWidthPx = 32;
    /**
     * canvas height and with in game squares(not pixels)
     */
    private int canvasHeight = 10;
    private int canvasWidth = 10;
    /**
     * time passed between in order to add a pair of squares
     */
    private long drawTime = 120000;
    /**
     * initial rows added 
     */
    private int initialFill = 10;
    /**
     * Drop speed multiplier 
     */
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
    public void setDropSpeed( int dropSpeed) {
	this.dropSpeed=dropSpeed;
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
