package gamePackage;

public class Wall extends GameObject {
	private double xPosition;
	private double yPosition;
	private double width;
	private double height;
	private double wallSpeed = 1.5;
	
	public boolean up, down;
	
	/**
	 * Constructor
	 */
	public Wall(double x, double y, double w, double h) {
		this.xPosition = x;
		this.yPosition = y;
		this.width = w;
		this.height = h;
		this.up = true;
		this.down = false;
		
	}
	
	/**
	 * moveWall()
	 * moves the walls up or down
	 */
	void moveWall() {
		if (up) {
			yPosition -= wallSpeed;
		}
		if (down) {
			yPosition += wallSpeed;
		}
	}
	
	/**
	 * getX()
	 * Returns the wall's x position (top left corner)
	 */
	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return this.xPosition;
	}

	/**
	 * getY()
	 * Returns the wall's y position (top left corner)
	 */
	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return this.yPosition;
	}

	/**
	 * setLocation(double x, double y)
	 * Returns the wall's x and y coords (top left corner)
	 */
	@Override
	public void setLocation(double x, double y) {
		xPosition = x;
		yPosition = y;
	}

	/**
	 * getSpeed()
	 * Returns the wall's vertical speed
	 */
	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return wallSpeed;
	}

	/**
	 * setSpeed(double s)
	 * Sets the vertical speed of the walls
	 */
	@Override
	public void setSpeed(double s) {
		// TODO Auto-generated method stub
		wallSpeed = s;
	}
	
	/**
	 * getWidth()
	 * Returns the wall's width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * setWidth(double width)
	 * Sets the wall's width
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * getHeight()
	 * Returns the wall's height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * setHeight(double height)
	 * Sets the wall's height
	 */
	public void setHeight(double height) {
		this.height = height;
	}
}
