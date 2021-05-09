package cn.mmf.lastsmith.se;

import cn.mmf.lastsmith.TLSConfig;
import cn.mmf.lastsmith.util.IMultiModeBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialeffect.ISpecialEffect;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import mods.flammpfeil.slashblade.util.SlashBladeEvent;
import mods.flammpfeil.slashblade.util.SlashBladeHooks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SEtacticalLaserSystem implements ISpecialEffect {

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
		if(!(event.blade.getItem() instanceof IMultiModeBlade))
			return;
		if(((IMultiModeBlade)event.blade.getItem()).getMode(event.blade)!=1)
			return;
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

		float damage =10 + (2.5F * EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, event.blade));
		if(TLSConfig.advanced_mode)
			damage -= TLSConfig.extra_sharpness_damage;
		DamageSource ds = new EntityDamageSource("tactical_laser_burn", player);
		event.target.attackEntityFrom(ds, damage);
		player.onEnchantmentCritical(event.target);

	}

	@SubscribeEvent
	public void onUpdateItemSlashBlade(SlashBladeEvent.OnUpdateEvent event) {
		if(!(event.blade.getItem() instanceof IMultiModeBlade))
			return;
		if(((IMultiModeBlade)event.blade.getItem()).getMode(event.blade)!=1) {
			return;
		}
		if (!SpecialEffects.isPlayer(event.entity))
			return;
		EntityPlayer player = (EntityPlayer) event.entity;

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

		((IMultiModeBlade)event.blade.getItem()).setMode(event.blade, 0);
	}

	@Override
	public void register() {
		SlashBladeHooks.EventBus.register(this);
	}

	@Override
	public int getDefaultRequiredLevel() {
		return 10;
	}

	@Override
	public String getEffectKey() {
		return "technical_experimental_laser_system";
	}

}
