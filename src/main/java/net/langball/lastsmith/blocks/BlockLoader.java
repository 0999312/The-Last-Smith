package net.langball.lastsmith.blocks;

import net.langball.lastsmith.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.GameData;

public class BlockLoader {
	
	   public static Block Blast = new BlockBlast(false).setRegistryName("blast_off").setUnlocalizedName("blast").setCreativeTab(CommonProxy.tab);
	   public static Block Blast_on = new BlockBlast(true).setRegistryName("blast_on").setLightLevel(0.875F).setUnlocalizedName("blast");
	   public static Item BlastItem = new ItemBlock(Blast).setRegistryName("blast_off").setUnlocalizedName("blast");
	 
	   public BlockLoader(FMLPreInitializationEvent event)
	    {
	        register(Blast,BlastItem);
	        register(Blast_on,null);
	    }

	    @SideOnly(Side.CLIENT)
	    public static void registerRenders()
	    {
	        registerRender(Blast);
	        registerRender(Blast_on);
	    }

	    private static void register(Block block,Item itemBlock)
	    {
	    	ForgeRegistries.BLOCKS.register(block);
	    	if(itemBlock!=null){
	        ForgeRegistries.ITEMS.register(itemBlock);
	        }
	    	GameData.getBlockItemMap().put(block, itemBlock);
	    }

	    @SideOnly(Side.CLIENT)
	    private static void registerRender(Block block)
	    {
	        ModelResourceLocation model = new ModelResourceLocation(block.getRegistryName(), "inventory");
	        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, model);
	    }
}
