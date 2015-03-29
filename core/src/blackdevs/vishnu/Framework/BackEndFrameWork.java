package blackdevs.vishnu.Framework;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import blackdevs.vishnu.Colors.Dataprovider;
import blackdevs.vishnu.Constants.FileMap;
import blackdevs.vishnu.Constants.IntMap;
import blackdevs.vishnu.Constants.StringMap;
import blackdevs.vishnu.model.PlayerProgress;
import blackdevs.vishnu.model.ColorList;

import com.badlogic.gdx.Gdx;

/**
 * 
 * @author vishnu.satis
 * 
 */
public class BackEndFrameWork implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8395969600962172297L;
	private ArrayList<ColorList> colorMatrix = new ArrayList<>();
	public ArrayList<Integer> currentColorSet;
	private static PlayerProgress playerProgress;
	private ColorGameUtility colorGameUtility;
	private int currentColorSetCount;
	private int matrixSize;

	public BackEndFrameWork(int matrixSize) {
		super();
		this.matrixSize = matrixSize;
		createNextColorCombo();
		// printUiColorCombo();
	}

	public ArrayList<ColorList> getColorMatrix() {
		return colorMatrix;
	}

	public void setColorMatrix(ArrayList<ColorList> colorMatrix) {
		this.colorMatrix = colorMatrix;
	}

	/**
	 * Creates the next colorSet from which Backend matrix in going to be
	 * created
	 */
	private void createNextColorCombo() {
		if (Gdx.files.local(FileMap.GAME_PROGRESS).exists()) {
			playerProgress = new PlayerProgress(
					(PlayerProgress) Dataprovider
							.readData(FileMap.GAME_PROGRESS));
			Logger.Backlog("Read from file : " + playerProgress.toString());
			currentColorSet = new ArrayList<>(playerProgress.getLastColorSet());
			currentColorSetCount = playerProgress.getCurrentColorSetCount();
			if ((playerProgress.getIsColorComboFinished())
					.equals(StringMap.TRUE)) {
				if ((playerProgress.getIsColorSetFinished())
						.equals(StringMap.TRUE)) {
					Logger.Backlog("Both colorSet and colorCombo finished so generate both TTTT !!!!");
					if (currentColorSetCount >= IntMap.COLOR_SET_MULTIPLE) {
						Logger.Backlog("Going to generate new colorSet !!!");
						colorGameUtility = new ColorGameUtility(matrixSize,
								currentColorSet, StringMap.TRUE);
					} else {
						Logger.Backlog("Going to generate new colorSet (2) !!!");
						colorGameUtility = new ColorGameUtility(matrixSize,
								currentColorSet, StringMap.TRUE);
					}
					currentColorSet = colorGameUtility.getColorSet();
					createUImatrix();
					playerProgress
							.setCurrentColorSetCount(IntMap.INITIALIZE_COLORSET);
				} else {
					Logger.Backlog("Unfinished colorSet count .. !!! generate a colorCombo return it");
					createUImatrix();
					currentColorSetCount++;
					playerProgress
							.setCurrentColorSetCount(currentColorSetCount);
				}
				playerProgress.setLastColorSet(currentColorSet);
				playerProgress.setUiColorCombo(colorMatrix);
				playerProgress.setIsColorComboFinished(StringMap.FALSE);
				playerProgress.setIsColorSetFinished(StringMap.FALSE);
				Dataprovider.saveData(playerProgress, FileMap.GAME_PROGRESS);
			} else {
				Logger.Backlog("The color combo was not finished so loading from the file and displaying it");
				colorMatrix = playerProgress.getUiColorCombo();
			}
		} else {
			Logger.Backlog(" Game Starts for the first time ---");
			playerProgress = new PlayerProgress();
			currentColorSet = new ArrayList<>();
			colorGameUtility = new ColorGameUtility(matrixSize,
					currentColorSet, StringMap.TRUE);
			currentColorSet = colorGameUtility.getColorSet();
			createUImatrix();
			// Intializing the colorSetCount
			playerProgress.setCurrentColorSetCount(IntMap.INITIALIZE_COLORSET);
			playerProgress.setIsColorComboFinished(StringMap.FALSE);
			playerProgress.setIsColorSetFinished(StringMap.FALSE);
			playerProgress.setLastColorSet(currentColorSet);
			playerProgress.setUiColorCombo(colorMatrix);
			Logger.Backlog("EEEE :: "+playerProgress.toString());
			Dataprovider.saveData(playerProgress, FileMap.GAME_PROGRESS);
		}
	}

	/**
	 * upDates the player progress after each stage. call this in stage finish
	 * and game exit.
	 * 
	 * @param uiPlayer
	 */
	public void UpdateGameProgress(String isColorComboFinished) {
		playerProgress.setIsColorComboFinished(isColorComboFinished);
		if (currentColorSetCount >= IntMap.COLOR_SET_MULTIPLE) {
			playerProgress.setIsColorSetFinished(StringMap.TRUE);
		} else {
			playerProgress.setIsColorSetFinished(StringMap.FALSE);
		}
		Logger.Backlog("Just before updating to file : "
				+ playerProgress.toString());
		Dataprovider.saveData(playerProgress, FileMap.GAME_PROGRESS);
	}
	
	public void readPlayerProgress(){
		PlayerProgress ply = new PlayerProgress((PlayerProgress) Dataprovider
				.readData(FileMap.GAME_PROGRESS));
		Logger.Backlog("red :: "+ ply.toString());
	}

	/**
	 * Creating the backend Matrix
	 */
	private void createUImatrix() {
		int colornumber = 0;
		int randomColorIndex;
		ArrayList<Integer> allColor = new ArrayList<>();
		ArrayList<Integer> rowColor = new ArrayList<>();
		Logger.Backlog("allColor" + "[");
		for (int i = 0; i < currentColorSet.size(); i++) {
			for (int j = 0; j < currentColorSet.get(i); j++) {
				allColor.add(colornumber);
				Logger.Backlog(colornumber + ",");
			}
			colornumber++;
		}
		for (int i = 0; i < Math.sqrt(matrixSize); i++) {
			for (int j = 0; j < Math.sqrt(matrixSize); j++) {
				randomColorIndex = generateRandom(0, (allColor.size() - 1));
				rowColor.add(allColor.get(randomColorIndex));
				allColor.remove(randomColorIndex);
			}
			Logger.Backlog(rowColor.toString());
			colorMatrix.add(new ColorList(rowColor));
			Logger.Backlog("uiColorCombo -->"
					+ colorMatrix.get(i).getColorList().toString());
			rowColor = new ArrayList<>();
		}
	}

	private int generateRandom(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	/*
	 * private void printUiColorCombo(){ ColorList rowCombo = new ColorList();
	 * ArrayList<Integer> rowColor = new ArrayList<>(); for(int i=0; i<
	 * colorMatrix.size();i++){ rowCombo = colorMatrix.get(i); rowColor =
	 * rowCombo.getColorList(); for(int j=0;j<rowColor.size();j++){
	 * System.out.print(rowColor.get(j)+"  "); } System.out.println(); } }
	 */
}
