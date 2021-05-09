package cn.mmf.lastsmith.blades.compat.tfc;

import static net.dries007.tfc.util.forge.ForgeRule.*;
import static net.dries007.tfc.util.skills.SmithingSkill.Type.*;

import cn.mcmod_mmf.mmlib.util.OreWildcardIngredient;
import cn.mcmod_mmf.mmlib.util.RecipesUtil;
import cn.mmf.lastsmith.TLSMain;
import cn.mmf.lastsmith.blades.BladeLoader;
import cn.mmf.lastsmith.event.RegisterSlashBladeRecipeEvent;
import cn.mmf.lastsmith.item.ItemLoader;
import mods.flammpfeil.slashblade.SlashBlade;
import net.dries007.tfc.api.recipes.WeldingRecipe;
import net.dries007.tfc.api.recipes.anvil.AnvilRecipe;
import net.dries007.tfc.api.recipes.heat.HeatRecipe;
import net.dries007.tfc.api.recipes.heat.HeatRecipeSimple;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.dries007.tfc.util.forge.ForgeRule;
import net.dries007.tfc.util.skills.SmithingSkill;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Optional.Method;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber
public class TFCCompat {
	@SubscribeEvent
	@Method(modid="tfc")
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		if (!Loader.isModLoaded("tfc"))
			return;
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID, "lastsmith.handmade_red_steel",
				new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid, "handmade_red_steel"),
				BladeLoader.getInstance().getCustomBlade("lastsmith.handmade_red_steel"),
				new Object[] { "  W", " B ", "LSH", 'W', BladeLoader.wrapper, 'B',
						new ItemStack(ItemLoader.BLADE, 1, 23), 'L', "logWood", 'H',
						new OreWildcardIngredient("toolForginghammer"), 'S', new OreWildcardIngredient("saw") }));
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID, "lastsmith.handmade_blue_steel",
				new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid, "handmade_blue_steel"),
				BladeLoader.getInstance().getCustomBlade("lastsmith.handmade_blue_steel"),
				new Object[] { "  W", " B ", "LSH", 'W', BladeLoader.wrapper, 'B',
						new ItemStack(ItemLoader.BLADE, 1, 24), 'L', "logWood", 'H',
						new OreWildcardIngredient("toolForginghammer"), 'S', new OreWildcardIngredient("saw") }));
		if (Loader.isModLoaded("thaumcraft")){
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID, "lastsmith.handmade_thaum",
				new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid, "handmade_thaum"),
				BladeLoader.getInstance().getCustomBlade("lastsmith.handmade_thaum"),
				new Object[] { "  W", " B ", "LSH", 'W', BladeLoader.wrapper, 'B',
						new ItemStack(ItemLoader.BLADE, 1, 33), 'L', "logWood", 'H',
						new OreWildcardIngredient("toolForginghammer"), 'S', new OreWildcardIngredient("saw") }));
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID, "lastsmith.handmade_void",
				new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid, "handmade_void"),
				BladeLoader.getInstance().getCustomBlade("lastsmith.handmade_void"),
				new Object[] { "  W", " B ", "LSH", 'W', BladeLoader.wrapper, 'B',
						new ItemStack(ItemLoader.BLADE, 1, 34), 'L', "logWood", 'H',
						new OreWildcardIngredient("toolForginghammer"), 'S', new OreWildcardIngredient("saw") }));
		}
	}
	@SubscribeEvent
	@Method(modid="tfc")
	public static void registerMetal(RegistryEvent.Register<Metal> event) {
		if (!Loader.isModLoaded("tfc"))
			return;
		for(int i = 0;i <15;i++)
		cn.mcmod_mmf.mmlib.compat.TFCCompat.getInstance().addMetalItemInfo(new ItemStack(ItemLoader.BLADE,1,i), "wrought_iron", 200, true);
		for(int i = 0;i <3;i++)
		cn.mcmod_mmf.mmlib.compat.TFCCompat.getInstance().addMetalItemInfo(new ItemStack(ItemLoader.BLADE_HEATTED,1,i), "wrought_iron", 200, true);
		
		cn.mcmod_mmf.mmlib.compat.TFCCompat.getInstance().addMetalItemInfo(SlashBlade.getCustomBlade(SlashBlade.IngotBladeSoulStr), "steel", 200, true);
		
		cn.mcmod_mmf.mmlib.compat.TFCCompat.getInstance().addMetalItemInfo(new ItemStack(ItemLoader.BLADE,1,15), "red_steel", 200, true);
		cn.mcmod_mmf.mmlib.compat.TFCCompat.getInstance().addMetalItemInfo(new ItemStack(ItemLoader.BLADE,1,17), "red_steel", 200, true);
		cn.mcmod_mmf.mmlib.compat.TFCCompat.getInstance().addMetalItemInfo(new ItemStack(ItemLoader.BLADE,1,19), "red_steel", 200, true);
		cn.mcmod_mmf.mmlib.compat.TFCCompat.getInstance().addMetalItemInfo(new ItemStack(ItemLoader.BLADE,1,21), "red_steel", 200, true);
		cn.mcmod_mmf.mmlib.compat.TFCCompat.getInstance().addMetalItemInfo(new ItemStack(ItemLoader.BLADE,1,23), "red_steel", 200, true);
		cn.mcmod_mmf.mmlib.compat.TFCCompat.getInstance().addMetalItemInfo(new ItemStack(ItemLoader.BLADE_HEATTED,1,3), "red_steel", 200, true);
		
		cn.mcmod_mmf.mmlib.compat.TFCCompat.getInstance().addMetalItemInfo(new ItemStack(ItemLoader.BLADE,1,16), "blue_steel", 200, true);
		cn.mcmod_mmf.mmlib.compat.TFCCompat.getInstance().addMetalItemInfo(new ItemStack(ItemLoader.BLADE,1,18), "blue_steel", 200, true);
		cn.mcmod_mmf.mmlib.compat.TFCCompat.getInstance().addMetalItemInfo(new ItemStack(ItemLoader.BLADE,1,20), "blue_steel", 200, true);
		cn.mcmod_mmf.mmlib.compat.TFCCompat.getInstance().addMetalItemInfo(new ItemStack(ItemLoader.BLADE,1,22), "blue_steel", 200, true);
		cn.mcmod_mmf.mmlib.compat.TFCCompat.getInstance().addMetalItemInfo(new ItemStack(ItemLoader.BLADE,1,24), "blue_steel", 200, true);
		cn.mcmod_mmf.mmlib.compat.TFCCompat.getInstance().addMetalItemInfo(new ItemStack(ItemLoader.BLADE_HEATTED,1,4), "blue_steel", 200, true);
	}
	@SubscribeEvent
	@Method(modid="tfc")
	public static void registerAnvil(RegistryEvent.Register<AnvilRecipe> event) {
		if (!Loader.isModLoaded("tfc"))
			return;
		addAnvil(event.getRegistry(), "blade", new ItemStack(ItemLoader.BLADE,1,12), new ItemStack(ItemLoader.BLADE,1,0), Metal.Tier.TIER_III, WEAPONS,
				UPSET_LAST, PUNCH_SECOND_LAST, PUNCH_THIRD_LAST);
		addAnvil(event.getRegistry(), "blade_1", new ItemStack(ItemLoader.BLADE,1,13), new ItemStack(ItemLoader.BLADE,1,1), Metal.Tier.TIER_III, WEAPONS,
				UPSET_LAST, PUNCH_SECOND_LAST, PUNCH_THIRD_LAST);
		addAnvil(event.getRegistry(), "blade_2", new ItemStack(ItemLoader.BLADE,1,14), new ItemStack(ItemLoader.BLADE,1,2), Metal.Tier.TIER_III, WEAPONS,
				UPSET_LAST, PUNCH_SECOND_LAST, PUNCH_THIRD_LAST);
		
		addAnvil(event.getRegistry(), "blade_unfinished", IIngredient.of(OreDictionaryHelper.toString("ingot", "double", "wrought_iron")), new ItemStack(ItemLoader.BLADE,1,3), Metal.Tier.TIER_III, WEAPONS,
				UPSET_LAST, BEND_SECOND_LAST, PUNCH_THIRD_LAST);
		addAnvil(event.getRegistry(), "blade_unfinished_1", IIngredient.of(OreDictionaryHelper.toString("ingot", "double", "steel")), new ItemStack(ItemLoader.BLADE,1,4), Metal.Tier.TIER_III, WEAPONS,
				UPSET_LAST, BEND_SECOND_LAST, PUNCH_THIRD_LAST);
		addAnvil(event.getRegistry(), "blade_unfinished_2", IIngredient.of(OreDictionaryHelper.toString("ingot", "double", "black_steel")), new ItemStack(ItemLoader.BLADE,1,5), Metal.Tier.TIER_III, WEAPONS,
				PUNCH_LAST, BEND_SECOND_LAST, PUNCH_THIRD_LAST);
		
		addAnvil(event.getRegistry(), "blade_red_steel", new ItemStack(ItemLoader.BLADE,1,12), new ItemStack(ItemLoader.BLADE,1,0), Metal.Tier.TIER_VI, WEAPONS,
				UPSET_LAST, PUNCH_SECOND_LAST, PUNCH_THIRD_LAST);
		addAnvil(event.getRegistry(), "blade_blue_steel", new ItemStack(ItemLoader.BLADE,1,13), new ItemStack(ItemLoader.BLADE,1,1), Metal.Tier.TIER_VI, WEAPONS,
				UPSET_LAST, PUNCH_SECOND_LAST, PUNCH_THIRD_LAST);
		
		addAnvil(event.getRegistry(), "blade_red_steel_unfinished", IIngredient.of(OreDictionaryHelper.toString("ingot", "double", "red_steel")), new ItemStack(ItemLoader.BLADE,1,15), Metal.Tier.TIER_VI, WEAPONS,
				UPSET_LAST, BEND_SECOND_LAST, PUNCH_THIRD_LAST);
		addAnvil(event.getRegistry(), "blade_blue_steel_unfinished", IIngredient.of(OreDictionaryHelper.toString("ingot", "double", "blue_steel")), new ItemStack(ItemLoader.BLADE,1,16), Metal.Tier.TIER_VI, WEAPONS,
				UPSET_LAST, BEND_SECOND_LAST, PUNCH_THIRD_LAST);
		
		
	}
	@SubscribeEvent
	@Method(modid="tfc")
	public static void registerHeat(RegistryEvent.Register<HeatRecipe> event) {
		if (!Loader.isModLoaded("tfc"))
			return;
		addHeat(event.getRegistry(), new ItemStack(ItemLoader.BLADE,1,9), new ItemStack(ItemLoader.BLADE_HEATTED,1,0), 500);
		addHeat(event.getRegistry(), new ItemStack(ItemLoader.BLADE,1,10), new ItemStack(ItemLoader.BLADE_HEATTED,1,1), 500);
		addHeat(event.getRegistry(), new ItemStack(ItemLoader.BLADE,1,11), new ItemStack(ItemLoader.BLADE_HEATTED,1,2), 500);
		
		addHeat(event.getRegistry(), SlashBlade.getCustomBlade(SlashBlade.IngotBladeSoulStr), SlashBlade.getCustomBlade(SlashBlade.SphereBladeSoulStr), 1539);
	}
	@SubscribeEvent
	@Method(modid="tfc")
	public static void registerWelding(RegistryEvent.Register<WeldingRecipe> event) {
		if (!Loader.isModLoaded("tfc"))
			return;
		addWelding(event.getRegistry(), new ItemStack(ItemLoader.BLADE,1,3),
				SlashBlade.getCustomBlade(SlashBlade.IngotBladeSoulStr), 
				new ItemStack(ItemLoader.BLADE,1,6), Metal.Tier.TIER_III);
		addWelding(event.getRegistry(), new ItemStack(ItemLoader.BLADE,1,4),
				SlashBlade.getCustomBlade(SlashBlade.IngotBladeSoulStr), 
				new ItemStack(ItemLoader.BLADE,1,7), Metal.Tier.TIER_III);
		addWelding(event.getRegistry(), new ItemStack(ItemLoader.BLADE,1,5),
				SlashBlade.getCustomBlade(SlashBlade.IngotBladeSoulStr), 
				new ItemStack(ItemLoader.BLADE,1,8), Metal.Tier.TIER_III);
		
		addWelding(event.getRegistry(), new ItemStack(ItemLoader.BLADE,1,15),
				SlashBlade.getCustomBlade(SlashBlade.IngotBladeSoulStr), 
				new ItemStack(ItemLoader.BLADE,1,17), Metal.Tier.TIER_VI);
		addWelding(event.getRegistry(), new ItemStack(ItemLoader.BLADE,1,16),
				SlashBlade.getCustomBlade(SlashBlade.IngotBladeSoulStr), 
				new ItemStack(ItemLoader.BLADE,1,18), Metal.Tier.TIER_VI);
	}
	@Method(modid="tfc")
	private static void addAnvil(IForgeRegistry<AnvilRecipe> registry,String name,ItemStack input, ItemStack output, Metal.Tier tier, SmithingSkill.Type skillType, ForgeRule... rules) {
		registry.register(new AnvilRecipe(new ResourceLocation(TLSMain.MODID, name), IIngredient.of(input), output, tier, skillType, rules));
	}
	@Method(modid="tfc")
	private static void addAnvil(IForgeRegistry<AnvilRecipe> registry,String name, IIngredient<ItemStack> input, ItemStack output, Metal.Tier tier, SmithingSkill.Type skillType, ForgeRule... rules) {
		registry.register(new AnvilRecipe(new ResourceLocation(TLSMain.MODID, name), input, output, tier, skillType, rules));
	}
	@Method(modid="tfc")
	private static void addHeat(IForgeRegistry<HeatRecipe> registry, ItemStack input, ItemStack output, float temp) {
		registry.register(new HeatRecipeSimple(IIngredient.of(input), output, temp).setRegistryName(TLSMain.MODID, input.getUnlocalizedName().substring(TLSMain.MODID.length()+6)));
	}
	@Method(modid="tfc")
	private static void addWelding(IForgeRegistry<WeldingRecipe> registry, ItemStack input1,ItemStack input2, ItemStack output, Metal.Tier tier) {
		registry.register(new WeldingRecipe(new ResourceLocation(TLSMain.MODID, (output.getUnlocalizedName().substring(TLSMain.MODID.length()+6)).toLowerCase()), IIngredient.of(input1), IIngredient.of(input2), output, tier, null));

	}
}
