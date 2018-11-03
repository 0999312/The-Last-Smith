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
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class SAxianshizhan extends SpecialAttackBase {


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "xianshizhan";
	}

	@Override
	public void doSpacialAttack(ItemStack stack, EntityPlayer player) {
		World world = player.world;

        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
        double playerDist = 4.5;
        float attackDist = (float)(playerDist / 5.0);
   	   player.motionX = -Math.sin(Math.toRadians(player.rotationYaw)) * playerDist;
       player.motionZ =  Math.cos(Math.toRadians(player.rotationYaw)) * playerDist;

        if(!world.isRemote){
      
            ItemSlashBlade blade = (ItemSlashBlade)stack.getItem();

            Entity target = null;

            int entityId = ItemSlashBlade.TargetEntityId.get(tag);

            if(entityId != 0){
                Entity tmp = world.getEntityByID(entityId);
                if(tmp != null){
                    if(tmp.getDistance(player) < 30.0f)
                        target = tmp;
                }
            }
            if(target != null){
                ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.SlashEdge);
             
                final int cost = -20;
                if(!ItemSlashBlade.ProudSoul.tryAdd(tag,cost,false)){
                    ItemSlashBlade.damageItem(stack, 15, player);
                }
                player.posX=target.posX; player.posZ=target.posZ;
           	   player.motionX = -Math.sin(Math.toRadians(player.rotationYaw)) * (playerDist-2);
               player.motionZ =  Math.cos(Math.toRadians(player.rotationYaw)) * (playerDist-2);

                StylishRankManager.setNextAttackType(player, StylishRankManager.AttackTypes.JudgmentCut);
                EntitySA dim = new EntitySA(world, player, 5F);
                if(dim != null){
                    dim.setPosition(target.posX,target.posY + target.height / 2.0,target.posZ);
                    dim.setLifeTime(2);
                    dim.setColor(0xFFE4E1);
                    world.spawnEntity(dim);
                }
                blade.attackTargetEntity(stack, target, player, true);
                player.onCriticalHit(target);
                target.motionX = 0;
                target.motionY = 0.5;
                target.motionZ = 0;
                
            }
        }
        player.playSound(SoundEvents.ENTITY_BLAZE_HURT, 1F, 1F);
        ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.SlashEdge);
	}
       
	}
