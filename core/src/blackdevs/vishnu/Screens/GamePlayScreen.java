package blackdevs.vishnu.Screens;

import blackdevs.vishnu.Colors.ColorGame;
import blackdevs.vishnu.uiApis.GameDraw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class GamePlayScreen implements Screen {

	private final ColorGame gameInstance;
    
	
	public GamePlayScreen(final ColorGame gameInstance){
		this.gameInstance = gameInstance;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (Gdx.input.isTouched()) {
		}
		gameInstance.batch.begin();
		GameDraw.drawBlocks();
		gameInstance.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
