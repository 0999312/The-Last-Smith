package net.langball.lastsmith.items;

import net.langball.lastsmith.CommonProxy;
import net.langball.lastsmith.Last_worker;
import net.langball.lastsmith.util.JSON_Creator;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemLoader {
	public static Item crashed_iron = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"crashed_iron");
	public static Item tamahagane = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"tamahagane");
	public static Item steel_ingot = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"steel_ingot");
	public static Item wooden_blade = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"wooden_blade");
	
	public static Item blade_unfinished = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"blade_unfinished");
	public static Item blade_2_unfinished = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"blade_2_unfinished");
	public static Item blade_3_unfinished = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"blade_3_unfinished");
	
	public static Item blade_unfinished_2 = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"blade_unfinished_2");
	public static Item blade_2_unfinished_2 = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"blade_2_unfinished_2");
	public static Item blade_3_unfinished_2 = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"blade_3_unfinished_2");
	public static Item blade_unfinished_3 = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"blade_unfinished_3");
	public static Item blade_2_unfinished_3 = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"blade_2_unfinished_3");
	public static Item blade_3_unfinished_3 = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"blade_3_unfinished_3");
	public static Item blade_unfinished_5 = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"blade_unfinished_5");
	public static Item blade_2_unfinished_5 = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"blade_2_unfinished_5");
	public static Item blade_3_unfinished_5 = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"blade_3_unfinished_5");
	public static Item blade_unfinished_4 = new ItemHeattedBlade(blade_unfinished_5).setUnlocalizedName(Last_worker.MODID+'.'+"blade_unfinished_4");
	public static Item blade_2_unfinished_4 = new ItemHeattedBlade(blade_2_unfinished_5).setUnlocalizedName(Last_worker.MODID+'.'+"blade_2_unfinished_4");
	public static Item blade_3_unfinished_4 = new ItemHeattedBlade(blade_3_unfinished_5).setUnlocalizedName(Last_worker.MODID+'.'+"blade_3_unfinished_4");
	
	
	public static Item blade = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"blade");
	public static Item blade_2 = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"blade_2");
	public static Item blade_3 = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"blade_3");
	public static Item yakibatsuchi = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"yakibatsuchi");
	public static Item coal_dust = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"coal_dust");
	public static Item sakura = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"sakura");
	public static Item sakura_full = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"sakura_full");
	public static Item sakura_ingot_unfinished = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"sakura_ingot_unfinished");
	public static Item sakura_ingot = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"sakura_ingot");
	
	public static Item paper_arthurs = new Item().setUnlocalizedName(Last_worker.MODID+'.'+"paper_arthurs");
	
	public static Item hammer = new ItemHammer();
	
	public ItemLoader(FMLPreInitializationEvent event) {
		register(hammer);
		register(crashed_iron);
		register(tamahagane);
		register(steel_ingot);
		register(coal_dust);
		register(yakibatsuchi);
		register(sakura);
		register(sakura_full);
		register(sakura_ingot_unfinished);
		register(sakura_ingot);
		register(paper_arthurs);	
		register(wooden_blade);
		
		register(blade_unfinished);
		register(blade_2_unfinished);
		register(blade_3_unfinished);
		register(blade_unfinished_2);
		register(blade_2_unfinished_2);
		register(blade_3_unfinished_2);
		register(blade_unfinished_3);
		register(blade_2_unfinished_3);
		register(blade_3_unfinished_3);
		register(blade_unfinished_5);
		register(blade_2_unfinished_5);
		register(blade_3_unfinished_5);
		register(blade_unfinished_4);
		register(blade_2_unfinished_4);
		register(blade_3_unfinished_4);

		register(blade);
		register(blade_2);
		register(blade_3);
		OreDictionary.registerOre("ingotSteel", steel_ingot);
		OreDictionary.registerOre("dustCoal", coal_dust);
		OreDictionary.registerOre("gemCoal", new ItemStack(Items.COAL,1,0));
		OreDictionary.registerOre("gemCoal", new ItemStack(Items.COAL,1,1));
		OreDictionary.registerOre("ingotSakura", sakura_ingot);
	}
	@SideOnly(Side.CLIENT)
	public static void registerRender() {
		registerRender(hammer);
		registerRender(sakura);
		registerRender(sakura_full);
		registerRender(sakura_ingot);
		registerRender(sakura_ingot_unfinished);
		registerRender(crashed_iron);
		registerRender(tamahagane);
		registerRender(coal_dust);
		registerRender(steel_ingot);
		registerRender(wooden_blade);
		registerRender(blade_unfinished);
		registerRender(blade_2_unfinished);
		registerRender(blade_3_unfinished);
		registerRender(paper_arthurs);
		registerRender(blade);
		registerRender(blade_2);
		registerRender(blade_3);
		registerRender(yakibatsuchi);
		registerRender(blade_unfinished_2);
		registerRender(blade_2_unfinished_2);
		registerRender(blade_3_unfinished_2);
		registerRender(blade_unfinished_3);
		registerRender(blade_2_unfinished_3);
		registerRender(blade_3_unfinished_3);
		registerRender(blade_unfinished_4);
		registerRender(blade_2_unfinished_4);
		registerRender(blade_3_unfinished_4);
		registerRender(blade_unfinished_5);
		registerRender(blade_2_unfinished_5);
		registerRender(blade_3_unfinished_5);
	}
	private static void register(Item item)
    {
		item.setCreativeTab(CommonProxy.tab);
        ForgeRegistries.ITEMS.register(item.setRegistryName(item.getUnlocalizedName().substring(5+Last_worker.MODID.length()+1)));
    }
	@SideOnly(Side.CLIENT)
    private static void registerRender(Item item,String textureName)
    {
		JSON_Creator.genItem(item.getRegistryName().toString().substring(10), textureName, "json_create");
        ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, model);
    }
	@SideOnly(Side.CLIENT)
    private static void registerRender(Item item)
    {
        ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, model);
    }

}
