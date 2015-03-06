package blackdevs.vishnu.Framework;

import java.util.ArrayList;
import java.util.Random;

import blackdevs.vishnu.Constants.StringMap;

/**
 * Create and returns the next BackendMatrix
 * 
 * @author Vishnu Satis
 * 
 */
public class ColorGameUtility {
	private ArrayList<Integer> colorSet;
	private int matrixSize;
	private int noOfColors;
	private String GenerateNextLevel;

	public String getGenerateNextLevel() {
		return GenerateNextLevel;
	}

	public void setGenerateNextLevel(String generateNextLevel) {
		GenerateNextLevel = generateNextLevel;
	}

	public ColorGameUtility() {
		super();
	}

	public ColorGameUtility(int matrixSize, ArrayList<Integer> colorSet,
			String GenerateNextLevel) {
		super();
		this.colorSet = colorSet;
		this.matrixSize = matrixSize;
		this.GenerateNextLevel = GenerateNextLevel;
		createNextColorSet();
		printColorSet();
	}

	public int getMatrixSize() {
		return matrixSize;
	}

	public void setMatrixSize(int matrixSize) {
		this.matrixSize = matrixSize;
	}

	public ArrayList<Integer> getColorSet() {
		return colorSet;
	}

	public void setColorSet(ArrayList<Integer> colorSet) {
		this.colorSet = colorSet;
	}

	/**
	 * Creates the next colorSet from which Backend matrix in going to be
	 * created
	 */
	private void createNextColorSet() {
		if (colorSet.size() <= 2) {
			noOfColors = 2;

		} else {
			if (GenerateNextLevel.equals(StringMap.TRUE)) {
				noOfColors = colorSet.size() + 1;

			} else {
				noOfColors = colorSet.size();

			}
		}
		generateColorSet();
	}

	/**
	 * Generates a Random colorSet
	 * @param noOfColors is taken
	 */
	private void generateColorSet() {
		int max, colorSum = 0;
		colorSet = new ArrayList<>();
		if (matrixSize % 2 == 0) {
			max = matrixSize / 2;
		} else {
			max = matrixSize / 2 + 1;
		}
		for (int i = 0; i < (noOfColors - 1); i++) {
			colorSet.add(generateRandom(1, max));
			colorSum = colorSum + colorSet.get(i);
		}
		if (matrixSize - colorSum > max) {
			generateColorSet();
		} else {
			colorSet.add(matrixSize - colorSum);
		}
	}

	private int generateRandom(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
	private void printColorSet(){
		Logger.Backlog("ColorSet :"+"[");
		for(int i = 0; i< colorSet.size(); i++){
			Logger.Backlog(colorSet.get(i)+",");
		}
		Logger.Backlog("]");
	}

}
