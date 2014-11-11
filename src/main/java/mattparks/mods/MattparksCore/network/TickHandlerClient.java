package mattparks.mods.MattparksCore.network;

import mattparks.mods.MattparksCore.util.MCUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TickHandlerClient
{
	public static boolean checkedVersion = true;

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onClientTick(ClientTickEvent event)
	{
		final Minecraft minecraft = FMLClientHandler.instance().getClient();
		final WorldClient world = minecraft.theWorld;

		if (world != null && TickHandlerClient.checkedVersion)
		{
			MCUtil.checkVersion(Side.CLIENT);
			TickHandlerClient.checkedVersion = false;
		}
	}
}