package net.langball.lastsmith.blade;

import java.util.Map;

import com.google.common.collect.Maps;

import ic2.api.recipe.IMachineRecipeManager;
import ic2.core.IC2;
import mods.flammpfeil.slashblade.ItemSlashBladeDetune;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.crafting.RecipeCustomBlade;
import mods.flammpfeil.slashblade.util.ResourceLocationRaw;
import mods.flammpfeil.slashblade.util.SlashBladeHooks;
import net.langball.lastsmith.CommonProxy;
import net.langball.lastsmith.Last_worker;
import net.langball.lastsmith.blade.*;
import net.langball.lastsmith.blade.arthurs.*;
import net.langball.lastsmith.blade.famous.*;
import net.langball.lastsmith.blade.others.ItemSlashblade_BambooLight_nice;
import net.langball.lastsmith.blade.others.ItemSlashblade_BambooLight_yin;
import net.langball.lastsmith.blade.others.ItemSlashblade_Eievui;
import net.langball.lastsmith.blade.others.ItemSlashblade_SilverBambooLight_blood;
import net.langball.lastsmith.blade.others.ItemSlashblade_SilverBambooLight_gold;
import net.langball.lastsmith.blade.others.ItemSlashblade_SilverBambooLight_nice;
import net.langball.lastsmith.blade.others.ItemSlashblade_Slashblade_old;
import net.langball.lastsmith.blade.others.ItemSlashblade_Sweapon;
import net.langball.lastsmith.blade.others.ItemSlashblade_kogawa;
import net.langball.lastsmith.blade.others.ItemSlashblade_kogawa2;
import net.langball.lastsmith.blade.others.ItemSlashblade_kogawa3;
import net.langball.lastsmith.blade.others.ItemSlashblade_lkaruga;
import net.langball.lastsmith.blade.others.ItemSlashblade_sagequoia;
import net.langball.lastsmith.blade.others.ItemSlashblade_sakuya;
import net.langball.lastsmith.blade.others.ItemSlashblade_sakuya2;
import net.langball.lastsmith.blade.others.ItemSlashblade_sakuya3;
import net.langball.lastsmith.blade.others.ItemSlashblade_sakuya4;
import net.langball.lastsmith.blade.others.ItemSlashblade_sakuya5;
import net.langball.lastsmith.blade.smith.*;
import net.langball.lastsmith.compat.*;
import net.langball.lastsmith.compat.blades.ItemSlashblade_Bailou_xf;
import net.langball.lastsmith.compat.blades.ItemSlashblade_Louguan_xf;
import net.langball.lastsmith.compat.blades.ItemSlashblade_Scorn;
import net.langball.lastsmith.compat.blades.ItemSlashblade_Scorn_2;
import net.langball.lastsmith.compat.blades.ItemSlashblade_Scorn_3;
import net.langball.lastsmith.compat.blades.ItemSlashblade_Scorn_4;
import net.langball.lastsmith.eusaber.*;
import net.langball.lastsmith.items.*;
import net.langball.lastsmith.louguan.blade.*;
import net.langball.lastsmith.recipe.FakeRecipeLoader;
import net.langball.lastsmith.sa.EntitySA;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.Thaumcraft;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeRuneAltar;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.BotaniaCreativeTab;


public class BladeLoader {
	public static Item blade,blade_model,blade_model_1,blade_tls;
	public static Item euBlade,bladeNamed,windBlade,voidBlade;
	public static ItemSlashBladeSaya wrapper,wrapper_bamboo;
	
	static public Map<ResourceLocationRaw, ItemStack> BladeRegistry = Maps.newHashMap();

    static public void registerCustomItemStack(String name, ItemStack stack){
        BladeRegistry.put(new ResourceLocationRaw(Last_worker.MODID, name),stack);
    }
    
