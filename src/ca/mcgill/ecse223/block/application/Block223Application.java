package ca.mcgill.ecse223.block.application;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;
import ca.mcgill.ecse223.block.view.Block223Page;

public class Block223Application {
	
	private static Block223 block223;
	private static UserRole currentRole = null;
	private static Game currentGame = null;
	private static PlayedGame currentPlayableGame = null;
	
	public static void main(String[] args) {
		// start UI
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	Block223Page window =new Block223Page();
            	window.frame.setVisible(true);
            }
        });
	}

	public static Block223 getBlock223() {
		if (block223 == null) {
			block223 = Block223Persistence.load();
		}
 		return block223;
	}
	public static Block223 resetBlock223() {
		block223.delete();
		Block223Application.setCurrentGame(null);
		Block223Application.setCurrentPlayableGame(null);
		block223 = Block223Persistence.load();
		return block223;
	}
	
	public static void setCurrentUserRole(UserRole aUserRole) {
		currentRole = aUserRole;
		
	}
	public static UserRole getCurrentUserRole() {
		return currentRole;
	}
	public static void setCurrentGame(Game aGame) {
		currentGame = aGame;
	}
	public static Game getCurrentGame() {
		return currentGame;
	}
	public static void setCurrentPlayableGame(PlayedGame aGame) {
		currentPlayableGame = aGame;
	}
	public static PlayedGame getCurrentPlayableGame() {
		return currentPlayableGame;
	}
	
}
