package blackdevs.vishnu.uiApis;

import java.util.ArrayList;
import java.util.Iterator;

import blackdevs.vishnu.Colors.Block;
import blackdevs.vishnu.Colors.ColorGame;
import blackdevs.vishnu.Constants.ImageMap;
import blackdevs.vishnu.Constants.IntMap;
import blackdevs.vishnu.Framework.BackEndFrameWork;
import blackdevs.vishnu.Framework.Logger;
import blackdevs.vishnu.model.BlockList;
import blackdevs.vishnu.model.ColorList;

import com.badlogic.gdx.math.Vector2;

public class F_GameUiController {
	public F_GameUiController() {
		super();
		generateUiBlockList();
	}
	
	private int rowIndex = 0;
	private int ColomnIndex = 0;
	private BackEndFrameWork colorCombo = new BackEndFrameWork(IntMap.MATRIX_SIZE);
	private ArrayList<ColorList> backEndArray;
	private ArrayList<BlockList> blockMatrix = new ArrayList<>();

	public ArrayList<BlockList> getBlockList() {
		return blockMatrix;
	}

	public void setBlockList(ArrayList<BlockList> blockList) {
		this.blockMatrix = blockList;
	}

	private void generateUiBlockList() {
		backEndArray = colorCombo.getUiColorCombo();
		Iterator<?> backEndIterator = backEndArray.iterator();
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
				Block objectToAdd = getMeABlock(color);
				listToAdd.addBlock(objectToAdd);
				++rowIndex;
			}
			rowIndex = 0;
			this.blockMatrix.add(listToAdd);
			++ColomnIndex;
		}
	}

	private Block getMeABlock(int colorToSet) {
		Vector2 blockpostion = new Vector2();
		blockpostion.y = (ColorGame.blockStartY
				+ ((ColomnIndex + 1) * ColorGame.blockWidth) + (ColomnIndex * ColorGame.blockSpacing));
		blockpostion.x = (ColorGame.blockStartX + (rowIndex * (ColorGame.blockWidth + ColorGame.blockSpacing)));
		Logger.Frontlog(("(X,Y) = "+"("+blockpostion.x + "," + blockpostion.y+")"));
		String textureLoc = ImageMap.color.get(colorToSet);
		Block block = new Block(blockpostion, textureLoc,
				ColorGame.blockWidth, ColorGame.blockWidth);
		return block;
	}
}
