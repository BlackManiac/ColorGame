package blackdevs.vishnu.Colors;

import java.io.Serializable;

public class AppInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5307016452948748517L;
	public String PlayerName;

	public AppInfo() {

		PlayerName = "Default";

	}

	public void setAppinfo(int PlayerLevel, int PlayerPoints, String PlayerName) {

		this.PlayerName = PlayerName;
	}
}
