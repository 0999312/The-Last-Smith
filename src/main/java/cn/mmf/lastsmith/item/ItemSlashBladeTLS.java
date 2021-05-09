package cn.mmf.lastsmith.item;

import java.util.EnumSet;
import java.util.List;

import cn.mcmod_mmf.mmlib.util.StringUtil;
import cn.mmf.lastsmith.TLSConfig;
import cn.mmf.lastsmith.event.TLSSlashBladeHooks;
import cn.mmf.lastsmith.util.BladeUtil;
import mods.flammpfeil.slashblade.ability.JustGuard;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.network.MessageRangeAttack.RangeAttackState;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import mods.flammpfeil.slashblade.util.SilentUpdateItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSlashBladeTLS extends ItemSlashBlade {
	public ItemSlashBladeTLS(ToolMaterial par2EnumToolMaterial, float defaultBaseAttackModifier) {
		super(par2EnumToolMaterial, defaultBaseAttackModifier);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack arg0, World arg1, List arg2, ITooltipFlag arg3) {
		NBTTagCompound nbt = getItemTagCompound(arg0);
		if (BladeUtil.getInstance().getname(nbt) != null) {
			arg2.add(TextFormatting.GOLD + I18n.format("blades.crafter") + ":" + TextFormatting.GRAY
					+ BladeUtil.getInstance().getname(nbt));
		} else {
			arg2.add(TextFormatting.GOLD + I18n.format("blades.crafter") + ":" + TextFormatting.GRAY
					+ I18n.format("lastsmith.name.unnamed_smith"));
		}
		if (StringUtil.getInstance().isAltKeyDown()) {
			super.addInformation(arg0, arg1, arg2, arg3);
		} else {
			arg2.add(I18n.format("lastsmith.info.hold_alt_for_details"));
		}
	}

	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		super.onCreated(stack, worldIn, playerIn);
		NBTTagCompound nbt = stack.getTagCompound();
		BladeUtil.getInstance().setPlayer(nbt, playerIn);
	}

	@Override
	public void onUpdate(ItemStack arg0, World arg1, Entity arg2, int arg3, boolean arg4) {
		TLSSlashBladeHooks.onUpdateHooks(arg0, arg1, arg2, arg3, arg4);
		super.onUpdate(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack itemStackIn, EntityPlayer playerIn, Entity arg2) {
		NBTTagCompound tag = getItemTagCompound(itemStackIn);
		ComboSequence comboSeq = getComboSequence(tag);
		if (TLSSlashBladeHooks.onDoAttack(playerIn, itemStackIn,
				getNextComboSeq(itemStackIn, comboSeq, false, playerIn))) {
			playerIn.swingProgressInt = 0;
			playerIn.swingProgress = 0.0f;
			playerIn.isSwingInProgress = false;
			return true;
		}
		return super.onLeftClickEntity(itemStackIn, playerIn, arg2);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		ItemStack itemStackIn = playerIn.getHeldItem(hand);
		if (hand == EnumHand.OFF_HAND) {
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
		}

		JustGuard.setJustGuardState(playerIn);

		NBTTagCompound tag = getItemTagCompound(itemStackIn);
		ComboSequence comboSeq = getComboSequence(tag);
		if (!TLSSlashBladeHooks.onDoAttack(playerIn, itemStackIn, comboSeq)) {
			nextAttackSequence(itemStackIn, comboSeq, playerIn);
			SilentUpdateItem.silentUpdateItem(playerIn, hand);
		} else {
			doSwingItem(itemStackIn, playerIn);
		}
		playerIn.setActiveHand(hand);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void doChargeAttack(ItemStack stack, EntityPlayer par3EntityPlayer, boolean isJust) {
		SpecialAttackBase sa = getSpecialAttack(stack);
		if (TLSSlashBladeHooks.onDoSpacialAttack(isJust, par3EntityPlayer, stack, sa))
			return;
		super.doChargeAttack(stack, par3EntityPlayer, isJust);
	}

	@Override
	public void doRangeAttack(ItemStack item, EntityLivingBase entity, RangeAttackState mode) {
		if (entity instanceof EntityPlayer)
			if (TLSSlashBladeHooks.onPlayerDoRangeAttack((EntityPlayer) entity, item, mode))
				return;
		super.doRangeAttack(item, entity, mode);
	}

	@Override
	public EnumSet<SwordType> getSwordType(ItemStack itemStack) {
		NBTTagCompound tag = getItemTagCompound(itemStack);
		EnumSet<SwordType> set = super.getSwordType(itemStack);
		if (TLSConfig.advanced_mode && !BladeUtil.getInstance().IsBewitchedActived.get(tag)) {
			set.remove(SwordType.Bewitched);
			set.remove(SwordType.SoulEeater);
		}
		return set;
	}

	@Override
	@SuppressWarnings("rawtypes")
	@SideOnly(Side.CLIENT)
	public void addInformationMaxAttack(ItemStack arg0, EntityPlayer arg1, List arg2, boolean arg3) {
		if (TLSConfig.advanced_mode && TLSConfig.attack_lock_enable) {
			
		} 
		else super.addInformationMaxAttack(arg0, arg1, arg2, arg3);
	}

	public void doAddAttack(ItemStack stack, EntityPlayer player, ComboSequence setCombo) {
		if (TLSConfig.advanced_mode && TLSConfig.add_drive_enable)
			return;
		NBTTagCompound tag = getItemTagCompound(stack);
		World world = player.world;
		if (!world.isRemote) {
			if (!ProudSoul.tryAdd(tag, -10, false)) {
				ItemSlashBlade.damageItem(stack, 5, player);
			}

			float baseModif = getBaseAttackModifiers(tag);
			int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
			float magicDamage = baseModif;
			int rank = StylishRankManager.getStylishRank(player);
			if (rank > 5) {
				magicDamage += AttackAmplifier.get(tag) * (0.5f + (level / 5.0f));
			}

			EntityDrive entityDrive = new EntityDrive(world, player, magicDamage, false,
					90.0f - setCombo.swingDirection);
			if (entityDrive != null) {
				entityDrive.setInitialSpeed(0.75f);
				entityDrive.setLifeTime(20);
				world.spawnEntity(entityDrive);
			}

			setComboSequence(tag, setCombo);
			return;
		}
	}

	public void doSlashBladeAttack(ItemStack stack, EntityLivingBase player, ComboSequence setCombo) {
		if (TLSConfig.advanced_mode && TLSConfig.add_drive_enable)
			return;
		NBTTagCompound tag = getItemTagCompound(stack);
		World world = player.world;

		player.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, 0.8F, 0.01F);

		if (!world.isRemote) {

			float baseModif = getBaseAttackModifiers(tag);
			int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
			float magicDamage = baseModif;
			int rank = StylishRankManager.getStylishRank(player);
			if (5 <= rank) {
				magicDamage += AttackAmplifier.get(tag) * (0.5f + (level / 5.0f));
			}

			EntityDrive entityDrive = new EntityDrive(world, player, magicDamage, false,
					90.0f - Math.abs(setCombo.swingDirection));
			if (entityDrive != null) {
				entityDrive.setInitialSpeed(0.1f);
				entityDrive.setLifeTime(20);

				EnumSet<SwordType> type = getSwordType(stack);
				entityDrive.setIsSlashDimension(type.contains(SwordType.FiercerEdge));

				world.spawnEntity(entityDrive);
			}

			setComboSequence(tag, setCombo);
			return;
		}
	}

	@Override
	public float getBaseAttackModifiers(NBTTagCompound tag) {
		return (float) Math.max(2, super.getBaseAttackModifiers(tag) - TLSConfig.base_attack_modifier_decrease);
	}
	
	@Override
	public void updateAttackAmplifier(EnumSet<SwordType> swordType, NBTTagCompound tag, EntityLivingBase el,
			ItemStack sitem) {
		if (!TLSConfig.advanced_mode) {
			super.updateAttackAmplifier(swordType, tag, el, sitem);
			return;
		}
		float tagAttackAmplifier = ItemSlashBlade.AttackAmplifier.get(tag);

		float baseModif = getBaseAttackModifiers(tag);
		float attackAmplifier = 0;
		if (TLSConfig.attack_lock_enable) {
			attackAmplifier = 0;
		} else {
			int rank = StylishRankManager.getStylishRank(el);

			if (rank < 3 || swordType.contains(SwordType.Broken) || swordType.contains(SwordType.Sealed)) {
				attackAmplifier = 2 - baseModif;
			} else if (rank == 7 || 5 <= rank && swordType.contains(SwordType.FiercerEdge)) {
				float level;
				if (el instanceof EntityPlayer)
					level = ((EntityPlayer) el).experienceLevel;
				else
					level = el.getHealth();

				float max = (float) ((RefineBase + RepairCount.get(tag)) * TLSConfig.refine_multiplier);

				attackAmplifier = Math.min(level, max);
			}
		}
		if (tagAttackAmplifier != attackAmplifier) {
			ItemSlashBlade.AttackAmplifier.set(tag, attackAmplifier);

			NBTTagList attrTag = new NBTTagList();
			tag.setTag("AttributeModifiers", attrTag);

			attrTag.appendTag(getAttrTag(
					SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER,
							"Weapon modifier", (double) (attackAmplifier + baseModif), 0),
					EntityEquipmentSlot.MAINHAND));
			attrTag.appendTag(getAttrTag(SharedMonsterAttributes.ATTACK_SPEED.getName(),
					new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0),
					EntityEquipmentSlot.MAINHAND));

			el.getAttributeMap().removeAttributeModifiers(sitem.getAttributeModifiers(EntityEquipmentSlot.MAINHAND));
			el.getAttributeMap().applyAttributeModifiers(sitem.getAttributeModifiers(EntityEquipmentSlot.MAINHAND));
		}
	}
}
