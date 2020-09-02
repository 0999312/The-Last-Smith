package cn.mmf.lastsmith;

import cn.mmf.lastsmith.blades.BladeLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public final class CreativeTabsTLS extends CreativeTabs {

	public CreativeTabsTLS() {
		super(TLSMain.MODID);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(BladeLoader.blade);
	}

}
