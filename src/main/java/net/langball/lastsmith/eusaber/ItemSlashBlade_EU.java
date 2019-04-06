package net.langball.lastsmith.eusaber;

import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.api.item.IItemHudInfo;

import java.util.LinkedList;
import java.util.List;

import mods.flammpfeil.slashblade.TagPropertyAccessor;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.util.ResourceLocationRaw;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.langball.lastsmith.util.IMultiModeBlade;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSlashBlade_EU
  extends ItemSlashBladeNamedSS
  implements IElectricItem, IItemHudInfo, IMultiModeBlade{
	 public static final TagPropertyAccessor.TagPropertyString Username = new TagPropertyAccessor.TagPropertyString("Username");
	 public static final TagPropertyAccessor.TagPropertyString TextureOnName = new TagPropertyAccessor.TagPropertyString("TextureOnName");
	 public static int maxEnergy = 200000;
	 public static int maxTransfer = 4096;
	 public int energyPerUse = 500;
	 public int energyPerUseCharged = 800;
  public ItemSlashBlade_EU(Item.ToolMaterial par2EnumToolMaterial, float baseAttackModifiers)
  {
    super(par2EnumToolMaterial, baseAttackModifiers);
  }
  
  public boolean canProvideEnergy(ItemStack itemStack)
  {
    return false;
  }
  
  @Override
  public boolean getIsRepairable(ItemStack arg0, ItemStack arg1) {
	return true;
  }

public Item getChargedItem(ItemStack itemStack)
  {
    return this;
  }
  
  public Item getEmptyItem(ItemStack itemStack)
  {
    return this;
  }
  
  public double getMaxCharge(ItemStack itemStack)
  {
    return maxEnergy;
  }
  
  public int getTier(ItemStack itemStack)
  {
    return 2;
  }
  
  public double getTransferLimit(ItemStack itemStack)
  {
    return maxTransfer;
  }
  
  	@Override
	public ResourceLocationRaw getModelTexture(ItemStack par1ItemStack) {
  		NBTTagCompound tag = getItemTagCompound(par1ItemStack);
  		ResourceLocationRaw result = ((ItemSlashBladeNamedSS)par1ItemStack.getItem()).getModelTexture();
  		if(isEmpowered(par1ItemStack)){
  	  		if(TextureOnName.exists(tag)){
  	            String textureName = TextureOnName.get(tag);
  	            ResourceLocationRaw loc;
  	            if(!textureMap.containsKey(textureName))
  	            {
  	                loc = new ResourceLocationRaw("flammpfeil.slashblade","model/" + textureName + ".png");
  	                textureMap.put(textureName,loc);
  	            }else{
  	                loc = textureMap.get(textureName);
  	            }
  	            result = loc;
  	        }
  		}
  		else
  		if(TextureName.exists(tag)){
            String textureName = TextureName.get(tag);
            ResourceLocationRaw loc;
            if(!textureMap.containsKey(textureName))
            {
                loc = new ResourceLocationRaw("flammpfeil.slashblade","model/" + textureName + ".png");
                textureMap.put(textureName,loc);
            }else{
                loc = textureMap.get(textureName);
            }
            result = loc;
        }
        return result;
	}
  
  	@Override
	public ResourceLocationRaw getModelLocation(ItemStack arg0) {
		
		return super.getModelLocation(arg0);
	}
  
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack arg0, World arg1, List arg2, ITooltipFlag arg3) {
		super.addInformation(arg0, arg1, arg2, arg3);
		arg2.add(I18n.format("info.flammpfeil.slashblade.tool.user") + ": " + Username.get(ItemSlashBlade.getItemTagCompound(arg0)));
		arg2.add("Stored EU : " + (int)ElectricItem.manager.getCharge(arg0) + "/" + maxEnergy +" EU");
	    arg2.add("PowerTier 2");
	}
    

	@Override
	public void onModeChange(EntityPlayer player, ItemStack stack) {
	    NBTTagCompound tag = getItemTagCompound(stack);
	    if (isEmpowered(stack))
	    {
	      boolean cancel = false;
	      if (IsBroken.get(tag).booleanValue()) {
	        cancel = true;
	      }
	      if (Username.exists(tag))
	      {
	        if (!player.getName().toString().trim().equals(Username.get(tag).trim())) {
	          cancel = true;
	        }
	      }
	      else {
	        Username.set(tag, player.getName().toString());
	      }
	      if (cancel)
	      {
	        player.world.playSound(null, player.getPosition(),SoundEvents.BLOCK_NOTE_BASS, SoundCategory.PLAYERS,1, 1.0F);
	        setMode(stack, 0);
	        return;
	      }
	      player.world.playSound(null, player.getPosition(),SoundEvents.ENTITY_LIGHTNING_THUNDER, SoundCategory.PLAYERS,1, 1.0F);
	      onStartupEmpowered(player, stack);
	    }
	    else
	    {
	    	player.world.playSound(null, player.getPosition(),SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS,1, 1.0F);
	    }
	}
	
	public boolean isEmpowered(ItemStack stack) {
		return getMode(stack) == 1 && ElectricItem.manager.getCharge(stack) >= energyPerUseCharged;
	}


	@Override

	public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase player) {
		int rank = StylishRankManager.getStylishRank(player);
	    int usage = (int)(this.energyPerUse * Math.pow(2.0D, rank));
	    if ((isEmpowered(stack)) && ( ElectricItem.manager.use(stack, usage, player)) && 
	      ((player instanceof EntityPlayer)))
	    {
	      entity.hurtResistantTime = 0;
	    }
	    return super.hitEntity(stack, entity, player);
	}
	
	  public void onUpdate(ItemStack sitem, World par2World, Entity par3Entity, int indexOfMainSlot, boolean isCurrent)
	  {
	    NBTTagCompound tag = getItemTagCompound(sitem);
	    if ((isEmpowered(sitem)) && (par3Entity != null) && (par2World.getTotalWorldTime() % 10L == 0L) && ((!isCurrent) || 
	      (OnClick.get(tag).booleanValue()) || (((par3Entity instanceof EntityPlayer))  )))
	    {
	      if (!par2World.isRemote)
	      {
	    	EntityPlayer player = (EntityPlayer)par3Entity;
	        int runingCost = energyPerUse;
	        ElectricItem.manager.use(sitem, runingCost, player);
		      int rankPoint = StylishRankManager.getTotalRankPoint(par3Entity);
			    int aRankPoint = (int)(StylishRankManager.RankRange * 7D);
			    int rankAmount = aRankPoint - rankPoint;
			    if (0 < rankAmount)
			    {
			      StylishRankManager.addRankPoint(par3Entity, rankAmount);
			    }
	      }

	      if ((Username.exists(tag)) && 
	        (!par3Entity.getName().toString().trim().equals(Username.get(tag).trim()))) {
		        setMode(sitem, 0);
	      }
	      double d0 = itemRand.nextGaussian() * 0.02D;
	      double d1 = itemRand.nextGaussian() * 0.02D;
	      double d2 = itemRand.nextGaussian() * 0.02D;
	      double d3 = 10.0D;
	      par2World.spawnParticle(EnumParticleTypes.PORTAL, par3Entity.posX + itemRand
	        .nextFloat() * par3Entity.width * 2.0F - par3Entity.width - d0 * d3, par3Entity.posY, par3Entity.posZ + itemRand
	        
	        .nextFloat() * par3Entity.width * 2.0F - par3Entity.width - d2 * d3, d0, d1, d2);
	    }
	    if(par3Entity instanceof EntityPlayer){ 
	    	EntityPlayer player = (EntityPlayer)par3Entity;
	    	if (!par2World.isRemote){
	    		ItemStack sitem1 = player.getHeldItemMainhand();
	    		 if (((sitem1.getItem() instanceof IElectricItem)&&(sitem1.getItem() instanceof IMultiModeBlade))&&!(isEmpowered(sitem)))
	    		StylishRankManager.setRankPoint(player, 0);
	    		}
	      }
	    super.onUpdate(sitem, par2World, par3Entity, indexOfMainSlot, isCurrent);
	  }
	
	protected int getEnergyPerUse(ItemStack stack) {
		return isEmpowered(stack) ? energyPerUseCharged : energyPerUse;
	}
	
	 public void onStartupEmpowered(EntityPlayer player, ItemStack stack)
	  {
	    int rankPoint = StylishRankManager.getTotalRankPoint(player);
	    int aRankPoint = (int)(StylishRankManager.RankRange * 7D);
	    int rankAmount = aRankPoint - rankPoint;
	    
	    int energy = (int) ElectricItem.manager.getCharge(stack);
	    rankAmount = Math.min(energy, rankAmount);
	    
	    int startupCost = 1000;
	    
	    World world = player.world;
	    
	    int energyUsage = startupCost;
	    if (energyUsage <= energy)
	    {
            for(int i = 0; i < 6;i++){
                EntityDrive entityDrive = new EntityDrive(world, player, 0F,false,0);
                entityDrive.setLocationAndAngles(player.posX,
                        player.posY + (double)player.getEyeHeight()/2D,
                        player.posZ,
                        player.rotationYaw + 60 * i ,
                        0);
                entityDrive.setDriveVector(0.5f);
                entityDrive.setLifeTime(5);
                entityDrive.setIsMultiHit(false);
                entityDrive.setRoll(90.0f);
                if (entityDrive != null) {
                    world.spawnEntity(entityDrive);
                }
            }
	    }
	    int rank = StylishRankManager.getStylishRank(player);
	    energyUsage = (int)(energyUsage * Math.pow(2.0D, rank));
	    if (0 < rankAmount)
	    {
	      energyUsage += rankAmount * 2;
	      StylishRankManager.addRankPoint(player, rankAmount);
	    }
	    ElectricItem.manager.use(stack, energyPerUse, player);
	  }

  
  	@SuppressWarnings({ "unchecked", "rawtypes" })
  	public List<String> getHudInfo(ItemStack stack, boolean advanced)
  	{
  		LinkedList info = new LinkedList();
  		info.add(ElectricItem.manager.getToolTip(stack));
  		return info;
  	}
  
}
