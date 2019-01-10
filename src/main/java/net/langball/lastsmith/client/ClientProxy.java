package net.langball.lastsmith.client;

import net.langball.lastsmith.CommonProxy;
import net.langball.lastsmith.blocks.BlockLoader;
import net.langball.lastsmith.items.ItemLoader;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	public static final ModelResourceLocation modelLoc = new ModelResourceLocation("flammpfeil.slashblade:model/named/blade.obj","inventory");
	@Override
	    public void preInit(FMLPreInitializationEvent event)
	    {
	        super.preInit(event);
	        new ModelReg();
	        BlockLoader.registerRenders();
	        ItemLoader.registerRender();

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
