package mattparks.mods.MattparksCore.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import mattparks.mods.MattparksCore.MattCore;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class ThreadVersionCheck extends Thread
{
	public static ThreadVersionCheck instance = new ThreadVersionCheck();
	private int count = 0;

	public static void startCheck(Side sideToCheck)
	{
		final Thread thread = new Thread(ThreadVersionCheck.instance);
		thread.start();
	}

	public ThreadVersionCheck()
	{
		super("Mattparks Version Check Thread");
	}

	@Override
	public void run()
	{
		final Side sideToCheck = FMLCommonHandler.instance().getSide();

		if (sideToCheck == null)
		{
			return;
		}

		while (this.count < 3 && MattCore.remoteBuildVer == 0)
		{
			try
			{
				final URL url = new URL(" http://version.mattparks.webatu.com/MattCore.html");
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
							MattCore.remoteMajVer = Integer.parseInt(str2[0]);
							MattCore.remoteMinVer = Integer.parseInt(str2[1]);
							MattCore.remoteBuildVer = Integer.parseInt(str2[2]);
						}

						if (MattCore.remoteMajVer > MattCore.LOCALMAJVERSION || MattCore.remoteMajVer == MattCore.LOCALMAJVERSION && MattCore.remoteMinVer > MattCore.LOCALMINVERSION || MattCore.remoteMajVer == MattCore.LOCALMAJVERSION && MattCore.remoteMinVer == MattCore.LOCALMINVERSION && MattCore.remoteBuildVer > MattCore.LOCALBUILDVERSION)
						{
							Thread.sleep(5000);

							if (sideToCheck.equals(Side.CLIENT))
							{
								FMLClientHandler.instance().getClient().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "New " + EnumChatFormatting.DARK_AQUA + "Mattparks mods" + EnumChatFormatting.GRAY + " versions available! v" + String.valueOf(MattCore.remoteMajVer) + "." + String.valueOf(MattCore.remoteMinVer) + "." + String.valueOf(MattCore.remoteBuildVer) + EnumChatFormatting.DARK_BLUE + " http://mattparks.webatu.com"));
							}

							else if (sideToCheck.equals(Side.SERVER))
							{
								MCLog.severe("New Mattparks Mod versions available! v" + String.valueOf(MattCore.remoteMajVer) + "." + String.valueOf(MattCore.remoteMinVer) + "." + String.valueOf(MattCore.remoteBuildVer) + " http://mattparks.webatu.com");
							}
						}
					}
				}
			}
			catch (final Exception e)
			{
			}

			if (MattCore.remoteBuildVer == 0)
			{
				try
				{
					MCLog.severe(StatCollector.translateToLocal("mattcore.failed.name"));
					Thread.sleep(15000);
				}
				catch (final InterruptedException e)
				{
				}
			}
			else
			{
				MCLog.info(StatCollector.translateToLocal("mattcore.success.name") + " " + MattCore.remoteMajVer + "." + MattCore.remoteMinVer + "." + MattCore.remoteBuildVer);
			}
			this.count++;
		}
	}
}