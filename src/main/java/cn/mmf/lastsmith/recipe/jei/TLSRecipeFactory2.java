package cn.mmf.lastsmith.recipe.jei;

import cn.mmf.lastsmith.recipe.RecipeAwakeBladeTLS;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;

public class TLSRecipeFactory2 implements IRecipeWrapperFactory<RecipeAwakeBladeTLS> {
	@Override
	public IRecipeWrapper getRecipeWrapper(RecipeAwakeBladeTLS recipe) {
		return new AwakedBladeRecipeWrapper(JEIPlugin.jeiHelpers, recipe);
	}
	
}
