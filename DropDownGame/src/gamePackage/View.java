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
	private double ballX;
	private double ballY;
	
	
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
	
	/**
	 * updateBallLocation()
	 * updates the View's ball coordinates after the Model changes them. Necessary because the View class needs to repaint() AFTER the model changes coordinates.
	 */
	public void updateBallLocation() {
		this.ballX = ball.getX();
		this.ballY = ball.getY();
//		System.out.println("updated");
//		System.out.println("ball X value: " + ball.getX());
//		System.out.println("Y: " + ballY);
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		//background
		g.drawImage(background, BORDER, BORDER, getWidth(), getHeight(), this);
		//ball
		g.drawImage(ballImg, (int)ballX, (int)ballY, null);
	}
	
	public Ball setBall(Ball b) {
		return ball = b;
	}
}
