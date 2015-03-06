package blackdevs.vishnu.model;

import blackdevs.vishnu.Constants.ImageMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Basic unit in the front end block matrix
 * 
 * @author eldo.joseph, vishnu.satis
 */
public class Block {

	private int color;
	private Vector2 position;
	private String textureLocation;
	private Texture texture;
	private float textureWidth;
	private float textureHieght;
	private int rowIndex;
	private int colIndex;

	public Block(Vector2 position, float textureWidth,
			float textureHieght, int color) {
		this.position = position;
		this.color = color;
		this.textureLocation = ImageMap.color.get(color);
		this.texture = new Texture(Gdx.files.internal(textureLocation));
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

	private void setTextureLocation(String textureLocation) {
		this.textureLocation = textureLocation;
	}

	public Texture getTexture() {
		return this.texture;
	}

	private void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void SetPosition(Vector2 position) {
		this.position = position;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int getColIndex() {
		return colIndex;
	}

	public void setColIndex(int colIndex) {
		this.colIndex = colIndex;
	}
	
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
		setTextureLocation(ImageMap.color.get(color));
		setTexture(new Texture(Gdx.files.internal(textureLocation)));
	}

}
