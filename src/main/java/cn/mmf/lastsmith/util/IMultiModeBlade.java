package cn.mmf.lastsmith.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public interface IMultiModeBlade {

	default int getMode(ItemStack stack) {

		return !stack.hasTagCompound() ? 0 : stack.getTagCompound().getInteger("Mode");
	}

	default boolean setMode(ItemStack stack, int mode) {

		if (getNumModes(stack) <= 1) {
			return false;
		}
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		if (mode < getNumModes(stack)) {
			stack.getTagCompound().setInteger("Mode", mode);
			return true;
		}
		return false;
	}

	default boolean incrMode(ItemStack stack) {

		if (getNumModes(stack) <= 1) {
			return false;
		}
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		int curMode = getMode(stack);
		curMode++;
		if (curMode >= getNumModes(stack)) {
			curMode = 0;
		}
		stack.getTagCompound().setInteger("Mode", curMode);
		return true;
	}

	default boolean decrMode(ItemStack stack) {

		if (getNumModes(stack) <= 1) {
			return false;
		}
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		int curMode = getMode(stack);
		curMode--;
		if (curMode <= 0) {
			curMode = getNumModes(stack) - 1;
		}
		stack.getTagCompound().setInteger("Mode", curMode);
		return true;
	}

	default int getNumModes(ItemStack stack) {
		return 2;
	}

	default void onModeChange(EntityPlayer player, ItemStack stack) {

	}

}
