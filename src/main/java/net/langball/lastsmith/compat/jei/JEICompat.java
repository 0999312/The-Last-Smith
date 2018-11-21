package net.langball.lastsmith.compat.jei;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.langball.lastsmith.blocks.BlockLoader;
import net.langball.lastsmith.gui.ContainerBlast;
import net.langball.lastsmith.gui.GuiBlast;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JEICompat implements IModPlugin {
	@Override
	public void register(IModRegistry registry) {
		IIngredientRegistry ingredientRegistry = registry.getIngredientRegistry();
		IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();
		registry.addRecipes(BlastRecipeMaker.getRecipes(jeiHelpers),"lastsmith.blast");
		registry.addRecipeClickArea(GuiBlast.class, 78, 32, 28, 23,"lastsmith.blast");
		recipeTransferRegistry.addRecipeTransferHandler(ContainerBlast.class,"lastsmith.blast", 0, 1, 3, 36);
		registry.addRecipeCatalyst(new ItemStack(BlockLoader.BlastItem),"lastsmith.blast");

	}
	
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new IRecipeCategory[]{
				new CategoryBlast(registry.getJeiHelpers().getGuiHelper()),
		}
	);
		
	}
	
}
