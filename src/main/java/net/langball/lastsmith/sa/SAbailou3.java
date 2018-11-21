package net.langball.lastsmith.sa;

import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.entity.EntitySakuraEndManager;
import mods.flammpfeil.slashblade.entity.EntityWitherSword;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import mods.flammpfeil.slashblade.util.SlashBladeEvent.BladeStandAttack;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SAbailou3 extends SpecialAttackBase {


	@Override
	public String toString() {
		return "relife";
	}

	@Override
	public void doSpacialAttack(ItemStack stack, EntityPlayer player) {
		World world = player.world;
		 ItemSlashBlade blade = (ItemSlashBlade)stack.getItem();
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);     
        if(!world.isRemote){
        	final int cost = -25;
            if(!ItemSlashBlade.ProudSoul.tryAdd(tag,cost,false)){
            	ItemSlashBlade.damageItem(stack, 20, player);
            }
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(10),80,3));
           player.addPotionEffect(new PotionEffect(Potion.getPotionById(12),80,3));
            
           }
        ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.SlashDim);
        }

	}
       
	
