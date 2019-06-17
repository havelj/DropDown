package gamePackage;

import java.awt.event.KeyEvent;

public class Ball extends GameObject {
	private double xPosition;
	private double yPosition;
	
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
	}
	
	/**
	 * moveLeft()
	 * Moves the ball horSpeed pixels to the left
	 */
	public void moveLeft() {
		xPosition -= horSpeed;
	}
	
	/**
	 * moveRight()
	 * Moves the ball horSpeed pixels to the right
	 */
	public void moveRight() {
		xPosition += horSpeed;
	}
	
	/**
	 * moveUp()
	 * Move the ball up currJumpSpeed pixels
	 */
	public void moveUp() {
		yPosition -= currJumpSpeed;
	}
	
	/**
	 * moveDown()
	 * Move the ball down fallSpeed pixels
	 */
	public void moveDown() {
		//this.setLocation(this.getX(), this.getY() + ballSpeed);
		yPosition += fallSpeed;
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
	
	@Override
	public double getSpeed() {
		return horSpeed;
	}
	
	@Override
	public void setSpeed(int s) {
		horSpeed = s;
	}
}
