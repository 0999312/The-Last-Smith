package net.langball.lastsmith.blade;

import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.sa.Iai;
import net.langball.lastsmith.sa.SAbailou1;
import net.langball.lastsmith.sa.SAbailou2;
import net.langball.lastsmith.sa.SAbailou3;
import net.langball.lastsmith.sa.Sakuya;
import net.langball.lastsmith.sa.Sakuya2;
import net.langball.lastsmith.sa.SlashDimension_old;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SALoader {
	@SubscribeEvent
	public void init(InitEvent event) {
			ItemSlashBlade.specialAttacks.put(Integer.valueOf(264), new SlashDimension_old());
			ItemSlashBlade.specialAttacks.put(Integer.valueOf(265), new SAbailou3());
			ItemSlashBlade.specialAttacks.put(Integer.valueOf(266), new Sakuya());
			ItemSlashBlade.specialAttacks.put(Integer.valueOf(267), new Sakuya2());
			ItemSlashBlade.specialAttacks.put(Integer.valueOf(268), new SAbailou1());
			ItemSlashBlade.specialAttacks.put(Integer.valueOf(269), new SAbailou2());
			ItemSlashBlade.specialAttacks.put(Integer.valueOf(270), new Iai());
	}
}
