package cn.mmf.lastsmith.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemHammer extends Item {

	public ItemHammer() {
		super();
		this.setMaxDamage(256);
	}
	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		 int dmg = itemStack.getItemDamage();
		 if (dmg < this.getMaxDamage(itemStack)) {
			 ItemStack stack = itemStack.copy();
			 stack.setItemDamage(dmg+1);
	         return stack;
	        }
		return super.getContainerItem(itemStack);
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		  int dmg = stack.getItemDamage();
		  if (dmg < this.getMaxDamage(stack)) {
	            return true;
	        }
		return super.hasContainerItem(stack);
	}
}