	 static public ItemStack findItemStack(String modid, String name, int count){
	        ResourceLocationRaw key = new ResourceLocationRaw(modid, name);
	        ItemStack stack = ItemStack.EMPTY;

	        if(BladeRegistry.containsKey(key)) {
	            stack = BladeRegistry.get(new ResourceLocationRaw(modid, name)).copy();
	        }else {
	            Item item = Item.REGISTRY.getObject(new ResourceLocationRaw(modid, name));
	            if (item != null)
	                stack = new ItemStack(item);
	        }
	        if(!stack.isEmpty()) {
	            stack.setCount(count);
	        }
	        return stack;
	    }

	    public static ItemStack getCustomBlade(String modid,String name){
	        return findItemStack(modid, name, 1);
	    }
	    public static ItemStack getCustomBlade(String key){
	        String modid;
	        String name;
	        {
	            String str[] = key.split(":",2);
	            if(str.length == 2){
	                modid = str[0];
	                name = str[1];
	            }else{
	                modid = Last_worker.MODID;
	                name = key;
	            }
	        }

	        return getCustomBlade(modid,name);
	    }
public BladeLoader(FMLPreInitializationEvent event){
  	 wrapper = (ItemSlashBladeSaya)(new ItemSlashBladeSaya(ToolMaterial.IRON))
             .setMaxDamage(40)
             .setUnlocalizedName("flammpfeil.slashblade.wooden_saya")
             .setCreativeTab(CommonProxy.tab)
             .setRegistryName("slashbladeSaya");
     ForgeRegistries.ITEMS.register(wrapper);
  	 wrapper_bamboo = (ItemSlashBladeSaya)(new ItemSlashBladeSaya(ToolMaterial.IRON))
  			 .setModelTexture(new ResourceLocationRaw("flammpfeil.slashblade","model/bamboo_saya.png"))
             .setMaxDamage(40)
             .setUnlocalizedName("flammpfeil.slashblade.bamboo_saya")
             .setCreativeTab(CommonProxy.tab)
             .setRegistryName("slashbladeSayaBamboo");
     ForgeRegistries.ITEMS.register(wrapper_bamboo);
	blade= (new ItemSlashBladeBasic(ToolMaterial.IRON,6))
            .setDestructable(false)
            .setModelTexture(new ResourceLocationRaw("flammpfeil.slashblade","model/unnamed/texture.png"))
            .setRepairMaterialOreDic("ingotSteel","nuggetSteel")
            .setMaxDamage(71)
            .setUnlocalizedName("flammpfeil.slashblade.unnamedBlade")
            .setRegistryName("UnnamedBlade")
            .setCreativeTab(CommonProxy.tab);
	ForgeRegistries.ITEMS.register(blade);
	blade_model=(new ItemSlashBladeModel(ToolMaterial.IRON,5))
            .setDestructable(false)
            .setModelTexture(new ResourceLocationRaw("flammpfeil.slashblade","model/unnamed/texture_model.png"))
            .setRepairMaterialOreDic("ingotSteel","nuggetSteel")
            .setMaxDamage(51)
            .setUnlocalizedName("flammpfeil.slashblade.model_Blade")
            .setRegistryName("modelBlade")
            .setCreativeTab(CommonProxy.tab);
	ForgeRegistries.ITEMS.register(blade_model);
	blade_model_1= new ItemSlashBladeModel(ToolMaterial.IRON,7)
            .setDestructable(false)
            .setModelTexture(new ResourceLocationRaw("flammpfeil.slashblade","model/unnamed/texture_model_1.png"))
            .setRepairMaterialOreDic("ingotSteel","nuggetSteel")
            .setMaxDamage(71)
            .setUnlocalizedName("flammpfeil.slashblade.model_Blade_1")
            .setRegistryName("modelBlade_1")
            .setCreativeTab(CommonProxy.tab);
	ForgeRegistries.ITEMS.register(blade_model_1);
	
    bladeNamed = (ItemSlashBladeNamedSS)(new ItemSlashBladeNamedSS(ToolMaterial.IRON, 4.0f))
            .setMaxDamage(40)
            .setUnlocalizedName("flammpfeil.slashblade.named")
            .setCreativeTab(CommonProxy.tab)
            .setRegistryName("slashbladeNamedSS");
    ForgeRegistries.ITEMS.register(bladeNamed);
    
	blade_tls = (new ItemSlashbladeNamed_smith(ToolMaterial.IRON, 4.0F)).setRegistryName("blade_tls");
  	ForgeRegistries.ITEMS.register(blade_tls);
  		SlashBlade.InitEventBus.register(new SALoader());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_sakuya());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_sakuya2());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_sakuya3());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_sakuya4());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_sakuya5());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_Louguan_fake());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_Louguan_old());

		 SlashBlade.InitEventBus.register(new ItemSlashblade_Louguan());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_Bailou_fake());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_Bailou());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_Louguan_tx());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_Bailou_tx());
		 SlashBlade.InitEventBus.register(new FakeRecipeLoader());
	if(Loader.isModLoaded(Thaumcraft.MODID)){
		windBlade = (new ItemSlashBladeWind(ToolMaterial.IRON, 4.0F)).setRegistryName("WindBlade");
		    	 ForgeRegistries.ITEMS.register(windBlade);
		 voidBlade = (new ItemSlashBladeVoid(ToolMaterial.IRON, 4.0F)).setRegistryName("VoidBlade");
		    	 ForgeRegistries.ITEMS.register(voidBlade);
				 SlashBlade.InitEventBus.register(new ItemSlashblade_Louguan_xf());
				 SlashBlade.InitEventBus.register(new ItemSlashblade_Bailou_xf());
		    	}
	if(Loader.isModLoaded("botania")){
		 SlashBlade.InitEventBus.register(new ItemSlashblade_BOT());
			}
    
    if(Loader.isModLoaded(IC2.MODID)){
    	 euBlade = (new ItemSlashblade_EU(ToolMaterial.IRON, 4.0F)).setRegistryName("EUBlade");
    	 ForgeRegistries.ITEMS.register(euBlade);
    	 SlashBlade.InitEventBus.register(new ItemSlashblade_1());
    	 SlashBlade.InitEventBus.register(new ItemSlashblade_2());
    	 SlashBlade.InitEventBus.register(new ItemSlashblade_3());
    	}
		 SlashBlade.InitEventBus.register(new ItemSlashblade_BambooLight_nice());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_BambooLight_yin());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_SilverBambooLight_nice());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_SilverBambooLight_gold());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_SilverBambooLight_blood());
    	 SlashBlade.InitEventBus.register(new ItemSlashblade_Eievui());
    	 SlashBlade.InitEventBus.register(new ItemSlashblade_sagequoia());
    	 SlashBlade.InitEventBus.register(new ItemSlashblade_lkaruga());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_Sweapon());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_Slashblade_old());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_Smith());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_Smith2());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_Smith3());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_Smith4());
		 
		 SlashBlade.InitEventBus.register(new ItemSlashblade_Blade_basic());
    	 SlashBlade.InitEventBus.register(new ItemSlashblade_Blade_bamboo_fake());
	     SlashBlade.InitEventBus.register(new ItemSlashblade_Blade_bamboo());
	 	 SlashBlade.InitEventBus.register(new ItemSlashblade_Template_fake());
	 	 SlashBlade.InitEventBus.register(new ItemSlashblade_Template2_fake());
	 	 SlashBlade.InitEventBus.register(new ItemSlashblade_Template());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_Template2());
		 
	     SlashBlade.InitEventBus.register(new ItemSlashblade_zheng());
	 	 SlashBlade.InitEventBus.register(new ItemSlashblade_pear());
	 	 SlashBlade.InitEventBus.register(new ItemSlashblade_yu());
	 	 SlashBlade.InitEventBus.register(new ItemSlashblade_luli());
	 	 SlashBlade.InitEventBus.register(new ItemSlashblade_liu());
	 	 SlashBlade.InitEventBus.register(new ItemSlashblade_943());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_117());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_yile());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_nethop());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_irsoft());	
		 
		 SlashBlade.InitEventBus.register(new ItemSlashblade_kogawa());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_kogawa2());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_kogawa3());
		 
		 SlashBlade.InitEventBus.register(new ItemSlashblade_Scorn_2());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_Scorn_3());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_Scorn_4());
		 SlashBlade.InitEventBus.register(new ItemSlashblade_Scorn());
}
}
