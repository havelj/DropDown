package gamePackage;

import java.util.Random;

public class Model {
	public final static int GAME_HEIGHT = 500;
	public final static int GAME_WIDTH = 500;
	private Ball ball;
	
	//wall attributes to fill array of wall objects
	private int wallInterval = 70; //was 70
	private int wallGap = 60;
	private int wallHeight = 30; //was 30
	Random rand = new Random();
	private int randWallWidth = rand.nextInt(GAME_WIDTH - 40);
	private int wallStartingY = 275;
	
	
	private Wall[] wallArr = new Wall[4];
	
	
	/**
	 * Default constructor; method call to initialize beginning data of the game.
	 */
	public Model(Ball b) {
		ball = b;
		
		fillWallArr();
	}
	
	public void fillWallArr() {
//		wallArr[0] = new Wall(0, 375, 200, 30);
//		wallArr[1] = new Wall(250, 375, 250, 30);
		for (int i = 0; i < wallArr.length; i++) {
			//if it is a wall on the left
			if (i%2 == 0) {
				wallArr[i] = new Wall(0, wallStartingY, randWallWidth, wallHeight);
			}
			//if it is a wall on the right of the left one
			else {
				wallArr[i] = new Wall(wallArr[i-1].getWidth() + wallGap, wallArr[i-1].getY(), GAME_WIDTH - wallArr[i-1].getWidth() - wallGap, wallHeight);
				randWallWidth = rand.nextInt(GAME_WIDTH - 40);
				wallStartingY += wallInterval;
			}
		}
	}
	
	
	public Ball getBall() {
		return this.ball;
	}	
	
	public Wall getWallFromArr(int i) {
		return wallArr[i];
	}
	public int getWallArrSize() {
		return wallArr.length;
	}
	public Wall[] getWalls() {
		return wallArr;
	}

}
