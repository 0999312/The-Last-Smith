package cn.mmf.lastsmith.item;

import cn.mmf.lastsmith.TLSMain;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemBase extends Item {
	protected String[] subNames;
	public Item containerItem;
	public ItemBase(String name, int stackSize, String... subNames) {
		this.setUnlocalizedName(TLSMain.MODID+"."+name);
		this.setHasSubtypes(subNames!=null&&subNames.length > 0);
		this.setMaxStackSize(stackSize);
		this.subNames = subNames!=null&&subNames.length > 0?subNames: null;
		this.setMaxDamage(0);
		this.setNoRepair();
	}
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
		if(this.isInCreativeTab(tab))
			if(getSubNames()!=null) {
				for(int i = 0; i < getSubNames().length; i++)
						list.add(new ItemStack(this, 1, i));
			}
			else list.add(new ItemStack(this));
	}

	public String[] getSubNames() {
		return subNames;
	}
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		if(getSubNames()!=null) {
			String subName = stack.getMetadata() < getSubNames().length?"item."+getSubNames()[stack.getMetadata()]: "";
			return subName;
		}
		return this.getUnlocalizedName();
	}

	public ItemBase setContainerItem(Item containerItem) {
		this.containerItem = containerItem;
		return this;
	}

}
