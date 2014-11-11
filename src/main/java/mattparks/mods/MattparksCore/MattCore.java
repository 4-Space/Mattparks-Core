package mattparks.mods.MattparksCore;

import java.io.File;

import mattparks.mods.MattparksCore.proxy.CommonProxy;
import mattparks.mods.MattparksCore.util.ConfigManager;
import mattparks.mods.MattparksCore.util.MCUtil;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.relauncher.Side;

@Mod(name = MattCore.NAME, version = MattCore.LOCALMAJVERSION + "." + MattCore.LOCALMINVERSION + "." + MattCore.LOCALBUILDVERSION, useMetadata = true, modid = MattCore.MODID)
public class MattCore
{
	public static final int LOCALMAJVERSION = 3;
	public static final int LOCALMINVERSION = 5;
	public static final int LOCALBUILDVERSION = 0;
	public static int remoteMajVer;
	public static int remoteMinVer;
	public static int remoteBuildVer;

	public static final String NAME = "Mattparks Core";
	public static final String MODID = "MattCore";

	@SidedProxy(clientSide = "mattparks.mods.MattparksCore.proxy.ClientProxy", serverSide = "mattparks.mods.MattparksCore.proxy.CommonProxy")
	public static CommonProxy proxy;

	@Instance(MattCore.MODID)
	public static MattCore instance;

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		if (event.getSide() == Side.CLIENT)
		{
			//TODO FMLCommonHandler.instance().bus().register(new EventCapeRender());
		}
	}

	@EventHandler
	public void load(FMLInitializationEvent event)
	{
	}

	@EventHandler
	public void postLoad(FMLPostInitializationEvent event)
	{
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		new ConfigManager(new File(event.getModConfigurationDirectory(), "MattparksCore.cfg"));
	}

	@EventHandler
	public void serverInit(FMLServerStartedEvent event)
	{
		MCUtil.checkVersion(Side.SERVER);
	}
}