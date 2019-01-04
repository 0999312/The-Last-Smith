package net.langball.lastsmith;

import net.langball.lastsmith.blade.BladeLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabsSmith extends CreativeTabs {

	public CreativeTabsSmith() {
		super(Last_worker.MODID);
	}

	@Override
	public ItemStack getTabIconItem() {
		// TODO Auto-generated method stub
		return new ItemStack(BladeLoader.blade);
	}

}
