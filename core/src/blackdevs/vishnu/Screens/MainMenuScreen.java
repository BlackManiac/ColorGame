package blackdevs.vishnu.Screens;

import blackdevs.vishnu.Colors.ColorGame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenuScreen implements Screen {

	private final ColorGame gameInstance;
	private Stage stage; // For Setting up the Screen
	private TextButton playButton, exitButton; // Buttons to be used
	private TextureAtlas atlas; // The Background of the Main Screen
	private BitmapFont gameFont;// The Font in the buttons
	Skin skin; // Tota skin to be used in the screen

	public MainMenuScreen(final ColorGame gameInstance) {
		this.gameInstance = gameInstance;
	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		atlas = new TextureAtlas("UiGadgets/pack");
		skin = new Skin(atlas);
		gameFont = new BitmapFont(Gdx.files.internal("Fonts/BlackFont.fnt"),
				false);
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("play2.9");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = gameFont;
		playButton = new TextButton("", textButtonStyle);
		exitButton = new TextButton("", textButtonStyle);
		playButton.pad(20);
		exitButton.setX(50);
		exitButton.setY(50);
		playButton.setX(100);
		playButton.setY(100);
		playButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				dispose();
				((Game) Gdx.app.getApplicationListener())
						.setScreen(new GamePlayScreen(gameInstance));

			}
		});
		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				dispose();
				Gdx.app.exit();
			}
		});
		stage.addActor(exitButton);
		stage.addActor(playButton);

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Table.drawDebugChildren(stage);

		stage.act(delta);
		stage.draw();

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
		stage.dispose();
		atlas.dispose();
		gameFont.dispose();
	}

}


/*
 * LabelStyle headingStyle = new LabelStyle(gameFont, Color.RED);
 * heading = new Label("VISHNU", headingStyle); heading.setFontScale(5);
 * heading.setX(150); heading.setY(150);
 */
// table.debug();
// stage.addActor(heading);
