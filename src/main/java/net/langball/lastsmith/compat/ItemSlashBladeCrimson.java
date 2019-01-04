package net.langball.lastsmith.compat;

import java.util.List;

import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.items.IWarpingGear;

public class ItemSlashBladeCrimson extends ItemSlashBladeNamedSS implements IWarpingGear{

	public ItemSlashBladeCrimson(ToolMaterial par2EnumToolMaterial, float baseAttackModifiers) {
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
	        target.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 120));
	      }
	      catch (Exception localException) {}
	    }
	    return super.hitEntity(is, target, hitter);
	  }
	  
	  public int getWarp(ItemStack itemstack, EntityPlayer player)
	  {
	    return 2;
	  }
	  
	  @SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	  public void addInformation(ItemStack stack, World worldIn, List tooltip, ITooltipFlag flagIn)
	  {
	    tooltip.add(TextFormatting.GOLD +  I18n.format("enchantment.special.sapgreat"));
	    super.addInformation(stack, worldIn, tooltip, flagIn);
	  }
}
