package cn.mmf.lastsmith.blades.handmade;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.ItemSlashBladeWrapper;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.util.ResourceLocationRaw;
import java.util.EnumSet;
import java.util.List;

import cn.mmf.lastsmith.blades.BladeLoader;
import cn.mmf.lastsmith.util.BladeUtil;

public class ItemSlashBladeSayaHandmade extends ItemSlashBladeWrapper {

    public ItemSlashBladeSayaHandmade(ToolMaterial par2EnumToolMaterial){
        super(par2EnumToolMaterial);
    }
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    @SideOnly(Side.CLIENT)
	public void addInformation(ItemStack arg0, World arg1, List arg2, ITooltipFlag arg3) {
		NBTTagCompound nbt = getItemTagCompound(arg0);
		if(BladeUtil.getInstance().getname(nbt) != null){
			arg2.add(TextFormatting.GOLD + I18n.format("blades.crafter")+":"+TextFormatting.GRAY+BladeUtil.getInstance().getname(nbt));	
		}
	}
	public ResourceLocationRaw texture = new ResourceLocationRaw("flammpfeil.slashblade", "model/handmade/sheath_0_wood.png");
	public ResourceLocationRaw getModelTexture() {
		return texture;
	}
	
	public ResourceLocationRaw model = new ResourceLocationRaw(SlashBlade.modid, "model/named/smith/model.obj");	
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
    
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
    	if (!this.isInCreativeTab(tab)) return;
        if (this.isInCreativeTab(CreativeTabs.COMBAT)) return;
        {
            ItemStack item0 = new ItemStack(this);
        	subItems.add(item0);
        	BladeLoader.getInstance().registerCustomItemStack("lastsmith.handmade_saya_0", item0);
        	
            ItemStack item1 = new ItemStack(this);
            NBTTagCompound tag1 = new NBTTagCompound();
            item1.setTagCompound(tag1);
        	ItemSlashBladeNamed.CurrentItemName.set(tag1, "lastsmith.handmade_saya_1");
        	ItemSlashBlade.TextureName.set(tag1, "handmade/sheath_1_wood");
        	item1.addEnchantment(Enchantments.FEATHER_FALLING, 1);
        	item1.addEnchantment(Enchantments.SMITE, 2);
        	item1.addEnchantment(Enchantments.UNBREAKING, 2);
        	subItems.add(item1);
        	BladeLoader.getInstance().registerCustomItemStack("lastsmith.handmade_saya_1", item1);
        	
            ItemStack item2 = new ItemStack(this);
            NBTTagCompound tag2 = new NBTTagCompound();
            item2.setTagCompound(tag2);
        	ItemSlashBladeNamed.CurrentItemName.set(tag2, "lastsmith.handmade_saya_2");
        	ItemSlashBlade.TextureName.set(tag2, "handmade/sheath_2_wood");
        	item2.addEnchantment(Enchantments.SHARPNESS, 1);
        	item2.addEnchantment(Enchantments.FIRE_ASPECT, 2);
        	item2.addEnchantment(Enchantments.THORNS, 2);
        	subItems.add(item2);
        	BladeLoader.getInstance().registerCustomItemStack("lastsmith.handmade_saya_2", item2);
        }
    }
    
    @Override
    public ComboSequence getNextComboSeq(ItemStack itemStack, ComboSequence current, boolean isRightClick, EntityPlayer player) {
        return ComboSequence.None;
    }

    @Override
    public EnumSet<SwordType> getSwordType(ItemStack itemStack) {
        EnumSet<SwordType> set =  EnumSet.noneOf(SwordType.class);
        set.add(SwordType.Perfect);
        set.add(SwordType.Sealed);
        set.remove(SwordType.FiercerEdge);
        return set;
    }

    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
        ComboSequence comboSec = getComboSequence(getItemTagCompound(par1ItemStack));
        switch (comboSec) {
            case Saya1:
            case Saya2:
                break;

            default:
                break;
        }

        return super.hitEntity(par1ItemStack, par2EntityLivingBase, par3EntityLivingBase);
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
            super.setDamage(stack,0);
    }
    
}

