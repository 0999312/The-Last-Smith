package cn.mmf.lastsmith.event;

import cn.mmf.lastsmith.TLSConfig;
import cn.mmf.lastsmith.advancement.AdvancementHelper;
import cn.mmf.lastsmith.util.IMultiModeBlade;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
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
		if(ItemSlashBladeNamed.CurrentItemName.get(nbt).equalsIgnoreCase("flammpfeil.slashblade.named.thousand")) {
			if(((IMultiModeBlade)blade.getItem()).getMode(blade)!=1)
			event.setCanceled(true);
		}
		if (!TLSConfig.slashblade_broken_blade_attack_enable) {
			if(ItemSlashBlade.IsBroken.get(nbt)) {
				event.setCanceled(true);
			}
		}
		if (!TLSConfig.advanced_mode)
			return ;
		
		if (TLSConfig.slashblade_action_cooldown_enable && TLSConfig.slashblade_action_cooldown > 0) {
			int timer = event.getComboSeq().comboResetTicks;
			if(timer < 10) timer -=2;
			else if(timer > 20)timer +=2;
			event.getPlayer().getCooldownTracker().setCooldown(blade.getItem(), 
					(int)(timer * TLSConfig.slashblade_action_cooldown));
		}
	}
	@SubscribeEvent
	public static void onDoSA(UseSlashBladeEvent.doSpacialAttackEvent event) {
		if (!TLSConfig.advanced_mode)
			return ;
		ItemStack blade = event.getBlade();
		NBTTagCompound nbt = ItemSlashBlade.getItemTagCompound(blade);
		if(TLSConfig.sa_lock_enable) {
			if(!AdvancementHelper.getInstance().checkAdvancement(event.getPlayer(), "bewitched_blade"))
				event.setCanceled(true);
		}
		if(ItemSlashBladeNamed.CurrentItemName.get(nbt).equalsIgnoreCase("flammpfeil.slashblade.named.thousand")) {
			if(((IMultiModeBlade)blade.getItem()).getMode(blade)!=1)
			event.setCanceled(true);
		}
	}
	@SubscribeEvent
	public static void onDoRange(UseSlashBladeEvent.doRangeAttackEvent event) {
		if (!TLSConfig.advanced_mode)
			return ;
		ItemStack blade = event.getBlade();
		NBTTagCompound nbt = ItemSlashBlade.getItemTagCompound(blade);
		if(TLSConfig.sb_lock_enable) {
			if(!AdvancementHelper.getInstance().checkAdvancement(event.getPlayer(), "sharpness"))
				event.setCanceled(true);
		}
		if(ItemSlashBladeNamed.CurrentItemName.get(nbt).equalsIgnoreCase("flammpfeil.slashblade.named.thousand")) {
			if(((IMultiModeBlade)blade.getItem()).getMode(blade)!=1)
			event.setCanceled(true);
		}
	}
	
}
