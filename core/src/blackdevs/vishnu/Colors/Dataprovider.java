package blackdevs.vishnu.Colors;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import blackdevs.vishnu.Framework.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Class is used for data transfer B/W Game and file system
 * 
 * @author vishnu.satis
 * 
 */
public class Dataprovider implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Saving data to file
	 * 
	 * @param obj
	 *            Data obj to be saved
	 * @param fileName
	 *            fileName to which dat is saved
	 */
	public static void saveData(Object obj, String fileName) {

		FileHandle file = Gdx.files.local(fileName);
		OutputStream out = null;
		try {
			file.writeBytes(serialize(obj), false);
		} catch (Exception ex) {
			Logger.error("Data Saving Exeception Occured ---->"
					+ ex.toString());
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (Exception ex) {
				}
		}
		Logger.DataLog("Data Saved to file ----> " + fileName);
	}

	/**
	 * Reading data from file
	 * 
	 * @param fileName
	 *            file to which data is to be written
	 * @return
	 */
	public static Object readData(String fileName) {
		Object obj = null;
		FileHandle file = Gdx.files.local(fileName);
		try {
			obj = (Object) deserialize(file.readBytes());
			return obj;
		} catch (Exception e) {
			Logger.error("Data Saving Exeception Occured ---->"
					+ e.toString());
			e.printStackTrace();
		}
		Logger.DataLog("Data Retrieved to file ----> " + fileName);
		return null;
	}

	/**
	 * Serializing the Data obj before Saving
	 * 
	 * @param byteArray
	 * @return
	 */
	public static Object deserialize(byte[] byteArray) {
		ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
		ObjectInput in = null;
		try {
			in = new ObjectInputStream(bis);
			Object o = in.readObject();
			return o;

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				bis.close();
			} catch (IOException ex) {
				Logger.error("Error While deserializing File Data --> "
						+ ex.toString());
			}
			try {
				if (in != null)
					in.close();
			} catch (IOException ex) {
				Logger.error("Error While deserializing File Data --> "
						+ ex.toString());
			}
		}
	}

	/**
	 * Deserializing the data Obj before Saving
	 * 
	 * @param object
	 * @return
	 */
	public static <T> byte[] serialize(T object) {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		try {
			out = new ObjectOutputStream(bos);
			out.writeObject(object);

			byte[] byteArray = bos.toByteArray();
			return byteArray;

		} catch (IOException e) {
			e.printStackTrace();
			return null;

		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException ex) {
				Logger.error("Error While serializing File Data --> "
						+ ex.toString());
			}
			try {
				bos.close();
			} catch (IOException ex) {
				Logger.error("Error While serializing File Data --> "
						+ ex.toString());
			}
		}

	}
	
	
}