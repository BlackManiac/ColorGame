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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class dataprovider implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static log logger = new log();

	public static void saveData(AppInfo appinfo, String filename) {

		FileHandle file = Gdx.files.local(filename);
		OutputStream out = null;
		try {
			file.writeBytes(serialize(appinfo), false);
		} catch (Exception ex) {
			logger.logObject("saveData", "dataprovider.java");
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (Exception ex) {
				}
		}
		System.out.println("Saving appinfo");
	}

	public static AppInfo readData(String filename) {
		AppInfo appinfo = null;
		FileHandle file = Gdx.files.local(filename);
		try {
			appinfo = (AppInfo) deserialize(file.readBytes());
			return appinfo;
		} catch (Exception e) {
			logger.logObject("readData", "dataprovider.java");
			e.printStackTrace();
		}
		System.out.println("Reading appinfo");
		return null;
	}

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
				logger.logObject("deserialize", "dataprovider.java");
			}
			try {
				if (in != null)
					in.close();
			} catch (IOException ex) {
				logger.logObject("deserialize", "dataprovider.java");
			}
		}
	}

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
				logger.logObject("serialize", "dataprovider.java");
			}
			try {
				bos.close();
			} catch (IOException ex) {
				logger.logObject("serialize", "dataprovider.java");
			}
		}

	}
}