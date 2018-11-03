package net.langball.lastsmith.blade;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import mods.flammpfeil.slashblade.ItemSlashBladeDetune;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.TagPropertyAccessor;
import mods.flammpfeil.slashblade.util.ResourceLocationRaw;
import net.langball.lastsmith.items.ItemLoader;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemSlashBladeBasic extends ItemSlashBladeDetune{

	public ItemSlashBladeBasic(ToolMaterial par2EnumToolMaterial, float baseAttackModifiers) {
		super(par2EnumToolMaterial, baseAttackModifiers);
	}
	@Override
	public ResourceLocationRaw getModel() {
		return new ResourceLocationRaw(SlashBlade.modid, "model/named/yasha/yasha.obj");
	}
	
    @Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase,
			EntityLivingBase par3EntityLivingBase) {
		return super.hitEntity(par1ItemStack, par2EntityLivingBase, par3EntityLivingBase);
	}
	
    private static void dropItem(ItemStack itemStack, World world, EntityLivingBase entity)
	  {
	    if ((world.restoringBlockSnapshots) || (world.isRemote)) {
	      return;
	    }
	    float f = 0.5F;
	    double d0 = world.rand.nextFloat() * f + 0.25D;
	    double d1 = world.rand.nextFloat() * f + 0.25D;
	    double d2 = world.rand.nextFloat() * f + 0.25D;
	    

	    EntityItem entityItem = new EntityItem(world, entity.posX + d0, entity.posY + d1, entity.posZ + d2, itemStack);
	    entityItem.setDefaultPickupDelay();
	    world.spawnEntity(entityItem);
	  }
    
	@Override
	public void addInformation(ItemStack arg0, World arg1, List arg2, ITooltipFlag arg3) {
		super.addInformation(arg0, arg1, arg2, arg3);
		NBTTagCompound nbt = getOrCreateNbtData(arg0);
		if(isActive(arg0)){
			arg2.add(TextFormatting.GOLD + I18n.format("blades.crafter")+":"+TextFormatting.GRAY+getname(nbt));	
			}
	}

	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		super.onCreated(stack, worldIn, playerIn);
		NBTTagCompound nbt = stack.getTagCompound();
		setPlayer(nbt,playerIn);
		setActive(nbt, true);
	}
	public static boolean isActive(ItemStack stack)
	  {
	    NBTTagCompound nbt = getOrCreateNbtData(stack);
	    return isActive(nbt);
	  }
	private static String getname(ItemStack stack)
	  {
		NBTTagCompound nbt = getOrCreateNbtData(stack);
	    return getname(nbt);
	  }
	  private static String getname(NBTTagCompound nbt)
	  {
	    return nbt.getString("craftername");
	  }
	  private static void setActive(NBTTagCompound nbt, boolean active)
	  {
	    nbt.setBoolean("active", active);
	  }
	  private static void setPlayer(NBTTagCompound nbt, EntityPlayer playerIn)
	  {
		  nbt.setString("craftername", playerIn.getName().toString());
	  }
	  private static boolean isActive(NBTTagCompound nbt)
	  {
	    return nbt.getBoolean("active");
	  }
	 public static NBTTagCompound getOrCreateNbtData(ItemStack itemStack) {
	      NBTTagCompound ret = itemStack.getTagCompound();
	      if(ret == null) {
	         ret = new NBTTagCompound();
	         itemStack.setTagCompound(ret);
	      }
	      return ret;
	   }
}
