package blackdevs.vishnu.uiApis;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.Batch;

import blackdevs.vishnu.model.Block;
import blackdevs.vishnu.Colors.ColorGame;
import blackdevs.vishnu.model.BlockList;

/**
 * DRAW Game Matrix
 * 
 * @author vishnu.satis, eldo.joseph
 */
public class GameDraw {

	public static void drawBlocks() {
		Batch batch = ColorGame.batch;
		BlockList currentRowListObj = null;
		ArrayList<Block> rowColorList;
		Iterator<?> uiColorIterator = ColorGame.f_blockMatrix.iterator();
		while (uiColorIterator.hasNext()) {
			currentRowListObj = (BlockList) uiColorIterator.next();
			rowColorList = currentRowListObj.getBlockList();
			Iterator<?> rowColors = rowColorList.iterator();
			while (rowColors.hasNext()) {
				Block block = (Block) rowColors.next();
				int x = (int) block.getPosition().x;
				int y = (int) block.getPosition().y;
				batch.draw(block.getTexture(), x, ColorGame.screenY - y, block.getTextureWidth(),
						block.getTextureHieght());
			}
		}
	}
}
