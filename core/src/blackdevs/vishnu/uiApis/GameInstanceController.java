package blackdevs.vishnu.uiApis;

import java.util.ArrayList;
import java.util.Iterator;

import blackdevs.vishnu.Colors.ColorGame;
import blackdevs.vishnu.Constants.IntMap;
import blackdevs.vishnu.Framework.Logger;
import blackdevs.vishnu.model.Block;
import blackdevs.vishnu.model.BlockList;
import blackdevs.vishnu.model.ColorList;

/**
 * Controls game instance
 * 
 * @author eldo.joseph
 */
public class GameInstanceController {

	private BlockList blockListObject;
	private ArrayList<Block> blockList;
	private Block block;

	public GameInstanceController() {
	}

	/**
	 * Swaps two blocks passed
	 * 
	 * @param firstBlock
	 * @param secondBlock
	 */
	public void swapBlocks(Block firstBlock, Block secondBlock) {
		int firstRowIndex = firstBlock.getRowIndex();
		int firstColIndex = firstBlock.getColIndex();
		int secondRowIndex = secondBlock.getRowIndex();
		int secondColIndex = secondBlock.getColIndex();
		Logger.controlLog("Swapping Blocks @ " + firstColIndex + "->"
				+ firstRowIndex + " with " + secondColIndex + "->"
				+ secondRowIndex);
		firstBlock.setRowIndex(secondRowIndex);
		firstBlock.setColIndex(secondColIndex);
		secondBlock.setRowIndex(firstRowIndex);
		secondBlock.setColIndex(firstColIndex);
		ColorGame.f_blockMatrix.get(firstColIndex).getBlockList()
				.set(firstRowIndex, secondBlock);
		ColorGame.f_blockMatrix.get(secondColIndex).getBlockList()
				.set(secondRowIndex, firstBlock);
		ColorGame.areMatricesSynced = false;
		syncBackEndMatrix();
	}

	/**
	 * Resets backEndMatrix to sync with frontEndMatrix
	 */
	public void syncBackEndMatrix() {
		Logger.controlLog("Syncing Matrices");
		int blockValue;
		int rowIndex;
		int colIndex;
		Iterator<?> blockMatrixIterator = ColorGame.f_blockMatrix.iterator();
		while (blockMatrixIterator.hasNext()) {
			blockListObject = (BlockList) blockMatrixIterator.next();
			blockList = blockListObject.getBlockList();
			Iterator<?> blockListIterator = blockList.iterator();
			while (blockListIterator.hasNext()) {
				block = (Block) blockListIterator.next();
				blockValue = block.getColor();
				rowIndex = block.getRowIndex();
				colIndex = block.getColIndex();
				ColorGame.b_colorMatrix.get(colIndex).getColorList()
						.set(rowIndex, blockValue);
			}
		}
		ColorGame.areMatricesSynced = true;
	}

	/**
	 * Prints out matrices for logging and debugging if IntMap.CONTROL_LOG is
	 * set true
	 */
	public void printMatrices() {
		Logger.controlLog("FrontEnd: \n");
		int blockValue;
		Iterator<?> blockMatrixIterator = ColorGame.f_blockMatrix.iterator();
		while (blockMatrixIterator.hasNext()) {
			blockListObject = (BlockList) blockMatrixIterator.next();
			blockList = blockListObject.getBlockList();
			Iterator<?> blockListIterator = blockList.iterator();
			while (blockListIterator.hasNext()) {
				block = (Block) blockListIterator.next();
				blockValue = block.getColor();
				if (IntMap.CONTROL_LOG)
					System.out.print(blockValue + " ");
			}
			if (IntMap.CONTROL_LOG)
				System.out.print("\n");
		}

		Logger.controlLog("BackEnd: ");

		ColorList list = null;
		Iterator<?> colorMatrixIterator = ColorGame.b_colorMatrix.iterator();
		while (colorMatrixIterator.hasNext()) {
			list = (ColorList) colorMatrixIterator.next();
			ArrayList<Integer> intList = list.getColorList();
			Iterator<?> intIterator = intList.iterator();
			while (intIterator.hasNext()) {
				if (IntMap.CONTROL_LOG)
					System.out.print(intIterator.next() + " ");
			}
			if (IntMap.CONTROL_LOG)
				System.out.print("\n");
		}
	}
}
