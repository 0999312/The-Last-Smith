package cn.mmf.lastsmith.item;

import java.util.List;

import cn.mmf.lastsmith.CommonProxy;
import cn.mmf.lastsmith.TLSMain;
import cn.mmf.lastsmith.util.JSON_Creator;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemLoader {
	public static ItemBase MATERIALS = new ItemBase("materials", 64, new String[]{
		TLSMain.MODID+"."+"wooden_blade",
		TLSMain.MODID+"."+"bamboo_blade",
		TLSMain.MODID+"."+"yakibatsuchi",	
		TLSMain.MODID+"."+"sakura",
		TLSMain.MODID+"."+"sakura_full",
		TLSMain.MODID+"."+"sakura_ingot",
		TLSMain.MODID+"."+"sakura_sphere"
		}
	);
	public static ItemBase SCROLL = new ItemBase("scroll", 1, new String[]{
			TLSMain.MODID+"."+"scroll_namedblade_1",
			TLSMain.MODID+"."+"scroll_namedblade_2",
			TLSMain.MODID+"."+"scroll_namedblade_3",
			TLSMain.MODID+"."+"scroll_bamboo_1",
			TLSMain.MODID+"."+"scroll_bamboo_2",
			TLSMain.MODID+"."+"scroll_sakura",
			TLSMain.MODID+"."+"scroll_bewitched",
			TLSMain.MODID+"."+"scroll_requiem",
			TLSMain.MODID+"."+"scroll_aoi",
			TLSMain.MODID+"."+"scroll_tuki",
			TLSMain.MODID+"."+"scroll_ghost",
			TLSMain.MODID+"."+"scroll_blood",
			TLSMain.MODID+"."+"scroll_shapeness",
			TLSMain.MODID+"."+"scroll_nether",
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
	public static ItemBase BLADE = new ItemBase("blade",1,
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
	}
	public static void renderItem() {
		if(!Loader.isModLoaded("sakura")){
			registerRender(hammer);
		}
		registerRender(SCROLL);
		registerRender(MATERIALS);
		registerRender(BLADE);
		registerRender(BLADE_HEATTED);
	}
    public static void register(Item item) {
        item.setCreativeTab(CommonProxy.tab);
        ForgeRegistries.ITEMS.register(item.setRegistryName(item.getUnlocalizedName().substring(5 + TLSMain.MODID.length() + 1)));
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(ItemBase item) {
        registerRender(item, false);
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(ItemBase item, boolean json_create) {
        for (int i = 0; i < item.getSubNames().length; i++) {
            String name = item.getSubNames()[i].substring(TLSMain.MODID.length() + 1);
            if (json_create) JSON_Creator.genItem(name, name, "json_create");
            ModelResourceLocation model = new ModelResourceLocation(new ResourceLocation(TLSMain.MODID, name), "inventory");
            ModelLoader.setCustomModelResourceLocation(item, i, model);
        }
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item, int meta, String name) {
        ModelResourceLocation model = new ModelResourceLocation(name, "inventory");
        ModelLoader.setCustomModelResourceLocation(item, meta, model);
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item, int meta, String name, String textureName) {
        JSON_Creator.genItem(name, textureName, "json_create");
        ModelResourceLocation model = new ModelResourceLocation(name, "inventory");
        ModelLoader.setCustomModelResourceLocation(item, meta, model);
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item, String textureName) {
        JSON_Creator.genItem(item.getRegistryName().toString().substring(TLSMain.MODID.length() + 1), textureName, "json_create");
        ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, model);
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item) {
        ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, model);
    }
}
