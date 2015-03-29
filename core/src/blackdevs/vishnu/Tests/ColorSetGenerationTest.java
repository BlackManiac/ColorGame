package blackdevs.vishnu.Tests;

import java.util.ArrayList;

import blackdevs.vishnu.Constants.IntMap;
import blackdevs.vishnu.Constants.StringMap;
import blackdevs.vishnu.Framework.ColorGameUtility;

public class ColorSetGenerationTest {
	
	private static ColorGameUtility colorsetTest;
	private static ArrayList<Integer> currentColorSet;
	
	public ColorSetGenerationTest() {
		currentColorSet = new ArrayList<>();
		currentColorSet.add(12);
		currentColorSet.add(13);
	}
	
	public static void constructorTest_1(){
		setColorsetTest(new ColorGameUtility(IntMap.MATRIX_SIZE,
				ColorSetGenerationTest.currentColorSet, StringMap.TRUE));
	}
	public static void constructorTest_2(){
		currentColorSet.add(23);
		setColorsetTest(new ColorGameUtility(IntMap.MATRIX_SIZE,
				ColorSetGenerationTest.currentColorSet, StringMap.TRUE));
	}
	
	public static void constructorTest_3(){
		currentColorSet.add(18);
		setColorsetTest(new ColorGameUtility(IntMap.MATRIX_SIZE,
				ColorSetGenerationTest.currentColorSet, StringMap.TRUE));
	}
	
	public static void constructorTest_4(){
		currentColorSet.add(18);
		setColorsetTest(new ColorGameUtility(IntMap.MATRIX_SIZE,
				ColorSetGenerationTest.currentColorSet, StringMap.TRUE));
	}
	public static void constructorTest_5(){
		currentColorSet.add(18);
		setColorsetTest(new ColorGameUtility(IntMap.MATRIX_SIZE,
				ColorSetGenerationTest.currentColorSet, StringMap.TRUE));
	}

	public static ColorGameUtility getColorsetTest() {
		return colorsetTest;
	}

	public static void setColorsetTest(ColorGameUtility colorsetTest) {
		ColorSetGenerationTest.colorsetTest = colorsetTest;
	}
}
