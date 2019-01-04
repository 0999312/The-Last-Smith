package net.langball.lastsmith;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Logger;

public class ConfigLoader
{
    private static Configuration config;

    private static Logger logger;

    public static boolean steel;
    
    public ConfigLoader(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        load();
    }

    public static void load()
    {
        logger.info("Started loading config. ");
        String comment;
        comment = "Set not to load TLS's Steel ingot recipes";
       steel = config.get(Configuration.CATEGORY_GENERAL, "steel_ingot_recipes", false, comment).getBoolean();

        config.save();
        logger.info("Finished loading config. ");
    }

    public static Logger logger()
    {
        return logger;
    }
}