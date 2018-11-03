package net.langball.lastsmith;

import mods.flammpfeil.slashblade.ModelRendererProxy;
import mods.flammpfeil.slashblade.client.model.BladeModel;
import mods.flammpfeil.slashblade.client.model.BladeModelManager;
import mods.flammpfeil.slashblade.tileentity.DummyTileEntity;
import net.langball.lastsmith.blocks.BlockLoader;
import net.langball.lastsmith.items.ItemLoader;
import net.langball.lastsmith.sa.EntitySA;
import net.langball.lastsmith.sa.RenderSA;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends CommonProxy {
	static final ModelResourceLocation modelLoc = new ModelResourceLocation("flammpfeil.slashblade:model/named/blade.obj");
	@Override
	    public void preInit(FMLPreInitializationEvent event)
	    {
	        super.preInit(event);
	        new ModelReg();
	        BlockLoader.registerRenders();
	        ItemLoader.registerRender();
	        RenderingRegistry.registerEntityRenderingHandler(EntitySA.class, new IRenderFactory<EntitySA>() {
	            @Override
	            public Render<? super EntitySA> createRenderFor(RenderManager manager) {
	                return new RenderSA(manager);
	            }
	        });
	    }


	    @Override
	    public void init(FMLInitializationEvent event)
	    {
	        super.init(event);
	       
	        
	    }

	    @Override
	    public void postInit(FMLPostInitializationEvent event)
	    {
	        super.postInit(event);
	       
	    }

}
