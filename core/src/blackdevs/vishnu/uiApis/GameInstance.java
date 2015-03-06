package blackdevs.vishnu.uiApis;

import java.util.ArrayList;
import java.util.Iterator;

import blackdevs.vishnu.model.Block;
import blackdevs.vishnu.Colors.ColorGame;
import blackdevs.vishnu.Constants.IntMap;
import blackdevs.vishnu.Framework.BackEndFrameWork;
import blackdevs.vishnu.Framework.Logger;
import blackdevs.vishnu.model.BlockList;
import blackdevs.vishnu.model.ColorList;

import com.badlogic.gdx.math.Vector2;

/**
 * Game Control. Only one instance per game session. Methods are NOT to be
 * called elsewhere
 * 
 * @author eldo.joseph, vishnu.satis
 */
public class GameInstance {
	public GameInstance() {
		super();
		generateBlockMatrix();
	}

	private int rowIndex = 0;
	private int colomnIndex = 0;
	private BackEndFrameWork backEndGenerator = new BackEndFrameWork(
			IntMap.MATRIX_SIZE);
	private ArrayList<ColorList> colorMatrix;
	private ArrayList<BlockList> blockMatrix = new ArrayList<>();

	// BackEND matrix
	public ArrayList<ColorList> getColorMatrix() {
		return colorMatrix;
	}

	public void setColorMatrix(ArrayList<ColorList> colorMatrix) {
		this.colorMatrix = colorMatrix;
	}

	// FrontEND matrix
	public ArrayList<BlockList> getBlockMatrix() {
		return blockMatrix;
	}

	public void setBlockMatrix(ArrayList<BlockList> blockMatrix) {
		this.blockMatrix = blockMatrix;
	}

	/**
	 * Generates Front end Block Matrix based on back end Colormatrix
	 */
	private void generateBlockMatrix() {
		colorMatrix = backEndGenerator.getColorMatrix();
		Iterator<?> backEndIterator = colorMatrix.iterator();
		ColorList currentRow = null;
		ArrayList<Integer> rowColorCombination;
		BlockList listToAdd;
		while (backEndIterator.hasNext()) {
			listToAdd = new BlockList();
			currentRow = (ColorList) backEndIterator.next();
			rowColorCombination = currentRow.getColorList();
			Iterator<?> rowColors = rowColorCombination.iterator();
			while (rowColors.hasNext()) {
				int color = (Integer) rowColors.next();
				Block objectToAdd = generateBlock(color);
				objectToAdd.setRowIndex(rowIndex);
				objectToAdd.setColIndex(colomnIndex);
				listToAdd.addBlock(objectToAdd);
				++rowIndex;
			}
			rowIndex = 0;
			this.blockMatrix.add(listToAdd);
			++colomnIndex;
		}
	}

	/**
	 * Not to be used explicitly. Gets a block depending on row and column
	 * indices with UI positioning coordinates
	 * 
	 * @param colorToSet
	 * @return
	 */
	private Block generateBlock(int colorToSet) {
		Vector2 blockpostion = new Vector2();
		blockpostion.y = (ColorGame.blockStartY
				+ ((colomnIndex + 1) * ColorGame.blockWidth) + (colomnIndex * ColorGame.blockSpacing));
		blockpostion.x = (ColorGame.blockStartX + (rowIndex * (ColorGame.blockWidth + ColorGame.blockSpacing)));
		Logger.Frontlog(("(X,Y) = " + "(" + blockpostion.x + ","
				+ blockpostion.y + ")"));
		Block block = new Block(blockpostion, ColorGame.blockWidth,
				ColorGame.blockWidth, colorToSet);
		return block;
	}
}
