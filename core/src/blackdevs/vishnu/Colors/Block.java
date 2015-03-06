package blackdevs.vishnu.Colors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Block {

	private Vector2 position;
	private String textureLocation;
	private Texture texture;
	private float textureWidth;
	private float textureHieght;

	public Block(Vector2 position, String textureLoc, float textureWidth,
			float textureHieght) {
		this.position = position;
		this.texture = new Texture(Gdx.files.internal(textureLoc));
		this.textureWidth = textureWidth;
		this.textureHieght = textureHieght;
	}

	public void setTextureSize(float textureWidth, float textureHeight) {
		this.textureWidth = textureWidth;
		this.textureHieght = textureHeight;
	}

	public float getTextureWidth() {
		return this.textureWidth;
	}

	public float getTextureHieght() {
		return this.textureHieght;
	}

	public String getTextureLocation() {
		return textureLocation;
	}

	public void setTextureLocation(String textureLocation) {
		this.textureLocation = textureLocation;
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
		if (Gdx.input.isTouched(0)) {
			if (Gdx.input.getDeltaX() != 0) {
				position.x = position.x + Gdx.input.getDeltaX();
			} else if (Gdx.input.getDeltaY() != 0) {
				position.y = position.y - Gdx.input.getDeltaY();
			}
		}
		
	}
}
