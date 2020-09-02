package cn.mmf.lastsmith.sa;

import java.util.List;

import com.google.common.base.Predicate;

import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.entity.EntitySummonedSwordBase;
import mods.flammpfeil.slashblade.entity.selector.EntitySelectorAttackable;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialattack.IJustSpecialAttack;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SAFallenSakura extends SpecialAttackBase implements IJustSpecialAttack {

	@Override
	public String toString() {
		return "fallen_sakura";
	}

	private static final int COST = 25;
	private static final int NO_COST_DAMAGE = 10;

	@Override
	public void doSpacialAttack(ItemStack stack, EntityPlayer player) {
		World world = player.world;
		if (world.isRemote)
			return;

		NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
		Entity target = getTarget(tag, player);
		int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
		if (target == null){
			ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.SSlashBlade);
	        EntityDrive entityDrive = new EntityDrive(world, player, 1F + level, true,180-ItemSlashBlade.ComboSequence.SSlashBlade.swingDirection);
	        if (entityDrive != null) {
	            entityDrive.setInitialSpeed(0.5F);
	            entityDrive.setLifeTime(5);
	            world.spawnEntity(entityDrive);
	        }
			return;
		}

		ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.SSlashBlade);

		if (!ItemSlashBlade.ProudSoul.tryAdd(tag, -COST, false))
			ItemSlashBlade.damageItem(stack, NO_COST_DAMAGE, player);

		ItemSlashBlade blade = (ItemSlashBlade) stack.getItem();
		if (target instanceof EntityLivingBase) {
			EntityLivingBase tmp = (EntityLivingBase) target;
			blade.setDaunting(tmp);
		}
		int count = 10 + StylishRankManager.getStylishRank(player);

		spawnEntity(2F + level, count, target, player);
        EntityDrive entityDrive = new EntityDrive(world, player, 1F + level, true,180-ItemSlashBlade.ComboSequence.SSlashBlade.swingDirection);
        if (entityDrive != null) {
            entityDrive.setInitialSpeed(0.5F);
            entityDrive.setLifeTime(5);
            world.spawnEntity(entityDrive);
        }
	}

	private Entity getTarget(NBTTagCompound tag, EntityPlayer player) {
		int id = ItemSlashBlade.TargetEntityId.get(tag);
		if (id != 0) {
			Entity entity = player.world.getEntityByID(id);
			if (entity != null && entity.getDistance(player) < 30.0f)
				return entity;
		}
		return getEntityToWatch(player);
	}

	@Override
	public void doJustSpacialAttack(ItemStack stack, EntityPlayer player) {
		World world = player.world;
		if (world.isRemote)
			return;
		int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
		NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
		Entity target = getTarget(tag, player);
		if (target == null){
			ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.SSlashBlade);
	        EntityDrive entityDrive = new EntityDrive(world, player, 5F + level, true,180-ItemSlashBlade.ComboSequence.SSlashBlade.swingDirection);
	        if (entityDrive != null) {
	            entityDrive.setInitialSpeed(1F);
	            entityDrive.setLifeTime(20);
	            world.spawnEntity(entityDrive);
	        }
			return;
		}
		ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.SSlashBlade);

		if (!ItemSlashBlade.ProudSoul.tryAdd(tag, -COST, false))
			ItemSlashBlade.damageItem(stack, NO_COST_DAMAGE, player);

		ItemSlashBlade blade = (ItemSlashBlade) stack.getItem();
		if (target instanceof EntityLivingBase) {
			EntityLivingBase tmp = (EntityLivingBase) target;
			blade.setDaunting(tmp);
		}

		
		int count = 10 + StylishRankManager.getStylishRank(player);

		spawnEntity(2F + level, count, target, player);
        EntityDrive entityDrive = new EntityDrive(world, player, 5F + level, true,180-ItemSlashBlade.ComboSequence.SSlashBlade.swingDirection);
        if (entityDrive != null) {
            entityDrive.setInitialSpeed(1F);
            entityDrive.setLifeTime(20);
            world.spawnEntity(entityDrive);
        }
	}
	
	private Entity getEntityToWatch(EntityPlayer player) {
		World world = player.world;
		Predicate<Entity> selector = EntitySelectorAttackable.getInstance();

		AxisAlignedBB bb = player.getEntityBoundingBox().expand(2.0, 0.25, 2.0);
		Vec3d vec = player.getLookVec().normalize();

		for (int dist = 2; dist < 20; dist += 2) {
			AxisAlignedBB temp = bb.offset(vec.x * dist, vec.y * dist, vec.z * dist);

			List<Entity> list = world.getEntitiesInAABBexcluding(player, temp, selector);

			Entity target = null;
			float distance = 30.0f;

			for (Entity entity : list) {
				float curDist = entity.getDistance(player);
				if (curDist < distance) {
					target = entity;
					distance = curDist;
				}
			}

			if (target != null)
				return target;
		}

		return null;
	}

	protected void spawnEntity(float damage, int count, Entity target, EntityPlayer player) {
		final World world = player.world;
		final int targetId = target.getEntityId();
		world.playSound(player, target.prevPosX, target.prevPosY, target.prevPosZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT,
				SoundCategory.NEUTRAL, 1F, 1.0F);

		for (int i = 0; i < count; i++) {
			EntitySummonedSwordBase summonedSword = new EntitySummonedSwordBase(world, player, damage,
						90.0f - ItemSlashBlade.ComboSequence.SSlashBlade.swingDirection);
				if (summonedSword != null) {
					summonedSword.setLifeTime(20+i);
					summonedSword.setTargetEntityId(targetId);
					summonedSword.setColor(15204584);
					world.spawnEntity(summonedSword);
				}
		}

	}
	
	public void onImpact(Entity target) {
		target.motionX = 0.0;
		target.motionY = 0.0;
		target.motionZ = 0.0;
	}

	public void onSticking(Entity target) {
		target.motionX = 0.0;
		target.motionY = 0.0;
		target.motionZ = 0.0;

	}
	
}
