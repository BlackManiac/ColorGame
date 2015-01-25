package blackdevs.vishnu.Colors;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ColorGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture texture;
	AppInfo appinfo;
	Player player;

	@Override
	public void create() {
		player = new Player(new Vector2(Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2), "game.png");
		batch = new SpriteBatch();
		if (Gdx.files.local("Appinfo.dat").exists()) {
			appinfo = (AppInfo) dataprovider.readData("Appinfo.dat");

		} else {
			appinfo = new AppInfo();
			dataprovider.saveData(appinfo, "Appinfo.dat");
			System.out.println("Creating file , Writing to file");
		}
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		player.update();

		batch.begin();
		batch.draw(player.getTexture(), player.getPosition().x,
				player.getPosition().y);
		batch.end();
	}
}
