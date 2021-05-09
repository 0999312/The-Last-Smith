package cn.mmf.lastsmith.se;

import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialeffect.ISpecialEffect;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import mods.flammpfeil.slashblade.util.SlashBladeEvent;
import mods.flammpfeil.slashblade.util.SlashBladeHooks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SESapping implements ISpecialEffect {

	private boolean useBlade(ItemSlashBlade.ComboSequence sequence) {
		if (sequence.useScabbard)
			return false;
		if (sequence == ItemSlashBlade.ComboSequence.None)
			return false;
		if (sequence == ItemSlashBlade.ComboSequence.Noutou)
			return false;
		return true;
	}

	@SubscribeEvent
	public void onImpactEffectEvent(SlashBladeEvent.ImpactEffectEvent event) {
		if (!useBlade(event.sequence))
			return;

		if (!SpecialEffects.isPlayer(event.user))
			return;
		EntityPlayer player = (EntityPlayer) event.user;

		switch (SpecialEffects.isEffective(player, event.blade, this)) {
		case None:
			return;
		case Effective:
			break;
		case NonEffective:
			return;
		}
		event.target.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 120, 1));
		event.target.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 120, 1));
		player.onEnchantmentCritical(event.target);

	}

	@SubscribeEvent
	public void onUpdateItemSlashBlade(SlashBladeEvent.OnUpdateEvent event) {
		if (!SpecialEffects.isPlayer(event.entity))
			return;
		EntityPlayer player = (EntityPlayer) event.entity;
		NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(event.blade);
		if (!useBlade(ItemSlashBlade.getComboSequence(tag)))
			return;
		switch (SpecialEffects.isEffective(player, event.blade, this)) {
		case None:
			return;
		case NonEffective:
			if (player.getRNG().nextInt(4) != 0)
				return;
			break;
		case Effective:
			return;
		}
		PotionEffect haste = player.getActivePotionEffect(MobEffects.MINING_FATIGUE);
		int check = haste != null ? haste.getAmplifier() != 1 ? 3 : 4 : 2;
		if (player.swingProgressInt != check)
			return;
		player.addPotionEffect(new PotionEffect(MobEffects.WITHER, 20 * 5, 1));
	}

	@Override
	public void register() {
		SlashBladeHooks.EventBus.register(this);
	}

	@Override
	public int getDefaultRequiredLevel() {
		return 30;
	}

	@Override
	public String getEffectKey() {
		return "sap_slash";
	}

}
