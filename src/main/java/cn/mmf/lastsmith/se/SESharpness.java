package cn.mmf.lastsmith.se;

import cn.mmf.lastsmith.TLSConfig;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialeffect.ISpecialEffect;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import mods.flammpfeil.slashblade.util.SlashBladeEvent;
import mods.flammpfeil.slashblade.util.SlashBladeHooks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SESharpness implements ISpecialEffect {

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
			if (event.target.getRNG().nextInt(2) != 0)
				return;
			break;
		case NonEffective:
			return;
		}
		NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(event.blade);
		float damage = ItemSlashBlade.BaseAttackModifier.get(tag, 10F) + ItemSlashBlade.RepairCount.get(tag, 0)
				+ (2.5F * EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, event.blade));
		if(TLSConfig.advanced_mode)
			damage -= TLSConfig.extra_sharpness_damage;
		DamageSource ds = new EntityDamageSource("sharpness_blade", player);
		event.target.attackEntityFrom(ds, damage);
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
		player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20 * 5, 2));
		player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 20 * 5, 5));
		player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 20 * 5, 10));
	}

	@Override
	public void register() {
		SlashBladeHooks.EventBus.register(this);
	}

	@Override
	public int getDefaultRequiredLevel() {
		return 40;
	}

	@Override
	public String getEffectKey() {
		return "bewitched_sharpness";
	}

}
