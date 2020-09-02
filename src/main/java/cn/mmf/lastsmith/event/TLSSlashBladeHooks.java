package cn.mmf.lastsmith.event;

import net.minecraftforge.common.MinecraftForge;
import mods.flammpfeil.slashblade.item.ItemSlashBlade.ComboSequence;
import mods.flammpfeil.slashblade.network.MessageRangeAttack.RangeAttackState;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import mods.flammpfeil.slashblade.util.SlashBladeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Furia on 14/12/25.
 */
public class TLSSlashBladeHooks {
	public static boolean onUpdateHooks(ItemStack blade,World world,Entity entity,int indexOfMainSlot,boolean isCurrent){
        return MinecraftForge.EVENT_BUS.post(new SlashBladeEvent.OnUpdateEvent(blade, world, entity, indexOfMainSlot, isCurrent));
    }
	public static boolean onDoAttack(EntityPlayer playerIn,ItemStack bladeStack,ComboSequence comboSeqIn){
        return MinecraftForge.EVENT_BUS.post(new UseSlashBladeEvent.doAttackEvent(playerIn, bladeStack, comboSeqIn));
    }
    public static boolean onDoSpacialAttack(boolean isjust,EntityPlayer playerIn,ItemStack bladeStack,SpecialAttackBase specialAttckIn) {
        return MinecraftForge.EVENT_BUS.post(new UseSlashBladeEvent.doSpacialAttackEvent(isjust, playerIn, bladeStack, specialAttckIn));
    }
    public static boolean onPlayerDoRangeAttack(EntityPlayer playerIn,ItemStack bladeStack,RangeAttackState modeIn) {
        return MinecraftForge.EVENT_BUS.post(new UseSlashBladeEvent.doRangeAttackEvent(playerIn, bladeStack, modeIn));
    }
}
