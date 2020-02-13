package cn.mmf.lastsmith.blades.vanilla;

import cn.mmf.lastsmith.blades.BladeLoader;
import cn.mmf.lastsmith.event.RegisterSlashBladeEvent;
import cn.mmf.lastsmith.event.RegisterSlashBladeRecipeEvent;
import cn.mmf.lastsmith.item.ItemLoader;
import cn.mmf.lastsmith.recipe.RecipeAwakeBladeTLS;
import cn.mmf.lastsmith.util.BladeUtil;
import cn.mmf.lastsmith.util.OreWildcardIngredient;
import cn.mmf.slashblade_addon.SJAP;
import cn.mmf.slashblade_addon.item.ItemSlashBladeRF;
import cn.mmf.slashblade_addon.item.ItemSlashBladeWind;
import cofh.CoFHCore;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.RecipeUpgradeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.item.crafting.BladeIngredient;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.ShapedOreRecipe;
import thaumcraft.Thaumcraft;

@EventBusSubscriber
public class VanillaBladeRegister {
	@SubscribeEvent
	public static void onSlashBladeRegister(RegisterSlashBladeEvent event) {
		SlashBlade.BladeRegistry.forEach((name, blade) -> {
			if (!(blade.getItem() instanceof ItemSlashBladeNamed))
				return;
			NBTTagCompound oldNBT = ItemSlashBlade.getItemTagCompound(blade);
			ItemStack newBlade = new ItemStack(BladeLoader.bladeNamed);
			if (Loader.isModLoaded(SJAP.MODID)) {
				if (Loader.isModLoaded(CoFHCore.MOD_ID) && blade.getItem() instanceof ItemSlashBladeRF) {
					newBlade = new ItemStack(BladeLoader.rfblade);
					BladeUtil.ModelOnName.set(oldNBT, ItemSlashBladeNamed.ModelName.get(oldNBT));
					BladeUtil.TextureOnName.set(oldNBT, ItemSlashBladeNamed.TextureName.get(oldNBT));
					newBlade.setTagCompound(oldNBT);
					SlashBlade.BladeRegistry.put(name, newBlade);
					return;
				}
				if (Loader.isModLoaded(Thaumcraft.MODID) && blade.getItem() instanceof ItemSlashBladeWind)
					newBlade = new ItemStack(BladeLoader.windBlade);
			}
			newBlade.setTagCompound(oldNBT);
			SlashBlade.BladeRegistry.put(name, newBlade);
		});
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid, "recipexes"),
				new ItemStack(SlashBlade.bladeWood), new Object[] {
						"  S",
						" B ",
						"#  ",
						'#', "logWood",
						'B', new ItemStack(ItemLoader.MATERIALS, 1, 0), 
						'S', BladeLoader.wrapper,
				}).setRegistryName(new ResourceLocation(SlashBlade.modid, "recipexes")));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid, "bamboolight"),
				new ItemStack(SlashBlade.bladeBambooLight),
				new Object[] { 
						" PS",
						"PBP",
						"#P ",
						'#', new ItemStack(SlashBlade.bladeWood),
						'B', new ItemStack(ItemLoader.MATERIALS, 1, 1),
						'P', "bamboo",
						'S', BladeLoader.wrapper_bamboo, 
				}).setRegistryName(new ResourceLocation(SlashBlade.modid, "bamboolight")));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid, "white"),
				new ItemStack(SlashBlade.bladeWhiteSheath, 1, 70 / 3),
				new Object[] {
						"  W",
						" B ",
						"LSH", 
						'W', BladeLoader.wrapper, 
						'B', new ItemStack(ItemLoader.BLADE, 1, 0),
						'L', new ItemStack(SlashBlade.bladeWood, 1),
						'H', new OreWildcardIngredient("toolForginghammer"),
						'S', "ingotIron"
		}).setRegistryName(new ResourceLocation(SlashBlade.modid, "white")));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid, "white2"),
				new ItemStack(SlashBlade.bladeWhiteSheath, 1, 70 / 4),
				new Object[] {
						"  W",
						" B ",
						"LSH", 
						'W', BladeLoader.wrapper, 
						'B', new ItemStack(ItemLoader.BLADE, 1, 1),
						'L', new ItemStack(SlashBlade.bladeWood, 1),
						'H', new OreWildcardIngredient("toolForginghammer"),
						'S', "ingotIron" 
		}).setRegistryName(new ResourceLocation(SlashBlade.modid, "white2")));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid, "white3"),
				new ItemStack(SlashBlade.bladeWhiteSheath, 1),
				new Object[] {
						"  W",
						" B ",
						"LSH", 
						'W', BladeLoader.wrapper, 
						'B', new ItemStack(ItemLoader.BLADE, 1, 2),
						'L', new ItemStack(SlashBlade.bladeWood, 1),
						'H', new OreWildcardIngredient("toolForginghammer"),
						'S', "ingotIron"
		}).setRegistryName(new ResourceLocation(SlashBlade.modid, "white3")));
		ItemStack brokenBladeWhite = new ItemStack(SlashBlade.bladeWhiteSheath,1,0);
	    brokenBladeWhite.setItemDamage(brokenBladeWhite.getMaxDamage());
	    ItemSlashBlade.IsBroken.set(brokenBladeWhite.getTagCompound(), true);
		ForgeRegistries.RECIPES.register(new RecipeUpgradeBlade(new ResourceLocation(SlashBlade.modid,"slashblade"), new ItemStack(BladeLoader.weapon),
                " IS",
                "IBC",
                "#G ",
                'C', SlashBlade.getCustomBlade(SlashBlade.SphereBladeSoulStr),
                'I', "ingotSteel",
                'B', new ItemStack(ItemLoader.BLADE, 1, 2),
                'G', new OreWildcardIngredient("toolForginghammer"),
                'S', SlashBlade.wrapBlade,
                '#', new BladeIngredient(brokenBladeWhite)
        ).setRegistryName(new ResourceLocation(SlashBlade.modid,"slashblade")));
        SlashBlade.addRecipe("flammpfeil.slashblade.named.yamato",
                new RecipeAwakeBladeTLS(new ResourceLocation(SlashBlade.modid,"yamato"), "root",
                SlashBlade.getCustomBlade("flammpfeil.slashblade.named.yamato"),
                SlashBlade.getCustomBlade("flammpfeil.slashblade.named.yamato.reqired"),
                "XXX",
                "XBX",
                "XXX",
                'X', SlashBlade.getCustomBlade(SlashBlade.SphereBladeSoulStr),
                'B', SlashBlade.getCustomBlade("flammpfeil.slashblade.named.yamato.reqired")));
        SlashBlade.addRecipe("flammpfeil.slashblade.named.agito",
                new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,"agito"),
                SlashBlade.getCustomBlade("flammpfeil.slashblade.named.agito"),
                SlashBlade.getCustomBlade("flammpfeil.slashblade.named.reqired"),
                " X ",
                "XBX",
                " X ",
                'X', SlashBlade.getCustomBlade(SlashBlade.ProudSoulStr),
                'B', SlashBlade.getCustomBlade("flammpfeil.slashblade.named.reqired")));
        SlashBlade.addRecipe("flammpfeil.slashblade.named.orotiagito",
                new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,"agito_ex"),
        		SlashBlade.getCustomBlade("flammpfeil.slashblade.named.orotiagito"),
                SlashBlade.getCustomBlade("flammpfeil.slashblade.named.orotiagito.reqired"),
                "PXP",
                "XBX",
                "PXP",
                'X', SlashBlade.getCustomBlade(SlashBlade.SphereBladeSoulStr),
                'P', SlashBlade.getCustomBlade(SlashBlade.ProudSoulStr),
                'B', SlashBlade.getCustomBlade("flammpfeil.slashblade.named.orotiagito.reqired")));
        SlashBlade.addRecipe("flammpfeil.slashblade.named.orotiagito.seald",
                new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,"agito2"),
        		SlashBlade.getCustomBlade("flammpfeil.slashblade.named.orotiagito.seald"),
                SlashBlade.getCustomBlade("flammpfeil.slashblade.named.orotiagito.seald.reqired"),
                " X ",
                "XBX",
                " X ",
                'X',SlashBlade.getCustomBlade(SlashBlade.ProudSoulStr),
                'B',SlashBlade.getCustomBlade("flammpfeil.slashblade.named.orotiagito.seald.reqired")));
        ItemStack tagayasanreqiredBlade = new ItemStack(SlashBlade.bladeWood);
        NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(tagayasanreqiredBlade);
        ItemSlashBlade.KillCount.set(reqTag,1000);

        SlashBlade.addRecipe("flammpfeil.slashblade.named.tagayasan",
                new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,"tagayasan"),
                SlashBlade.getCustomBlade("flammpfeil.slashblade.named.tagayasan"),
                tagayasanreqiredBlade,
                "XEX",
                "PBP",
                "XEX",
                'X', SlashBlade.getCustomBlade(SlashBlade.SphereBladeSoulStr),
                'B', tagayasanreqiredBlade,
                'P', new ItemStack(Items.ENDER_PEARL),
                'E', new ItemStack(Items.ENDER_EYE)));
        SlashBlade.addRecipe("flammpfeil.slashblade.named.yuzukitukumo",
                new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,"tukumo"),
                SlashBlade.getCustomBlade("flammpfeil.slashblade.named.yuzukitukumo"),
                SlashBlade.getCustomBlade("flammpfeil.slashblade.thousandkill"),
                "ESD",
                "RBL",
                "ISG",
                'E', "blockEmerald",
                'D', "blockDiamond",
                'R', "blockRedstone",
                'L', "blockLapis",
                'I', "blockIron",
                'G', "blockGold",
                'S', SlashBlade.findItemStack(SlashBlade.modid, SlashBlade.SphereBladeSoulStr, 1),
                'B', SlashBlade.getCustomBlade("flammpfeil.slashblade.thousandkill")));
	}
}
