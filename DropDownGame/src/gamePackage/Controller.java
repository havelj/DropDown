package gamePackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public class Controller {
	private JFrame frame;
	Model model = new Model();
	View view = new View();
	private final int FPS = 15;
	
	
	/**
	 * Default constructor; calls the Initial User Interface
	 */
	public Controller() {
		initUI();
	}
	
	/**
	 * initUI()
	 * Method that creates the Initial User Interface for the game.
	 */
	public void initUI() {
		createJFrame();	
		initializeKeyBindings();
		instantiateBall();
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
		
		//adds the necessary JPanel (the view) onto the JFrame instance
		frame.add(view);
	}
	
	/**
	 * initializeKeyBindings()
	 * initializes all necessary key bindings for the game. (As of right now, it appears as if we will only need bindings for the ball).
	 */
	public void initializeKeyBindings() {
		setBallBindings();
	}
	
	/**
	 * setBallBindings()
	 * initializes specifically the key bindings of the ball. This method is called in initializeKeyBindings().
	 */
	public void setBallBindings() {
		//right
		addKeyBinding(view, KeyEvent.VK_RIGHT, "go right", (evt) -> {
			//System.out.println("ball right pressed");
			model.getBall().setKeyState(KeyState.RIGHT);
//			System.out.println(model.getBall().getKeyState());
			model.moveBallAccordingToKeyState();
			view.updateBallLocation();
			view.repaint();
		}, false);
		
		addKeyBinding(view, KeyEvent.VK_RIGHT, "go right release", (evt) -> {
			//System.out.println("ball right released");
			model.getBall().setKeyState(KeyState.STILL);
		}, true);
		//left
		addKeyBinding(view, KeyEvent.VK_LEFT, "go left", (evt) -> {
			//System.out.println("ball left pressed");
			model.getBall().setKeyState(KeyState.LEFT);
			model.moveBallAccordingToKeyState();
			view.updateBallLocation();
			view.repaint();
		}, false);
		
		addKeyBinding(view, KeyEvent.VK_LEFT, "go left release", (evt) -> {
			//System.out.println("ball left released");
			model.getBall().setKeyState(KeyState.STILL);
		}, true);
		//up
		addKeyBinding(view, KeyEvent.VK_UP, "go up", (evt) -> {
			//System.out.println("ball up pressed");
			model.getBall().setKeyState(KeyState.UP);
			model.moveBallAccordingToKeyState();
			view.updateBallLocation();
			view.repaint();
		}, false);
		addKeyBinding(view, KeyEvent.VK_UP, "go up release", (evt) -> {
			//System.out.println("ball up released");
			model.getBall().setKeyState(KeyState.STILL);
		}, true);
		//down
		addKeyBinding(view, KeyEvent.VK_DOWN, "go down", (evt) -> {
			//System.out.println("ball down pressed");
			model.getBall().setKeyState(KeyState.DOWN);
			model.moveBallAccordingToKeyState();
			view.updateBallLocation();
			view.repaint();
		}, false);
		addKeyBinding(view, KeyEvent.VK_DOWN, "go down release", (evt) -> {
			//System.out.println("ball down released");
			model.getBall().setKeyState(KeyState.STILL);
		}, true);
	}
	
	/**
	 * instantiateBall()
	 * Sets the ball instance in Model to be the ball instance from View
	 * 
	 * Why?
	 * The Model class keeps track of when to change the ball's data (x and y coordinates). However, the View needs certain information; some of this being the coordinates of the ball (so the
	 *  View class knows where to draw the ball on screen). Originally before this method was created there was a disconnect between the Model's ball data and the coordinates that the View class
	 *  has access to; the ball's coordinates would be updated, but the ball's location would not change on screen.
	 *  
	 *    IS THERE A MORE MODULAR FIX?
	 */
	public void instantiateBall() {
		model.setBall(view.getBall());
		view.updateBallLocation();
		view.repaint();
	}
	
	/**
	 * addKeyBinding(JComponent comp, int keyCode, String id, ActionListener actionListener, boolean isReleased)
	 * @param comp				= the specific component we are adding key bindings to
	 * @param keyCode			= the specific key press we are adding a binding to
	 * @param id				= A String to specify what the key action will do
	 * @param actionListener	
	 * @param isReleased
	 * 
	 * The back end to how we will add key bindings
	 */
	public static void addKeyBinding(JComponent comp, int keyCode, String id, ActionListener actionListener, boolean isReleased) {
		InputMap inputMap = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = comp.getActionMap();

		inputMap.put(KeyStroke.getKeyStroke(keyCode, 0, isReleased), id);
		actionMap.put(id, new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				actionListener.actionPerformed(e);

			}
		});
	}
	
	/**
	 * start()
	 * starts our game, initializes the beginning View.
	 *  *Still needs work*
	 */
	public void start() {
		while (true) {
			try {
				Thread.sleep(FPS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
