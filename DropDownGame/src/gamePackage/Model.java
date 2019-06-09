package gamePackage;

public class Model {
	public final static int GAME_HEIGHT = 500;
	public final static int GAME_WIDTH = 500;
	private Ball ball;
	
	
	/**
	 * Default constructor; method call to initialize beginning data of the game.
	 */
	public Model() {
		init();
	}
	
	/**
	 * init()
	 * Initializes the beginning data of the game.
	 */
	public void init() {
		
	}
	
	/**
	 * moveBallAccordingToKeyState()
	 * Moves the ball based on which arrow key is pressed.
	 */
	public void moveBallAccordingToKeyState() {
		switch (this.ball.getKeyState()) {
		case RIGHT:
			this.ball.moveRight();
			break;
		case LEFT:
			this.ball.moveLeft();
			break;
		case UP:
			this.ball.moveUp();
			break;
		case DOWN:
			this.ball.moveDown();
			break;
		default:
			break;
		}
	}
	
	
	public Ball getBall() {
		return this.ball;
	}

	public void setBall(Ball b) {
		this.ball = b;
	}
	
}
