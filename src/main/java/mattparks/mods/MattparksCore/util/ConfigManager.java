package mattparks.mods.MattparksCore.util;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;

public class ConfigManager
{
	public static boolean loaded;
	public static Configuration configuration;
	public static boolean capesEnabled;

	public ConfigManager(File file)
	{
		if (!ConfigManager.loaded)
		{
			ConfigManager.configuration = new Configuration(file);
			this.setDefaultValues();
		}
	}

	private void setDefaultValues()
	{
		try
		{
			ConfigManager.configuration.load();

			ConfigManager.capesEnabled = ConfigManager.configuration.get(Configuration.CATEGORY_GENERAL, "Enable Mattparks donator capes", true).getBoolean(true);
		}

		catch (final Exception e)
		{
			FMLLog.log(Level.ERROR, e, "Mattparks Config has a problem loading it's configuration");
		}

		finally
		{
			ConfigManager.configuration.save();
			ConfigManager.loaded = true;
		}
	}
}