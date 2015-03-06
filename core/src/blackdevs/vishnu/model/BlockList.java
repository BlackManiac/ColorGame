package blackdevs.vishnu.model;

import java.util.ArrayList;

import blackdevs.vishnu.model.Block;

/**
 * List of Blocks
 * 
 * @author vishnu.satis, eldo.joseph
 */
public class BlockList {
	public BlockList() {

	}
	
	private ArrayList<Block> blockList = new ArrayList<>();

	public ArrayList<Block> getBlockList() {
		return blockList;
	}

	public void setBlockList(ArrayList<Block> blockList) {
		this.blockList = blockList;
	}

	public BlockList(ArrayList<Block> blockList) {
		super();
		this.blockList = blockList;
	}

	public void addBlock(Block object) {
		this.blockList.add(object);
	}
}
