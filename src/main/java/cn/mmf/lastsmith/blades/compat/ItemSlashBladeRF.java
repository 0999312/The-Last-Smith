package cn.mmf.lastsmith.blades.compat;

import java.util.List;

import cn.mmf.lastsmith.item.ItemSlashBladeNamedTLS;
import cn.mmf.lastsmith.util.BladeUtil;
import cofh.api.item.IMultiModeItem;
import cofh.core.init.CoreEnchantments;
import cofh.core.init.CoreProps;
import cofh.core.key.KeyBindingItemMultiMode;
import cofh.core.util.helpers.DamageHelper;
import cofh.core.util.helpers.EnergyHelper;
import cofh.core.util.helpers.MathHelper;
import cofh.core.util.helpers.StringHelper;
import cofh.redstoneflux.api.IEnergyContainerItem;
import cofh.redstoneflux.util.EnergyContainerItemWrapper;
import mods.flammpfeil.slashblade.TagPropertyAccessor;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.util.ResourceLocationRaw;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSlashBladeRF extends ItemSlashBladeNamedTLS implements IEnergyContainerItem, IMultiModeItem {
	// public static int maxEnergy = 2000000;
	// public static int maxTransfer = 20000;
	// public int energyPerUse = 100;
	// public int energyPerUseCharged = 800;
	public static final TagPropertyAccessor.TagPropertyInteger MAXENERGY = new TagPropertyAccessor.TagPropertyInteger(
			"maxEnergy");
	public static final TagPropertyAccessor.TagPropertyInteger MAXTRANSFER = new TagPropertyAccessor.TagPropertyInteger(
			"maxTransfer");
	public static final TagPropertyAccessor.TagPropertyInteger ENERGYPERUSE = new TagPropertyAccessor.TagPropertyInteger(
			"energyPerUse");
	public static final TagPropertyAccessor.TagPropertyInteger ENERGYPERUSECHARGED = new TagPropertyAccessor.TagPropertyInteger(
			"energyPerUseCharged");

	public ItemSlashBladeRF(ToolMaterial par2EnumToolMaterial, float baseAttackModifiers) {
		super(par2EnumToolMaterial, baseAttackModifiers);
	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
		NBTTagCompound tag = getItemTagCompound(container);
		if (container.getTagCompound() == null)
			EnergyHelper.setDefaultEnergyTag(container, 0);
		int stored = Math.min(container.getTagCompound().getInteger(CoreProps.ENERGY), getMaxEnergyStored(container));
		int receive = Math.min(maxReceive,
				Math.min(getMaxEnergyStored(container) - stored, MAXTRANSFER.get(tag, 20000)));
		if (!simulate) {
			stored += receive;
			container.getTagCompound().setInteger(CoreProps.ENERGY, stored);
		}
		return receive;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		if (container.getTagCompound() == null)
			EnergyHelper.setDefaultEnergyTag(container, 0);
		int stored = Math.min(container.getTagCompound().getInteger(CoreProps.ENERGY), getMaxEnergyStored(container));
		int extract = Math.min(maxExtract, stored);
		if (!simulate) {
			stored -= extract;
			container.getTagCompound().setInteger(CoreProps.ENERGY, stored);
			if (stored == 0)
				setMode(container, 0);
		}
		return extract;
	}

	protected int useEnergy(ItemStack stack, boolean simulate) {
		NBTTagCompound tag = getItemTagCompound(stack);
		int unbreakingLevel = MathHelper.clamp(EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, stack), 0,
				10);
		if (MathHelper.RANDOM.nextInt(2 + unbreakingLevel) >= 2)
			return 0;

		return extractEnergy(stack, isEmpowered(stack) ? ENERGYPERUSECHARGED.get(tag, 800) : ENERGYPERUSE.get(tag, 100),
				simulate);
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		if (container.getTagCompound() == null)
			EnergyHelper.setDefaultEnergyTag(container, 0);
		return Math.min(container.getTagCompound().getInteger(CoreProps.ENERGY), getMaxEnergyStored(container));
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		NBTTagCompound tag = getItemTagCompound(container);
		int enchant = EnchantmentHelper.getEnchantmentLevel(CoreEnchantments.holding, container);
		return MAXENERGY.get(tag, 2000000) + MAXENERGY.get(tag, 2000000) * enchant / 2;
	}

	@Override
	public void onModeChange(EntityPlayer player, ItemStack stack) {
		NBTTagCompound tag = getItemTagCompound(stack);
		if (isEmpowered(stack)) {
			boolean cancel = false;
			if (IsBroken.get(tag).booleanValue()) {
				cancel = true;
			}
			if (BladeUtil.Username.exists(tag)) {
				if (!player.getName().toString().trim().equals(BladeUtil.Username.get(tag).trim())) {
					cancel = true;
				}
			} else {
				BladeUtil.Username.set(tag, player.getName().toString());
			}
			if (cancel) {
				player.world.playSound(null, player.getPosition(), SoundEvents.BLOCK_NOTE_BASS, SoundCategory.PLAYERS,
						1, 1.0F);
				setMode(stack, 0);
				return;
			}
			player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_LIGHTNING_THUNDER,
					SoundCategory.PLAYERS, 1, 1.0F);
			onStartupEmpowered(player, stack);
		} else {
			player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,
					SoundCategory.PLAYERS, 1, 1.0F);
		}
	}

	public boolean isEmpowered(ItemStack stack) {
		NBTTagCompound tag = getItemTagCompound(stack);
		return getMode(stack) == 1 && getEnergyStored(stack) >= ENERGYPERUSECHARGED.get(tag, 800);
	}

	@Override
	public ResourceLocationRaw getModelTexture(ItemStack par1ItemStack) {
		NBTTagCompound tag = getItemTagCompound(par1ItemStack);
		ResourceLocationRaw result = ((ItemSlashBladeNamedTLS) par1ItemStack.getItem()).getModelTexture();
		if (isEmpowered(par1ItemStack)) {
			if (BladeUtil.TextureOnName.exists(tag)) {
				String textureName = BladeUtil.TextureOnName.get(tag);
				ResourceLocationRaw loc;
				if (!textureMap.containsKey(textureName)) {
					loc = new ResourceLocationRaw("flammpfeil.slashblade", "model/" + textureName + ".png");
					textureMap.put(textureName, loc);
				} else {
					loc = textureMap.get(textureName);
				}
				result = loc;
			}
		} else if (TextureName.exists(tag)) {
			String textureName = TextureName.get(tag);
			ResourceLocationRaw loc;
			if (!textureMap.containsKey(textureName)) {
				loc = new ResourceLocationRaw("flammpfeil.slashblade", "model/" + textureName + ".png");
				textureMap.put(textureName, loc);
			} else {
				loc = textureMap.get(textureName);
			}
			result = loc;
		}
		return result;
	}

	@Override
	public ResourceLocationRaw getModelLocation(ItemStack par1ItemStack) {
		NBTTagCompound tag = getItemTagCompound(par1ItemStack);
		ResourceLocationRaw result = ((ItemSlashBladeNamedTLS) par1ItemStack.getItem()).getModel();
		if (isEmpowered(par1ItemStack)) {
			if (BladeUtil.ModelOnName.exists(tag)) {
				String textureName = BladeUtil.ModelOnName.get(tag);
				ResourceLocationRaw loc;
				if (!modelMap.containsKey(textureName)) {
					loc = new ResourceLocationRaw("flammpfeil.slashblade", "model/" + textureName + ".obj");
					modelMap.put(textureName, loc);
				} else {
					loc = modelMap.get(textureName);
				}
				result = loc;
			}
		} else if (ModelName.exists(tag)) {
			String textureName = ModelName.get(tag);
			ResourceLocationRaw loc;
			if (!modelMap.containsKey(textureName)) {
				loc = new ResourceLocationRaw("flammpfeil.slashblade", "model/" + textureName + ".obj");
				modelMap.put(textureName, loc);
			} else {
				loc = modelMap.get(textureName);
			}
			result = loc;
		}
		return result;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase player) {
		NBTTagCompound tag = getItemTagCompound(stack);
		int rank = StylishRankManager.getStylishRank(player);
		int usage = (int) (ENERGYPERUSE.get(tag, 100) * Math.pow(2.0D, rank));
		if ((isEmpowered(stack)) && (extractEnergy(stack, usage, false) == usage)
				&& ((player instanceof EntityPlayer))) {
			entity.hurtResistantTime = 0;
			entity.attackEntityFrom(DamageHelper.causePlayerFluxDamage((EntityPlayer) player), rank);
		}
		return super.hitEntity(stack, entity, player);
	}

	public void onUpdate(ItemStack sitem, World par2World, Entity par3Entity, int indexOfMainSlot, boolean isCurrent) {
		NBTTagCompound tag = getItemTagCompound(sitem);
		if ((isEmpowered(sitem)) && (par3Entity != null) && (par2World.getTotalWorldTime() % 10L == 0L)
				&& ((!isCurrent) || (OnClick.get(tag).booleanValue()) || (((par3Entity instanceof EntityPlayer))))) {
			if (!par2World.isRemote) {
				int runingCost = ENERGYPERUSE.get(tag, 100);
				extractEnergy(sitem, runingCost, false);
				int rankPoint = StylishRankManager.getTotalRankPoint(par3Entity);
				int aRankPoint = (int) (StylishRankManager.RankRange * 7D);
				int rankAmount = aRankPoint - rankPoint;
				if (0 < rankAmount) {
					StylishRankManager.addRankPoint(par3Entity, rankAmount);
				}
			}

			if ((BladeUtil.Username.exists(tag))
					&& (!par3Entity.getName().toString().trim().equals(BladeUtil.Username.get(tag).trim()))) {
				setMode(sitem, 0);
			}
			double d0 = itemRand.nextGaussian() * 0.02D;
			double d1 = itemRand.nextGaussian() * 0.02D;
			double d2 = itemRand.nextGaussian() * 0.02D;
			double d3 = 10.0D;
			par2World.spawnParticle(EnumParticleTypes.PORTAL,
					par3Entity.posX + itemRand.nextFloat() * par3Entity.width * 2.0F - par3Entity.width - d0 * d3,
					par3Entity.posY, par3Entity.posZ + itemRand

							.nextFloat() * par3Entity.width * 2.0F - par3Entity.width - d2 * d3,
					d0, d1, d2);
		}
		super.onUpdate(sitem, par2World, par3Entity, indexOfMainSlot, isCurrent);
	}

	protected int getEnergyPerUse(ItemStack stack) {
		NBTTagCompound tag = getItemTagCompound(stack);
		return isEmpowered(stack) ? ENERGYPERUSECHARGED.get(tag, 800) : ENERGYPERUSE.get(tag, 100);
	}

	public void onStartupEmpowered(EntityPlayer player, ItemStack stack) {
		int rankPoint = StylishRankManager.getTotalRankPoint(player);
		int aRankPoint = (int) (StylishRankManager.RankRange * 7D);
		int rankAmount = aRankPoint - rankPoint;

		int energy = getEnergyStored(stack);
		rankAmount = Math.min(energy, rankAmount);

		int startupCost = 1000;

		World world = player.world;

		int energyUsage = startupCost;
		if (energyUsage <= energy) {
			for (int i = 0; i < 6; i++) {
				EntityDrive entityDrive = new EntityDrive(world, player, 0F, false, 0);
				entityDrive.setLocationAndAngles(player.posX, player.posY + player.getEyeHeight() / 2D, player.posZ,
						player.rotationYaw + 60 * i, 0);
				entityDrive.setDriveVector(0.5f);
				entityDrive.setLifeTime(5);
				entityDrive.setIsMultiHit(false);
				entityDrive.setRoll(90.0f);
				if (entityDrive != null) {
					world.spawnEntity(entityDrive);
				}
			}
		}
		int rank = StylishRankManager.getStylishRank(player);
		energyUsage = (int) (energyUsage * Math.pow(2.0D, rank));
		if (0 < rankAmount) {
			energyUsage += rankAmount * 2;
			StylishRankManager.addRankPoint(player, rankAmount);
		}
		extractEnergy(stack, energyUsage, false);
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new EnergyContainerItemWrapper(stack, this);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World arg1, List tooltip, ITooltipFlag arg3) {
		EntityPlayer par2EntityPlayer = Minecraft.getMinecraft().player;
		boolean par4 = arg3.isAdvanced();
		if (par2EntityPlayer == null)
			return;
		addInformationOwner(stack, par2EntityPlayer, tooltip, par4);
		addInformationSwordClass(stack, par2EntityPlayer, tooltip, par4);
		addInformationKillCount(stack, par2EntityPlayer, tooltip, par4);
		addInformationProudSoul(stack, par2EntityPlayer, tooltip, par4);
		addInformationSpecialAttack(stack, par2EntityPlayer, tooltip, par4);
		addInformationRepairCount(stack, par2EntityPlayer, tooltip, par4);
		addInformationRangeAttack(stack, par2EntityPlayer, tooltip, par4);
		addInformationSpecialEffec(stack, par2EntityPlayer, tooltip, par4);
		addInformationMaxAttack(stack, par2EntityPlayer, tooltip, par4);
		NBTTagCompound tag = getItemTagCompound(stack);
		if (tag.hasKey(adjustXStr)) {
			float ax = tag.getFloat(adjustXStr);
			float ay = tag.getFloat(adjustYStr);
			float az = tag.getFloat(adjustZStr);
			tooltip.add(String.format("adjust x:%.1f y:%.1f z:%.1f", ax, ay, az));
		}
		if (BladeUtil.getname(tag) != null) {
			tooltip.add(TextFormatting.GOLD + I18n.format("blades.crafter") + ":" + TextFormatting.GRAY
					+ BladeUtil.getname(tag));
		}
		if (StringHelper.displayShiftForDetail && !StringHelper.isShiftKeyDown()) {
			tooltip.add(I18n.format("info.cofh.holdShiftForDetails"));
		}
		if (!StringHelper.isShiftKeyDown()) {
			return;
		}
		if (stack.getTagCompound() == null) {
			EnergyHelper.setDefaultEnergyTag(stack, 0);
		}
		tooltip.add(
				StringHelper.localize("info.cofh.charge") + ": " + StringHelper.getScaledNumber(getEnergyStored(stack))
						+ " / " + StringHelper.getScaledNumber(getMaxEnergyStored(stack)) + " RF");
		tooltip.add(StringHelper.ORANGE + getEnergyPerUse(stack) + " "
				+ StringHelper.localize("info.flammpfeil.slashblade.tool.energyPerUse") + StringHelper.END);
		tooltip.add(StringHelper.RED + StringHelper.localize("info.flammpfeil.slashblade.tool.user") + ": "
				+ BladeUtil.Username.get(ItemSlashBlade.getItemTagCompound(stack)));
		addEmpoweredTip(this, stack, tooltip);
	}

	public void addEmpoweredTip(IMultiModeItem item, ItemStack stack, List<String> tooltip) {
		if (!isEmpowered(stack)) {
			tooltip.add(StringHelper.localizeFormat("info.flammpfeil.slashblade.tool.chargeOn",
					StringHelper.getKeyName(KeyBindingItemMultiMode.INSTANCE.getKey())));
		} else {
			tooltip.add(StringHelper.localizeFormat("info.flammpfeil.slashblade.tool.chargeOff",
					StringHelper.getKeyName(KeyBindingItemMultiMode.INSTANCE.getKey())));
		}
	}
}
