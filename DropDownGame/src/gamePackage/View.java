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
	
	private Ball ball = new Ball(Model.GAME_WIDTH/2, Model.GAME_HEIGHT/2);
	private double ballX;
	private double ballY;
	
	public View() {
		loadImage();
	}
	
	public void loadImage() {
		//background
		ImageIcon bg = new ImageIcon("src/images/Grass and Sky.png");
		background = bg.getImage();
		//ball
		ImageIcon b = new ImageIcon("src/images/Ball.png");
		ballImg = b.getImage();
	}
	
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
	
	public Ball getBall() {
		return ball;
	}
}
