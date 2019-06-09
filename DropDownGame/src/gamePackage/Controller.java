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
	private final int FPS = 15;
	
	
	private JFrame frame;
	Model model = new Model();
	View view = new View();
	
	
	public Controller() {
		initUI();
	}
	
	public void initUI() {
		createJFrame();	
		initializeKeyBindings();
		instantiateBall();
		frame.setVisible(true);
	}
	
	/**
	 * instantiates the frame for the game
	 */
	public void createJFrame() {
		frame = new JFrame();
		
		frame.setSize(Model.GAME_HEIGHT, Model.GAME_WIDTH);
		frame.setTitle("DropDown");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);						//centers the window on the screen
		
		//adds the necessary JPanel (the view) onto the JFrame (the frame)
		frame.add(view);
	}
	
	public void initializeKeyBindings() {
		setBallBindings();
		
	}
	
	public void instantiateBall() {
		model.setBall(view.getBall());
	}
	
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
		}, false);
		
		addKeyBinding(view, KeyEvent.VK_LEFT, "go left release", (evt) -> {
			//System.out.println("ball left released");
			model.getBall().setKeyState(KeyState.STILL);
		}, true);
		//up
		addKeyBinding(view, KeyEvent.VK_UP, "go up", (evt) -> {
			//System.out.println("ball up pressed");
			model.getBall().setKeyState(KeyState.UP);
		}, false);
		addKeyBinding(view, KeyEvent.VK_UP, "go up release", (evt) -> {
			//System.out.println("ball up released");
			model.getBall().setKeyState(KeyState.STILL);
		}, true);
		//down
		addKeyBinding(view, KeyEvent.VK_DOWN, "go down", (evt) -> {
			//System.out.println("ball down pressed");
			model.getBall().setKeyState(KeyState.DOWN);
		}, false);
		addKeyBinding(view, KeyEvent.VK_DOWN, "go down release", (evt) -> {
			//System.out.println("ball down released");
			model.getBall().setKeyState(KeyState.STILL);
		}, true);
	}
	

	
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
	 * starts our game, initializes the beginning View.
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
