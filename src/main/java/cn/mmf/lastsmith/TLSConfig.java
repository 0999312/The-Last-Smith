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
	private final static String advanced_mode_config = TLSMain.MODID + ".config.advanced_mode.";
	@Config.LangKey(config + "sakura_drop_rate")
	@Config.RangeDouble(min = 0D, max = 1D)
	@Config.Comment("Changes drop rate of Sakura leaf.")
	public static double sakura_drop_rate = 0.15D;
	
	@Config.LangKey(config + "spawn_first_with_book")
	@Config.Comment("Whether First Spawn with TLS Book.")
	public static boolean spawn_first_with_book = true;
	
	@Config.LangKey(config + "advanced_mode")
	@Config.Comment("Enable or disable Advanced Mode TLS. If it is false, all advanced mode configurations will be disabled.")
	public static boolean advanced_mode = true;
	
	@Config.LangKey(advanced_mode_config + "slashblade_action_cooldown_enable")
	@Config.Comment("Whether to enable SlashBlade has a attack cooldown.")
	public static boolean slashblade_action_cooldown_enable = true;

	@Config.LangKey(advanced_mode_config + "slashblade_broken_blade_attack_enable")
	@Config.Comment("Whether a broken SlashBlade can attack.")
	public static boolean slashblade_broken_blade_attack_enable = false;
	
	@Config.LangKey(advanced_mode_config + "slashblade_action_cooldown")
	@Config.RangeDouble(min = 0D, max = 1D)
	@Config.Comment("Changes Cooldown of SlashBlade.")
	public static double slashblade_action_cooldown = 0.4D;
	
	@Config.LangKey(advanced_mode_config + "refine_multiplier")
	@Config.RangeDouble(min = 0D, max = 10D)
	@Config.Comment("Changes Multiplier of SlashBlade's Refine.")
	public static double refine_multiplier = 1D;
	
	@Config.LangKey(advanced_mode_config + "extra_sharpness_damage")
	@Config.RangeInt(min = 0, max = 16384)
	@Config.Comment("Changes Base Damage of Extra Sharpness or TLS System.")
	public static double extra_sharpness_damage = 10;
	
	@Config.LangKey(advanced_mode_config + "recipe_lock_enable")
	@Config.Comment("Enable or disable the Recipe Advancemant lock?")
	public static boolean recipe_lock_enable = true;
	
	@Config.LangKey(advanced_mode_config + "sa_lock_enable")
	@Config.Comment("Enable or disable the SA lock?")
	public static boolean sa_lock_enable = false;

	@Config.LangKey(advanced_mode_config + "sb_lock_enable")
	@Config.Comment("Enable or disable the SB lock.")
	public static boolean sb_lock_enable = false;
	
	@Config.LangKey(advanced_mode_config + "sa_lock_advancement")
	@Config.Comment("Advancement name of SA Lock.")
	public static String sa_lock_advancement = "bewitched_blade";
	
	@Config.LangKey(advanced_mode_config + "sb_lock_advancement")
	@Config.Comment("Advancement name of SB Lock.")
	public static String sb_lock_advancement = "sharpness";
	
	@Config.LangKey(advanced_mode_config + "attack_lock_enable")
	@Config.Comment("Enable or disable the Attack Lock.")
	public static boolean attack_lock_enable = false;
	
	@Config.LangKey(advanced_mode_config + "base_attack_modifier_decrease")
	@Config.RangeInt(min = 0, max = 32768)
	@Config.Comment("Changes Base Damage of Blade.")
	public static double base_attack_modifier_decrease = 0;
	
	@Config.LangKey(advanced_mode_config + "add_drive_enable")
	@Config.Comment("Enable or disable Attack add Drive.")
	public static boolean add_drive_enable = true;
	
	@Config.LangKey(advanced_mode_config + "kill_count_multiplier")
	@Config.RangeInt(min = 0, max = 1000)
	@Config.Comment("Changes Multiplier of Required Kill Count")
	public static int kill_count_multiplier = 100;
	
	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(TLSMain.MODID)) {
			ConfigManager.sync(TLSMain.MODID, Config.Type.INSTANCE);
		}
	}
}
