package cn.mmf.lastsmith.blades.handmade;

import java.util.EnumSet;
import java.util.List;

import cn.mcmod_mmf.mmlib.util.StringUtil;
import cn.mmf.lastsmith.blades.BladeLoader;
import cn.mmf.lastsmith.item.ItemSlashBladeTLS;
import cn.mmf.lastsmith.se.SELoader;
import cn.mmf.lastsmith.util.BladeUtil;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.TagPropertyAccessor;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import mods.flammpfeil.slashblade.util.ResourceLocationRaw;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSlashBladeHandmade extends ItemSlashBladeTLS {

	public ItemSlashBladeHandmade(ToolMaterial par2EnumToolMaterial, float defaultBaseAttackModifier) {
		super(par2EnumToolMaterial, defaultBaseAttackModifier);
	}
	public static final TagPropertyAccessor.TagPropertyString SheathName = new TagPropertyAccessor.TagPropertyString("SheathName");
	public ResourceLocationRaw texture = new ResourceLocationRaw("flammpfeil.slashblade","model/handmade/white_0.png");
	public ResourceLocationRaw getModelTexture() {
		return texture;
	}
	
	public ResourceLocationRaw model = new ResourceLocationRaw(SlashBlade.modid, "model/named/yasha/yasha.obj");	
	@Override
	public ResourceLocationRaw getModel() {
		return model;
	}
	
    public ResourceLocationRaw getModelTexture(ItemStack par1ItemStack){
        NBTTagCompound tag = getItemTagCompound(par1ItemStack);
        if(TextureName.exists(tag)){
            String textureName = TextureName.get(tag);
            ResourceLocationRaw loc;
            if(!textureMap.containsKey(textureName)) {
                loc = new ResourceLocationRaw("flammpfeil.slashblade","model/" + textureName + ".png");
                textureMap.put(textureName,loc);
            }else{
                loc = textureMap.get(textureName);
            }
            return loc;
        }
        return this.getModelTexture();
    }
    
    public ResourceLocationRaw getModelLocation(ItemStack par1ItemStack){
        NBTTagCompound tag = getItemTagCompound(par1ItemStack);
        if(ModelName.exists(tag)){
            String modelName = ModelName.get(tag);
            ResourceLocationRaw loc;
            if(!modelMap.containsKey(modelName))
            {
                loc = new ResourceLocationRaw("flammpfeil.slashblade","model/" + modelName + ".obj");
                modelMap.put(modelName,loc);
            }else{
                loc = modelMap.get(modelName);
            }
            return loc;
        }
        return this.getModel();
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    @SideOnly(Side.CLIENT)
	public void addInformation(ItemStack arg0, World arg1, List arg2, ITooltipFlag arg3) {
		NBTTagCompound nbt = getItemTagCompound(arg0);
		if(BladeUtil.getInstance().getname(nbt) != null){
			arg2.add(TextFormatting.GOLD + I18n.format("blades.crafter")+":"+TextFormatting.GRAY+BladeUtil.getInstance().getname(nbt));	
		}
		if(BladeUtil.getInstance().getname(nbt) != null){
			arg2.add(TextFormatting.GOLD + I18n.format("blades.sheath")+":"+TextFormatting.WHITE+I18n.format(SheathName.get(nbt)));	
		}
		if (StringUtil.getInstance().isAltKeyDown()) {
			super.addInformation(arg0, arg1, arg2, arg3);
		}else{
			arg2.add(I18n.format("lastsmith.info.hold_alt_for_details"));
		}
	}
	
	@Override
	public EnumSet<SwordType> getSwordType(ItemStack itemStack) {
		NBTTagCompound tag = getItemTagCompound(itemStack);
		EnumSet<SwordType> set = super.getSwordType(itemStack);
		if (!BladeUtil.getInstance().IsBewitchedActived.get(tag)) {
			set.remove(SwordType.Enchanted);
			set.remove(SwordType.Bewitched);
			set.remove(SwordType.SoulEeater);
		}
		return set;
	}

	@Override
	public int getItemEnchantability(ItemStack stack) {
		return 0;
	}
	
    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        String result = super.getUnlocalizedName(par1ItemStack);
        if(par1ItemStack.hasTagCompound()){
            NBTTagCompound tag = par1ItemStack.getTagCompound();
            if(ItemSlashBladeNamed.CurrentItemName.exists(tag)){
                result = "item." + ItemSlashBladeNamed.CurrentItemName.get(tag);
            }
        }
        return result;
    }
	
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
    	if (!this.isInCreativeTab(tab)) return;
        if (this.isInCreativeTab(CreativeTabs.COMBAT)) return;
        ItemStack item0 = new ItemStack(this);
        subItems.add(item0);
    	BladeLoader.getInstance().registerCustomItemStack("lastsmith.handmade_white", item0);
        
        if(Loader.isModLoaded("tfc")) {
            ItemStack item = new ItemStack(this);
            NBTTagCompound tag = new NBTTagCompound();
            item.setTagCompound(tag);
        	ItemSlashBladeNamed.CurrentItemName.set(tag, "lastsmith.handmade_red_steel");
        	ItemSlashBlade.TextureName.set(tag, "handmade/white_red_steel");
        	subItems.add(item);
        	BladeLoader.getInstance().registerCustomItemStack("lastsmith.handmade_red_steel", item);
            
        	ItemStack item1 = new ItemStack(this);
            NBTTagCompound tag1 = new NBTTagCompound();
            item1.setTagCompound(tag1);
        	ItemSlashBladeNamed.CurrentItemName.set(tag1, "lastsmith.handmade_blue_steel");
        	ItemSlashBlade.TextureName.set(tag1, "handmade/white_blue_steel");
        	subItems.add(item1);
        	BladeLoader.getInstance().registerCustomItemStack("lastsmith.handmade_blue_steel", item1);
        }
        
        if(Loader.isModLoaded("thaumcraft")) {
            ItemStack item = new ItemStack(this);
            NBTTagCompound tag = new NBTTagCompound();
            item.setTagCompound(tag);
        	ItemSlashBladeNamed.CurrentItemName.set(tag, "lastsmith.handmade_thaum");
        	ItemSlashBlade.TextureName.set(tag, "handmade/white_thaum");
        	subItems.add(item);
        	BladeLoader.getInstance().registerCustomItemStack("lastsmith.handmade_thaum", item);
            
        	ItemStack item1 = new ItemStack(this);
            NBTTagCompound tag1 = new NBTTagCompound();
            item1.setTagCompound(tag1);
        	ItemSlashBladeNamed.CurrentItemName.set(tag1, "lastsmith.handmade_void");
        	ItemSlashBlade.TextureName.set(tag1, "handmade/white_void");
        	SpecialEffects.addEffect(item1, SELoader.SAP);
        	subItems.add(item1);
        	BladeLoader.getInstance().registerCustomItemStack("lastsmith.handmade_void", item1);
        }
    }
	
	@SuppressWarnings("rawtypes")
	@Override
    public void addInformationSwordClass(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		NBTTagCompound tag = getItemTagCompound(par1ItemStack);
		if (BladeUtil.getInstance().IsBewitchedActived.get(tag)) {
			super.addInformationSwordClass(par1ItemStack, par2EntityPlayer, par3List, par4);
		}
	}
}
