package net.langball.lastsmith.sa;

import java.util.List;

import mods.flammpfeil.slashblade.entity.EntityBlisteringSwords;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * Created by Furia on 14/07/07.
 */
public class Sakuya extends SpecialAttackBase {
    @Override
    public String toString() {
        return "sakuyaSA";
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

        if(target == null) {
            ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.Battou);

        }else{
        	final int cost = -20;
            if(!ItemSlashBlade.ProudSoul.tryAdd(tag,cost,false)){
                ItemSlashBlade.damageItem(stack, 10, player);
            }
        if(!world.isRemote){
        	
            int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
            float magicDamage = 5f + ItemSlashBlade.AttackAmplifier.get(tag) * (level / 3.0f);
            ItemSlashBlade blade = (ItemSlashBlade)stack.getItem();
           if(player.onGround){
            for(int i = 0; i < 8;i++){
             EntityBlisteringSwords entityDrive = new EntityBlisteringSwords(world, player, magicDamage,90.0f, i);
            if (entityDrive != null) {
                entityDrive.setInterval(9+i*2);
                entityDrive.setLifeTime(30);
                @SuppressWarnings("static-access")
				int color = blade.SummonedSwordColor.get(tag);
                entityDrive.setColor(color);
                entityDrive.setTargetEntityId(target.getEntityId());
                world.spawnEntity(entityDrive);
            }
            }}else{
            	for(int i = 0; i < 2;i++){
                    EntityDrive entityDrive = new EntityDrive(world, player, magicDamage,false,0);
                    entityDrive.setInitialSpeed(2f);
                    entityDrive.setRoll(i*90);
                    if (entityDrive != null) {
                        world.spawnEntity(entityDrive);
                    }
                }
            }
        }
        }
        world.playSound(player,player.posX,player.posY,player.posZ,SoundEvents.ENTITY_GENERIC_EXPLODE,SoundCategory.PLAYERS, 1.0F, 1.0F);
        ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.SlashEdge);
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
}
