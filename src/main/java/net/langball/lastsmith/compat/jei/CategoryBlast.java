package net.langball.lastsmith.compat.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.langball.lastsmith.Last_worker;
import net.langball.lastsmith.blocks.BlockLoader;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CategoryBlast<T extends IRecipeWrapper> implements IRecipeCategory<T>{
	  protected final IDrawable background;
	  private final IDrawable icon;
	  public CategoryBlast(IGuiHelper helper) {
		  ResourceLocation backgroundTexture = new ResourceLocation(Last_worker.MODID+":textures/gui/blastfurnace.png");
		  icon = helper.createDrawableIngredient(new ItemStack(BlockLoader.BlastItem));
		  background = helper.createDrawable(backgroundTexture, 55, 16, 82, 54);
	}
	@Override
	public IDrawable getBackground() {
		// TODO Auto-generated method stub
		return background;
	}

	@Override
	public String getModName() {
		// TODO Auto-generated method stub
		return Last_worker.NAME;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return I18n.format("jei.lastsmith.category.blast", new Object[0]);
	}

	@Override
	public String getUid() {
		// TODO Auto-generated method stub
		return "lastsmith.blast";
	}
	@Override
	public void setRecipe(IRecipeLayout arg0, IRecipeWrapper arg1, IIngredients arg2) {
		IGuiItemStackGroup items = arg0.getItemStacks();
		
		items.init(0, true, 0, 0);
		items.init(2, false, 60, 18);
	    
		items.set(arg2);
	}
	public IDrawable getIcon() {
		return icon;
	}

}
