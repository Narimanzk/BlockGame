package ca.mcgill.ecse223.block.application;

import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;
import ca.mcgill.ecse223.block.view.Block223Page;

public class BlockApplication {
	
	private static Block223 block223;
	
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
		
	}
	public static UserRole getCurrentUserRole() {
		UserRole curUserRole = null;
		for(UserRole userRole : block223.getUsers().get(0).getRoles()) {
			if(userRole.getClass() == Admin.class) {
				curUserRole = (Admin)userRole;
			}
			if(userRole.getClass() == Player.class) {
				curUserRole = (Player)userRole;
			}
		}
		return curUserRole;
	}
	public static void setCurrentGame(Game aGame) {
		
	}
	public static Game getCurrentGame() {
		return null;
	}
	
}
