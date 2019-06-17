package gamePackage;

public class Model {
	public final static int GAME_HEIGHT = 500;
	public final static int GAME_WIDTH = 500;
	private Ball ball;
	
	
	/**
	 * Default constructor; method call to initialize beginning data of the game.
	 */
	public Model(Ball b) {
		ball = b;
	}
	
	public Ball getBall() {
		return this.ball;
	}	
}
