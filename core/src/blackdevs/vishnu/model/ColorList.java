package blackdevs.vishnu.model;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Data Structure for generating the ui Back end Matrix
 * @author Vishnu Satis
 *
 */
public class ColorList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5174370808406121016L;
	/**
	 * 
	 */
	ArrayList<Integer> colorList = new ArrayList<>();

	public ColorList(ArrayList<Integer> colorList) {
		this.colorList = colorList;
	}

	public ArrayList<Integer> getColorList() {
		return colorList;
	}

	public void setColorList(ArrayList<Integer> colorList) {
		this.colorList = colorList;
	}
}
