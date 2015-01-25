package blackdevs.vishnu.Colors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Player {

	Vector2 position;
	String textureLoc;
	Texture texture;

	public Player(Vector2 position, String textureLoc) {
		this.position = position;
		this.texture = new Texture(Gdx.files.internal(textureLoc));
	}

	public Texture getTexture() {
		return this.texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void SetPosition(Vector2 position) {
		this.position = position;
	}

	public void update() {
		if (Gdx.input.isKeyPressed(Keys.W)) {
			position.y += 1f;
		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			position.x -= 1f;
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			position.y -= 1f;
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			position.x += 1f;
		}
		if(Gdx.input.isTouched(0)){
			if(Gdx.input.getDeltaX() != 0){
				position.x = position.x + Gdx.input.getDeltaX();
			}
			else if(Gdx.input.getDeltaY() != 0){
				position.y = position.y - Gdx.input.getDeltaY();
			}
		}
	}
}
