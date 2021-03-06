package networking.project.game.display;

import networking.project.game.Game;

import javax.swing.*;
import java.awt.*;

/**
 *	The Display will create a Canvas that all graphics will
 *  be rendered to, and a JFrame which will hold it.
 * 	
 *	@author 
 *	@version 1.0
 *	@since version 1.0
 */
public class Display {

	private JFrame frame;
	private Canvas canvas;

	private Game parent;

	private String title;
	private int width, height;
	
	public Display(String title, int width, int height, Game parent){
		this.title = title;
		this.width = width;
		this.height = height;
		this.parent = parent;

		createDisplay();
	}
	
	/**
	 *  Creates a Display by adding a canvas to a JFrame.
	 */
	private void createDisplay(){
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.addWindowListener(parent);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
	}

	/**
	 * @return canvas
	 */
	public Canvas getCanvas(){
		return canvas;
	}
	
	/**
	 * @return frame
	 */
	public JFrame getFrame(){
		return frame;
	}

}