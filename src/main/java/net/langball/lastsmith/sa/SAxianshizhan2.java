package net.langball.lastsmith.sa;

import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.ability.UntouchableTime;
import mods.flammpfeil.slashblade.entity.EntityJudgmentCutManager;
import mods.flammpfeil.slashblade.entity.EntitySlashDimension;
import mods.flammpfeil.slashblade.entity.selector.EntitySelectorAttackable;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialattack.IJustSpecialAttack;
import mods.flammpfeil.slashblade.specialattack.ISuperSpecialAttack;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.List;

/**
 * Created by Furia on 14/05/27.
 */
public class SAxianshizhan2 extends SpecialAttackBase{
    @Override
    public String toString() {
        return "sanhua1";
    }

    @Override
    public void doSpacialAttack(ItemStack stack, EntityPlayer player) {

        double playerDist = 2.5;
        float attackDist = (float)(playerDist / 5.0);
        
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

        if(target == null) {
            ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.None);

        }else{
            player.motionX = -Math.sin(Math.toRadians(player.rotationYaw)) * playerDist;
            player.motionZ =  Math.cos(Math.toRadians(player.rotationYaw)) * playerDist;
            ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.SlashDim);

            spawnParticle(world, target);

            player.world.playSound(player,player.posX, player.posY, player.posZ,SoundEvents.ENTITY_ENDERMEN_TELEPORT,SoundCategory.PLAYERS, 0.5F, 1.0F);

            final int cost = -20;
            if(!ItemSlashBlade.ProudSoul.tryAdd(tag, cost, false)){
                ItemSlashBlade.damageItem(stack, 15, player);
            }

            AxisAlignedBB bb = target.getEntityBoundingBox();
            bb = bb.expand(2.0f, 0.25f, 2.0f);

            List<Entity> list = world.getEntitiesInAABBexcluding(player, bb, EntitySelectorAttackable.getInstance());
            if(!EntitySelectorAttackable.getInstance().apply(target))
                list.add(target);
            ItemSlashBlade blade = (ItemSlashBlade)stack.getItem();

            for(Entity curEntity : list){
                StylishRankManager.setNextAttackType(player, StylishRankManager.AttackTypes.SlashDim);
                blade.attackTargetEntity(stack, curEntity, player, true);
            }

            if(!target.world.isRemote){
            
                int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
                float magicDamage = 1f + ItemSlashBlade.AttackAmplifier.get(tag) * (level / 5.0f);
                EntitySA dim = new EntitySA(world, player, magicDamage);
                if(dim != null){
                    dim.setPosition(target.posX,target.posY + target.height / 2.0,target.posZ);
                    dim.setLifeTime(20);
                    dim.setColor(0xFFE4E1);
                    world.spawnEntity(dim);

                }
             
                  player.motionX = -(-Math.sin(Math.toRadians(player.rotationYaw)) * playerDist);
                  player.motionZ =  -(Math.cos(Math.toRadians(player.rotationYaw)) * playerDist);
              
                    
            }
        }
        
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


    private void spawnParticle(World world, Entity target){
        //target.spawnExplosionParticle();
        world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE,
                target.posX,
                target.posY + target.height,
                target.posZ,
                3.0, 3.0, 3.0);
        world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE,
                target.posX + 1.0,
                target.posY + target.height + 1.0,
                target.posZ,
                3.0, 3.0, 3.0);
        world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE,
                target.posX,
                target.posY + target.height + 0.5,
                target.posZ + 1.0,
                3.0, 3.0, 3.0);
    }
  
}
