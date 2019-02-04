package net.langball.lastsmith.client;

import org.lwjgl.input.Keyboard;

import net.langball.lastsmith.CommonProxy;
import net.langball.lastsmith.Last_worker;
import net.langball.lastsmith.blocks.BlockLoader;
import net.langball.lastsmith.items.ItemLoader;
import net.langball.lastsmith.packet.PacketKeyMessage;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class ClientProxy extends CommonProxy {
	public static final ModelResourceLocation modelLoc = new ModelResourceLocation("flammpfeil.slashblade:model/named/blade.obj","inventory");
	public static KeyBinding ChangeMode;
	
		@Override
	    public void preInit(FMLPreInitializationEvent event)
	    {
	        super.preInit(event);
	        new ModelReg();
	        BlockLoader.registerRenders();
	        ItemLoader.registerRender();
	        MinecraftForge.EVENT_BUS.register(this);
	    }

		@SubscribeEvent
		public void KeyInput(InputEvent.KeyInputEvent event) {
			if(ChangeMode.isPressed()){
			System.out.print("|MO");
			 getNetwork().sendToServer(new PacketKeyMessage(Last_worker.MODID));
			 }
		}

	    @Override
	    public void init(FMLInitializationEvent event)
	    {
	        super.init(event);
	        ChangeMode = new KeyBinding("key.lastsmith.mode_change", Keyboard.KEY_V, "key.categories.lastsmith");
	        ClientRegistry.registerKeyBinding(ChangeMode); 
	    }

	    @Override
	    public void postInit(FMLPostInitializationEvent event)
	    {
	        super.postInit(event);
	       
	    }

}
