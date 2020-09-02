package cn.mmf.lastsmith.block;

import cn.mcmod_mmf.mmlib.register.BlockRegister;
import cn.mmf.lastsmith.TLSMain;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class BlockLoader {
	public static Block Sakura_Steel_Block = new Block(Material.IRON).setHardness(3F);
	private static final BlockLoader instance = new BlockLoader();
	private BlockLoader() {
	}
	public static BlockLoader getInstance() {
		return instance;
	}
	public void register() {
		Sakura_Steel_Block.setHarvestLevel("pickaxe", 2);
		BlockRegister.getInstance().register(TLSMain.MODID, Sakura_Steel_Block, new ItemBlock(Sakura_Steel_Block),
				"sakura_steel_block");
	}

	@SideOnly(Side.CLIENT)
	public void registerRenders() {
		BlockRegister.getInstance().registerRender(Sakura_Steel_Block);
	}

}
