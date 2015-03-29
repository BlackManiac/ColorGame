package blackdevs.vishnu.Framework;

import java.util.ArrayList;
import java.util.Random;

import blackdevs.vishnu.Constants.StringMap;

/**
 * Create and returns the next BackendMatrix
 * 
 * @author vishnu.satis
 * 
 */
public class ColorGameUtility {
	private ArrayList<Integer> colorSet;
	private int matrixSize;
	private int noOfColors;
	private String isNextTime;

	public String getisNextTime() {
		return isNextTime;
	}

	public void setisNextTime(String isNextTime) {
		this.isNextTime = isNextTime;
	}

	public ColorGameUtility() {
		super();
	}

	public ColorGameUtility(int matrixSize, ArrayList<Integer> colorSet,
			String isNextTime) {
		super();
		Logger.Backlog("The ColoSet :" + colorSet.toString());
		
			Logger.Backlog("The ColoSet size :" + colorSet.size());
			this.noOfColors = colorSet.size();
		
		this.colorSet = colorSet;
		this.matrixSize = matrixSize;
		this.isNextTime = isNextTime;
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
		if (colorSet.size() < 2) {
			noOfColors = 2;
		} else {
			if (isNextTime.equals(StringMap.TRUE)) {
				Logger.Backlog("Generating the colorSet size for new ColorSet ... ");
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
		Logger.Backlog("Generating the next colorSet MAX : "+max+"NO_OF_COLORS : "+ noOfColors);
		for (int i = 0; i < (noOfColors - 1); i++) {
			colorSet.add(generateRandom(1, max));
			Logger.Backlog("Values added to ColorSet::" + colorSet.get(i));
			colorSum = colorSum + colorSet.get(i);
		}
		if (matrixSize - colorSum > max || (matrixSize - colorSum) <= 0) {
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
