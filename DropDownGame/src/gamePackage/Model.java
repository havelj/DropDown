package gamePackage;

import java.util.Random;
import java.util.ArrayList;
import java.util.LinkedList;

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
	private int wallCount = 14; //Twice the amount of levels on the screen at a time (change to 4 when fixing direction bug)
	
	private LinkedList<Wall> wallQueue = new LinkedList<Wall>();	
	
	/**
	 * Default constructor; method call to initialize beginning data of the game.
	 */
	public Model(Ball b) {
		ball = b;
		
		fillWallQueue();
	}
		
	public void fillWallQueue() { //Will need to alter wallHeight if we want 8 layers (16 walls) on screen
		for (int i = 0; i < wallCount; i++) {
			//if it is a wall on the left
			if (i%2 == 0) {
				wallQueue.add(new Wall(0, wallStartingY, randWallWidth, wallHeight));
			}
			//if it is a wall on the right of the left one
			else {
				wallQueue.add(new Wall(wallQueue.get(i-1).getWidth() + wallGap, wallQueue.get(i-1).getY(), GAME_WIDTH - wallQueue.get(i-1).getWidth() - wallGap, wallHeight));
				randWallWidth = rand.nextInt(GAME_WIDTH - 40);
				wallStartingY += wallInterval;
			}
		}
	}
	
	public boolean shouldReplace() {
		boolean result = false;
		boolean up = wallQueue.getFirst().up;
		for(int i = 0; i < wallQueue.size(); i++) {
			if(up && (i%2 == 0) && (wallQueue.getFirst().getY() <= -wallHeight)) {
				result = true;
			} else if(!up && (i%2 == 0) && (wallQueue.getLast().getY() >= GAME_HEIGHT)) {
				result = true;
			}
		}

		return result;
	}
	
	public void replaceLevel() {
		Wall left, right;
		
		if(wallQueue.getFirst().up == true) {
			//Remove top layer (left and right walls)
			wallQueue.poll();
			wallQueue.poll();
			
			//Create new walls with random gap
			left = new Wall(0, GAME_HEIGHT, randWallWidth, wallHeight);
			right = new Wall(left.getWidth() + wallGap, GAME_HEIGHT, GAME_WIDTH - left.getWidth(), wallHeight);
			
			//Add layer to bottom of queue
			wallQueue.add(left);
			wallQueue.add(right);
		} else {
			//Remove bottom layer (left and right walls)
			wallQueue.removeLast();
			wallQueue.removeLast();
			
			//Create new walls with random gap
			left = new Wall(0, -wallHeight, randWallWidth, wallHeight);
			right = new Wall(left.getWidth() + wallGap, -wallHeight, GAME_WIDTH - left.getWidth(), wallHeight);
			
			wallQueue.addFirst(right);
			wallQueue.addFirst(left);
		}
		randWallWidth = rand.nextInt(GAME_WIDTH - 40);
	}
	
	public void changeDirection() {
		for(int i = 0; i < wallQueue.size(); i++) {
			if(wallQueue.get(i).up) {
				wallQueue.get(i).up = false;
				wallQueue.get(i).down = true;
			} else {
				wallQueue.get(i).up = true;
				wallQueue.get(i).down = false;
			}
		}
	}
	
	public Ball getBall() {
		return this.ball;
	}	
	
	public Wall getWallFromQueue(int i) {
		return wallQueue.get(i);
	}
	
	public int getWallQueueSize() {
		return wallQueue.size();
	}
	
	public LinkedList<Wall> getWalls() {
		return wallQueue;
	}

}
