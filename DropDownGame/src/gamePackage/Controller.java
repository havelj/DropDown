package gamePackage;


import java.awt.event.KeyEvent;
import java.awt.event.*;
import javax.swing.JFrame;


public class Controller implements KeyListener {
	private final int FPS = 60;
	private final long targetTime = 1000 / FPS;
	private JFrame frame;
	Model model;
	View view;
	
	/**
	 * Default constructor; calls the Initial User Interface
	 */
	public Controller() {
		model = new Model(new Ball(Model.GAME_WIDTH/2, Model.GAME_HEIGHT/2));
		view = new View();
		
		createJFrame();
		updateViewWalls();
		view.setBall(model.getBall());
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
		Wall wallBelow, wallAbove, wallLeft, wallRight;
		
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
		
		//Resets initial fall speed when ball stops falling
		if(!model.getBall().falling) {
			model.getBall().fallSpeed = 0.1;
		}
		
		//Moves the walls up unless a powerup is chosen
		moveWalls();
		
		//Stores wall that the ball is colliding with
		wallLeft = getWallLeft(model.getBall());
		wallRight = getWallRight(model.getBall());
		wallBelow = getWallBelow(model.getBall());
		wallAbove = getWallAbove(model.getBall());
		
		//Force ball location if a collision (with a wall) exists
		if(model.getBall().right && wallRight != null) {
			model.getBall().setLocation(wallRight.getX() - 30, model.getBall().getY());
		} else if(model.getBall().left && wallLeft != null) { 
			model.getBall().setLocation(wallLeft.getX() + wallLeft.getWidth(), model.getBall().getY());
		} else if(wallBelow != null) {
			model.getBall().setLocation(model.getBall().getX(), wallBelow.getY() - 30);
		} else if(wallAbove != null) {
			model.getBall().setLocation(model.getBall().getX(), wallAbove.getY() + wallAbove.getHeight());
		}
		
		//Replace a level if it is completely out of view
		if(model.shouldReplace()) {
			model.replaceLevel();
		}
	}
	
	/**
	 * moveWalls()
	 * Calls moveWall() for every wall in the game
	 */
	public void moveWalls() {
		for (int i = 0; i < model.getWallQueueSize(); i++) {
			model.getWalls().get(i).moveWall();
		}
	}
	
	/**
	 * updateViewWalls()
	 * Updates the view's copy of the walls
	 */
	public void updateViewWalls() {
		view.updateWalls(model.getWalls());
	}
	
	/**
	 * getWallBelow(Ball ball)
	 * Iterates through all of the walls and determines
	 * whether or not there exists a wall that the ball is sitting on
	 */
	public Wall getWallBelow(Ball ball) {
		Wall myWall = null;
		Wall temp;
		for(int i = 0; i < model.getWallQueueSize(); i++) {
			temp = model.getWalls().get(i);
			//If the ball is sitting on top of the wall
			if((ball.getY() >= (temp.getY() - ball.getSize())) && (ball.getY() <= (temp.getY() + temp.getHeight() - ball.getSize())) && (ball.getX() >= temp.getX() - ball.getSize()) && (ball.getX() <= temp.getX() + temp.getWidth())) {
				myWall = temp;
			}
		}
		
		return myWall;
	}
	
	/**
	 * getWallAbove(Ball ball) 
	 * Iterates through all of the walls and determines whether
	 * or not there exists a wall that the ball is jumping up/into
	 */
	public Wall getWallAbove(Ball ball) {
		Wall myWall = null;
		Wall temp;
		for(int i = 0; i < model.getWallQueueSize(); i++) {
			temp = model.getWalls().get(i);
			//If the ball is hitting the bottom of the wall
			if((ball.getY() >= (temp.getY() + temp.getHeight()- ball.getSize())) && (ball.getY() <= (temp.getY() + temp.getHeight())) && (ball.getX() >= temp.getX() - ball.getSize()) && (ball.getX() <= temp.getX() + temp.getWidth())) {
				myWall = temp;
			}
		}
		
		return myWall;
	}
	
	/**
	 * getWallRight(Ball ball) 
	 * Iterates through all of the walls and determines whether 
	 * or not the ball is trying to move right into one as it's falling
	 */
	public Wall getWallRight(Ball ball) {
		Wall myWall = null;
		Wall temp;
		for(int i = 0; i < model.getWallQueueSize(); i++) {
			temp = model.getWalls().get(i);
			//If the ball is hitting the left side of the wall
			if((ball.getX() >= temp.getX() - ball.getSize()) && (ball.getX() <= temp.getX() - ball.getSize() + ball.horSpeed) && (ball.getY() >= temp.getY() - ball.getSize()) && (ball.getY() <= temp.getY() + temp.getHeight())) {
				myWall = temp;
			}
		}
		
		return myWall;
	}
	
	/**
	 * getWallLeft(Ball ball)
	 * Iterates through all of the walls and determines whether
	 * or not the ball is trying to move left into one as it's falling
	 */
	public Wall getWallLeft(Ball ball) {
		Wall myWall = null;
		Wall temp;
		for(int i = 0; i < model.getWallQueueSize(); i++) {
			temp = model.getWalls().get(i);
			//If the ball is hitting the right side of the wall
			if((ball.getX() >= temp.getX() + temp.getWidth() - ball.horSpeed) && (ball.getX() <= temp.getX() + temp.getWidth()) && (ball.getY() >= temp.getY() - ball.getSize()) && (ball.getY() <= temp.getY() + temp.getHeight())) {
				myWall = temp;
			}
		}
		
		return myWall;
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
			updateViewWalls();
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
		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			model.getBall().left = true;
		}
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			model.getBall().right = true; 
		}
		if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) { 
			model.getBall().jumping = true;
			model.getBall().falling = false;
		} 
		
		//Temporary, only for testing purposes
		if(key == KeyEvent.VK_SPACE) {
			model.changeDirection();
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
		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			model.getBall().left = false;
		}
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			model.getBall().right = false; 
		}
	}
}
