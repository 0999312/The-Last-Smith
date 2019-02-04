package net.langball.lastsmith;

import java.util.Random;

import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.langball.lastsmith.items.ItemLoader;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventKillingMob {
	@SubscribeEvent
	public void KillingMob(LivingExperienceDropEvent event) {
		if(event.getAttackingPlayer()!=null){
		if(event.getAttackingPlayer().getHeldItemMainhand().getItem() instanceof ItemSlashBlade){
			int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, event.getAttackingPlayer().getHeldItemMainhand());
			if(level>9)level = 9;
			Random random = new Random();
         	int i=random.nextInt(100-(10*level));
         	if(i<2) dropItem(new ItemStack(ItemLoader.material,1,7), event.getEntityLiving().world, event.getEntityLiving());
		}
		}
	}
    private static void dropItem(ItemStack itemStack, World world, EntityLivingBase entity)
	  {
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
}
