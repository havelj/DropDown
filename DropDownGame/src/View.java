import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class View extends JPanel {
	private final int GAME_HEIGHT = 500;
	private final int GAME_WIDTH = 500;
	private final int BALL_SIZE = 30;
	
	
	public View() {
		loadImage();
	}
	
	public void loadImage() {
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillOval(GAME_HEIGHT/2, GAME_WIDTH/2, BALL_SIZE, BALL_SIZE);
	}
}
