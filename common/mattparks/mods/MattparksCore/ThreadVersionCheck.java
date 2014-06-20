package mattparks.mods.MattparksCore;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import mattparks.mods.space.core.Version;
import mekanism.api.EnumColor;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class ThreadVersionCheck extends Thread
{
	public static ThreadVersionCheck instance = new ThreadVersionCheck();
	private int count = 0;

	public ThreadVersionCheck()
	{
		super("Mattparks Version Check Thread");
	}

	public static void startCheck(Side sideToCheck)
	{
		final Thread thread = new Thread(ThreadVersionCheck.instance);
		thread.start();
	}

	@Override
	public void run()
	{
		final Side sideToCheck = FMLCommonHandler.instance().getSide();

		if (sideToCheck == null)
		{
			return;
		}

		while (this.count < 3 && Version.remoteBuildVer == 0)
		{
			try
			{
				final URL url = new URL(" http://version.mattparks.hostei.com/MattCore.html");
				final HttpURLConnection http = (HttpURLConnection) url.openConnection();
				http.addRequestProperty("User-Agent", "Mozilla/4.76");
				final BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
				String str;
				String str2[] = null;

				while ((str = in.readLine()) != null)
				{

					if (str.contains("Version"))
					{
						str = str.replace("Version=", "");

						str2 = str.split("#");

						if (str2 != null && str2.length == 3)
						{
							Version.remoteMajVer = Integer.parseInt(str2[0]);
							Version.remoteMinVer = Integer.parseInt(str2[1]);
							Version.remoteBuildVer = Integer.parseInt(str2[2]);
						}

						if (Version.remoteMajVer > Version.LOCALMAJVERSION || Version.remoteMajVer == Version.LOCALMAJVERSION && Version.remoteMinVer > Version.LOCALMINVERSION || Version.remoteMajVer == Version.LOCALMAJVERSION && Version.remoteMinVer == Version.LOCALMINVERSION && Version.remoteBuildVer > Version.LOCALBUILDVERSION)
						{
							Thread.sleep(5000);

							if (sideToCheck.equals(Side.CLIENT))
							{
								FMLClientHandler.instance().getClient().thePlayer.addChatMessage(EnumColor.GREY + "New " + EnumColor.DARK_AQUA + "Mattparks mods" + EnumColor.GREY + " versions available! v" + String.valueOf(Version.remoteMajVer) + "." + String.valueOf(Version.remoteMinVer) + "." + String.valueOf(Version.remoteBuildVer) + EnumColor.DARK_BLUE + " http://mattparks.hostei.com");
							}
							
							else if (sideToCheck.equals(Side.SERVER))
							{
								Log.severe("New Mattparks Mod versions available! v" + String.valueOf(Version.remoteMajVer) + "." + String.valueOf(Version.remoteMinVer) + "." + String.valueOf(Version.remoteBuildVer) + " http://mattparks.hostei.com");
							}
						}
					}
				}
			}
			
			catch (final Exception e)
			{
			}

			if (Version.remoteBuildVer == 0)
			{
				try
				{
					Log.severe(StatCollector.translateToLocal("mattcore.failed.name"));
					Thread.sleep(15000);
				}
				
				catch (final InterruptedException e)
				{
				}
			}
			
			else
			{
				Log.info(StatCollector.translateToLocal("mattcore.success.name") + " " + Version.remoteMajVer + "." + Version.remoteMinVer + "." + Version.remoteBuildVer);
			}

			this.count++;
		}
	}
}
