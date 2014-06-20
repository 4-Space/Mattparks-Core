package mattparks.mods.MattparksCore;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;

public class TickHandlerClient implements ITickHandler
{
	public static boolean checkedVersion = true; 
	private static long tickCount;

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
		final Minecraft minecraft = FMLClientHandler.instance().getClient();

		final WorldClient world = minecraft.theWorld;

		final EntityClientPlayerMP player = minecraft.thePlayer;

			if (world != null && TickHandlerClient.checkedVersion)
			{
				Util.checkVersion(Side.CLIENT);
				TickHandlerClient.checkedVersion = false; 
			}
	}
	
		
	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		final Minecraft minecraft = FMLClientHandler.instance().getClient();
	}

	@Override
	public String getLabel()
	{
		return "Mattparks Core Client";
	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.RENDER, TickType.CLIENT); 
	}
}
