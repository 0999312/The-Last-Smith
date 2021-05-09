package cn.mmf.lastsmith.recipe.jei;

import cn.mmf.lastsmith.blades.BladeLoader;
import cn.mmf.lastsmith.recipe.RecipeAwakeBladeTLS;
import cn.mmf.lastsmith.recipe.RecipeKiriSayaTLS;
import cn.mmf.lastsmith.recipe.RecipeTriBladeTLS;
import ic2.core.IC2;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.IRecipeRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.ISubtypeRegistry.ISubtypeInterpreter;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Loader;
import thaumcraft.Thaumcraft;

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
//      
        registry.handleRecipes(RecipeAwakeBladeTLS.class, new TLSRecipeFactory2(), VanillaRecipeCategoryUid.CRAFTING);
        registry.handleRecipes(RecipeKiriSayaTLS.class, new TLSRecipeFactory(), VanillaRecipeCategoryUid.CRAFTING);
        registry.handleRecipes(RecipeTriBladeTLS.class, new TLSTriRecipeFactory(), VanillaRecipeCategoryUid.CRAFTING);
    }
	@Override
	public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {
		
		ISubtypeInterpreter blade = itemStack -> {
			NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(itemStack);
			return ItemSlashBladeNamed.CurrentItemName.get(reqTag, "flammpfeil.slashblade.named"); 
		};
		subtypeRegistry.registerSubtypeInterpreter(BladeLoader.bladeNamed, blade);
		if(Loader.isModLoaded(IC2.MODID)) {
			subtypeRegistry.registerSubtypeInterpreter(BladeLoader.euBlade, blade);
		}
		subtypeRegistry.registerSubtypeInterpreter(BladeLoader.rfblade, blade);
		if(Loader.isModLoaded(Thaumcraft.MODID)){
			subtypeRegistry.registerSubtypeInterpreter(BladeLoader.voidBlade, blade);
			subtypeRegistry.registerSubtypeInterpreter(BladeLoader.windBlade, blade);
			subtypeRegistry.registerSubtypeInterpreter(BladeLoader.crimsonBlade, blade);
		}
	}
}
