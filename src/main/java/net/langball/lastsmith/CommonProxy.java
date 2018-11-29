package net.langball.lastsmith;

import java.util.Random;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.core.config.plugins.Plugin;

import mods.flammpfeil.slashblade.ItemSlashBladeDetune;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.event.DropEventHandler;
import mods.flammpfeil.slashblade.item.crafting.RecipeCustomBlade;
import mods.flammpfeil.slashblade.util.SlashBladeHooks;
import net.langball.lastsmith.blade.*;
import net.langball.lastsmith.blade.famous.*;
import net.langball.lastsmith.blocks.BlockLoader;
import net.langball.lastsmith.blocks.TileEntityLoader;
import net.langball.lastsmith.compat.*;
import net.langball.lastsmith.eusaber.*;
import net.langball.lastsmith.gui.GuiLoader;
import net.langball.lastsmith.items.ItemHammer;
import net.langball.lastsmith.items.ItemLoader;
import net.langball.lastsmith.louguan.blade.*;
import net.langball.lastsmith.recipe.RecipeLoader;
import net.langball.lastsmith.sa.EntitySA;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.GameData;
import thaumcraft.Thaumcraft;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.common.lib.research.ResearchManager;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeRuneAltar;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.BotaniaCreativeTab;

public class CommonProxy {
	public static CreativeTabs tab;
	 public void preInit(FMLPreInitializationEvent event)
	    {
		 new ConfigLoader(event);		
		 tab=new CreativeTabsSmith();
		 new BlockLoader(event);
		 new ItemLoader(event);
		 new TileEntityLoader(event);
		 new BladeLoader(event);
}
    public void init(FMLInitializationEvent event)
    { 
    	new GuiLoader();
    	new RecipeLoader();
    	if(Loader.isModLoaded(Thaumcraft.MODID)){
    		 ThaumcraftApi.registerResearchLocation(new ResourceLocation(Last_worker.MODID+":research/research.json"));
        	 ResearchCategories.registerCategory("KATANA", null, new AspectList().add(Aspect.SOUL, 1), new ResourceLocation(Last_worker.MODID+":textures/research/yamatooo.png"), new ResourceLocation(Last_worker.MODID+":textures/research/guislashblade.jpg"));
    	}
    }

    public void postInit(FMLPostInitializationEvent event)
    {
    	if(Loader.isModLoaded("botania")){
    		ItemStack sphere = SlashBlade.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
    		ItemStack soul = SlashBlade.findItemStack("flammpfeil.slashblade", "proudsoul", 1);
    		ItemStack terraBlade = SlashBlade.findItemStack("Botania", "terraSword", 1);
    		ItemStack vineBall = SlashBlade.findItemStack("Botania", "vineBall", 1);
    		ItemStack thornChakram = SlashBlade.findItemStack("Botania", "thornChakram", 1);
    		SlashBladeHooks.EventBus.register(new ManaRepair());
    		RecipeRuneAltar runeAltarRecipe = BotaniaAPI.registerRuneAltarRecipe(SlashBlade.findItemStack("flammpfeil.slashblade", "flammpfeil.slashblade.named.yingjian", 1), 1000, new Object[] { SlashBlade.findItemStack("flammpfeil.slashblade", "flammpfeil.slashblade.named.roukan", 1), sphere, "gaiaIngot", vineBall, new ItemStack(Blocks.LOG), thornChakram, "ingotTerrasteel", new ItemStack(Items.STICK) });
    	}
    }
	}
