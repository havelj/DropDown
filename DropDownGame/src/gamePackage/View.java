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
	private Image powerupImg;
	
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
		//powerup
		ImageIcon p = new ImageIcon("src/images/Power Up.png");
		powerupImg = p.getImage();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		int count = 0;
		
		//background
		g.drawImage(background, BORDER, BORDER, getWidth(), getHeight(), this);
		//ball
		g.drawImage(ballImg, (int)ball.getX(), (int)ball.getY(), null);
		//powerup
//		g.drawImage(powerupImg, 250, 250, null);
		//walls
		for(int i = 0; i < walls.size(); i++) {
			g.fillRect((int) walls.get(i).getX(), (int) walls.get(i).getY(), (int) walls.get(i).getWidth(), (int) walls.get(i).getHeight());
			count++;
			
			if (count == 5) {
				g.drawImage(powerupImg, (int) walls.get(i).getX() + 75, (int) walls.get(i).getY() - 15, null);
				System.out.println("powerup drawn");
			}
		}
	}
	
	public void updateWalls(LinkedList<Wall> myWalls) {
		walls = myWalls;
	}
	
	public Ball setBall(Ball b) {
		return ball = b;
	}
}
