package blackdevs.vishnu.uiApis;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.math.Vector2;

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
	private float blockfactor;

	public GameInstanceController() {
		Logger.AniLog("BlockFactor(Square Size / 5) : "
				+ ((ColorGame.upperRight.x - ColorGame.upperLeft.x) / 5));
		setBlockfactor((float) ((ColorGame.upperRight.x - ColorGame.upperLeft.x) / 5));
	}

	public Vector2 getTouchBlock(float touchX, float touchY) {
		Logger.AniLog("Bloackfactor :" + blockfactor);
		Logger.AniLog("touch (X,Y) : (" + touchX + "," + touchY + ")");
		int rowIndex = this.getTouchX(touchX);
		int colIndex = this.getTouchY(touchY);
		Logger.AniLog("Index (X,Y) : (" + rowIndex + "," + colIndex + ")");
		return new Vector2(rowIndex, colIndex);
	}

	private int getTouchX(float touchX) {
		if (touchX >= ColorGame.upperLeft.x
				&& touchX < (ColorGame.upperLeft.x + this.blockfactor)) {
			return 0;
		} else if (touchX >= (ColorGame.upperLeft.x + this.blockfactor)
				&& touchX < (ColorGame.upperLeft.x + 2 * this.blockfactor)) {
			return 1;
		} else if (touchX >= (ColorGame.upperLeft.x + 2 * this.blockfactor)
				&& touchX < (ColorGame.upperLeft.x + 3 * this.blockfactor)) {
			return 2;
		} else if (touchX >= (ColorGame.upperLeft.x + 3 * this.blockfactor)
				&& touchX < (ColorGame.upperLeft.x + 4 * this.blockfactor)) {
			return 3;
		} else if (touchX >= (ColorGame.upperLeft.x + 4 * this.blockfactor)
				&& touchX <= (ColorGame.upperLeft.x + 5 * this.blockfactor)) {
			return 4;
		}
		return -1;
	}

	private int getTouchY(float touchY) {
		if (touchY >= ColorGame.upperLeft.y
				&& touchY < ColorGame.upperLeft.y + this.blockfactor) {
			return 0;
		} else if (touchY >= ColorGame.upperLeft.y + this.blockfactor
				&& touchY < ColorGame.upperLeft.y + 2 * this.blockfactor) {
			return 1;
		} else if (touchY >= ColorGame.upperLeft.y + 2 * this.blockfactor
				&& touchY < ColorGame.upperLeft.y + 3 * this.blockfactor) {
			return 2;
		} else if (touchY >= ColorGame.upperLeft.y + 3 * this.blockfactor
				&& touchY < ColorGame.upperLeft.y + 4 * this.blockfactor) {
			return 3;
		} else if (touchY >= ColorGame.upperLeft.y + 4 * this.blockfactor
				&& touchY < ColorGame.upperLeft.y + 5 * this.blockfactor) {
			return 4;
		}
		return -1;
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
		//printMatrices();
		ColorGame.f_blockMatrix.get(firstColIndex).getBlockList()
				.set(firstRowIndex, secondBlock);
		ColorGame.f_blockMatrix.get(secondColIndex).getBlockList()
				.set(secondRowIndex, firstBlock);
		//printMatrices();
		ColorGame.areMatricesSynced = false;
		syncBackEndMatrix();
	}

	/**
	 * Resets backEndMatrix to sync with frontEndMatrix
	 */
	public void syncBackEndMatrix() { //TODO make this function better for better performance
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
	 * Is the Game Completed 
	 * @return
	 */
	public boolean isLevelFinished(){
		for(int i=0;i < IntMap.BLOCKS_PER_ROW ;i++){
			for(int j=0;j< IntMap.BLOCKS_PER_ROW;j++){
				if((this.getColorNumber(i,j) != this.getColorNumber(i,j+1)) && (this.getColorNumber(i,j) != this.getColorNumber(i+1,j))){
					Logger.gameLog("Game finish Condition 1 Passed ***");
					if(i-1 >= 0){
						if((this.getColorNumber(i,j) != this.getColorNumber(i-1,j))){
							Logger.gameLog("Game finish Condition 2 Passed ***");
						}else{
							Logger.gameLog("The game is unfinished please continue playing(-i) !!!");
							return false;
						}
					}	
					if(j-1 >= 0){
						if((this.getColorNumber(i,j) != this.getColorNumber(i,j-1))){
						Logger.gameLog("Game finish Condition 2 Passed ***");
							return true;
						}else{
							Logger.gameLog("The game is unfinished please continue playing(-j) !!!");
							return false;
						}	
					}
					
				}else{
					Logger.gameLog("The game is unfinished please continue playing(+) !!!");
					return false;
				}
			}
		}
		return false;		
	}
	private int getColorNumber(int row,int colomn){
		return ColorGame.b_colorMatrix.get(row).getColorList().get(colomn);		
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

	public float getBlockfactor() {
		return blockfactor;
	}

	public void setBlockfactor(float blockfactor) {
		this.blockfactor = blockfactor;
	}
}
