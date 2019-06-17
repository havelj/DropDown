package gamePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class View extends JPanel {
	private final int BORDER = 0;
	
	//images
	private Image background;
	private Image ballImg;
	
	private Ball ball;
	
	private Wall wallOneLeft;
	private Wall wallOneRight;
	private Wall wallTwoLeft;
	private Wall wallTwoRight;
	

	/**
	 * Default constructor; method call to load all necessary images.
	 */
	public View() {
		loadImage();
	}
	
	/**
	 * loadImage()
	 * Loads all necessary images.
	 */
	public void loadImage() {
		//background
		ImageIcon bg = new ImageIcon("src/images/Grass and Sky.png");
		background = bg.getImage();
		//ball
		ImageIcon b = new ImageIcon("src/images/Ball.png");
		ballImg = b.getImage();
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		//background
		g.drawImage(background, BORDER, BORDER, getWidth(), getHeight(), this);
		//ball
		g.drawImage(ballImg, (int)ball.getX(), (int)ball.getY(), null);
		
		//wallOneLeft
		g.fillRect((int)wallOneLeft.getX(), (int)wallOneLeft.getY(), (int)wallOneLeft.getWidth(), (int)wallOneLeft.getHeight());
		//wallOneRight
		g.fillRect((int)wallOneRight.getX(), (int)wallOneRight.getY(), (int)wallOneRight.getWidth(), (int)wallOneRight.getHeight());
		//wallTwoLeft
		g.fillRect((int)wallTwoLeft.getX(), (int)wallTwoLeft.getY(), (int)wallTwoLeft.getWidth(), (int)wallTwoLeft.getHeight());
		//wallTwoRight
		g.fillRect((int)wallTwoRight.getX(), (int)wallTwoRight.getY(), (int)wallTwoRight.getWidth(), (int)wallTwoRight.getHeight());
	}
	
	public Ball setBall(Ball b) {
		return ball = b;
	}
	
	public Wall getWallOneLeft() {
		return wallOneLeft;
	}

	public void setWallOneLeft(Wall wallOneLeft) {
		this.wallOneLeft = wallOneLeft;
	}

	public Wall getWallOneRight() {
		return wallOneRight;
	}

	public void setWallOneRight(Wall wallOneRight) {
		this.wallOneRight = wallOneRight;
	}
	
	public Wall getWallTwoLeft() {
		return wallTwoLeft;
	}

	public void setWallTwoLeft(Wall wallTwoLeft) {
		this.wallTwoLeft = wallTwoLeft;
	}

	public Wall getWallTwoRight() {
		return wallTwoRight;
	}

	public void setWallTwoRight(Wall wallTwoRight) {
		this.wallTwoRight = wallTwoRight;
	}

}
