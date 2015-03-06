package blackdevs.vishnu.uiApis;

import blackdevs.vishnu.Colors.ColorGame;
import blackdevs.vishnu.Framework.Logger;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class GameAnimation implements InputProcessor{
	
	private boolean dragFlag = false; 
	private Vector2 touchDownCoordinate;

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
		// TODO Auto-generated method stub
		Logger.AniLog("Touched Coordinate (X,Y) : (" + screenX + "," + screenY +")" );
		if((screenX > ColorGame.upperLeft.x) && (screenX < ColorGame.upperRight.x) && (screenY > ColorGame.upperLeft.y) && (screenY < ColorGame.lowerLeft.y)){
			touchDownCoordinate = new Vector2(screenX,screenY);
		}else{
			Logger.AniLog("Touched Outside Game Blocks");
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Logger.AniLog("TouchUp Done (X,Y):: (" + screenX + "," + screenY+")");
		if((screenX > ColorGame.upperLeft.x) && (screenX < ColorGame.upperRight.x) && (screenY > ColorGame.upperLeft.y) && (screenY < ColorGame.lowerLeft.y)){
			
		}else{
			Logger.AniLog("TouchUp happened outside Game Blocks");
		}
		dragFlag = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		Logger.AniLog("The dragging is started : ");
		dragFlag = true;
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

	
	
}
