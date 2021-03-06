package blackdevs.vishnu.Framework;

import blackdevs.vishnu.Constants.IntMap;

public class Logger {
	public static void Backlog(String log) {
		if (IntMap.B_LOG)
			System.out.println("[B_INFO] :"+log);
	}
	public static void Frontlog(String log) {
		if (IntMap.F_LOG)
			System.out.println("[F_INFO] :"+log);
	}
	public static void AniLog(String log){
		if (IntMap.ANI_LOG)
			System.out.println("[ANI_INFO] :"+log);
	}
	public static void DataLog(String log){
		if (IntMap.D_LOG)
			System.out.println("[D_INFO] :"+log);
	}
	public static void error(String log){
		if (IntMap.ERR_LOG)
			System.out.println("[ERROR] :"+log);
	}
}
