package cn.mmf.lastsmith;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = TLSMain.MODID)
@Mod.EventBusSubscriber(modid = TLSMain.MODID)
public class TLSConfig {
    private final static String config = TLSMain.MODID + ".config.";

    @Config.LangKey(config + "sakura_drop_rate")
    @Config.RequiresMcRestart
    @Config.RangeDouble(min=0D,max=1D)
    @Config.Comment("Changes drop rate of Sakura leaf.")
    public static double sakura_drop_rate = 0.15D;

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(TLSMain.MODID)) {
            ConfigManager.sync(TLSMain.MODID, Config.Type.INSTANCE);
        }
    }
}
