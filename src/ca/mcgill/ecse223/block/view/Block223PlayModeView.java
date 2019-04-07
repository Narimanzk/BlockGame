package ca.mcgill.ecse223.block.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.model.PlayedGame.PlayStatus;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class Block223PlayModeView extends JFrame implements Block223PlayModeInterface {
	private static final long serialVersionUID = -613534727974834342L;
	private static final int GAMEWIDTH = 390;
	private static final int GAMEHEIGHT = 390;
	JTextArea gameArea;
	Block223PlayModeListener bp;
	private Block223PlayModeVisualiser block223PlayModeVisualiser;
	private JButton btnQuit;

	public Block223PlayModeView() {
		createAndShowGUI();
	}

	/**
	 * Creating GUI
	 */
	private void createAndShowGUI() {
		//tudor's new ui

		// Create and set up the window.
		this.setTitle("Block223 PlayMode");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add components to window pane
		this.addComponentsToPane();

		// Display the window.
		this.pack();
		this.setVisible(true);
	}
	/**
	 * Hide the GUI
	 */
	private void hideGUI() {
		Block223Application.setCurrentGame(null);
		Block223Application.setCurrentPlayableGame(null);
		Block223Application.setCurrentUserRole(null);
		this.setVisible(false);
	}

	private void addComponentsToPane() {

		JButton btnGame = new JButton("Start Game");

		gameArea = new JTextArea();
		gameArea.setEditable(false);
		//		JScrollPane scrollPane = new JScrollPane(gameArea);
		block223PlayModeVisualiser = new Block223PlayModeVisualiser(gameArea);

		block223PlayModeVisualiser.setPreferredSize(new Dimension(GAMEWIDTH, GAMEHEIGHT));

		getContentPane().add(block223PlayModeVisualiser, BorderLayout.CENTER);

		btnQuit = new JButton("Quit");
		block223PlayModeVisualiser.setColumnHeaderView(btnQuit);
		getContentPane().add(btnGame, BorderLayout.PAGE_END);
		btnQuit.setVisible(false);

		btnGame.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnGame.setVisible(false);
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
							btnQuit.setVisible(true);
							if (Block223Application.getCurrentPlayableGame() != null) {
								btnGame.setText("Resume");
								btnGame.setVisible(true);
							}
						} catch (InvalidInputException e) {
						}
					}
				};
				Thread t2 = new Thread(r2);
				t2.start();
			}
		});

		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideGUI();
				Block223Page window = new Block223Page();
				window.frame.setVisible(true);
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
		block223PlayModeVisualiser.paintComponent(getGraphics());
	}
}
