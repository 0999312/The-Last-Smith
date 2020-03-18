package cn.mmf.lastsmith.item;

import java.util.List;

import cn.mcmod_mmf.mmlib.item.ItemBase;
import cn.mcmod_mmf.mmlib.register.ItemRegister;
import cn.mmf.lastsmith.CommonProxy;
import cn.mmf.lastsmith.TLSMain;
import cn.mmf.lastsmith.block.BlockLoader;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

public class ItemLoader {
	public static ItemBase MATERIALS = new ItemBase(TLSMain.MODID,"materials", 64, new String[]{
		TLSMain.MODID+"."+"wooden_blade",
		TLSMain.MODID+"."+"bamboo_blade",
		TLSMain.MODID+"."+"yakibatsuchi",	
		TLSMain.MODID+"."+"sakura",
		TLSMain.MODID+"."+"sakura_full",
		TLSMain.MODID+"."+"sakura_ingot",
		TLSMain.MODID+"."+"sakura_sphere"
		}
	);
	public static ItemBase SCROLL = new ItemBase(TLSMain.MODID,"scroll", 1, new String[]{
		TLSMain.MODID+"."+"scroll_namedblade_1",//0
		TLSMain.MODID+"."+"scroll_namedblade_2",
		TLSMain.MODID+"."+"scroll_namedblade_3",
		TLSMain.MODID+"."+"scroll_bamboo_1",
		TLSMain.MODID+"."+"scroll_bamboo_2",
		TLSMain.MODID+"."+"scroll_sakura",//5
		TLSMain.MODID+"."+"scroll_bewitched",//6
		TLSMain.MODID+"."+"scroll_requiem",//7
		TLSMain.MODID+"."+"scroll_aoi",//8
		TLSMain.MODID+"."+"scroll_tuki",//9
		TLSMain.MODID+"."+"scroll_ghost",//10
		TLSMain.MODID+"."+"scroll_blood",
		TLSMain.MODID+"."+"scroll_shapeness",
		TLSMain.MODID+"."+"scroll_nether",
		TLSMain.MODID+"."+"scroll_nihil",
		TLSMain.MODID+"."+"scroll_thaumium",
		TLSMain.MODID+"."+"scroll_crimson",
		TLSMain.MODID+"."+"scroll_creait",
		TLSMain.MODID+"."+"scroll_tech",
		TLSMain.MODID+"."+"scroll_highpowered"
		}
	){
		@Override
		public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
			if(getSubNames()!=null) {
				String subName = stack.getMetadata() < getSubNames().length?"tooltip."+getSubNames()[stack.getMetadata()]: "null";
				tooltip.add(subName);
			}
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}

	};
	public static ItemBase BLADE = new ItemBase(TLSMain.MODID,"blade",1,
		TLSMain.MODID+'.'+"blade",
		TLSMain.MODID+'.'+"blade_2",
		TLSMain.MODID+'.'+"blade_3",//2
		TLSMain.MODID+'.'+"blade_unfinished",
		TLSMain.MODID+'.'+"blade_2_unfinished",
		TLSMain.MODID+'.'+"blade_3_unfinished",//5
		TLSMain.MODID+'.'+"blade_unfinished_2",
		TLSMain.MODID+'.'+"blade_2_unfinished_2",
		TLSMain.MODID+'.'+"blade_3_unfinished_2",//8
		TLSMain.MODID+'.'+"blade_unfinished_3",
		TLSMain.MODID+'.'+"blade_2_unfinished_3",
		TLSMain.MODID+'.'+"blade_3_unfinished_3",//11
		TLSMain.MODID+'.'+"blade_unfinished_5",
		TLSMain.MODID+'.'+"blade_2_unfinished_5",
		TLSMain.MODID+'.'+"blade_3_unfinished_5"//14
	);
	public static ItemBase BLADE_HEATTED = new ItemHeattedBlade(
		new String[]{
				TLSMain.MODID+'.'+"blade_unfinished_4",
				TLSMain.MODID+'.'+"blade_2_unfinished_4",
				TLSMain.MODID+'.'+"blade_3_unfinished_4"
		},
		new ItemStack(BLADE,1,12),
		new ItemStack(BLADE,1,13),
		new ItemStack(BLADE,1,14)
	);
	public static Item hammer = new ItemHammer().setUnlocalizedName(TLSMain.MODID+'.'+"forging_hammer");
	public ItemLoader() {
		register(MATERIALS);
		register(SCROLL);
		register(BLADE);
		register(BLADE_HEATTED);
		if(!Loader.isModLoaded("sakura")){
			register(hammer);
			OreDictionary.registerOre("toolForginghammer", new ItemStack(hammer,1,32767));
		}
		OreDictionary.registerOre("sphereSakura",  new ItemStack(MATERIALS,1,6));
		OreDictionary.registerOre("ingotSakura",  new ItemStack(MATERIALS,1,5));
		OreDictionary.registerOre("fullSakura",  new ItemStack(MATERIALS,1,4));
		OreDictionary.registerOre("leafSakura",  new ItemStack(MATERIALS,1,3));
		OreDictionary.registerOre("blockSakura",  new ItemStack(BlockLoader.Sakura_Steel_Block));
	}
	public static void renderItem() {
		if(!Loader.isModLoaded("sakura")){
			ItemRegister.registerRender(hammer);
		}
		ItemRegister.registerRender(SCROLL);
		ItemRegister.registerRender(MATERIALS);
		ItemRegister.registerRender(BLADE);
		ItemRegister.registerRender(BLADE_HEATTED);
	}
    public static void register(Item item) {
        item.setCreativeTab(CommonProxy.tab);
        ItemRegister.register(TLSMain.MODID, item);
    }

}
