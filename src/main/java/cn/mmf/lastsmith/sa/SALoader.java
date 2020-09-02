package cn.mmf.lastsmith.sa;

import cn.mmf.lastsmith.event.RegisterSlashBladeEvent;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class SALoader {
	@SubscribeEvent
	public static void SARegister(RegisterSlashBladeEvent event) {
		ItemSlashBlade.specialAttacks.put(263, new SASpaceDim());
		ItemSlashBlade.specialAttacks.put(264, new SlashDimension_old());
		ItemSlashBlade.specialAttacks.put(265, new SASakuraSkill());
		ItemSlashBlade.specialAttacks.put(266, new SAFallenSakura());
	}
}
