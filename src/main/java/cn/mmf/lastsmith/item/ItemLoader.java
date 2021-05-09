package cn.mmf.lastsmith.item;

import java.util.List;

import cn.mcmod_mmf.mmlib.item.ItemBase;
import cn.mcmod_mmf.mmlib.register.ItemRegister;
import cn.mcmod_mmf.mmlib.util.StringUtil;
import cn.mmf.lastsmith.CommonProxy;
import cn.mmf.lastsmith.TLSMain;
import cn.mmf.lastsmith.block.BlockLoader;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemLoader {
	public static ItemBase MATERIALS = new ItemBase(TLSMain.MODID,"materials", 64, new String[]{
		"wooden_blade",
		"bamboo_blade",
		"yakibatsuchi",	
		"sakura",
		"sakura_full",
		"sakura_ingot",
		"sakura_sphere"
		}
	);
	public static ItemBase SCROLL = new ItemBase(TLSMain.MODID,"scroll", 1, new String[]{
		"scroll_namedblade_1",//0
		"scroll_namedblade_2",
		"scroll_namedblade_3",
		"scroll_bamboo_1",
		"scroll_bamboo_2",
		"scroll_sakura",//5
		"scroll_bewitched",//6
		"scroll_requiem",//7
		"scroll_aoi",//8
		"scroll_tuki",//9
		"scroll_ghost",//10
		"scroll_blood",//11
		"scroll_shapeness",//12
		"scroll_nether",//13
		"scroll_nihil",//14
		"scroll_thaumium",//15
		"scroll_crimson",
		"scroll_creait",
		"scroll_tech",
		"scroll_highpowered"
		}
	){
		@Override
		@SideOnly(Side.CLIENT)
		public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
			if(getSubNames()!=null) {
				StringBuilder builder = new StringBuilder("tooltip.");
				String str[] = StringUtil.getInstance().localizeFormat(builder.append(getSubNames()[stack.getMetadata()]).append(".name").toString())
						.split("<BR>");
				for(String localized :str) {
				String subName = stack.getMetadata() < getSubNames().length?localized: "tooltip.null";
				tooltip.add(subName);
				}
			}
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}

	};
	public static ItemBase BLADE = new ItemBase(TLSMain.MODID,"blade",1,
		"blade",
		"blade_2",
		"blade_3",//2
		"blade_unfinished",
		"blade_2_unfinished",
		"blade_3_unfinished",//5
		"blade_unfinished_2",
		"blade_2_unfinished_2",
		"blade_3_unfinished_2",//8
		"blade_unfinished_3",
		"blade_2_unfinished_3",
		"blade_3_unfinished_3",//11
		"blade_unfinished_5",
		"blade_2_unfinished_5",
		"blade_3_unfinished_5",//14
		"blade_red_steel_unfinished",//15
		"blade_blue_steel_unfinished",//16
		"blade_red_steel_unfinished_2",
		"blade_blue_steel_unfinished_2",
		"blade_red_steel_unfinished_3",
		"blade_blue_steel_unfinished_3",
		"blade_red_steel_unfinished_5",//21
		"blade_blue_steel_unfinished_5",//22
		"blade_red_steel",
		"blade_blue_steel",//24
		
		"blade_thaum_unfinished",//25
		"blade_void_unfinished",//26
		"blade_thaum_unfinished_2",//27
		"blade_void_unfinished_2",//28
		"blade_thaum_unfinished_3",//29
		"blade_void_unfinished_3",
		"blade_thaum_unfinished_5",//31
		"blade_void_unfinished_5",//32
		"blade_thaum",
		"blade_void"
	);
	public static ItemBase BLADE_HEATTED = new ItemHeattedBlade(
		new String[]{
				"blade_unfinished_4",//0
				"blade_2_unfinished_4",
				"blade_3_unfinished_4",
				"blade_red_steel_unfinished_4",
				"blade_blue_steel_unfinished_4",
				"blade_thaum_unfinished_4",
				"blade_void_unfinished_4"
		},
		new ItemStack(BLADE,1,12),
		new ItemStack(BLADE,1,13),
		new ItemStack(BLADE,1,14),
		new ItemStack(BLADE,1,21),
		new ItemStack(BLADE,1,22),
		new ItemStack(BLADE,1,31),
		new ItemStack(BLADE,1,22)
	);
	public static Item hammer = new ItemHammer().setUnlocalizedName(TLSMain.MODID+'.'+"forging_hammer");
	private static final ItemLoader instance = new ItemLoader();
	private ItemLoader() {
	}
	public void register() {
		registerItem(MATERIALS);
		registerItem(SCROLL);
		registerItem(BLADE);
		registerItem(BLADE_HEATTED);
		if(!Loader.isModLoaded("sakura")){
			registerItem(hammer);
			OreDictionary.registerOre("toolForginghammer", new ItemStack(hammer,1,32767));
		}
		OreDictionary.registerOre("sphereSakura",  new ItemStack(MATERIALS,1,6));
		OreDictionary.registerOre("ingotSakura",  new ItemStack(MATERIALS,1,5));
		OreDictionary.registerOre("fullSakura",  new ItemStack(MATERIALS,1,4));
		OreDictionary.registerOre("leafSakura",  new ItemStack(MATERIALS,1,3));
		OreDictionary.registerOre("blockSakura",  new ItemStack(BlockLoader.Sakura_Steel_Block));
	}
	public void renderItem() {
		if(!Loader.isModLoaded("sakura")){
			ItemRegister.getInstance().registerRender(hammer);
		}
		ItemRegister.getInstance().registerRender(SCROLL);
		ItemRegister.getInstance().registerRender(MATERIALS);
		ItemRegister.getInstance().registerRender(BLADE);
		ItemRegister.getInstance().registerRender(BLADE_HEATTED);
	}
    public void registerItem(Item item) {
        item.setCreativeTab(CommonProxy.tab);
        ItemRegister.getInstance().register(TLSMain.MODID, item);
    }
	public static ItemLoader getInstance() {
		return instance;
	}

}
