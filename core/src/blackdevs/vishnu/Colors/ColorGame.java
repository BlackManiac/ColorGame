package blackdevs.vishnu.Colors;

import java.util.ArrayList;

import blackdevs.vishnu.Constants.ImageMap;
import blackdevs.vishnu.Framework.Logger;
import blackdevs.vishnu.Screens.MainMenuScreen;

import blackdevs.vishnu.model.BlockList;
import blackdevs.vishnu.uiApis.F_GameUiController;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ColorGame extends Game {

	public static float blockWidth;
	public static float blockSpacing;
	public static float blockStartY;
	public static float blockStartX;
	public static float matrixSize;
	public static float remainingX;
	public static float remainingY;
	public static float screenX;
	public static float screenY;
	public static Vector2 upperLeft;
	public static Vector2 upperRight;
	public static Vector2 lowerLeft;
	public static Vector2 lowerRight;
	public static SpriteBatch batch;
	F_GameUiController uiController;
	public static ArrayList<BlockList> uiMatrix = new ArrayList<>();

	@Override
	public void create() {
		initializeCoordinates();
		uiController = new F_GameUiController();
		uiMatrix = uiController.getBlockList();
		//Gdx.input.setInputProcessor(inputProcessor);
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	private void initializeCoordinates() {
		screenX = (float) Gdx.graphics.getWidth();
		screenY = (float) Gdx.graphics.getHeight();
		float drawingLength = screenX;
		if (drawingLength > screenY) {
			drawingLength = screenY;
		}
		Logger.Frontlog("Bigger Side Length = " + drawingLength);
		blockSpacing = (float) (((ImageMap.blockSquareSide * (0.01)) * (1 / ImageMap.spaceWidthRatio)) * drawingLength);
		matrixSize = ImageMap.spaceWidthRatio * blockSpacing;
		blockWidth = 5 * blockSpacing;
		remainingX = screenX - matrixSize;
		remainingY = screenY - matrixSize;
		blockStartY = (ImageMap.headerSpaceRatio / (ImageMap.headerSpaceRatio + ImageMap.footerSpaceRatio))
				* remainingY;
		blockStartX = (ImageMap.widthLeftRatio / (ImageMap.widthLeftRatio + ImageMap.widthRightRatio))
				* remainingX;
		Logger.Frontlog("blockSpacing = " + blockSpacing);
		Logger.Frontlog("blockWidth = " + blockWidth);
		Logger.Frontlog("blockStartX = " + blockStartX);
		Logger.Frontlog("blockStartY = " + blockStartY);

		upperLeft = new Vector2(blockStartX, blockStartY);
		upperRight = new Vector2(blockStartX + matrixSize, blockStartY);
		lowerLeft = new Vector2(blockStartX, blockStartY + matrixSize);
		lowerRight = new Vector2(blockStartX + matrixSize, blockStartY
				+ matrixSize);

		Logger.Frontlog("upperLeft.x = " + upperLeft.x + " " + "upperLeft.y = "
				+ upperLeft.y);
		Logger.Frontlog("upperRight.x = " + upperRight.x + " "
				+ "upperRight.y = " + upperRight.y);
		Logger.Frontlog("lowerLeft.x = " + lowerLeft.x + " " + "lowerLeft.y = "
				+ lowerLeft.y);
		Logger.Frontlog("lowerRight.x = " + lowerRight.x + " "
				+ "lowerRight.y = " + lowerRight.y);
	}

	public void dispose() {
		batch.dispose();
	}
}
