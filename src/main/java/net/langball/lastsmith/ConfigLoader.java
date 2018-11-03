package net.langball.lastsmith;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Logger;

public class ConfigLoader
{
    private static Configuration config;

    private static Logger logger;

    public static boolean Sakuya;
    public static boolean roukan;
    public static boolean EU;
    public static boolean roukan_addon;
    public static boolean trade;
    public static boolean others;
    public static boolean oldtime;
    public static boolean bamboo;
    public static boolean smith;
    
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
        String comment,comment1,comment2,comment3,comment4,comment5,comment7,comment8,comment9;

        comment = "Sakuya Blade past ";
        comment1 = "Roukanken part(If you set smith true��DO NOT SET FALSE)";
        comment2 = "EUslashblade part(IC2 NEED)";
        comment3 = "roukan_addon part(If you set Roukanken false��DO NOT SET TRUE)";
        comment4 = "trade part";
        comment5 = "oldtime part";
        comment7 = "bamboo_plus part(If you set smith true��DO NOT SET FALSE)";
        comment8 = "others part";
        comment9 = "smith's part(If you set Roukanken and bamboo_plus false,DO NOT SET TRUE)";
       Sakuya = config.get(Configuration.CATEGORY_GENERAL, "sakuyablade", true, comment).getBoolean();
       roukan = config.get(Configuration.CATEGORY_GENERAL, "Roukanken", true, comment1).getBoolean();
       EU = config.get(Configuration.CATEGORY_GENERAL, "EUslashblade", true, comment2).getBoolean();
       roukan_addon = config.get(Configuration.CATEGORY_GENERAL, "roukan_addon", true, comment3).getBoolean();
       trade = config.get(Configuration.CATEGORY_GENERAL, "trade", true, comment4).getBoolean();
       others = config.get(Configuration.CATEGORY_GENERAL, "others", true, comment8).getBoolean();
       oldtime = config.get(Configuration.CATEGORY_GENERAL, "oldtime", true, comment5).getBoolean();
       bamboo = config.get(Configuration.CATEGORY_GENERAL, "bamboo", true, comment7).getBoolean();
       smith = config.get(Configuration.CATEGORY_GENERAL, "smith", true, comment9).getBoolean();
       
        //diamondBurnTime = config.get(Configuration.CATEGORY_GENERAL, "diamondBurnTime", 640, comment).getInt();
        
        
        config.save();
        logger.info("Finished loading config. ");
    }

    public static Logger logger()
    {
        return logger;
    }
}