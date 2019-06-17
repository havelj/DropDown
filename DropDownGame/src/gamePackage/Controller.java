package gamePackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.*;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public class Controller implements KeyListener {
	private JFrame frame;
	Model model;
	View view;
	private final int FPS = 120;
	private final long targetTime = 1000 / FPS;
	
	/**
	 * Default constructor; calls the Initial User Interface
	 */
	public Controller() {
		model = new Model(new Ball(Model.GAME_WIDTH/2, Model.GAME_HEIGHT/2));
		view = new View();
		
		createJFrame();
		
		view.setBall(model.getBall());
		view.updateBallLocation();
		view.repaint();
		
		frame.setVisible(true);
	}
	
	/**
	 * createJFrame()
	 * instantiates the JFrame for the game and adds the view to it; the Controller class has an instance of a JFrame, but the entire View class is treated as the JPanel
	 */
	public void createJFrame() {
		frame = new JFrame();
		
		frame.setSize(Model.GAME_HEIGHT, Model.GAME_WIDTH);
		frame.setTitle("DropDown");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);								//Allows decorations to be added to the JFrame
		frame.setLocationRelativeTo(null);						//centers the window on the screen
		frame.addKeyListener(this);								//Tells frame to listen to user's input
		//adds the necessary JPanel (the view) onto the JFrame instance
		frame.add(view);
	}
	
	/**
	 * tick()
	 * Contains all the logic of the game. Called every iteration of the game loop
	 */
	public void tick() {
		//Moves the ball left and right
		if(model.getBall().left) {
			model.getBall().moveLeft();
		}
		if(model.getBall().right) { 
			model.getBall().moveRight();
		}
		
		//Moves the ball up (decelerates)
		if(model.getBall().jumping) {
			model.getBall().moveUp();
			model.getBall().currJumpSpeed -= 0.2; //The bigger the change (.2), the small the jump
			
			if(model.getBall().currJumpSpeed <= 0) {
				model.getBall().currJumpSpeed = model.getBall().jumpSpeed;
				model.getBall().falling = true;
				model.getBall().jumping = false;
			}
		}
		
		//Moves the ball down (accelerates)
		if(model.getBall().falling) {
			model.getBall().moveDown();
			
			if(model.getBall().fallSpeed < model.getBall().maxFallSpeed) {
				model.getBall().fallSpeed += 0.2; //The bigger the change (.2), the faster the acceleration
			}
		}
		
		if(!model.getBall().falling) {
			model.getBall().fallSpeed = 0.1;
		}
		
	}
	
	/**
	 * start()
	 * starts our game, initializes the beginning View. This is our game loop.
	 *  *Still needs work*
	 */
	public void start() {
		long start, elapsed, wait;
		while (true) { //One loop == 1 Frame Per Second
			start = System.nanoTime();
			
			tick();
			view.updateBallLocation();
			view.repaint();
			
			elapsed = System.nanoTime() - start;
			wait = targetTime - (elapsed / 1000000);
			
			if(wait <= 0) wait = 5;
			
			try {
				Thread.sleep(wait); //Pauses if loop is finished running before desired FPS
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * keyTyped(KeyEvent e)
	 * Invoked when a key is typed
	 * Not needed but we must include because of interface
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub	
	}

	/**
	 * keyPressed(KeyEvent e)
	 * Invoked when user presses on a key
	 * Sets the Ball's left and right properties accordingly
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) model.getBall().left = true;
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) model.getBall().right = true; 
		if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) { 
			model.getBall().jumping = true;
			
			/* 
			 * This allows the ball to jump while it's falling.
			 * We don't want this feature in the final game.
			 * Testing purposes only!
			 */
			model.getBall().falling = false;
		} 
	}

	/**
	 * keyReleased(KeyEvent e)
	 * Invoked when user releases a key
	 * Sets the Ball's left and right properties accordingly
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) model.getBall().left = false;
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) model.getBall().right = false; 
	}
}
