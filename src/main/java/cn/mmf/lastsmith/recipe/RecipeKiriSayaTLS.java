package cn.mmf.lastsmith.recipe;

import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RecipeKiriSayaTLS extends RecipeAwakeBladeTLS {
	private final ItemStack sphere;

	public RecipeKiriSayaTLS(ResourceLocation loc, String advancement, ItemStack result, ItemStack requiredStateBlade,
			ItemStack reqiredSphere, Object[] recipe) {
		super(loc, advancement, result, requiredStateBlade, recipe);
		this.sphere = reqiredSphere;
	}

	@Override
	public boolean matches(InventoryCrafting inv, World world) {
		if (this.sphere == null)
			return false;

		boolean result = super.matches(inv, world);
		if (!result)
			return false;

		for (int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack current = inv.getStackInSlot(i);
			if (!current.isItemEqual(this.sphere))
				continue;
			int requiredsa = ItemSlashBlade.SpecialAttackType.get(this.sphere.getTagCompound());
			int currentsa = ItemSlashBlade.SpecialAttackType.get(current.getTagCompound());
			if (requiredsa != currentsa)
				return false;

		}
		return true;
	}
}
