package blackdevs.vishnu.Colors.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import blackdevs.vishnu.Colors.ColorGame;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 300;
		config.height = 500;
		new LwjglApplication(new ColorGame(), config);
	}
}
