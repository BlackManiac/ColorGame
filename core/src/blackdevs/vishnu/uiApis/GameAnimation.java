package blackdevs.vishnu.uiApis;

import blackdevs.vishnu.Colors.ColorGame;
import blackdevs.vishnu.Constants.IntMap;
import blackdevs.vishnu.Constants.StringMap;
import blackdevs.vishnu.Framework.Logger;
import blackdevs.vishnu.Tests.ColorSetGenerationTest;
import blackdevs.vishnu.model.Block;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class GameAnimation implements InputProcessor {

	private boolean dragFlag = false;
	private boolean touchDownFlag = false;
	private Vector2 touchDownIndex;
	private Vector2 touchUpIndex;
	private Vector2 dragStartVector; 

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {		
		Logger.AniLog("Touched Coordinate (X,Y) : (" + screenX + "," + screenY
				+ ")");
		if ((screenX > ColorGame.upperLeft.x)
				&& (screenX < ColorGame.upperRight.x)
				&& (screenY > ColorGame.upperLeft.y)
				&& (screenY < ColorGame.lowerLeft.y)) {
			touchDownFlag = true;
			touchDownIndex = ColorGame.controller.getTouchBlock(screenX,
					screenY);
		} else {
			Logger.AniLog("TouchDown Outside Game Blocks");
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(IntMap.IS_TESTING){
			ColorGame.gameInstance.getBackEndGenerator().UpdateGameProgress(StringMap.FALSE);
			ColorGame.gameInstance.getBackEndGenerator().readPlayerProgress();
			ColorSetGenerationTest colorsetTest = new ColorSetGenerationTest();
			ColorSetGenerationTest.constructorTest_1();
			ColorSetGenerationTest.constructorTest_2();
			ColorSetGenerationTest.constructorTest_3();
			ColorSetGenerationTest.constructorTest_4();
			ColorSetGenerationTest.constructorTest_5();
		}		
		Logger.AniLog("TouchUp Done (X,Y):: (" + screenX + "," + screenY + ")");
		if (this.touchDownFlag == true && this.dragFlag == true) {
			if ((screenX > ColorGame.upperLeft.x)
					&& (screenX < ColorGame.upperRight.x)
					&& (screenY > ColorGame.upperLeft.y)
					&& (screenY < ColorGame.lowerLeft.y)) {
				this.touchUpIndex = ColorGame.controller.getTouchBlock(screenX,
						screenY);
				if ((touchDownIndex.x == touchUpIndex.x)
						|| (touchDownIndex.y == touchUpIndex.y)) {
					if ((Math.abs((touchDownIndex.x - touchUpIndex.x)) == 1)
							|| (Math.abs((touchDownIndex.y - touchUpIndex.y)) == 1)) {
						this.DragNDropSuccessCallBack();
					} else {
						Logger.AniLog("The blocks were not swapped because user tried to swap non-adjecent blocks");
						this.DragNDropErrorCallBack();
					}
				} else {
					Logger.AniLog("The blocks where not swapped as the drag was not 1-D");
					this.DragNDropErrorCallBack();
				}
			} else {
				Logger.AniLog("TouchUp outside Game Blocks");
				this.DragNDropErrorCallBack();
			}
		} else {
			Logger.AniLog("touchDownFlag = " + this.touchDownFlag
					+ " dragFlag = " + this.dragFlag);
			this.DragNDropErrorCallBack();
		}
		this.setDragFlag(false);
		this.setTouchDownFlag(false);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Logger.uiLog("Blocks Dragging is Going to Start !!! TouchDown Status : " + this.touchDownFlag);
		/*if(this.touchDownFlag == true){
			Block touchedDownBlock = ColorGame.f_blockMatrix.get((int) touchDownIndex.x)
			.getBlockList().get((int) touchDownIndex.y);
			dragStartVector = touchedDownBlock.getPosition(); 
			touchedDownBlock.SetPosition(new Vector2(((touchedDownBlock.getPosition().x) + 1f),((touchedDownBlock.getPosition().y) + 1f)));
		}*/	
		setDragFlag(true);
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isDragFlag() {
		return dragFlag;
	}

	public void setDragFlag(boolean dragFlag) {
		this.dragFlag = dragFlag;
	}

	private void DragNDropSuccessCallBack() {
		ColorGame.controller.swapBlocks(
				ColorGame.f_blockMatrix.get((int) touchDownIndex.x)
						.getBlockList().get((int) touchDownIndex.y),
				ColorGame.f_blockMatrix.get((int) touchUpIndex.x)
						.getBlockList().get((int) touchUpIndex.y));
		ColorGame.controller.printMatrices();
	    if(ColorGame.controller.isLevelFinished()){
	    	Logger.gameLog("Level finished .. :)");
	    	ColorGame.gameInstance.getBackEndGenerator().UpdateGameProgress(StringMap.TRUE);
	    	ColorGame.gameStart();
	    }else{
	    	Logger.gameLog("Level still unfinished *** continue playing");
	    }
	}
	
	private void DragNDropErrorCallBack() {
		if(this.touchDownFlag == true){
			Block touchedDownBlock = ColorGame.f_blockMatrix.get((int) touchDownIndex.x)
					.getBlockList().get((int) touchDownIndex.y);
			touchedDownBlock.SetPosition(dragStartVector);
		}	
	}

	public boolean isTouchDownFlag() {
		return touchDownFlag;
	}

	public void setTouchDownFlag(boolean touchDownFlag) {
		this.touchDownFlag = touchDownFlag;
	}

	public Vector2 getDragStartVector() {
		return dragStartVector;
	}

	public void setDragStartVector(Vector2 dragStartVector) {
		this.dragStartVector = dragStartVector;
	}

}
