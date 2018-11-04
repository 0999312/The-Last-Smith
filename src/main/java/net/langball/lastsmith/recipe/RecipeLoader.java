package net.langball.lastsmith.recipe;

import mods.flammpfeil.slashblade.RecipeUpgradeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.crafting.BladeIngredient;
import mods.flammpfeil.slashblade.item.crafting.RecipeCustomBlade;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blocks.BlastRecipes;
import net.langball.lastsmith.blocks.BlockLoader;
import net.langball.lastsmith.items.ItemLoader;
import net.langball.lastsmith.util.RecipesUtil;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.ForgeRegistry;

public class RecipeLoader {
	public RecipeLoader() {
		BlastRecipes.instance().addSmeltingRecipe(new ItemStack(ItemLoader.crashed_iron), new ItemStack(ItemLoader.tamahagane,1), 0F);
		 ForgeRegistry<IRecipe> recipeRegistry = (ForgeRegistry<IRecipe>)ForgeRegistries.RECIPES;
		 recipeRegistry.remove(new ResourceLocation(SlashBlade.modid,"recipexes"));
		 recipeRegistry.remove(new ResourceLocation(SlashBlade.modid,"bamboolight"));
		 recipeRegistry.remove(new ResourceLocation(SlashBlade.modid,"white"));
		 recipeRegistry.remove(new ResourceLocation(SlashBlade.modid,"white2"));
		 recipeRegistry.remove(new ResourceLocation(SlashBlade.modid,"white3"));

		RecipesUtil.addRecipe("hammer", new ShapedOreRecipe(new ResourceLocation(""), ItemLoader.hammer, new Object[]{
				" I "," LI","L  ",'I',"ingotIron",'L',"logWood"
		}));
		RecipesUtil.addRecipe("blast", new ShapedOreRecipe(new ResourceLocation(""), BlockLoader.BlastItem, new Object[]{
				" I ","BBB","BFB",'I',"blockIron",'B',Blocks.BRICK_BLOCK,'F',Blocks.FURNACE
		}));
		RecipesUtil.addRecipe("paper_arthurs", new ShapedOreRecipe(new ResourceLocation(""), ItemLoader.paper_arthurs, new Object[]{
				"BBB","BIB","BBB",'B',ItemLoader.sakura,'I',"paper"
		}));
		RecipesUtil.addRecipe("steel_ingot", new ShapelessOreRecipe(new ResourceLocation(""), ItemLoader.steel_ingot, new Object[]{ItemLoader.hammer,ItemLoader.tamahagane,ItemLoader.tamahagane}));
		RecipesUtil.addRecipe("crushed_iron", new ShapelessOreRecipe(new ResourceLocation(""), ItemLoader.crashed_iron, new Object[]{ItemLoader.hammer,"oreIron","gemCoal"}));
		RecipesUtil.addRecipe("red_dye", new ShapelessOreRecipe(new ResourceLocation(""), new ItemStack(Items.DYE,1,1), new Object[]{ItemLoader.sakura,ItemLoader.sakura}));
		RecipesUtil.addRecipe("wooden_blade", new ShapedOreRecipe(new ResourceLocation(""), ItemLoader.wooden_blade, new Object[]{
				" PP","PP ","L  ",'P',"plankWood",'L',"logWood"
		}));
		RecipesUtil.addRecipe("wooden_saya", new ShapedOreRecipe(new ResourceLocation(""),BladeLoader.wrapper, new Object[]{
				" PL","PLP","LP ",'P',"plankWood",'L',"logWood"
		}));
		SlashBlade.addRecipe("slashbladeWood",new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid,"recipexes"), new ItemStack(SlashBlade.bladeWood) , new Object[]{
				"  W"," B ","L  ",Character.valueOf('W'),BladeLoader.wrapper,Character.valueOf('B'),ItemLoader.wooden_blade,Character.valueOf('L'),"logWood"
		}));
		RecipesUtil.addRecipe("blade_unfinished", new ShapedOreRecipe(new ResourceLocation(""), ItemLoader.blade_unfinished, new Object[]{
				" PP","PP ","L H",'P',"ingotIron",'L',SlashBlade.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1),'H',ItemLoader.hammer
		}));
		RecipesUtil.addRecipe("blade_2_unfinished", new ShapedOreRecipe(new ResourceLocation(""), ItemLoader.blade_2_unfinished, new Object[]{
				" IP","IP ","L H",'I',"ingotSteel",'P',"ingotIron",'L',SlashBlade.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1),'H',ItemLoader.hammer
		}));
		RecipesUtil.addRecipe("blade_3_unfinished", new ShapedOreRecipe(new ResourceLocation(""), ItemLoader.blade_3_unfinished, new Object[]{
				" LP","LP ","L H",'P',"ingotIron",'L',SlashBlade.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1),'H',ItemLoader.hammer
		}));
		RecipesUtil.addRecipe("blade", new ShapedOreRecipe(new ResourceLocation(""), ItemLoader.blade, new Object[]{
				"  I"," I ","B H",'I',"ingotIron",'H',ItemLoader.hammer,'B',ItemLoader.blade_unfinished
		}));
		RecipesUtil.addRecipe("blade_2", new ShapedOreRecipe(new ResourceLocation(""), ItemLoader.blade_2, new Object[]{
				"  I"," I ","B H",'I',"ingotSteel",'H',ItemLoader.hammer,'B',ItemLoader.blade_2_unfinished
		}));
		RecipesUtil.addRecipe("blade_3", new ShapedOreRecipe(new ResourceLocation(""), ItemLoader.blade_3, new Object[]{
				"  I"," I ","B H",'I',SlashBlade.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1),'H',ItemLoader.hammer,'B',ItemLoader.blade_3_unfinished
		}));
		if(OreDictionary.getOres("bamboo").isEmpty()){
            SlashBlade.addRecipe("slashbladeBambooLight",
                    new RecipeUpgradeBlade(new ResourceLocation(SlashBlade.modid,"bamboolight"),
                            new ItemStack(SlashBlade.bladeBambooLight),new Object[]{
				" LB","LB ","D  ",Character.valueOf('L'),"logWood",Character.valueOf('B'),Items.REEDS,'D',new BladeIngredient(new ItemStack(SlashBlade.bladeWood,1))
		}));}else{
            SlashBlade.addRecipe("slashbladeBambooLight",
                    new RecipeUpgradeBlade(new ResourceLocation(SlashBlade.modid,"bamboolight"),
                            new ItemStack(SlashBlade.bladeBambooLight),new Object[]{
				" LB","LB ","D  ",Character.valueOf('L'),"logWood",Character.valueOf('B'),"bamboo",'D',new BladeIngredient(new ItemStack(SlashBlade.bladeWood,1))
		}));}
		
		SlashBlade.addRecipe("slashbladeWhite",new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid,"white"), new ItemStack(SlashBlade.bladeWhiteSheath, 1, SlashBlade.bladeWhiteSheath.getMaxDamage() / 3) , new Object[]{
				"  W"," B ","L H",Character.valueOf('W'),BladeLoader.wrapper,Character.valueOf('B'),ItemLoader.blade,Character.valueOf('L'),new BladeIngredient(new ItemStack(SlashBlade.bladeWood,1)),'H',ItemLoader.hammer
		}));
		SlashBlade.addRecipe("slashbladeWhite2",new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid,"white2"), new ItemStack(SlashBlade.bladeWhiteSheath, 1, SlashBlade.bladeWhiteSheath.getMaxDamage() / 4) , new Object[]{
				"  W"," B ","L H",Character.valueOf('W'),BladeLoader.wrapper,Character.valueOf('B'),ItemLoader.blade_2,Character.valueOf('L'),new BladeIngredient(new ItemStack(SlashBlade.bladeWood,1)),'H',ItemLoader.hammer
		}));
		SlashBlade.addRecipe("slashbladeWhite3",new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid,"white3"), new ItemStack(SlashBlade.bladeWhiteSheath, 1) , new Object[]{
				"  W"," B ","L H",Character.valueOf('W'),BladeLoader.wrapper,Character.valueOf('B'),ItemLoader.blade_3,Character.valueOf('L'),new BladeIngredient(new ItemStack(SlashBlade.bladeWood,1)),'H',ItemLoader.hammer
		}));
	}
}
