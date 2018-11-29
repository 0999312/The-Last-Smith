package net.langball.lastsmith;

import ic2.core.IC2;
import mods.flammpfeil.slashblade.client.model.BladeModel;
import mods.flammpfeil.slashblade.client.model.BladeSpecialRender;
import mods.flammpfeil.slashblade.tileentity.DummyTileEntity;
import net.langball.lastsmith.blade.BladeLoader;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.animation.FastTESR;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.Thaumcraft;

public class ModelReg {
	static final ModelResourceLocation modelLoc = new ModelResourceLocation("flammpfeil.slashblade:model/named/blade.obj");
    public static final ModelResourceLocation dummyLoc = new ModelResourceLocation("flammpfeil.slashblade:emptymodel");

    public ModelReg() {
        Slashblade_model(BladeLoader.blade);
        Slashblade_model(BladeLoader.blade_model);
        Slashblade_model(BladeLoader.bladeNamed);
        Slashblade_model(BladeLoader.blade_model_1);
        Slashblade_model(BladeLoader.blade_tls);
        Slashblade_model(BladeLoader.wrapper);
        Slashblade_model(BladeLoader.wrapper_bamboo);
        if(Loader.isModLoaded(IC2.MODID)){
        Slashblade_model(BladeLoader.euBlade);
        }
        if(Loader.isModLoaded(Thaumcraft.MODID)){
        Slashblade_model(BladeLoader.windBlade);
        Slashblade_model(BladeLoader.voidBlade);
        }
    }
    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item)
    {
        ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, model);
    }

    public static void Slashblade_model(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, modelLoc);
        ForgeHooksClient.registerTESRItemStack(item, 0, DummyTileEntity.class);
  	}
    
}
