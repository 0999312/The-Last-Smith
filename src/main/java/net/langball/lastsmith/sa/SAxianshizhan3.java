package net.langball.lastsmith.sa;

import java.util.List;

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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SAxianshizhan3 extends SpecialAttackBase {


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "flyintosky";
	}
	  private Entity getEntityToWatch(EntityPlayer player){
	        World world = player.world;
	        Entity target = null;
	        for(int dist = 2; dist < 20; dist+=2){
	            AxisAlignedBB bb = player.getEntityBoundingBox();
	            Vec3d vec = player.getLookVec();
	            vec = vec.normalize();
	            bb = bb.expand(2.0f, 0.25f, 2.0f);
	            bb = bb.offset(vec.x*(float)dist,vec.y*(float)dist,vec.z*(float)dist);

	            List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(player, bb);
	            float distance = 30.0f;
	            for(Entity curEntity : list){
	                float curDist = curEntity.getDistance(player);
	                if(curDist < distance)
	                {
	                    target = curEntity;
	                    distance = curDist;
	                }
	            }
	            if(target != null)
	                break;
	        }
	        return target;
	    }
	@Override
	public void doSpacialAttack(ItemStack stack, EntityPlayer player) {
		World world = player.world;

        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);

       Entity target = null;

       int entityId = ItemSlashBlade.TargetEntityId.get(tag);

       if(entityId != 0){
           Entity tmp = world.getEntityByID(entityId);
           if(tmp != null){
               if(tmp.getDistance(player) < 30.0f)
                   target = tmp;
           }
       }

       if(target == null){
           target = getEntityToWatch(player);
       }

       if(target != null) {
                ItemSlashBlade blade = (ItemSlashBlade)stack.getItem();

                final int cost = -20;
                if(!ItemSlashBlade.ProudSoul.tryAdd(tag,cost,false)){
                    ItemSlashBlade.damageItem(stack, 15, player);
                }
                double playerDist = 2.5;
                float attackDist = (float)(playerDist / 5.0);
                player.setPosition(target.posX, player.posY, target.posZ);
                   
                if(!player.onGround){
           	   player.motionY=-(playerDist);
           	 blade.attackTargetEntity(stack, target, player, true);
           	   }
                StylishRankManager.setNextAttackType(player, StylishRankManager.AttackTypes.JudgmentCut);
                EntitySA dim = new EntitySA(world, player, 6F);
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
       player.playSound(SoundEvents.ENTITY_BLAZE_HURT, 1F, 1F);
        ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.Iai);
	}
       
	}
