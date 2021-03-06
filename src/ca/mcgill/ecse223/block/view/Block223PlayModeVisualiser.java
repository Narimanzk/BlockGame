package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOCurrentBlock;

public class Block223PlayModeVisualiser extends JScrollPane{
	private Rectangle2D paddle;
	private Ellipse2D ball;
	private static final long serialVersionUID = -113534727974834342L;
	
	public static final int BLOCKSIZE = 20;
	private static final int GAMEWIDTH = 390;
	private static final int GAMEHEIGHT = 390;
	public static final int PADDLE_WIDTH = 5;
	public static final int VERTICAL_DISTANCE = 30;
	public static final int BALL_DIAMETER = 10;


	public Block223PlayModeVisualiser(Component v) {
		super(v);
		repaint();
	}

		
		
	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		double paddleX = 0;
		double paddleLength = 0;
		double ballX = 0;
		double ballY = 0;

		try {
			//get paddle info
			paddleX = Block223Controller.getCurrentPlayableGame().getCurrentPaddleX();
			paddleLength = Block223Controller.getCurrentPlayableGame().getCurrentPaddleLength();
			//get ball info
			ballX = Block223Controller.getCurrentPlayableGame().getCurrentBallX();
			ballY = Block223Controller.getCurrentPlayableGame().getCurrentBallY();
			//get blocks
			for(TOCurrentBlock block : Block223Controller.getCurrentPlayableGame().getBlocks()) {
				Rectangle2D screenBlock = new Rectangle2D.Double(block.getX(), block.getY(), BLOCKSIZE, BLOCKSIZE);
				g2d.setColor(new Color(block.getRed(),block.getGreen(),block.getBlue()));
				g2d.fill(screenBlock);
				g2d.draw(screenBlock);
			}
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		//set paddle
		paddle = new Rectangle2D.Double(paddleX, GAMEHEIGHT-VERTICAL_DISTANCE, paddleLength, PADDLE_WIDTH);
		g2d.setColor(Color.BLACK);
		g2d.fill(paddle);
		g2d.draw(paddle);
		//set ball
		ball = new Ellipse2D.Double(ballX, ballY, BALL_DIAMETER, BALL_DIAMETER);
		g2d.fill(ball);
		g2d.draw(ball);

	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}

}
