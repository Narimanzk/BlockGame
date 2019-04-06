package ca.mcgill.ecse223.block.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;



public class Block223PlayModeView extends JFrame implements Block223PlayModeInterface {
	private static final long serialVersionUID = -613534727974834342L;
	private static final int GAMEWIDTH = 390;
	private static final int GAMEHEIGHT = 390;
	JTextArea gameArea;
	Block223PlayModeListener bp;
	private Block223PlayModeVisualiser block223PlayModeVisualiser;

	public Block223PlayModeView() {
		createAndShowGUI();
	}

	/**
	 * Creating GUI
	 */
	private void createAndShowGUI() {
		//tudor's new ui

		// Create and set up the window.
		this.setTitle("Block223 PlayMode Example");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add components to window pane
		this.addComponentsToPane();

		// Display the window.
		this.pack();
		this.setVisible(true);
	}

	private void addComponentsToPane() {

		JButton button = new JButton("Start Game");

		gameArea = new JTextArea();
		gameArea.setEditable(false);
//		JScrollPane scrollPane = new JScrollPane(gameArea);
		block223PlayModeVisualiser = new Block223PlayModeVisualiser(gameArea);

		block223PlayModeVisualiser.setPreferredSize(new Dimension(GAMEWIDTH, GAMEHEIGHT));
		
		getContentPane().add(block223PlayModeVisualiser, BorderLayout.CENTER);
		getContentPane().add(button, BorderLayout.PAGE_END);

		button.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				button.setVisible(false);
				// initiating a thread to start listening to keyboard inputs
				bp = new Block223PlayModeListener();
				Runnable r1 = new Runnable() {
					@Override
					public void run() {
						// in the actual game, add keyListener to the game window
						gameArea.addKeyListener(bp);
					}
				};
				Thread t1 = new Thread(r1);
				t1.start();
				// to be on the safe side use join to start executing thread t1 before executing
				// the next thread
				try {
					t1.join();
				} catch (InterruptedException e1) {
				}

				// initiating a thread to start the game loop
				Runnable r2 = new Runnable() {
					@Override
					public void run() {
						try {
							Block223Controller.startGame(Block223PlayModeView.this);
							button.setVisible(true);
						} catch (InvalidInputException e) {
						}
					}
				};
				Thread t2 = new Thread(r2);
				t2.start();
			}
		});
	}
	
	

	@Override
	public String takeInputs() {
		if (bp == null) {
			return "";
		}
		return bp.takeInputs();
	}

	@Override
	public void refresh() {
		System.out.println("UI is refreshing now...");
		block223PlayModeVisualiser.paintComponent(getGraphics());
		}
}
