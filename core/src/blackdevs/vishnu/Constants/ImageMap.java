package blackdevs.vishnu.Constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;

public interface ImageMap {
	//blockSquareSide = 5 * (blockWidth) + 4 * (spaceWidth) (Assuming 5*5 block)
	//blockSquareSide = 80 percent of screen width
	public float blockSquareSide = 80;
	public float spaceWidthRatio = 29;
	public float thirty = (float) (0.3 * (Gdx.graphics.getHeight()));
	
	public float headerHeight = 30;
	public float marginWidth = 10;
	
	public float headerSpaceRatio = 3;
	public float footerSpaceRatio = 1;
	public float widthLeftRatio = 1;
	public float widthRightRatio = 1;
	
	public Map<Integer, String> color = Collections
			.unmodifiableMap(new HashMap<Integer, String>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 7260523580986838375L;

				{
					put(0,"game.png");
					put(1,"game.png");
					put(3,"game.png");
					put(4,"game.png");
					put(2,"game.png");
				}				
			});
}
