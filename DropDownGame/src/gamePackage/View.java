package gamePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class View extends JPanel {
	private static final long serialVersionUID = 1L;

	private final int BORDER = 0;
	
	//images
	private Image background;
	private Image ballImg;
	
	private Ball ball;
	private LinkedList<Wall> walls;

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
	protected void paintComponent(Graphics g) {
		//background
		g.drawImage(background, BORDER, BORDER, getWidth(), getHeight(), this);
		//ball
		g.drawImage(ballImg, (int)ball.getX(), (int)ball.getY(), null);
		
		for(int i = 0; i < walls.size(); i++) {
			g.fillRect((int) walls.get(i).getX(), (int) walls.get(i).getY(), (int) walls.get(i).getWidth(), (int) walls.get(i).getHeight());
		}
	}
	
	public void updateWalls(LinkedList<Wall> myWalls) {
		walls = myWalls;
	}
	
	public Ball setBall(Ball b) {
		return ball = b;
	}
}
