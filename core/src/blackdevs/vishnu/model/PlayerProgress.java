package blackdevs.vishnu.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Object has the details of the last Level player by the player
 * 
 * @author vishnu.satis
 * 
 */
public class PlayerProgress implements Serializable {
	public PlayerProgress() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 638713945830967571L;
	private String isColorComboFinished;
	private String isColorSetFinished;
	private ArrayList<Integer> LastColorSet = new ArrayList<>();
	private ArrayList<ColorList> uiColorCombo = new ArrayList<>();
	private int currentColorSetCount;

	public ArrayList<ColorList> getUiColorCombo() {
		return uiColorCombo;
	}

	public PlayerProgress(String isColorComboFinished,
			ArrayList<Integer> LastColorSet,
			ArrayList<ColorList> uiColorCombo) {
		super();
		this.isColorComboFinished = isColorComboFinished;
		this.LastColorSet = LastColorSet;
		this.uiColorCombo = uiColorCombo;
	}

	public void setUiColorCombo(ArrayList<ColorList> uiColorCombo) {
		this.uiColorCombo = uiColorCombo;
	}

	public PlayerProgress(PlayerProgress progress) {
		this.isColorComboFinished = progress.isColorComboFinished;
		this.LastColorSet = progress.LastColorSet;
		this.uiColorCombo = progress.uiColorCombo;
	}

	public String getIsColorComboFinished() {
		return isColorComboFinished;
	}

	public void setIsColorComboFinished(String isLevelFinished) {
		this.isColorComboFinished = isLevelFinished;
	}

	public String getIsColorSetFinished() {
		return isColorSetFinished;
	}

	public void setIsColorSetFinished(String isColorSetFinished) {
		this.isColorSetFinished = isColorSetFinished;
	}

	public ArrayList<Integer> getLastColorSet() {
		return LastColorSet;
	}

	public void setLastColorSet(ArrayList<Integer> lastColorSet) {
		LastColorSet = lastColorSet;
	}

	public int getCurrentColorSetCount() {
		return currentColorSetCount;
	}

	public void setCurrentColorSetCount(int currentColorSetCount) {
		this.currentColorSetCount = currentColorSetCount;
	}

}
