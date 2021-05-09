package cn.mmf.lastsmith.blades.vanilla;

import cn.mcmod_mmf.mmlib.util.OreWildcardIngredient;
import cn.mmf.lastsmith.TLSConfig;
import cn.mmf.lastsmith.blades.BladeLoader;
import cn.mmf.lastsmith.event.RegisterSlashBladeEvent;
import cn.mmf.lastsmith.event.RegisterSlashBladeRecipeEvent;
import cn.mmf.lastsmith.item.ItemLoader;
import cn.mmf.lastsmith.recipe.RecipeAwakeBladeTLS;
import cn.mmf.lastsmith.recipe.RecipeKiriSayaTLS;
import cn.mmf.lastsmith.util.BladeUtil;
import cn.mmf.slashblade_addon.SJAP;
import cn.mmf.slashblade_addon.item.ItemSlashBladeRF;
import cn.mmf.slashblade_addon.item.ItemSlashBladeWind;
import cn.mmf.slashblade_addon.recipes.RecipeNihil;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.RecipeUpgradeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.item.crafting.BladeIngredient;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
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
	private static String[] defaultBewitched = new String[]{
			"flammpfeil.slashblade.named.tagayasan",
			"flammpfeil.slashblade.named.crimsoncherry",
			"flammpfeil.slashblade.named.nihilex",
			"flammpfeil.slashblade.named.nihilul",
			"flammpfeil.slashblade.named.nihilbx",
			"flammpfeil.slashblade.named.yamato",
			
			"flammpfeil.slashblade.named.sange",
			"flammpfeil.slashblade.named.orotiagito",
			"flammpfeil.slashblade.named.kirisaya",
			
			"flammpfeil.slashblade.named.kamuy.water",
			"flammpfeil.slashblade.named.kamuy.fire",
			"flammpfeil.slashblade.named.kamuy.lightning",
	};
	@SubscribeEvent
	public static void onSlashBladeRegister(RegisterSlashBladeEvent event) {

		SlashBlade.BladeRegistry.forEach((name, blade) -> {
			if (!(blade.getItem() instanceof ItemSlashBladeNamed))
				return;
			NBTTagCompound oldNBT = ItemSlashBlade.getItemTagCompound(blade);
			ItemStack newBlade = new ItemStack(BladeLoader.bladeNamed);

			if (Loader.isModLoaded(SJAP.MODID)) {
				if (blade.getItem() instanceof ItemSlashBladeRF) {
					newBlade = new ItemStack(BladeLoader.rfblade);
					BladeUtil.getInstance().ModelOnName.set(oldNBT, ItemSlashBladeNamed.ModelName.get(oldNBT));
					BladeUtil.getInstance().TextureOnName.set(oldNBT, ItemSlashBladeNamed.TextureName.get(oldNBT));
					newBlade.setTagCompound(oldNBT);
					SlashBlade.BladeRegistry.put(name, newBlade);
					return;
				}
				if (Loader.isModLoaded(Thaumcraft.MODID) && blade.getItem() instanceof ItemSlashBladeWind)
					newBlade = new ItemStack(BladeLoader.windBlade);
			}
			for(String bewitched : defaultBewitched) {
				if (ItemSlashBladeNamed.CurrentItemName.get(oldNBT)
						.equalsIgnoreCase(bewitched))
					BladeUtil.getInstance().IsBewitchedActived.set(oldNBT, true);
			}
			newBlade.setTagCompound(oldNBT);
			SlashBlade.BladeRegistry.put(name, newBlade);
		});
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {	
		if (!TLSConfig.advanced_mode)
			return;
		
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid, "recipexes"),
				new ItemStack(SlashBlade.bladeWood), new Object[] { "  S", " B ", "#  ", '#', "logWood", 'B',
						new ItemStack(ItemLoader.MATERIALS, 1, 0), 'S', BladeLoader.wrapper, })
								.setRegistryName(new ResourceLocation(SlashBlade.modid, "recipexes")));
		
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid, "bamboolight"),
				new ItemStack(SlashBlade.bladeBambooLight),
				new Object[] { " PS", "PSP", "#P ", '#', new ItemStack(SlashBlade.bladeWood), 
						'P', "bamboo", 'S', "logWood" })
								.setRegistryName(new ResourceLocation(SlashBlade.modid, "bamboolight")));
		
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid, "bamboolight"),
				new ItemStack(SlashBlade.bladeBambooLight),
				new Object[] { " PS", "PBP", "#P ", '#', "logWood", 'B',
						new ItemStack(ItemLoader.MATERIALS, 1, 1), 'P', "bamboo", 'S', BladeLoader.wrapper_bamboo, })
								.setRegistryName(new ResourceLocation(SlashBlade.modid, "bamboolight_1")));
		
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid, "white"),
				new ItemStack(SlashBlade.bladeWhiteSheath, 1, 70 / 3),
				new Object[] { "  W", " B ", "LSH", 'W', BladeLoader.wrapper, 'B',
						new ItemStack(ItemLoader.BLADE, 1, 0), 'L', new ItemStack(SlashBlade.bladeWood, 1), 'H',
						new OreWildcardIngredient("toolForginghammer"), 'S', "ingotIron" })
								.setRegistryName(new ResourceLocation(SlashBlade.modid, "white")));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid, "white2"),
				new ItemStack(SlashBlade.bladeWhiteSheath, 1, 70 / 4),
				new Object[] { "  W", " B ", "LSH", 'W', BladeLoader.wrapper, 'B',
						new ItemStack(ItemLoader.BLADE, 1, 1), 'L', new ItemStack(SlashBlade.bladeWood, 1), 'H',
						new OreWildcardIngredient("toolForginghammer"), 'S', "ingotIron" })
								.setRegistryName(new ResourceLocation(SlashBlade.modid, "white2")));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid, "white3"),
				new ItemStack(SlashBlade.bladeWhiteSheath, 1),
				new Object[] { "  W", " B ", "LSH", 'W', BladeLoader.wrapper, 'B',
						new ItemStack(ItemLoader.BLADE, 1, 2), 'L', new ItemStack(SlashBlade.bladeWood, 1), 'H',
						new OreWildcardIngredient("toolForginghammer"), 'S', "ingotIron" })
								.setRegistryName(new ResourceLocation(SlashBlade.modid, "white3")));
		
		ItemStack brokenBladeWhite = new ItemStack(SlashBlade.bladeWhiteSheath, 1, 0);
		brokenBladeWhite.setItemDamage(brokenBladeWhite.getMaxDamage());
		ItemSlashBlade.IsBroken.set(brokenBladeWhite.getTagCompound(), true);
		ForgeRegistries.RECIPES.register(new RecipeUpgradeBlade(new ResourceLocation(SlashBlade.modid, "slashblade"),
				new ItemStack(BladeLoader.weapon), " IS", "IBC", "#G ", 'C',
				SlashBlade.getCustomBlade(SlashBlade.SphereBladeSoulStr), 'I', "ingotSteel", 'B',
				new ItemStack(ItemLoader.BLADE, 1, 2), 'G', new OreWildcardIngredient("toolForginghammer"), 'S',
				SlashBlade.wrapBlade, '#', new BladeIngredient(brokenBladeWhite))
						.setRegistryName(new ResourceLocation(SlashBlade.modid, "slashblade")));
		SlashBlade.addRecipe("flammpfeil.slashblade.named.yamato",
				new RecipeAwakeBladeTLS(new ResourceLocation(SlashBlade.modid, "yamato"), "bewitched_blade",
						SlashBlade.getCustomBlade("flammpfeil.slashblade.named.yamato"),
						SlashBlade.getCustomBlade("flammpfeil.slashblade.named.yamato.reqired"), "XXX", "XBX", "XXX",
						'X', SlashBlade.getCustomBlade(SlashBlade.SphereBladeSoulStr), 'B',
						SlashBlade.getCustomBlade("flammpfeil.slashblade.named.yamato.reqired")));
		SlashBlade.addRecipe("flammpfeil.slashblade.named.agito",
				new RecipeAwakeBladeTLS(new ResourceLocation(SlashBlade.modid, "agito"),"bewitched_blade",
						SlashBlade.getCustomBlade("flammpfeil.slashblade.named.agito"),
						SlashBlade.getCustomBlade("flammpfeil.slashblade.named.agito.reqired"), " X ", "XBX", " X ",
						'X', SlashBlade.getCustomBlade(SlashBlade.ProudSoulStr), 'B',
						SlashBlade.getCustomBlade("flammpfeil.slashblade.named.agito.reqired")));
		SlashBlade.addRecipe("flammpfeil.slashblade.named.orotiagito",
				new RecipeAwakeBladeTLS(new ResourceLocation(SlashBlade.modid, "agito_ex"),"bewitched_blade",
						SlashBlade.getCustomBlade("flammpfeil.slashblade.named.orotiagito"),
						SlashBlade.getCustomBlade("flammpfeil.slashblade.named.orotiagito.reqired"), "PXP", "XBX",
						"PXP", 'X', SlashBlade.getCustomBlade(SlashBlade.SphereBladeSoulStr), 'P',
						SlashBlade.getCustomBlade(SlashBlade.ProudSoulStr), 'B',
						SlashBlade.getCustomBlade("flammpfeil.slashblade.named.orotiagito.reqired")));
		SlashBlade.addRecipe("flammpfeil.slashblade.named.orotiagito.seald",
				new RecipeAwakeBladeTLS(new ResourceLocation(SlashBlade.modid, "agito2"),"bewitched_blade",
						SlashBlade.getCustomBlade("flammpfeil.slashblade.named.orotiagito.seald"),
						SlashBlade.getCustomBlade("flammpfeil.slashblade.named.orotiagito.seald.reqired"), " X ", "XBX",
						" X ", 'X', SlashBlade.getCustomBlade(SlashBlade.ProudSoulStr), 'B',
						SlashBlade.getCustomBlade("flammpfeil.slashblade.named.orotiagito.seald.reqired")));
		ItemStack tagayasanreqiredBlade = new ItemStack(SlashBlade.bladeWood);
		NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(tagayasanreqiredBlade);
		ItemSlashBlade.KillCount.set(reqTag, 1000);

		SlashBlade.addRecipe("flammpfeil.slashblade.named.tagayasan",
				new RecipeAwakeBladeTLS(new ResourceLocation(SlashBlade.modid, "tagayasan"), "bewitched_blade",
						SlashBlade.getCustomBlade("flammpfeil.slashblade.named.tagayasan"), tagayasanreqiredBlade,
						"XEX", "PBP", "XEX", 'X', SlashBlade.getCustomBlade(SlashBlade.SphereBladeSoulStr), 'B',
						tagayasanreqiredBlade, 'P', new ItemStack(Items.ENDER_PEARL), 'E',
						new ItemStack(Items.ENDER_EYE)));
		SlashBlade.addRecipe("flammpfeil.slashblade.named.yuzukitukumo",
				new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid, "tukumo"),
						SlashBlade.getCustomBlade("flammpfeil.slashblade.named.yuzukitukumo"),
						new ItemStack(BladeLoader.weapon), "ESD", "RBL", "ISG", 'E',
						"blockEmerald", 'D', "blockDiamond", 'R', "blockRedstone", 'L', "blockLapis", 'I', "blockIron",
						'G', "blockGold", 'S',
						SlashBlade.findItemStack(SlashBlade.modid, SlashBlade.SphereBladeSoulStr, 1), 'B',
						SlashBlade.getCustomBlade("flammpfeil.slashblade.thousandkill")));

	}
	@SubscribeEvent
	public static void initSJAPBlades(RegisterSlashBladeRecipeEvent event) {
		if (!Loader.isModLoaded(SJAP.MODID))
			return;
			if(!Loader.isModLoaded("twilightforest")) {
				ItemStack darkraven = SlashBlade.getCustomBlade("flammpfeil.slashblade.named.darkraven");
				ItemStack doutanuki = SlashBlade.getCustomBlade("flammpfeil.slashblade.named.doutanuki");
				SlashBlade.addRecipe("flammpfeil.slashblade.named.darkraven",
					new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade", 
						"flammpfeil.slashblade.named.darkraven"), darkraven, doutanuki,
						new Object[] {
							" FQ", "SQ ", "B  ",
							Character.valueOf('Q'), "blockCoal",
							Character.valueOf('F'), new ItemStack(Items.FEATHER),
							Character.valueOf('S'), "dyeBlack",
							Character.valueOf('B'), doutanuki 
				}));
			}
			if (!TLSConfig.advanced_mode)
				return;
	       ItemStack sphere = SlashBlade.findItemStack(SlashBlade.modid, SlashBlade.SphereBladeSoulStr, 1);
	        ItemStack blade_base = SlashBlade.getCustomBlade("flammpfeil.slashblade.named.kamuy.base");
	        ItemStack blade_water = SlashBlade.getCustomBlade("flammpfeil.slashblade.named.kamuy.water");
	        ItemStack blade_fire = SlashBlade.getCustomBlade("flammpfeil.slashblade.named.kamuy.fire");
	        ItemStack blade_lightning = SlashBlade.getCustomBlade("flammpfeil.slashblade.named.kamuy.lightning");
	        
			ItemStack reqblade_base = new ItemStack(SlashBlade.weapon);
			NBTTagCompound reqtag_base = ItemSlashBlade.getItemTagCompound(reqblade_base);
			ItemSlashBlade.RepairCount.set(reqtag_base, 5);
			ItemSlashBlade.KillCount.set(reqtag_base, 1000);
			ItemSlashBlade.ProudSoul.set(reqtag_base, 1000);
			reqblade_base.addEnchantment(Enchantments.LOOTING, 1);
			SlashBlade.addRecipe("flammpfeil.slashblade.named.kamuy.base", new RecipeAwakeBladeTLS(new ResourceLocation("flammpfeil.slashblade","flammpfeil.slashblade.named.kamuy.base"),"bewitched_blade", blade_base, reqblade_base, new Object[]{
					"SQS","IKI","SBS",'S', sphere,'K', reqblade_base,'Q', "gemQuartz",'I', "blockIron",'B', Items.BOOK
			}));
			ItemStack reqblade = SlashBlade.getCustomBlade("flammpfeil.slashblade.named.kamuy.base");
			NBTTagCompound reqtag = ItemSlashBlade.getItemTagCompound(reqblade);
			ItemSlashBlade.RepairCount.set(reqtag, 20);
			ItemSlashBlade.KillCount.set(reqtag, 2000);
			ItemSlashBlade.ProudSoul.set(reqtag, 5000);
			SlashBlade.addRecipe("flammpfeil.slashblade.named.kamuy.water", new RecipeAwakeBladeTLS(new ResourceLocation("flammpfeil.slashblade","flammpfeil.slashblade.named.kamuy.water"),"bewitched_blade", blade_water, reqblade, new Object[]{
					"S8S","4K6","S2S",
					'S', sphere,'K', reqblade,'8',"blockLapis",'4', Blocks.ICE,'6', Blocks.SNOW,'2', Items.WATER_BUCKET
			}));
			SlashBlade.addRecipe("flammpfeil.slashblade.named.kamuy.fire", new RecipeAwakeBladeTLS(new ResourceLocation("flammpfeil.slashblade","flammpfeil.slashblade.named.kamuy.fire"),"bewitched_blade", blade_fire, reqblade, new Object[]{
					"S8S","4K6","S2S",
					'S', sphere,'K', reqblade,'8',"blockRedstone",'4',Items.FIRE_CHARGE,'6', Items.BLAZE_ROD,'2', Items.LAVA_BUCKET
			}));
			SlashBlade.addRecipe("flammpfeil.slashblade.named.kamuy.lightning", new RecipeAwakeBladeTLS(new ResourceLocation("flammpfeil.slashblade","flammpfeil.slashblade.named.kamuy.lightning"),"bewitched_blade", blade_lightning, reqblade, new Object[]{
					"S8S","4K6","S2S",
					'S', sphere,'K', reqblade,'8',"blockIron",'4', "blockGold",'6', "blockDiamond",'2', "blockEmerald"
			}));
			
		    ItemStack sphere1 = SlashBlade.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
		    ItemSlashBlade.SpecialAttackType.set(sphere1.getTagCompound(), Integer.valueOf(0));
		    ItemStack blade = SlashBlade.getCustomBlade("flammpfeil.slashblade.named.kirisaya");
		    ItemStack reqiredBlade = new ItemStack(SlashBlade.wrapBlade);
		    NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
		    ItemSlashBlade.RepairCount.set(tag, Integer.valueOf(1));
		    ItemSlashBlade.KillCount.set(tag, Integer.valueOf(1000));
		    ItemSlashBlade.ProudSoul.set(tag, Integer.valueOf(20000));
		    reqiredBlade.addEnchantment(Enchantments.SHARPNESS, 3);
		    reqiredBlade.addEnchantment(Enchantments.POWER, 3);
		    
		    SlashBlade.addRecipe("flammpfeil.slashblade.named.kirisaya", new RecipeKiriSayaTLS(new ResourceLocation("flammpfeil.slashblade","flammpfeil.slashblade.named.kirisaya"),"sharpness", blade, reqiredBlade, sphere1, new Object[]{
		    		"DGD", "ZBZ", "GDG", Character.valueOf('G'), new ItemStack(Items.GOLDEN_APPLE, 1, 1), Character.valueOf('D'), new ItemStack(Items.RECORD_11), Character.valueOf('B'), reqiredBlade, Character.valueOf('Z'), sphere1 
		    }));
		    
	}
	@SubscribeEvent
	public static void initNihilRecipes(RegisterSlashBladeRecipeEvent event) {
		if (!Loader.isModLoaded(SJAP.MODID))
			return;
		if (!TLSConfig.advanced_mode)
			return;
		final String namenl = "flammpfeil.slashblade.named.nihil";
		final String nameex = "flammpfeil.slashblade.named.nihilex";
		final String nameul = "flammpfeil.slashblade.named.nihilul";
		final String namebx = "flammpfeil.slashblade.named.nihilbx";
		final String namecc = "flammpfeil.slashblade.named.crimsoncherry";
		ItemStack nihil = SlashBlade.getCustomBlade(namenl);
		ItemStack nihilex = SlashBlade.getCustomBlade(nameex);
		ItemStack nihilul = SlashBlade.getCustomBlade(nameul);
		ItemStack nihilcc = SlashBlade.getCustomBlade(namecc);
		ItemStack nihilbx = SlashBlade.getCustomBlade(namebx);

		ItemStack sphere = SlashBlade.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
		ItemStack ingot = SlashBlade.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1);

		ItemStack reqblade_nl = new ItemStack(SlashBlade.weapon);
		reqblade_nl.setItemDamage(Short.MAX_VALUE);

		SlashBlade.addRecipe(namenl, new RecipeAwakeBladeTLS(new ResourceLocation("flammpfeil.slashblade", namenl),
				"bewitched_blade", nihil,reqblade_nl, new Object[] { "SIS", "IBI", "SIS", 'I', ingot, 'S', sphere, 'B', reqblade_nl }));
		ItemStack reqblade_ex = SlashBlade.getCustomBlade(namenl);
		NBTTagCompound tag_ex = ItemSlashBlade.getItemTagCompound(reqblade_ex);
		ItemSlashBlade.RepairCount.set(tag_ex, Integer.valueOf(1));
		ItemSlashBlade.KillCount.set(tag_ex, Integer.valueOf(1000));
		ItemSlashBlade.ProudSoul.set(tag_ex, Integer.valueOf(1000));
		if (tag_ex.hasKey("ench")) {
			tag_ex.removeTag("ench");
		}
		SlashBlade.addRecipe(nameex,
				new RecipeAwakeBladeTLS(new ResourceLocation("flammpfeil.slashblade", nameex),
						"sharpness", nihilex, reqblade_ex,
						new Object[] {
								"SNS", "IBI", "SDS", 'S', sphere, 'I', ingot, 'B', reqblade_ex, 'N',
								Items.NETHER_STAR, 'D', "blockDiamond" 
								}));
		ItemStack reqblade_ul = SlashBlade.getCustomBlade(nameex);
		NBTTagCompound tag_ul = ItemSlashBlade.getItemTagCompound(reqblade_ul);
		ItemStack yamato = SlashBlade.getCustomBlade("flammpfeil.slashblade.named.yamato");
		ItemSlashBlade.RepairCount.set(tag_ul, Integer.valueOf(3));
		ItemSlashBlade.KillCount.set(tag_ul, Integer.valueOf(3000));
		ItemSlashBlade.ProudSoul.set(tag_ul, Integer.valueOf(6500));
		if (tag_ul.hasKey("ench")) {
			tag_ul.removeTag("ench");
		}
		SlashBlade.addRecipe(nameul,
				new RecipeNihil(new ResourceLocation("flammpfeil.slashblade", nameul), nihilul, reqblade_ul, 1, 1,
						yamato, 1, 2, true, new Object[] { "SNS", "DBD", "SYS", 'S', SlashBlade.weapon, 'Y', yamato,
								'B', reqblade_ul, 'N', Items.NETHER_STAR, 'D', "blockDiamond" }));
		ItemStack reqblade_cc = SlashBlade.getCustomBlade(nameex);
		NBTTagCompound tag_cc = ItemSlashBlade.getItemTagCompound(reqblade_cc);
		ItemSlashBlade.RepairCount.set(tag_cc, Integer.valueOf(3));
		ItemSlashBlade.KillCount.set(tag_cc, Integer.valueOf(3000));
		ItemSlashBlade.ProudSoul.set(tag_cc, Integer.valueOf(6500));
		if (tag_cc.hasKey("ench")) {
			tag_cc.removeTag("ench");
		}
		SlashBlade.addRecipe(namecc,
				new RecipeNihil(new ResourceLocation("flammpfeil.slashblade", namecc), nihilcc, reqblade_cc, 1, 1,
						nihil, 1, 0, false,
						new Object[] { "DSD", "DMD", "DDD", 'S', nihil, 'M', reqblade_cc, 'D', "blockDiamond" }));

		SlashBlade.addRecipe(namebx,
				new RecipeNihil(new ResourceLocation("flammpfeil.slashblade", namebx), nihilbx, nihilul, 0, 1, nihilcc,
						2, 1, false, new Object[] { "DDD", "ACB", "DDD", 'A', nihilul, 'B', nihilcc, 'C',
								SlashBlade.weapon, 'D', "blockDiamond" }));
	}
}
