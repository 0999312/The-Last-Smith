package net.langball.lastsmith.compat;

import java.util.List;

import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.items.IWarpingGear;

public class ItemSlashBladeVoid extends ItemSlashBladeNamedSS implements IWarpingGear{

	public ItemSlashBladeVoid(ToolMaterial par2EnumToolMaterial, float baseAttackModifiers) {
		super(par2EnumToolMaterial, baseAttackModifiers);
		// TODO Auto-generated constructor stub
	}
	 public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
	  {
	    super.onUpdate(stack, world, entity, p_77663_4_, p_77663_5_);
	    if ((stack.isItemDamaged()) && (entity != null) && (entity.ticksExisted % 20 == 0) && ((entity instanceof EntityLivingBase))) {
	      stack.damageItem(-1, (EntityLivingBase)entity);
	    }
	  }
	  
	  public boolean hitEntity(ItemStack is, EntityLivingBase target, EntityLivingBase hitter)
	  {
	    if ((!target.world.isRemote) && (
	      (!(target instanceof EntityPlayer)) || (!(hitter instanceof EntityPlayer)) || (FMLCommonHandler.instance().getMinecraftServerInstance().isPVPEnabled()))) {
	      try
	      {
	        target.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 60));
	      }
	      catch (Exception localException) {}
	    }
	    return super.hitEntity(is, target, hitter);
	  }
	  
	  public int getWarp(ItemStack itemstack, EntityPlayer player)
	  {
	    return 1;
	  }
	@Override
	public void addInformation(ItemStack arg0, World arg1, List arg2, ITooltipFlag arg3) {
		arg2.add(TextFormatting.GOLD + I18n.translateToLocal("enchantment.special.sapless"));
		super.addInformation(arg0, arg1, arg2, arg3);
	}
	  

}
