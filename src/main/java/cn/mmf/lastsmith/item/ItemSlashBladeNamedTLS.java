package cn.mmf.lastsmith.item;

import java.util.EnumSet;
import java.util.List;

import com.google.common.collect.Lists;

import cn.mmf.lastsmith.TLSConfig;
import cn.mmf.lastsmith.blades.BladeLoader;
import cn.mmf.lastsmith.util.BladeUtil;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.util.ResourceLocationRaw;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemSlashBladeNamedTLS extends ItemSlashBladeTLS{
	public ItemSlashBladeNamedTLS(ToolMaterial par2EnumToolMaterial, float baseAttackModifiers) {
	        super(par2EnumToolMaterial, baseAttackModifiers);
	}
   
    private static ResourceLocationRaw texture = new ResourceLocationRaw("flammpfeil.slashblade","model/white.png");
	public ResourceLocationRaw getModelTexture() {
		return texture;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    @SideOnly(Side.CLIENT)
    public void addInformationSwordClass(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		NBTTagCompound tag = getItemTagCompound(par1ItemStack);
		if(BladeUtil.getInstance().IsFakeBlade.get(tag)){
			par3List.add(I18n.format("flammpfeil.swaepon.info.fake"));
		}else
		super.addInformationSwordClass(par1ItemStack, par2EntityPlayer, par3List, par4);
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
        return ((ItemSlashBladeNamedTLS)par1ItemStack.getItem()).getModelTexture();
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
    public int getMaxDamage(ItemStack stack) {
        NBTTagCompound tag = getItemTagCompound(stack);
        return ItemSlashBladeNamed.CustomMaxDamage.get(tag,super.getMaxDamage(stack));
    }

    public static List<String> NamedBlades = Lists.newArrayList();

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (!this.isInCreativeTab(tab)) return;
        if (this.isInCreativeTab(CreativeTabs.COMBAT)) return;
        for(String bladename : NamedBlades){
            ItemStack blade = BladeLoader.getInstance().getCustomBlade(bladename);
            if(!blade.isEmpty()) {
                if(blade.getItemDamage() == OreDictionary.WILDCARD_VALUE)
                    blade.setItemDamage(-1);
            	subItems.add(blade);
            }
        }
    }
    
	@Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)  {
        Boolean result = super.getIsRepairable(par1ItemStack,par2ItemStack);

        NBTTagCompound tag = getItemTagCompound(par1ItemStack);

        if(!result && tag.hasKey(ItemSlashBladeNamed.RepairOreDicMaterialStr)) {
            String oreName = tag.getString(ItemSlashBladeNamed.RepairOreDicMaterialStr);
            List<ItemStack> list = OreDictionary.getOres(oreName);
            for(ItemStack curItem : list){
                if(curItem.getItemDamage() == OreDictionary.WILDCARD_VALUE){
                    result = curItem.getItem() == par2ItemStack.getItem();
                }else{
                    result = curItem.isItemEqual(par2ItemStack);
                }
                if(result)
                    break;
            }
        }

    if(!result && tag.hasKey(ItemSlashBladeNamed.RepairMaterialNameStr)) {
            String matName = tag.getString(ItemSlashBladeNamed.RepairMaterialNameStr);
            Item material = Item.REGISTRY.getObject(new ResourceLocationRaw(matName));
            if(material != null)
                result = par2ItemStack.getItem() == material;
        }

        return result;
    }
	@Override
	public EnumSet<SwordType> getSwordType(ItemStack itemStack) {
		EnumSet<SwordType> set = super.getSwordType(itemStack);
		NBTTagCompound tag = getItemTagCompound(itemStack);
		if(BladeUtil.getInstance().IsFakeBlade.get(tag)){
			set.remove(SwordType.Enchanted);
			if(TLSConfig.advanced_mode && !BladeUtil.getInstance().IsBewitchedActived.get(tag)){
				set.remove(SwordType.Bewitched);
				set.remove(SwordType.SoulEeater);
			}
		}
		return set;
	}
}
