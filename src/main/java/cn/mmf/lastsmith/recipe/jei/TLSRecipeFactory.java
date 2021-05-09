package cn.mmf.lastsmith.recipe.jei;

import cn.mmf.lastsmith.recipe.RecipeKiriSayaTLS;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;

public class TLSRecipeFactory implements IRecipeWrapperFactory<RecipeKiriSayaTLS> {
	@Override
	public IRecipeWrapper getRecipeWrapper(RecipeKiriSayaTLS recipe) {
		return new AwakedBladeRecipeWrapper(JEIPlugin.jeiHelpers, recipe);
	}
	
}
