package cn.mmf.lastsmith.event;

import cn.mmf.lastsmith.TLSConfig;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class PlayerEventRegister {
	@SubscribeEvent
	public static void onDoSlashBladeAttack(UseSlashBladeEvent.doAttackEvent event) {
		ItemStack blade = event.getBlade();
		NBTTagCompound nbt = ItemSlashBlade.getItemTagCompound(blade);
		if (!TLSConfig.slashblade_broken_blade_attack_enable) {
			if(ItemSlashBlade.IsBroken.get(nbt)) {
				event.setCanceled(true);
			}
		}
		if (TLSConfig.slashblade_action_cooldown_enable && TLSConfig.slashblade_action_cooldown > 0) {
			event.getPlayer().getCooldownTracker().setCooldown(blade.getItem(), 
					(int)(event.getComboSeq().comboResetTicks * TLSConfig.slashblade_action_cooldown));
		}
	}
}
