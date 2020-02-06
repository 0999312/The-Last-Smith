package cn.mmf.lastsmith.block;

import cn.mmf.lastsmith.CommonProxy;
import cn.mmf.lastsmith.TLSMain;
import cn.mmf.lastsmith.util.JSON_Creator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLoader {
	   public static Block Sakura_Steel_Block = new Block(Material.IRON).setHardness(3F);
	   public BlockLoader() {
	        Sakura_Steel_Block.setHarvestLevel("pickaxe", 2);
	        register(Sakura_Steel_Block,new ItemBlock(Sakura_Steel_Block),"sakura_steel_block");
	    }

	   @SideOnly(Side.CLIENT)
	   public static void registerRenders() {
		   registerRender(Sakura_Steel_Block);
	   }

	   private static void register(Block block, Item itemBlock, String string) {
			block.setRegistryName(string);
			block.setUnlocalizedName(TLSMain.MODID+"."+string);
			block.setCreativeTab(CommonProxy.tab);
			ForgeRegistries.BLOCKS.register(block);
			if (itemBlock != null) {
				itemBlock.setRegistryName(string);
				itemBlock.setUnlocalizedName(TLSMain.MODID+"."+string);
				ForgeRegistries.ITEMS.register(itemBlock);
			}
	   }

	   @SideOnly(Side.CLIENT)
	   public static void registerRender(Block block) {
		   ModelResourceLocation model = new ModelResourceLocation(TLSMain.MODID + ":" + block.getRegistryName().getResourcePath(), "inventory");
		   ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, model);
	   }

	   @SideOnly(Side.CLIENT)
	   public static void registerRender(Block block, String name) {
		   JSON_Creator.genBlock(block.getRegistryName().toString().substring(1 + TLSMain.MODID.length()), name, "json_create");
		   ModelResourceLocation model = new ModelResourceLocation(block.getRegistryName(), "inventory");
		   ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, model);
	   }
}
