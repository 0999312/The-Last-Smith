package net.langball.lastsmith.compat.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
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
		IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();
		registry.addRecipes(BlastRecipeMaker.getRecipes(jeiHelpers),"lastsmith.blast");
		registry.addRecipeClickArea(GuiBlast.class, 78, 32, 28, 23,"lastsmith.blast");
		recipeTransferRegistry.addRecipeTransferHandler(ContainerBlast.class,"lastsmith.blast", 0, 1, 3, 36);
		registry.addRecipeCatalyst(new ItemStack(BlockLoader.BlastItem),"lastsmith.blast");

	}
	
	@SuppressWarnings("rawtypes")
	public void registerCategories(IRecipeCategoryRegistration registry) {
		final IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		final IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
		registry.addRecipeCategories(new IRecipeCategory[]{
				new CategoryBlast(guiHelper),
		}
	);
		
	}
	
}
