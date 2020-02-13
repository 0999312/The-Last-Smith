package cn.mmf.lastsmith.advancement;

import cn.mmf.lastsmith.TLSMain;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

public class AdvancementHelper {
	public static void grantAdvancement(EntityPlayer player, String advancementName) {
		if (!(player instanceof EntityPlayerMP))
			return;

		AdvancementManager manager = player.world.getMinecraftServer().getAdvancementManager();
		Advancement advancement = manager.getAdvancement(new ResourceLocation(TLSMain.MODID, advancementName));
		if (advancement == null)
			return;

		((EntityPlayerMP) player).getAdvancements().grantCriterion(advancement, "done");
	}

	public static boolean checkAdvancement(EntityPlayer player, String advancementName) {
		return checkAdvancement(player, new ResourceLocation(TLSMain.MODID, advancementName));
	}
	public static boolean checkAdvancement(EntityPlayer player, ResourceLocation advancement) {
		if (!TLSMain.proxy.doesPlayerHaveAdvancement(player, advancement)) {
			return false;
		}
		return true;
	}
}
