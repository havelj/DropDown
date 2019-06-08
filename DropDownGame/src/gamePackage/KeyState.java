package gamePackage;

public enum KeyState {
	UP("up"),
	DOWN("down"),
	RIGHT("right"),
	LEFT("left"),
	STILL("still");
	
	private String keyState = null;
	
	private KeyState(String state) {
		this.keyState = state;
	}
	
	public void setState(String state) {
		this.keyState = state;
	}
	
	public String getState() {
		return this.keyState;
	}
}
