package gamePackage;

public class Wall extends GameObject {
	private double xPosition;
	private double yPosition;
	private double width;
	private double height;
	private double wallSpeed = 2.5;
	
	public boolean up, down;
	
	/**
	 * Constructor
	 */
	public Wall(double x, double y, double w, double h) {
		this.xPosition = x;
		this.yPosition = y;
		this.width = w;
		this.height = h;
		
		
	}
	
	
	/**
	 * moveWall()
	 * moves the walls up or down
	 */
	void moveWall() {
		up = true;
		down = false;
		if (up) {
			yPosition -= wallSpeed;
		}
		if (down) {
			yPosition += wallSpeed;
		}
	}
	
	
	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return this.xPosition;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return this.yPosition;
	}

	@Override
	public void setLocation(double x, double y) {
		xPosition = x;
		yPosition = y;
	}

	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return wallSpeed;
	}

	@Override
	public void setSpeed(int s) {
		// TODO Auto-generated method stub
		wallSpeed = s;
	}
	
	public double getWidth() {
		return width;
	}


	public void setWidth(double width) {
		this.width = width;
	}


	public double getHeight() {
		return height;
	}


	public void setHeight(double height) {
		this.height = height;
	}

}
