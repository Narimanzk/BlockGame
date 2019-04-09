package ca.mcgill.ecse223.block.persistence;

import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.persistence.PersistenceObjectStream;

public class Block223Persistence {
	
	private static String filename = "Block223.data";
	
	public static void save(Block223 block223) {
		PersistenceObjectStream.serialize(block223);
	}
	
	public static Block223 load() {
		PersistenceObjectStream.setFilename(filename);
		Block223 block223 = (Block223) PersistenceObjectStream.deserialize();
		if (block223 == null) {//If it can't be loaded for whatever reason
			block223 = new Block223(); //then a new Block223 needs to be created.
		}
		else {
			block223.reinitialize();
		}
		return block223;
	}
	
	public static void setFilename(String newFilename) {
		filename = newFilename;
	}

}