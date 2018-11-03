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
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SAbailou2 extends SpecialAttackBase {


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "miwang2";
	}

	@Override
	public void doSpacialAttack(ItemStack stack, EntityPlayer player) {
		World world = player.world;

        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);     
        if(!world.isRemote){
        	final int cost = -60;
            if(!ItemSlashBlade.ProudSoul.tryAdd(tag,cost,false)){
                ItemSlashBlade.damageItem(stack, 20, player);
            }
        	 ItemSlashBlade blade = (ItemSlashBlade)stack.getItem();
        	player.addPotionEffect((new PotionEffect(Potion.getPotionById(1),1200,2)));
        	player.addPotionEffect((new PotionEffect(Potion.getPotionById(15),100,3)));
        	player.addPotionEffect((new PotionEffect(Potion.getPotionById(5),1200,1)));
            }
        ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.SlashEdge);
        }

	}
       
	
