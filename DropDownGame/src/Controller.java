import javax.swing.JFrame;

public class Controller {
	private final int FPS = 15;
	private final int GAME_HEIGHT = 500;
	private final int GAME_WIDTH = 500;
	
	private JFrame frame;
	Model model = new Model();
	View view = new View();
	
	
	public Controller() {
		initUI();
	}
	
	public void initUI() {
		createJFrame();		
		frame.setVisible(true);
	}
	
	/**
	 * instantiates the frame for the game
	 */
	public void createJFrame() {
		frame = new JFrame();
		
		frame.setSize(GAME_HEIGHT, GAME_WIDTH);
		frame.setTitle("DropDown");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);						//centers the window on the screen
		
		//adds the necessary JPanel (the view) onto the JFrame (the frame)
		frame.add(view);
	}
	
	
	/**
	 * starts our game, initializes the beginning View.
	 */
	public void start() {
		try {
			Thread.sleep(FPS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
