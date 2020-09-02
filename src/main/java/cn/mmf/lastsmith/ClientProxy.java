package cn.mmf.lastsmith;

import org.lwjgl.input.Keyboard;

import cn.mmf.lastsmith.blades.BladeLoader;
import cn.mmf.lastsmith.block.BlockLoader;
import cn.mmf.lastsmith.item.ItemLoader;
import cn.mmf.lastsmith.packet.PacketKeyMessage;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.ClientAdvancementManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

@EventBusSubscriber
public class ClientProxy extends CommonProxy {
	public static final KeyBinding ChangeMode = new KeyBinding("key.flammpfeil.slashblade.mode_change", Keyboard.KEY_V, "key.categories.lastsmith");
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		ItemLoader.getInstance().renderItem();
		BlockLoader.getInstance().registerRenders();
		BladeLoader.getInstance().renderSlashBlade();
	}
	
	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		ClientRegistry.registerKeyBinding(ChangeMode); 
	}
	@SubscribeEvent
	public static void KeyInput(InputEvent.KeyInputEvent event) {
		if(ChangeMode.isPressed()){
		getNetwork().sendToServer(new PacketKeyMessage(TLSMain.MODID));
		}
	}
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
	@Override
	public boolean doesPlayerHaveAdvancement(EntityPlayer player, ResourceLocation advId) {
		if (player instanceof EntityPlayerSP) {
			ClientAdvancementManager manager = ((EntityPlayerSP) player).connection.getAdvancementManager();
			Advancement adv = manager.getAdvancementList().getAdvancement(advId);
			if (adv == null) return false;
			AdvancementProgress progress = manager.advancementToProgress.get(adv);
			return progress != null && progress.isDone();
		}

		return super.doesPlayerHaveAdvancement(player, advId);
	}
}
