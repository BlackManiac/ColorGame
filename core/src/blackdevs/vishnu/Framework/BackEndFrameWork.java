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
 * @author KH1888
 * 
 */
public class BackEndFrameWork implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8395969600962172297L;
	private ArrayList<ColorList> colorMatrix = new ArrayList<>();
	private ArrayList<Integer> currentColorSet;
	private static PlayerProgress playerProgress;
	private ColorGameUtility colorGameUtility;
	private int currentColorSetCount;
	private int matrixSize;
	public BackEndFrameWork(int matrixSize) {
		super();
		this.matrixSize = matrixSize;
		createNextColorCombo();
		//printUiColorCombo();
	}
	public ArrayList<ColorList> getUiColorCombo() {
		return colorMatrix;
	}

	public void setUiColorCombo(ArrayList<ColorList> uiColorCombo) {
		this.colorMatrix = uiColorCombo;
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
			currentColorSet = new ArrayList<>(playerProgress.getLastColorSet());
			currentColorSetCount = playerProgress.getCurrentColorSetCount();
			if ((playerProgress.getIsColorComboFinished())
					.equals(StringMap.TRUE)) {
				if ((playerProgress.getIsColorSetFinished())
						.equals(StringMap.TRUE)) {
					if (currentColorSetCount >= IntMap.COLOR_SET_MULTIPLE) {
						colorGameUtility = new ColorGameUtility(matrixSize,
								currentColorSet, StringMap.TRUE);
					} else {
						colorGameUtility = new ColorGameUtility(matrixSize,
								currentColorSet, StringMap.FALSE);
					}
					currentColorSet = colorGameUtility.getColorSet();
					createUImatrix();
					playerProgress
							.setCurrentColorSetCount(IntMap.INITIALIZE_COLORSET);
				} else {
					createUImatrix();
					currentColorSetCount++;
					playerProgress
							.setCurrentColorSetCount(currentColorSetCount);
				}
			} else {
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
			playerProgress.setCurrentColorSetCount(IntMap.INITIALIZE_COLORSET);
		}
		playerProgress.setIsColorComboFinished(StringMap.FALSE);
		playerProgress.setIsColorSetFinished(StringMap.FALSE);
		playerProgress.setLastColorSet(currentColorSet);
		playerProgress.setUiColorCombo(colorMatrix);
		UpdateGameProgress(playerProgress.getIsColorComboFinished());
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
		Dataprovider.saveData(playerProgress, FileMap.GAME_PROGRESS);
	}

	/**
	 * Creating the backend Matrix
	 */
	private void createUImatrix() {
		int colornumber = 0;
		int randomColorIndex;
		ArrayList<Integer> allColor = new ArrayList<>();
		ArrayList<Integer> rowColor = new ArrayList<>();
		Logger.Backlog("allColor"+"[");
		for (int i = 0; i < currentColorSet.size(); i++) {
			for (int j = 0; j < currentColorSet.get(i); j++) {
				allColor.add(colornumber);
				Logger.Backlog(colornumber+",");
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
			Logger.Backlog("uiColorCombo -->"+colorMatrix.get(i).getColorList().toString());
			rowColor = new ArrayList<>();
		}
	}

	private int generateRandom(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
/*	private void printUiColorCombo(){
		ColorList rowCombo = new ColorList();
		ArrayList<Integer> rowColor = new ArrayList<>();
		for(int i=0; i< colorMatrix.size();i++){
			rowCombo = colorMatrix.get(i);
			rowColor = rowCombo.getColorList();
			for(int j=0;j<rowColor.size();j++){
				System.out.print(rowColor.get(j)+"  ");
			}
		System.out.println();	
		}
	}*/
}
