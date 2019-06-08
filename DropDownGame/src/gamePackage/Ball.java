package gamePackage;

public class Ball extends GameObject {
	private double xPosition;
	private double yPosition;
	
	
	
	private int ballSpeed = 10;
	
	private KeyState keyState = KeyState.STILL;
	
	
	public Ball(double startXPosn, double startYPosn) {
		xPosition = startXPosn;
		yPosition = startYPosn;
	}
	
	/**
	 * moveLeft()
	 * Decrements the xPosition of the bird by the constant value BIRD_SPEED
	 */
	public void moveLeft() {
		this.setLocation(this.getX() - ballSpeed, this.getY());
	}
	
	/**
	 * moveRight()
	 * Increments the xPosition of the bird by the constant value BIRD_SPEED
	 */
	public void moveRight() {
		this.setLocation(this.getX() + ballSpeed, this.getY());
	}
	
	/**
	 * moveUp()
	 * Decrements the yPosition of the bird by the constant value BIRD_SPEED
	 */
	public void moveUp() {
		this.setLocation(this.getX(), this.getY() - ballSpeed);
	}
	
	/**
	 * moveDown()
	 * Increments the yPosition of the bird by the constant value BIRD_SPEED
	 */
	public void moveDown() {
		this.setLocation(this.getX(), this.getY() + ballSpeed);
	}
	
	
	/**
	 * getX()
	 * This method overrides the GameObject getX() method.
	 * It returns the bird's x-position at its current state.
	 */
	@Override
	public double getX() {
		return this.xPosition;
	}

	/**
	 * getY()
	 * This method overrides the GameObject getX() method.
	 * It returns the bird's y-position at its current state.
	 */
	@Override
	public double getY() {
		return this.yPosition;
	}
	
	/**
	 * setLocation(double x, double y)
	 * This method overrides the GameObject setLocation(double x, double y) method.
	 * It sets the location of the ball object
	 */
	@Override
	public void setLocation(double x, double y) {
		xPosition = x;
		yPosition = y;
	}
	
	@Override
	public int getSpeed() {
		return ballSpeed;
	}
	
	@Override
	public void setSpeed(int s) {
		ballSpeed = s;
	}
	
	/**
	 * getFlyState()
	 * @return FlyState enumeration to represent the current fly state
	 */
	public KeyState getKeyState() {
		return keyState;
	}

	/**
	 * setFlyState()
	 * Sets the FlyState to another enumeration type.
	 * @param flyState
	 */
	public void setKeyState(KeyState state) {
		this.keyState = state;
	}
}
