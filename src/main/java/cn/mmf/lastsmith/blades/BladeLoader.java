package cn.mmf.lastsmith.blades;

import java.util.Map;

import com.google.common.collect.Maps;

import cn.mmf.lastsmith.TLSMain;
import cn.mmf.lastsmith.blades.compat.ItemSlashBladeEU;
import cn.mmf.lastsmith.blades.compat.ItemSlashBladeRF;
import cn.mmf.lastsmith.blades.compat.ItemSlashBladeVoid;
import cn.mmf.lastsmith.blades.compat.ItemSlashBladeWind;
import cn.mmf.lastsmith.item.ItemLoader;
import cn.mmf.lastsmith.item.ItemSlashBladeDetuneTLS;
import cn.mmf.lastsmith.item.ItemSlashBladeSaya;
import cn.mmf.lastsmith.item.ItemSlashBladeTLS;
import cofh.CoFHCore;
import ic2.core.IC2;
import cn.mmf.lastsmith.item.ItemSlashBladeNamedTLS;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.client.model.BladeSpecialRender;
import mods.flammpfeil.slashblade.util.ResourceLocationRaw;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.Thaumcraft;
import thaumcraft.api.ThaumcraftMaterials;
import thaumcraft.common.items.tools.ItemCrimsonBlade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class BladeLoader {
	public static Item blade=new ItemSlashBladeDetuneTLS(ToolMaterial.IRON,6).setDestructable(false).setModelTexture(new ResourceLocationRaw("flammpfeil.slashblade","model/unnamed/texture.png")).setRepairMaterialOreDic("ingotSteel","nuggetSteel").setMaxDamage(71).setUnlocalizedName(TLSMain.MODID+"."+"white_top");
	public static Item bladeNamed= new ItemSlashBladeNamedTLS(ToolMaterial.IRON, 4.0f).setMaxDamage(40).setUnlocalizedName("flammpfeil.slashblade.named");
	public static Item euBlade,windBlade,voidBlade,rfblade,crimsonBlade;
	public static Item wrapper = new ItemSlashBladeSaya(ToolMaterial.WOOD).setUnlocalizedName(TLSMain.MODID+"."+"wooden_saya");
	public static Item wrapper_bamboo = new ItemSlashBladeSaya(ToolMaterial.WOOD).setModelTexture(new ResourceLocationRaw("flammpfeil.slashblade","model/bamboo_saya.png")).setUnlocalizedName(TLSMain.MODID+"."+"bamboo_saya");
	public static Item weapon;
	public static Map<ResourceLocationRaw, ItemStack> BladeRegistry = Maps.newHashMap();

    public BladeLoader() {
    	ItemLoader.register(wrapper);
    	ItemLoader.register(wrapper_bamboo);
    	ItemLoader.register(blade);
    	ItemLoader.register(bladeNamed);
		weapon = new ItemSlashBladeTLS(ToolMaterial.IRON, 4 + ToolMaterial.DIAMOND.getAttackDamage())
				.setRepairMaterial(new ItemStack(Items.IRON_INGOT))
				.setRepairMaterialOreDic("ingotSteel", "nuggetSteel")
				.setUnlocalizedName("flammpfeil.slashblade")
				.setCreativeTab(SlashBlade.tab)
                .setRegistryName(new ResourceLocation(SlashBlade.modid, "slashblade"));
		
        ForgeRegistries.ITEMS.register(weapon);
    	if(Loader.isModLoaded(Thaumcraft.MODID)){
    		windBlade = (new ItemSlashBladeWind(ThaumcraftMaterials.TOOLMAT_ELEMENTAL, 4.0F)).setRegistryName("slashblade_wind");
    		ForgeRegistries.ITEMS.register(windBlade);
    		voidBlade = (new ItemSlashBladeVoid(ThaumcraftMaterials.TOOLMAT_VOID, 4.0F)).setRegistryName("slashblade_void");
    		ForgeRegistries.ITEMS.register(voidBlade);
    		crimsonBlade = (new ItemSlashBladeVoid(ItemCrimsonBlade.toolMatCrimsonVoid, 4.0F)).setRegistryName("slashblade_crimson");
    	    ForgeRegistries.ITEMS.register(crimsonBlade);
    	}
       if(Loader.isModLoaded(IC2.MODID)){
       	 euBlade = (new ItemSlashBladeEU(ToolMaterial.IRON, 4.0F)).setRegistryName("slashblade_eu");
       	 ForgeRegistries.ITEMS.register(euBlade);
       }
    	if(Loader.isModLoaded(CoFHCore.MOD_ID)){
    		rfblade = new ItemSlashBladeRF(ToolMaterial.IRON, 4.0F).setRegistryName("slashBlade_rf");
    		ForgeRegistries.ITEMS.register(rfblade);
		}
    }
    
    @SideOnly(Side.CLIENT)
    public static void renderSlashBlade() {
    	setSlashBladeRender(blade);
    	setSlashBladeRender(bladeNamed);
    	setSlashBladeRender(wrapper);
    	setSlashBladeRender(wrapper_bamboo);
    	setSlashBladeRender(weapon);
        if(Loader.isModLoaded(IC2.MODID)) 
        	setSlashBladeRender(euBlade);
        if(Loader.isModLoaded(CoFHCore.MOD_ID)) 
        	setSlashBladeRender(rfblade);
        if(Loader.isModLoaded(Thaumcraft.MODID)){
        	setSlashBladeRender(windBlade);
        	setSlashBladeRender(voidBlade);
        	setSlashBladeRender(crimsonBlade);
        }
	}

	@SideOnly(Side.CLIENT)
    private static void setSlashBladeRender(Item blade) {
    	ModelLoader.setCustomModelResourceLocation(blade, 0, new ModelResourceLocation("flammpfeil.slashblade:model/named/blade.obj"));
    	blade.setTileEntityItemStackRenderer(new BladeSpecialRender());
	}
    public static void registerCustomItemStack(String name, ItemStack stack){
        BladeRegistry.put(new ResourceLocationRaw(TLSMain.MODID, name),stack);
    }
	public static ItemStack getCustomBlade(String bladename) {
		if(BladeRegistry.containsKey(new ResourceLocationRaw(TLSMain.MODID,bladename))) {
			return BladeRegistry.get(new ResourceLocationRaw(TLSMain.MODID, bladename)).copy();
        }
		return ItemStack.EMPTY;
	}
}
