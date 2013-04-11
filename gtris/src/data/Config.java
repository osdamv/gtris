package data;

public final class Config {
    private int squareHeightPx = 32;
    private int squareWidthPx = 32;
    private int canvasHeight = 20;
    private int canvasWidth = 8;
    private long drawTime = 120000;

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

    public void setCanvasWidth(int canvasWidth) {
	this.canvasWidth = canvasWidth;
    }

    public long getDrawTime() {
	return drawTime;
    }

    public void setDrawTime(long drawTime) {
	this.drawTime = drawTime;
    }
}
