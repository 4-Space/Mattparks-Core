package mattparks.mods.MattparksCore.util;

import java.util.logging.Level;

import cpw.mods.fml.relauncher.FMLRelaunchLog;

public class MCLog
{
	public static void info(String message)
	{
		FMLRelaunchLog.log("MattparksCore", Level.INFO, message); 
	}

	public static void severe(String message)
	{
		FMLRelaunchLog.log("MattparksCore", Level.SEVERE, message); 
	}
}
