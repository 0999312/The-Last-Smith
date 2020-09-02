package cn.mmf.lastsmith.sa;

import java.util.List;

import com.google.common.base.Predicate;

import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.entity.EntityHeavyRainSwords;
import mods.flammpfeil.slashblade.entity.selector.EntitySelectorAttackable;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialattack.IJustSpecialAttack;
import mods.flammpfeil.slashblade.specialattack.ISuperSpecialAttack;
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

public class SASpaceDim extends SpecialAttackBase implements IJustSpecialAttack, ISuperSpecialAttack {

	@Override
	public String toString() {
		return "sxsa_1";
	}

	private static final int COST = 40;
	private static final int NO_COST_DAMAGE = 20;

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
		float damage = 1.0f + ItemSlashBlade.AttackAmplifier.get(tag) * (level / 5.0F);
		int count = 15 + StylishRankManager.getStylishRank(player);

		spawnEntity(damage, count, target, player);
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
	public void doSuperSpecialAttack(ItemStack stack, EntityPlayer player) {
		World world = player.world;
		if (world.isRemote)
			return;
		NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
		int rank = StylishRankManager.getStylishRank(player);
		AxisAlignedBB axisalignedbb = new AxisAlignedBB(player.posX-2, player.posY-2, player.posZ-2, player.posX+2, player.posY+2, player.posZ+2).grow(5*rank);
		List<EntityLivingBase> entitys = world.getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
		
		int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
		float damage = 15.0f + ItemSlashBlade.AttackAmplifier.get(tag) * (level / 5.0F);
		int count = StylishRankManager.getStylishRank(player);
		if (!ItemSlashBlade.ProudSoul.tryAdd(tag, -5000, false))
			ItemSlashBlade.damageItem(stack, 40, player);

		ItemSlashBlade blade = (ItemSlashBlade) stack.getItem();
		for(EntityLivingBase entity : entitys){
			if(entity == player) continue;
			blade.setDaunting(entity);
			spawnEntity(damage, count, entity, player);
		}
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
		float damage = 2.0f + ItemSlashBlade.AttackAmplifier.get(tag) * (level / 5.0F);
		int count = 20 + StylishRankManager.getStylishRank(player);

		spawnEntity(damage, count, target, player);
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
		int rank = StylishRankManager.getStylishRank(player);
		world.playSound(player, target.prevPosX, target.prevPosY, target.prevPosZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT,
				SoundCategory.NEUTRAL, 1F, 1.0F);

		int multiplier = 2;
		if (5 <= rank)
			multiplier += 1;
		float magicDamage = 2;

		for (int i = 0; i < count; i++) {
			for (int j = 0; j < multiplier; j++) {
				EntityHeavyRainSwords summonedSword = new EntityHeavyRainSwords(world, player, magicDamage,
						player.getRNG().nextFloat() * 360.0f, i, targetId);
				if (summonedSword != null) {
					summonedSword.setLifeTime(30 + i);
					summonedSword.setColor(15204584);
					world.spawnEntity(summonedSword);
				}
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
