package mattparks.mods.MattparksCore;

import cpw.mods.fml.relauncher.Side;

public class Util
{
	public static void checkVersion(Side side)  
	{
		ThreadVersionCheck.startCheck(side);   
	}  
}
