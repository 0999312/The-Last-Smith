package net.langball.lastsmith.compat.jei;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.langball.lastsmith.blocks.BlastRecipes;
import net.minecraft.item.ItemStack;

public final class BlastRecipeMaker {
	  public static List<BlastRecipe> getRecipes(IJeiHelpers helpers)
	  {
	    IStackHelper stackHelper = helpers.getStackHelper();
	    BlastRecipes furnaceRecipes = BlastRecipes.instance();
	    Map<ItemStack, ItemStack> smeltingMap = furnaceRecipes.getSmeltingList();
	    
	    List<BlastRecipe> recipes = new ArrayList<BlastRecipe>();
	    for (Map.Entry<ItemStack, ItemStack> entry : smeltingMap.entrySet())
	    {
	      ItemStack input = (ItemStack)entry.getKey();
	      ItemStack output = (ItemStack)entry.getValue();
	      
	      List<ItemStack> inputs = stackHelper.getSubtypes(input);
	      BlastRecipe recipe = new BlastRecipe(inputs, output);
	      recipes.add(recipe);
	    }
	    return recipes;
	  }
}
