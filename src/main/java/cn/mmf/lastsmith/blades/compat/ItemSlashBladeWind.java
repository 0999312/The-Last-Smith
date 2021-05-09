package cn.mmf.lastsmith.blades.compat;

import java.util.List;

import cn.mmf.lastsmith.item.ItemSlashBladeNamedTLS;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.client.fx.FXDispatcher;
import thaumcraft.common.lib.SoundsTC;
import thaumcraft.common.lib.utils.EntityUtils;

public class ItemSlashBladeWind extends ItemSlashBladeNamedTLS {

	public ItemSlashBladeWind(ToolMaterial par2EnumToolMaterial, float baseAttackModifiers) {
		super(par2EnumToolMaterial, baseAttackModifiers);
	}

	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		super.onUsingTick(stack, player, count);
		int ticks = getMaxItemUseDuration(stack) - count;
		if (player.motionY < 0.0D) {
			player.motionY /= 1.200000047683716D;
			player.fallDistance /= 1.2F;
		}
		player.motionY += 0.07999999821186066D;
		if (player.motionY > 0.5D) {
			player.motionY = 0.2000000029802322D;
		}
		if ((player instanceof EntityPlayerMP)) {
			EntityUtils.resetFloatCounter((EntityPlayerMP) player);
		}
		List<Entity> targets = player.world.getEntitiesWithinAABBExcludingEntity(player,
				player.getEntityBoundingBox().grow(2.5D, 2.5D, 2.5D));
		if (targets.size() > 0) {
			for (int var9 = 0; var9 < targets.size(); var9++) {
				Entity entity = targets.get(var9);
				if ((!(entity instanceof EntityPlayer)) && ((entity instanceof EntityLivingBase)) && (!entity.isDead)
						&& ((player.getRidingEntity() == null) || (player.getRidingEntity() != entity))) {
					Vec3d p = new Vec3d(player.posX, player.posY, player.posZ);
					Vec3d t = new Vec3d(entity.posX, entity.posY, entity.posZ);
					double distance = p.distanceTo(t) + 0.1D;

					Vec3d r = new Vec3d(t.x - p.x, t.y - p.y, t.z - p.z);

					entity.motionX += r.x / 2.5D / distance;
					entity.motionY += r.y / 2.5D / distance;
					entity.motionZ += r.z / 2.5D / distance;
				}
			}
		}
		if (player.world.isRemote) {
			int miny = (int) (player.getEntityBoundingBox().minY - 2.0D);
			if (player.onGround) {
				miny = MathHelper.floor(player.getEntityBoundingBox().minY);
			}
			for (int a = 0; a < 5; a++) {
				FXDispatcher.INSTANCE.smokeSpiral(player.posX,
						player.getEntityBoundingBox().minY + player.height / 2.0F, player.posZ, 1.5F,
						player.world.rand.nextInt(360), miny, 14540253);
			}
			if (player.onGround) {
				float r1 = player.world.rand.nextFloat() * 360.0F;
				float mx = -MathHelper.sin(r1 / 180.0F * 3.141593F) / 5.0F;
				float mz = MathHelper.cos(r1 / 180.0F * 3.141593F) / 5.0F;
				player.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, player.posX,
						player.getEntityBoundingBox().minY + 0.1000000014901161D, player.posZ, mx, 0.0D, mz,
						new int[0]);
			}
		} else if ((ticks == 0) || (ticks % 20 == 0)) {
			player.playSound(SoundsTC.wind, 0.5F, 0.9F + player.world.rand.nextFloat() * 0.2F);
		}

	}

	public boolean getIsRepairable(ItemStack stack1, ItemStack stack2) {
		return stack2.isItemEqual(new ItemStack(ItemsTC.ingots, 1, 0)) ? true : super.getIsRepairable(stack1, stack2);
	}
}
