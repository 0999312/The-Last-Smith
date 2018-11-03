package net.langball.lastsmith.sa;

import mods.flammpfeil.slashblade.entity.EntitySpearManager;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * Created by Furia on 14/07/07.
 */
public class Spear2 extends SpecialAttackBase {
    @Override
    public String toString() {
        return "spear2";
    }

    @Override
    public void doSpacialAttack(ItemStack stack, EntityPlayer player) {
        World world = player.world;

        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);


        double playerDist = 3.5;
        float attackDist = (float)(playerDist / 3.0);


        if(!player.onGround)
            playerDist *= 0.35f;
        player.motionX = -Math.sin(Math.toRadians(player.rotationYaw)) * playerDist;
        player.motionZ =  Math.cos(Math.toRadians(player.rotationYaw)) * playerDist;

        if(!world.isRemote){

            final int cost = -20;
            if(!ItemSlashBlade.ProudSoul.tryAdd(tag,cost,false)){
                ItemSlashBlade.damageItem(stack, 10, player);
            }

            ItemSlashBlade blade = (ItemSlashBlade)stack.getItem();
            EntitySpearManager entityDA = new EntitySpearManager(world, player, false);
            entityDA.setLifeTime(7);
            if (entityDA != null) {
                world.spawnEntity(entityDA);
            }
        }
        player.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1F, 1F); 
        ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.ASlashEdge);
    }
}
