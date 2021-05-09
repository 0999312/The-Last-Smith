package cn.mmf.lastsmith;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = TLSMain.MODID, name = TLSMain.NAME, version = TLSMain.VERSION,
//      @[2.0.0,) @[1.0.0.0-MC1.12.2,)
dependencies="required-after:flammpfeil.slashblade@[mc1.12-r32,);required-after:mm_lib;after:sakura;after:thaumcraft;after:slashblade_addon")
public class TLSMain{
    public static final String MODID = "lastsmith";
    public static final String NAME = "The Last Smith";
    public static final String VERSION = "@version@";

    public static Logger logger;

	@SidedProxy(clientSide = "cn.mmf.lastsmith.ClientProxy",serverSide = "cn.mmf.lastsmith.CommonProxy")
	public static CommonProxy proxy; 
 
   
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		logger = event.getModLog();
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
	}
}
