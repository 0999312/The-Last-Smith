package cn.mmf.lastsmith.event;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

import com.google.common.collect.Maps;

import cn.mmf.lastsmith.advancement.AdvancementHelper;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.entity.EntityBladeStand;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class DropEvent {
	public static class DropInfo {
		private final ResourceLocation advancement;
		private final float dropRate;
		private final ItemStack itemStack;
		private final boolean onlyFirst;
		
		public DropInfo(ResourceLocation advancementKey, float rate, ItemStack item,boolean onlyFirstTime) {
			advancement=advancementKey;
			dropRate=rate;
			itemStack=item;
			onlyFirst=onlyFirstTime;
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
	public static final Map<UUID, Boolean> canDropMap = Maps.newHashMap();
	/**
	 *
	 * @param entityKey
	 * @param rate
	 * abs(rate) over 1 forcedrop
	 *
	 * @param item
	 */
	public static void registerEntityDrop(ResourceLocation entityKey,ResourceLocation advancementKey, float rate, ItemStack item,boolean onlyFirstTime) {
		DropInfo info = new DropInfo(advancementKey, rate, item, onlyFirstTime);
		dropData.put(entityKey, info);
	}
	public static boolean isCanDrop(UUID id) {
		return canDropMap.getOrDefault(id, true);
	}
	public static void setCanDrop(UUID id,boolean isCan) {
		canDropMap.put(id, isCan);
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
			if (!isCanDrop(target.getUniqueID()))
				return;
			ItemStack attackItem = target.getHeldItem(EnumHand.MAIN_HAND);
			if (attackItem.isEmpty())
				return;
			if (!(attackItem.getItem() instanceof ItemSlashBlade))
				return;
			if (!AdvancementHelper.checkAdvancement((EntityPlayer)target, info.getAdvancement())) 
				return;

			if ((event.isRecentlyHit() || forceDrop) && isDrop && info.getItemStack() != null && !info.getItemStack().isEmpty()) {
				ItemStack dropitem = info.getItemStack().copy();
				dropitem.setCount(Math.max(dropitem.getMaxStackSize(),
						Math.max(1, Math.round(dropitem.getCount() * rand.nextFloat()))));
				if(info.isOnlyFirstTime()){
					setCanDrop(target.getUniqueID(), false);
				}
				if (dropitem.getItem() instanceof ItemSlashBlade) {
					NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(dropitem);
					if (!tag.getBoolean("IsNoStandDrop") || SlashBlade.MobSafeDrop) {
						EntityBladeStand e = new EntityBladeStand(event.getEntityLiving().world,
								event.getEntityLiving().posX, event.getEntityLiving().posY,
								event.getEntityLiving().posZ, dropitem);
						e.setGlowing(true);
						event.getEntityLiving().world.spawnEntity(e);
						return;
					}
				}

				if (dropitem.getCount() != 0)
					event.getEntityLiving().entityDropItem(dropitem, 1);

			}
		}
	}
}
