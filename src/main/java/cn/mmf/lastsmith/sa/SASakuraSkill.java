package cn.mmf.lastsmith.sa;

import java.util.List;

import com.google.common.base.Predicate;

import mods.flammpfeil.slashblade.entity.EntityBlisteringSwords;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.entity.selector.EntitySelectorAttackable;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialattack.IJustSpecialAttack;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SASakuraSkill extends SpecialAttackBase implements IJustSpecialAttack {

	@Override
	public String toString() {
		return "sakura_skill";
	}

	private static final int COST = 20;
	private static final int NO_COST_DAMAGE = 10;

	@Override
	public void doSpacialAttack(ItemStack stack, EntityPlayer player) {
		World world = player.world;
		if (world.isRemote)
			return;

		NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
		Entity target = getTarget(tag, player);
		if (target == null)
			return;

		ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.SlashDim);

		if (!ItemSlashBlade.ProudSoul.tryAdd(tag, -COST, false))
			ItemSlashBlade.damageItem(stack, NO_COST_DAMAGE, player);

		ItemSlashBlade blade = (ItemSlashBlade) stack.getItem();
		if (target instanceof EntityLivingBase) {
			EntityLivingBase tmp = (EntityLivingBase) target;
			blade.setDaunting(tmp);
		}

		int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
		float magicDamage = 5f + ItemSlashBlade.AttackAmplifier.get(tag) * (level / 3.0f);
		int color = ItemSlashBlade.SummonedSwordColor.get(tag, 0x3333FF);
		if (!player.onGround) {
			for (int i = 0; i < 6; i++) {
				EntityBlisteringSwords entityDrive = new EntityBlisteringSwords(world, player, magicDamage, 90.0f, i);
				if (entityDrive != null) {
					entityDrive.setInterval(9 + i * 2);
					entityDrive.setLifeTime(30);
					entityDrive.setColor(color);
					entityDrive.setTargetEntityId(target.getEntityId());
					world.spawnEntity(entityDrive);
				}
			}
		} else {
			for (int i = 0; i < 2; i++) {
				EntityDrive entityDrive = new EntityDrive(world, player, magicDamage, false, 0);
				entityDrive.setInitialSpeed(2f);
				entityDrive.setRoll(i * 90);
				if (entityDrive != null) {
					world.spawnEntity(entityDrive);
				}
			}
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

		NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
		Entity target = getTarget(tag, player);
		if (target == null)
			return;

		ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.SlashDim);

		if (!ItemSlashBlade.ProudSoul.tryAdd(tag, -COST, false))
			ItemSlashBlade.damageItem(stack, NO_COST_DAMAGE, player);

		ItemSlashBlade blade = (ItemSlashBlade) stack.getItem();
		if (target instanceof EntityLivingBase) {
			EntityLivingBase tmp = (EntityLivingBase) target;
			blade.setDaunting(tmp);
		}

		int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
		float magicDamage = 7f + ItemSlashBlade.AttackAmplifier.get(tag) * (level / 3.0f);

		if (!player.onGround) {
			for (int i = 0; i < 6; i++) {
				EntityBlisteringSwords entityDrive = new EntityBlisteringSwords(world, player, magicDamage, 90.0f, i);
				if (entityDrive != null) {
					entityDrive.setInterval(9 + i * 2);
					entityDrive.setLifeTime(30);
					int color = ItemSlashBlade.SummonedSwordColor.get(tag);
					entityDrive.setColor(color);
					entityDrive.setTargetEntityId(target.getEntityId());
					world.spawnEntity(entityDrive);
				}
			}
		} else {
			for (int i = 0; i < 2; i++) {
				EntityDrive entityDrive = new EntityDrive(world, player, magicDamage, false, 0);
				entityDrive.setInitialSpeed(2f);
				entityDrive.setRoll(i * 90);
				if (entityDrive != null) {
					world.spawnEntity(entityDrive);
				}
			}
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
