package ca.mcgill.ecse223.block.application;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;
import ca.mcgill.ecse223.block.view.Block223Page;

public class BlockApplication {
	
	private static Block223 block223;
	private static UserRole currentRole;
	private static Game currentGame;
	public static void main(String[] args) {
		// start UI
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Block223Page().setVisible(true);
            }
        });
	}

	public static Block223 getBlock223() {
		if (block223 == null) {
			// for now, we are just creating an empty BTMS
			block223 = new Block223();
		}
 		return block223;
	}
	public static Block223 resetBlock223() {
		return Block223Persistence.load();
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
	
}
