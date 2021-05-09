package cn.mmf.lastsmith.se;

import mods.flammpfeil.slashblade.specialeffect.ISpecialEffect;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class SELoader {
	public static ISpecialEffect EXTREME_SHARPNESS,TLSystem,SAP,MUNIN_SHARPNESS;
	@SubscribeEvent
	public static void registerSE(RegistryEvent.Register<Item> event) {
		EXTREME_SHARPNESS = SpecialEffects.register(new SESharpnessLower());
		MUNIN_SHARPNESS = SpecialEffects.register(new SESharpness());
		TLSystem = SpecialEffects.register(new SEtacticalLaserSystem());
		SAP = SpecialEffects.register(new SESapping());
	}
}
