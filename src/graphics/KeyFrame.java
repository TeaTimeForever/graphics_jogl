package graphics;

import java.awt.Frame;
import java.awt.GraphicsConfiguration;

public class KeyFrame extends Frame {

	public KeyFrame(String title) {
		super(title);
		setVisible(true);
	}
	
	public KeyFrame(GraphicsConfiguration gc) {
		super(gc);
	}
	
	public KeyFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
	}
}

	
