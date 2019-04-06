package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JScrollPane;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

public class Block223PlayModeVisualiser extends JScrollPane{
	private Rectangle2D paddle;
	private static final long serialVersionUID = -113534727974834342L;

	private static final int GAMEWIDTH = 390;
	private static final int GAMEHEIGHT = 390;
	public static final int PADDLE_WIDTH = 5;
	public static final int VERTICAL_DISTANCE = 30;

	public Block223PlayModeVisualiser(Component v) {
		super(v);
		repaint();
	}

		
		
	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		double paddleX = 0;
		double paddleLength = 0;
		try {
			paddleX = Block223Controller.getCurrentPlayableGame().getCurrentPaddleX();
			paddleLength = Block223Controller.getCurrentPlayableGame().getCurrentPaddleLength();
			
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		paddle.setRect(paddleX, GAMEHEIGHT-VERTICAL_DISTANCE, paddleLength, PADDLE_WIDTH);
		paddle = new Rectangle2D.Double(paddleX, GAMEHEIGHT-VERTICAL_DISTANCE, paddleLength, PADDLE_WIDTH);
		g2d.setColor(Color.BLACK);
		g2d.fill(paddle);
		g2d.draw(paddle);
		
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}

}
