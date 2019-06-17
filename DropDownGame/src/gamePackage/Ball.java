package gamePackage;

import java.awt.event.KeyEvent;

public class Ball extends GameObject {
	private double xPosition;
	private double yPosition;
	private double ballDiameter = 30;
	
	public double horSpeed = 5;
	public double jumpSpeed = 5;
	public double currJumpSpeed = jumpSpeed;
	public double fallSpeed = 0.1;
	public double maxFallSpeed = 5;
	
	
	public boolean left, right, jumping, falling;
	
	/**
	 * Ball constructor which gives a ball its starting coordinates coordinates.
	 * @param startXPosn
	 * @param startYPosn
	 */
	public Ball(double startXPosn, double startYPosn) {
		xPosition = startXPosn;
		yPosition = startYPosn;
		left = false;
		right = false;
		jumping = false;
		falling = true;
	}
	
	/**
	 * moveLeft()
	 * Moves the ball horSpeed pixels to the left
	 */
	public void moveLeft() {
		xPosition -= horSpeed;
		checkBoundaries();
	}
	
	/**
	 * moveRight()
	 * Moves the ball horSpeed pixels to the right
	 */
	public void moveRight() {
		xPosition += horSpeed;
		checkBoundaries();
	}
	
	/**
	 * moveUp()
	 * Move the ball up currJumpSpeed pixels
	 */
	public void moveUp() {
		yPosition -= currJumpSpeed;
		checkBoundaries();
	}
	
	/**
	 * moveDown()
	 * Move the ball down fallSpeed pixels
	 */
	public void moveDown() {
		//this.setLocation(this.getX(), this.getY() + ballSpeed);
		yPosition += fallSpeed;
		checkBoundaries();
	}
	
	/**
	 * checkBoundaries()
	 * X and Y coordinate checking to keep ball on screen.
	 */
	public void checkBoundaries() {
		if (xPosition <= 0) {
			xPosition = 0;
		}
		if (xPosition + ballDiameter >= 500) {
			xPosition = 500 - ballDiameter;
		}
		/*if (yPosition <= 0) {
			yPosition = 0;
		} */
		
		if (yPosition + ballDiameter >= 500) {
			yPosition = 500 - ballDiameter;
		}
	}
	
	/**
	 * getSize()
	 * Returns the balls diameter
	 * which is the same as the hitbox's width and height
	 */
	public double getSize() {
		return ballDiameter;
	}
	
	/**
	 * getX()
	 * This method overrides the GameObject getX() method.
	 * It returns the ball's x-position at its current state.
	 */
	@Override
	public double getX() {
//		System.out.println(xPosition);
		return this.xPosition;
	}

	/**
	 * getY()
	 * This method overrides the GameObject getX() method.
	 * It returns the ball's y-position at its current state.
	 */
	@Override
	public double getY() {
		//System.out.println(yPosition);
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
	
	/**
	 * getSpeed()
	 * This method overrides the GameObject getSpeed() method.
	 * It returns the ball's horizontal speed
	 */
	@Override
	public double getSpeed() {
		return horSpeed;
	}
	
	/**
	 * setSpeed(double s)
	 * This method overrides the GameObject setSpeed(double s) method.
	 * It assigns the ball's horizontal speed to a value passed into the method.
	 */
	@Override
	public void setSpeed(double s) {
		horSpeed = s;
	}
}
