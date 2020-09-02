package cn.mmf.lastsmith.recipe.jei;

import cn.mmf.lastsmith.recipe.RecipeTriBladeTLS;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;

public class TLSTriRecipeFactory implements IRecipeWrapperFactory<RecipeTriBladeTLS> {
	@Override
	public IRecipeWrapper getRecipeWrapper(RecipeTriBladeTLS recipe) {
		return new TriBladeRecipeWrapper(JEIPlugin.jeiHelpers, recipe);
	}
}
