package cn.mmf.lastsmith.blades;

import java.util.Map;

import com.google.common.collect.Maps;

import cn.mmf.lastsmith.TLSMain;
import cn.mmf.lastsmith.blades.compat.ItemSlashBladeCrimson;
import cn.mmf.lastsmith.blades.compat.ItemSlashBladeEU;
import cn.mmf.lastsmith.blades.compat.ItemSlashBladeRF;
import cn.mmf.lastsmith.blades.compat.ItemSlashBladeVoid;
import cn.mmf.lastsmith.blades.compat.ItemSlashBladeWind;
import cn.mmf.lastsmith.blades.handmade.ItemSlashBladeHandmade;
import cn.mmf.lastsmith.blades.handmade.ItemSlashBladeSayaHandmade;
import cn.mmf.lastsmith.item.ItemLoader;
import cn.mmf.lastsmith.item.ItemSlashBladeDetuneTLS;
import cn.mmf.lastsmith.item.ItemSlashBladeSaya;
import cn.mmf.lastsmith.item.ItemSlashBladeTLS;
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
	public static Item blade=new ItemSlashBladeDetuneTLS(ToolMaterial.IRON,6).setDestructable(false).setModel(new ResourceLocationRaw(SlashBlade.modid, "model/named/yasha/yasha.obj")).setModelTexture(new ResourceLocationRaw("flammpfeil.slashblade","model/unnamed/texture.png")).setRepairMaterialOreDic("ingotSteel","nuggetSteel").setMaxDamage(71).setUnlocalizedName(TLSMain.MODID+".white_top");
	public static Item bladeNamed= new ItemSlashBladeNamedTLS(ToolMaterial.IRON, 4.0f).setMaxDamage(40).setUnlocalizedName("flammpfeil.slashblade.named");
	public static Item euBlade,windBlade,voidBlade,rfblade,crimsonBlade;
	public static Item wrapper = new ItemSlashBladeSaya(ToolMaterial.WOOD).setUnlocalizedName(TLSMain.MODID+"."+"wooden_saya");
	public static Item wrapper_bamboo = new ItemSlashBladeSaya(ToolMaterial.WOOD).setModelTexture(new ResourceLocationRaw("flammpfeil.slashblade","model/bamboo_saya.png")).setUnlocalizedName(TLSMain.MODID+"."+"bamboo_saya");
	public static Item weapon,bladeWood,bladeBambooLight,bladeSilverBambooLight,
	bladeWhiteSheath,
	bladeHandmade = new ItemSlashBladeHandmade(ToolMaterial.IRON,6).setUnlocalizedName("lastsmith.handmade_white"),
	bladeSayaHandmade = new ItemSlashBladeSayaHandmade(ToolMaterial.WOOD).setUnlocalizedName("lastsmith.handmade_saya_0");
	private Map<ResourceLocationRaw, ItemStack> BladeRegistry = Maps.newHashMap();
	private static final BladeLoader instance = new BladeLoader();
    private BladeLoader() {
    }
	public static BladeLoader getInstance() {
		return instance;
	}
	public Map<ResourceLocationRaw, ItemStack> getBladeRegistry() {
		return BladeRegistry;
	}
    public void register() {
    	ItemLoader.getInstance().registerItem(wrapper);
    	ItemLoader.getInstance().registerItem(wrapper_bamboo);
    	ItemLoader.getInstance().registerItem(blade);
    	ItemLoader.getInstance().registerItem(bladeNamed);
    	
    	ItemLoader.getInstance().registerItem(bladeHandmade);
    	ItemLoader.getInstance().registerItem(bladeSayaHandmade);
    	
		weapon = new ItemSlashBladeTLS(ToolMaterial.IRON, 4 + ToolMaterial.DIAMOND.getAttackDamage())
				.setRepairMaterial(new ItemStack(Items.IRON_INGOT))
				.setRepairMaterialOreDic("ingotSteel", "nuggetSteel")
				.setUnlocalizedName("flammpfeil.slashblade")
				.setCreativeTab(SlashBlade.tab)
                .setRegistryName(new ResourceLocation(SlashBlade.modid, "slashblade"));
		
        ForgeRegistries.ITEMS.register(weapon);
    	
        bladeWood = new ItemSlashBladeDetuneTLS(ToolMaterial.WOOD, 4 + ToolMaterial.WOOD.getAttackDamage())
                .setDestructable(true)
                .setModelTexture(new ResourceLocationRaw("flammpfeil.slashblade", "model/wood.png"))
                .setRepairMaterialOreDic("logWood")
                .setMaxDamage(60)
                .setUnlocalizedName("flammpfeil.slashblade.wood")
                .setCreativeTab(SlashBlade.tab)
                .setRegistryName(SlashBlade.modid,"slashbladeWood");
        ForgeRegistries.ITEMS.register(bladeWood);

        bladeBambooLight = new ItemSlashBladeDetuneTLS(ToolMaterial.WOOD, 4 + ToolMaterial.STONE.getAttackDamage())
                .setDestructable(true)
                .setModelTexture(new ResourceLocationRaw("flammpfeil.slashblade", "model/banboo.png"))
                .setRepairMaterialOreDic("bamboo")
                .setMaxDamage(50)
                .setUnlocalizedName("flammpfeil.slashblade.bamboo")
                .setCreativeTab(SlashBlade.tab)
                .setRegistryName(SlashBlade.modid,"slashbladeBambooLight");
        ForgeRegistries.ITEMS.register(bladeBambooLight);

        bladeSilverBambooLight = new ItemSlashBladeDetuneTLS(ToolMaterial.WOOD, 4 + ToolMaterial.IRON.getAttackDamage())
                .setDestructable(true)
                .setModelTexture(new ResourceLocationRaw("flammpfeil.slashblade", "model/silverbanboo.png"))
                .setRepairMaterialOreDic("bamboo")
                .setMaxDamage(40)
                .setUnlocalizedName("flammpfeil.slashblade.silverbamboo")
                .setCreativeTab(SlashBlade.tab)
                .setRegistryName(SlashBlade.modid,"slashbladeSilverBambooLight");
        ForgeRegistries.ITEMS.register(bladeSilverBambooLight);

        bladeWhiteSheath = new ItemSlashBladeDetuneTLS(ToolMaterial.IRON, 4 + ToolMaterial.IRON.getAttackDamage())
                .setDestructable(false)
                .setModelTexture(new ResourceLocationRaw("flammpfeil.slashblade", "model/white.png"))
                .setRepairMaterial(new ItemStack(Items.IRON_INGOT))
                .setRepairMaterialOreDic("ingotSteel", "nuggetSteel")
                .setMaxDamage(70)
                .setUnlocalizedName("flammpfeil.slashblade.white")
                .setCreativeTab(SlashBlade.tab)
                .setRegistryName(SlashBlade.modid,"slashbladeWhite");
        ForgeRegistries.ITEMS.register(bladeWhiteSheath);
        
        if(Loader.isModLoaded(Thaumcraft.MODID)){
    		windBlade = (new ItemSlashBladeWind(ThaumcraftMaterials.TOOLMAT_ELEMENTAL, 4.0F)).setRegistryName("slashblade_wind");
    		ForgeRegistries.ITEMS.register(windBlade);
    		voidBlade = (new ItemSlashBladeVoid(ThaumcraftMaterials.TOOLMAT_VOID, 4.0F)).setRegistryName("slashblade_void");
    		ForgeRegistries.ITEMS.register(voidBlade);
    		crimsonBlade = (new ItemSlashBladeCrimson(ItemCrimsonBlade.toolMatCrimsonVoid, 4.0F)).setRegistryName("slashblade_crimson");
    	    ForgeRegistries.ITEMS.register(crimsonBlade);
    	}
       if(Loader.isModLoaded(IC2.MODID)){
       	 euBlade = (new ItemSlashBladeEU(ToolMaterial.IRON, 4.0F)).setRegistryName("slashblade_eu");
       	 ForgeRegistries.ITEMS.register(euBlade);
       }
		rfblade = new ItemSlashBladeRF(ToolMaterial.IRON, 4.0F).setRegistryName("slashBlade_rf");
		ForgeRegistries.ITEMS.register(rfblade);
	}
    
    @SideOnly(Side.CLIENT)
    public void renderSlashBlade() {
    	setSlashBladeRender(bladeHandmade);
    	setSlashBladeRender(bladeSayaHandmade);
    	
    	setSlashBladeRender(blade);
    	setSlashBladeRender(bladeNamed);
    	setSlashBladeRender(wrapper);
    	setSlashBladeRender(wrapper_bamboo);
    	setSlashBladeRender(weapon);
    	
    	setSlashBladeRender(bladeBambooLight);
    	setSlashBladeRender(bladeWhiteSheath);
    	setSlashBladeRender(bladeWood);
    	setSlashBladeRender(bladeSilverBambooLight);
    	
        if(Loader.isModLoaded(IC2.MODID)) 
        	setSlashBladeRender(euBlade);
        	setSlashBladeRender(rfblade);
        if(Loader.isModLoaded(Thaumcraft.MODID)){
        	setSlashBladeRender(windBlade);
        	setSlashBladeRender(voidBlade);
        	setSlashBladeRender(crimsonBlade);
        }
	}

	@SideOnly(Side.CLIENT)
    private void setSlashBladeRender(Item blade) {
    	ModelLoader.setCustomModelResourceLocation(blade, 0, new ModelResourceLocation("flammpfeil.slashblade:model/named/blade.obj"));
    	blade.setTileEntityItemStackRenderer(new BladeSpecialRender());
	}
    public void registerCustomItemStack(String name, ItemStack stack){
        BladeRegistry.put(new ResourceLocationRaw(TLSMain.MODID, name),stack);
    }
	public ItemStack getCustomBlade(String bladename) {
		if(BladeRegistry.containsKey(new ResourceLocationRaw(TLSMain.MODID,bladename))) {
			return BladeRegistry.get(new ResourceLocationRaw(TLSMain.MODID, bladename)).copy();
        }
		return ItemStack.EMPTY;
	}

}
