package mattparks.mods.MattparksCore;

import java.util.logging.Level;

import cpw.mods.fml.relauncher.FMLRelaunchLog;

public class Log
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
