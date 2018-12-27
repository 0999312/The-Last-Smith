package net.langball.lastsmith.items;

import net.langball.lastsmith.CommonProxy;
import net.langball.lastsmith.Last_worker;
import net.langball.lastsmith.util.JSON_Creator;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemLoader {
	public static Item hammer = new ItemHammer();
	public static ItemBase material = new ItemBase("itemMaterials", 64,
			Last_worker.MODID+'.'+"crashed_iron",//0
			Last_worker.MODID+'.'+"tamahagane",//1
			Last_worker.MODID+'.'+"steel_ingot",//2
			Last_worker.MODID+'.'+"wooden_blade",//3
			Last_worker.MODID+'.'+"bamboo_blade",//4
			Last_worker.MODID+'.'+"yakibatsuchi",//5
			Last_worker.MODID+'.'+"coal_dust",//6
			Last_worker.MODID+'.'+"sakura",//7
			Last_worker.MODID+'.'+"sakura_full",//8
			Last_worker.MODID+'.'+"sakura_ingot_unfinished",//9
			Last_worker.MODID+'.'+"sakura_ingot",//10
			Last_worker.MODID+'.'+"paper_arthurs"//11
			);
	public static ItemBase blade = new ItemBase("itemBlade",1,
			Last_worker.MODID+'.'+"blade",
			Last_worker.MODID+'.'+"blade_2",
			Last_worker.MODID+'.'+"blade_3",
			Last_worker.MODID+'.'+"blade_unfinished",
			Last_worker.MODID+'.'+"blade_2_unfinished",
			Last_worker.MODID+'.'+"blade_3_unfinished",
			Last_worker.MODID+'.'+"blade_unfinished_2",
			Last_worker.MODID+'.'+"blade_2_unfinished_2",
			Last_worker.MODID+'.'+"blade_3_unfinished_2",
			Last_worker.MODID+'.'+"blade_unfinished_3",
			Last_worker.MODID+'.'+"blade_2_unfinished_3",
			Last_worker.MODID+'.'+"blade_3_unfinished_3",
			Last_worker.MODID+'.'+"blade_unfinished_5",
			Last_worker.MODID+'.'+"blade_2_unfinished_5",
			Last_worker.MODID+'.'+"blade_3_unfinished_5"
			);
	public static ItemBase blade_heatted = new ItemHeattedBlade(
			new String[]{
					Last_worker.MODID+'.'+"blade_unfinished_4",
					Last_worker.MODID+'.'+"blade_2_unfinished_4",
					Last_worker.MODID+'.'+"blade_3_unfinished_4"
			},
			new ItemStack(blade,1,12),
			new ItemStack(blade,1,13),
			new ItemStack(blade,1,14)
			);
	public ItemLoader(FMLPreInitializationEvent event) {
		register(hammer);
		register(material);
		register(blade);
		register(blade_heatted);
		OreDictionary.registerOre("ingotSteel",  new ItemStack(material,1,2));
		OreDictionary.registerOre("dustCoal", new ItemStack(material,1,6));
		OreDictionary.registerOre("gemCoal", new ItemStack(Items.COAL,1,0));
		OreDictionary.registerOre("gemCoal", new ItemStack(Items.COAL,1,1));
		OreDictionary.registerOre("ingotSakura",  new ItemStack(material,1,10));
	}
	@SideOnly(Side.CLIENT)
	public static void registerRender() {
		registerRender(hammer);
		registerRender(material);
		registerRender(blade);
		registerRender(blade_heatted);
	
	}
	private static void register(Item item)
    {
		item.setCreativeTab(CommonProxy.tab);
        ForgeRegistries.ITEMS.register(item.setRegistryName(item.getUnlocalizedName().substring(5+Last_worker.MODID.length()+1)));
    }
    @SideOnly(Side.CLIENT)
    private static void registerRender(ItemBase item)
    {
    	for(int i = 0;i<item.getSubNames().length;i++){
    		String name = item.getSubNames()[i].substring(10);
            ModelResourceLocation model = new ModelResourceLocation(new ResourceLocation(Last_worker.MODID,name), "inventory");
            ModelLoader.setCustomModelResourceLocation(item, i, model);
    	}

    }
    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item, int meta, String name)
    {
        ModelResourceLocation model = new ModelResourceLocation(name, "inventory");
        ModelLoader.setCustomModelResourceLocation(item, meta, model);
    }
    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item, int meta, String name,String textureName)
    {
    	JSON_Creator.genItem(name, textureName, "json_create");
        ModelResourceLocation model = new ModelResourceLocation(name, "inventory");
        ModelLoader.setCustomModelResourceLocation(item, meta, model);
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
