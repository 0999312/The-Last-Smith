package cn.mmf.lastsmith;

import cn.mmf.lastsmith.advancement.SlashBladeItemPredicate;
import cn.mmf.lastsmith.blades.BladeLoader;
import cn.mmf.lastsmith.block.BlockLoader;
import cn.mmf.lastsmith.event.RegisterSlashBladeEvent;
import cn.mmf.lastsmith.event.RegisterSlashBladeRecipeEvent;
import cn.mmf.lastsmith.item.ItemLoader;
import cn.mmf.lastsmith.packet.PacketKeyMessage;
import cn.mmf.lastsmith.packet.PacketKeyMessageHandler;
import net.minecraft.advancements.Advancement;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.advancements.critereon.ItemPredicates;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber
public class CommonProxy {
	public static CreativeTabs tab = new CreativeTabsTLS();
    private static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(TLSMain.MODID);
    public static SimpleNetworkWrapper getNetwork() {
        return network;
    }
	public void preInit(FMLPreInitializationEvent event) {
		new BlockLoader();
		new ItemLoader();
		new BladeLoader();
	}
	
    public void init(FMLInitializationEvent event) { 
    	GameRegistry.addSmelting(new ItemStack(ItemLoader.BLADE,1,9), new ItemStack(ItemLoader.BLADE_HEATTED,1,0), 1F);
    	GameRegistry.addSmelting(new ItemStack(ItemLoader.BLADE,1,10), new ItemStack(ItemLoader.BLADE_HEATTED,1,1), 1F);
    	GameRegistry.addSmelting(new ItemStack(ItemLoader.BLADE,1,11), new ItemStack(ItemLoader.BLADE_HEATTED,1,2), 1F);
    	ItemPredicates.register(new ResourceLocation(TLSMain.MODID, "slashblade"), SlashBladeItemPredicate::new);
    	MinecraftForge.EVENT_BUS.post(new RegisterSlashBladeEvent(event));
    	network.registerMessage(new PacketKeyMessageHandler(),PacketKeyMessage.class,0,Side.SERVER);
    }

    public void postInit(FMLPostInitializationEvent event) {
    	MinecraftForge.EVENT_BUS.post(new RegisterSlashBladeRecipeEvent(event));
    }
	public boolean doesPlayerHaveAdvancement(EntityPlayer player, ResourceLocation advId) {
		if (player instanceof EntityPlayerMP) {
			TLSMain.logger.info("PLPLPLPLPLPL");
			Advancement adv = ((EntityPlayerMP) player).getServerWorld().getAdvancementManager().getAdvancement(advId);
			if(adv == null){
				TLSMain.logger.info("adv not found:");
				return false;
			}
			TLSMain.logger.info(((EntityPlayerMP) player).getAdvancements().getProgress(adv).isDone());
			return ((EntityPlayerMP) player).getAdvancements().getProgress(adv).isDone();
		}
		return false;
	}
}
