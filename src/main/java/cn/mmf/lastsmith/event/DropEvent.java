package cn.mmf.lastsmith.event;

import java.util.Map;
import java.util.Random;
import com.google.common.collect.Maps;

import cn.mmf.lastsmith.TLSConfig;
import cn.mmf.lastsmith.advancement.AdvancementHelper;
import cn.mmf.lastsmith.item.ItemLoader;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.entity.EntityBladeStand;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class DropEvent {
	public static class DropInfo {
		private final ResourceLocation advancement;
		private final float dropRate;
		private final ItemStack itemStack;
		private final boolean onlyFirst;

		public DropInfo(ResourceLocation advancementKey, float rate, ItemStack item, boolean onlyFirstTime) {
			advancement = advancementKey;
			dropRate = rate;
			itemStack = item;
			onlyFirst = onlyFirstTime;
		}

		public ResourceLocation getAdvancement() {
			return advancement;
		}

		public float getDropRate() {
			return dropRate;
		}

		public ItemStack getItemStack() {
			return itemStack;
		}

		public boolean isOnlyFirstTime() {
			return onlyFirst;
		}
	}

	public static final Map<ResourceLocation, DropInfo> dropData = Maps.newHashMap();

	/**
	 *
	 * @param entityKey
	 * @param rate      abs(rate) over 1 forcedrop
	 *
	 * @param item
	 */
	public static void registerEntityDrop(ResourceLocation entityKey, ResourceLocation advancementKey, float rate,
			ItemStack item, boolean onlyFirstTime) {
		DropInfo info = new DropInfo(advancementKey, rate, item, onlyFirstTime);
		dropData.put(entityKey, info);
	}

	@SubscribeEvent
	public static void KillingMob(LivingExperienceDropEvent event) {
		World world = event.getEntityLiving().world;
		if (!world.isRemote && event.getAttackingPlayer() != null) {
			if (event.getAttackingPlayer().getHeldItemMainhand().getItem() instanceof ItemSlashBlade) {
				int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING,
						event.getAttackingPlayer().getHeldItemMainhand());
				if (world.rand.nextDouble() <= TLSConfig.sakura_drop_rate) {
					int amount = 0;
					for (int i = 0; i < 1 + level; ++i) {
						if (world.rand.nextInt(2 * Enchantments.LOOTING.getMaxLevel()) <= level)
							amount++;
					}
					dropItem(new ItemStack(ItemLoader.MATERIALS, amount, 3), world, event.getEntityLiving());
				}
			}
		}
	}

	private static void dropItem(ItemStack itemStack, World world, EntityLivingBase entity) {
		if ((world.restoringBlockSnapshots) || (world.isRemote)) {
			return;
		}
		float f = 0.5F;
		double d0 = world.rand.nextFloat() * f + 0.25D;
		double d1 = world.rand.nextFloat() * f + 0.25D;
		double d2 = world.rand.nextFloat() * f + 0.25D;

		EntityItem entityItem = new EntityItem(world, entity.posX + d0, entity.posY + d1, entity.posZ + d2, itemStack);
		entityItem.setDefaultPickupDelay();
		world.spawnEntity(entityItem);
	}

	@SubscribeEvent
	public static void LivingDrops(LivingDropsEvent event) {
		ResourceLocation key = EntityList.getKey(event.getEntityLiving());
		if (dropData.containsKey(key)) {
			Random rand = event.getEntityLiving().getRNG();
			DropInfo info = dropData.get(key);
			float rate = Math.abs(info.getDropRate());
			boolean isDrop = rate * (1.0f + 0.5f * event.getLootingLevel()) > rand.nextFloat();
			boolean forceDrop = rate > 1.1f;
			EntityLivingBase target = event.getEntityLiving().getRevengeTarget();
			if (!(target instanceof EntityPlayer))
				return;
			ItemStack attackItem = target.getHeldItem(EnumHand.MAIN_HAND);
			if (attackItem.isEmpty())
				return;
			if (!(attackItem.getItem() instanceof ItemSlashBlade))
				return;
			if (!AdvancementHelper.getInstance().checkAdvancement((EntityPlayer) target, info.getAdvancement()))
				return;

			if ((event.isRecentlyHit() || forceDrop) && isDrop && info.getItemStack() != null
					&& !info.getItemStack().isEmpty()) {
				ItemStack dropitem = info.getItemStack().copy();
				dropitem.setCount(Math.max(dropitem.getMaxStackSize(),
						Math.max(1, Math.round(dropitem.getCount() * rand.nextFloat()))));
				NBTTagCompound playerData = target.getEntityData();
				NBTTagCompound data = getTagSafe(playerData, EntityPlayer.PERSISTED_NBT_TAG);
				StringBuilder builder = new StringBuilder(dropitem.getUnlocalizedName().substring(5)).append("_has");
				if (info.isOnlyFirstTime()) {
					if (data.getBoolean(builder.toString())) {
						return ;
					}
				}
				if (dropitem.getItem() instanceof ItemSlashBlade) {
					NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(dropitem);
					if (!tag.getBoolean("IsNoStandDrop") || SlashBlade.MobSafeDrop) {
						EntityBladeStand e = new EntityBladeStand(event.getEntityLiving().world,
								event.getEntityLiving().posX, event.getEntityLiving().posY,
								event.getEntityLiving().posZ, dropitem);
						e.setGlowing(true);
						event.getEntityLiving().world.spawnEntity(e);
						data.setBoolean(builder.toString(), true);
						playerData.setTag(EntityPlayer.PERSISTED_NBT_TAG, data);
						return;
					}
				}
				if (dropitem.getCount() != 0)
					event.getEntityLiving().entityDropItem(dropitem, 1);
				data.setBoolean(builder.toString(), true);
				playerData.setTag(EntityPlayer.PERSISTED_NBT_TAG, data);
			}
		}
	}

	private static NBTTagCompound getTagSafe(NBTTagCompound tag, String key) {
		if (tag == null) {
			return new NBTTagCompound();
		}

		return tag.getCompoundTag(key);
	}
}
