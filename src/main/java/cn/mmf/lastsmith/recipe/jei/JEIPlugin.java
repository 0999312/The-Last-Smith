package cn.mmf.lastsmith.recipe.jei;

import cn.mmf.lastsmith.recipe.RecipeAwakeBladeTLS;
import cn.mmf.lastsmith.recipe.RecipeKiriSayaTLS;
import cn.mmf.lastsmith.recipe.RecipeTriBladeTLS;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.IRecipeRegistry;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;

@mezz.jei.api.JEIPlugin
public class JEIPlugin implements IModPlugin{
    public static IRecipeRegistry recipeRegistry;
    public static IJeiHelpers jeiHelpers;
    public static IIngredientRegistry ingredientRegistry;
    
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        jeiHelpers = registry.getJeiHelpers();
    }
    
    @Override
    public void register(IModRegistry registry) {
        ingredientRegistry = registry.getIngredientRegistry();
        registry.handleRecipes(RecipeAwakeBladeTLS.class, new TLSRecipeFactory2(), VanillaRecipeCategoryUid.CRAFTING);
        registry.handleRecipes(RecipeKiriSayaTLS.class, new TLSRecipeFactory(), VanillaRecipeCategoryUid.CRAFTING);
        registry.handleRecipes(RecipeTriBladeTLS.class, new TLSTriRecipeFactory(), VanillaRecipeCategoryUid.CRAFTING);
    }
}
