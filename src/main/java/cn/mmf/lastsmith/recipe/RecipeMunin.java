package cn.mmf.lastsmith.recipe;

import java.util.Iterator;
import java.util.List;

import cn.mmf.lastsmith.TLSConfig;
import cn.mmf.lastsmith.advancement.AdvancementHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RecipeMunin extends RecipeTriBladeTLS {
	private final List<String> required_advancements;

	public RecipeMunin(ResourceLocation group, List<String> advancements, ItemStack result,
			ItemStack requiredBladeMain, int posXMain, int posYMain, ItemStack requiredBladeSub, int posXSub,
			int posYSub, boolean remainedBladeSub, ItemStack requiredBladeTri, int posXTri, int posYTri,
			boolean remainedBladeTri, Object[] recipe) {
		super(group, advancements.get(0), result, requiredBladeMain, posXMain, posYMain, requiredBladeSub, posXSub, posYSub,
				remainedBladeSub, requiredBladeTri, posXTri, posYTri, remainedBladeTri, recipe);
		required_advancements = advancements;
	}

	@Override
	public boolean matches(InventoryCrafting inv, World world) {
		if (canCrafting(inv, world))
			return super.matches(inv, world);
		return false;
	}

	private boolean canCrafting(InventoryCrafting inv, World world) {
		if (!TLSConfig.advanced_mode)
			return true;
		if (!TLSConfig.recipe_lock_enable)
			return true;
		Container container = inv.eventHandler;
		if (container == null) {
			return false;
		}
		if(world == null)
			return false;
		if(world.playerEntities.iterator() == null)
			return false;
		EntityPlayer foundPlayer = null;
		Iterator<EntityPlayer> var6 = world.playerEntities.iterator();
		while (var6.hasNext()) {
			EntityPlayer entityPlayer = var6.next();
			if (entityPlayer.openContainer == container && container.canInteractWith(entityPlayer)
					&& container.getCanCraft(entityPlayer)) {
				foundPlayer = entityPlayer;
			}
		}
		if (foundPlayer != null) {
			boolean flag = true;
			for(String i : required_advancements) {
				if(flag == false) 
					break;
				flag = AdvancementHelper.getInstance().checkAdvancement(foundPlayer, i);
			}
			return flag;
		}
		return false;
	}
}
