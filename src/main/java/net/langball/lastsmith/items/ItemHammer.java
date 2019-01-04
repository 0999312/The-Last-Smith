package net.langball.lastsmith.items;

import java.util.List;

import net.langball.lastsmith.CommonProxy;
import net.langball.lastsmith.Last_worker;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemHammer extends Item {
	public String name;
public ItemHammer() {
	this.setCreativeTab(CommonProxy.tab);
	this.setUnlocalizedName(Last_worker.MODID+".smith_hammer");
	this.setContainerItem(this);
}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.ITALIC + I18n.format("smith_hammer.name"));
		tooltip.add(TextFormatting.ITALIC + I18n.format("smith_hammer.name"+".ps"));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
