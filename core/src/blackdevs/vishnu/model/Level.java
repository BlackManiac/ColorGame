package blackdevs.vishnu.model;

import java.util.ArrayList;

/**
 * User Levels
 * 
 * @author eldo.joseph
 */
public class Level {
	private String levelName;
	private ArrayList<Integer> combinations = new ArrayList<>();
	public Level(String levelName, ArrayList<Integer> combinations) {
		super();
		this.levelName = levelName;
		this.combinations = combinations;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public ArrayList<Integer> getCombinations() {
		return combinations;
	}
	public void setCombinations(ArrayList<Integer> combinations) {
		this.combinations = combinations;
	}
}
