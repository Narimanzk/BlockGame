package ca.mcgill.ecse223.block.application;

import ca.mcgill.ecse223.block.model.Block223;
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

	public static Block223 getBtms() {
		if (block223 == null) {
			// for now, we are just creating an empty BTMS
			block223 = new Block223();
		}
 		return block223;
	}
	
}
