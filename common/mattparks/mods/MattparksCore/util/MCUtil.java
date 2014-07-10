package mattparks.mods.MattparksCore.util;

import cpw.mods.fml.relauncher.Side;

public class MCUtil
{
	public static void checkVersion(Side side)  
	{
		ThreadVersionCheck.startCheck(side);   
	}  
}
