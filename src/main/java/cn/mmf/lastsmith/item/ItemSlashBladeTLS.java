package cn.mmf.lastsmith.item;

import java.util.EnumSet;
import java.util.List;

import cn.mcmod_mmf.mmlib.util.StringUtil;
import cn.mmf.lastsmith.event.TLSSlashBladeHooks;
import cn.mmf.lastsmith.util.BladeUtil;
import mods.flammpfeil.slashblade.ability.JustGuard;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.network.MessageRangeAttack.RangeAttackState;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import mods.flammpfeil.slashblade.util.SilentUpdateItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSlashBladeTLS extends ItemSlashBlade {
	public ItemSlashBladeTLS(ToolMaterial par2EnumToolMaterial, float defaultBaseAttackModifier) {
		super(par2EnumToolMaterial, defaultBaseAttackModifier);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    @SideOnly(Side.CLIENT)
	public void addInformation(ItemStack arg0, World arg1, List arg2, ITooltipFlag arg3) {
		NBTTagCompound nbt = getItemTagCompound(arg0);
		if(BladeUtil.getname(nbt) != null){
			arg2.add(TextFormatting.GOLD + I18n.format("blades.crafter")+":"+TextFormatting.GRAY+BladeUtil.getname(nbt));	
		}else{
			arg2.add(TextFormatting.GOLD + I18n.format("blades.crafter")+":"+TextFormatting.GRAY+I18n.format("lastsmith.name.unnamed_smith"));		
		}
		if (StringUtil.getInstance().isAltKeyDown()) {
			super.addInformation(arg0, arg1, arg2, arg3);
		}else{
			arg2.add(I18n.format("lastsmith.info.hold_alt_for_details"));
		}
	}
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		super.onCreated(stack, worldIn, playerIn);
		NBTTagCompound nbt = stack.getTagCompound();
		BladeUtil.setPlayer(nbt,playerIn);
	}
	@Override
	public void onUpdate(ItemStack arg0, World arg1, Entity arg2, int arg3, boolean arg4) {
		TLSSlashBladeHooks.onUpdateHooks(arg0, arg1, arg2, arg3, arg4);
		super.onUpdate(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack itemStackIn, EntityPlayer playerIn, Entity arg2) {
		NBTTagCompound tag = getItemTagCompound(itemStackIn);
		ComboSequence comboSeq = getComboSequence(tag);
		if (TLSSlashBladeHooks.onDoAttack(playerIn, itemStackIn, getNextComboSeq(itemStackIn, comboSeq, false, playerIn))) {
    		playerIn.swingProgressInt = 0;
    		playerIn.swingProgress = 0.0f;
    		playerIn.isSwingInProgress = false;
			return true;
		}
		return super.onLeftClickEntity(itemStackIn, playerIn, arg2);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		ItemStack itemStackIn = playerIn.getHeldItem(hand);
		if (hand == EnumHand.OFF_HAND) {
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
		}

		JustGuard.setJustGuardState(playerIn);

		NBTTagCompound tag = getItemTagCompound(itemStackIn);
		ComboSequence comboSeq = getComboSequence(tag);
		if (!TLSSlashBladeHooks.onDoAttack(playerIn, itemStackIn,comboSeq)) {
			nextAttackSequence(itemStackIn, comboSeq, playerIn);
			SilentUpdateItem.silentUpdateItem(playerIn, hand);
		}else {
			doSwingItem(itemStackIn, playerIn);
		}
		playerIn.setActiveHand(hand);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void doChargeAttack(ItemStack stack, EntityPlayer par3EntityPlayer, boolean isJust) {
		SpecialAttackBase sa = getSpecialAttack(stack);
		if (TLSSlashBladeHooks.onDoSpacialAttack(isJust, par3EntityPlayer, stack, sa))
			return;
		super.doChargeAttack(stack, par3EntityPlayer, isJust);
	}

	@Override
	public void doRangeAttack(ItemStack item, EntityLivingBase entity, RangeAttackState mode) {
		if (entity instanceof EntityPlayer)
			if (TLSSlashBladeHooks.onPlayerDoRangeAttack((EntityPlayer) entity, item, mode))
				return;
		super.doRangeAttack(item, entity, mode);
	}

	@Override
	public EnumSet<SwordType> getSwordType(ItemStack itemStack) {
		NBTTagCompound tag = getItemTagCompound(itemStack);
		EnumSet<SwordType> set = super.getSwordType(itemStack);
		if (!BladeUtil.IsBewitchedActived.get(tag)) {
			set.remove(SwordType.Bewitched);
			set.remove(SwordType.SoulEeater);
		}
		return set;
	}
}
