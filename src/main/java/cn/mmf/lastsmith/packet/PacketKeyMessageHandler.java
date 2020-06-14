package cn.mmf.lastsmith.packet;

import cn.mmf.lastsmith.util.IMultiModeBlade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketKeyMessageHandler implements IMessageHandler<PacketKeyMessage, IMessage> {

	@Override
	public IMessage onMessage(PacketKeyMessage message, MessageContext ctx) {
		FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
			EntityPlayer player = ctx.getServerHandler().player;
			if (isPlayerHoldingMultiModeItem(player) && incrHeldMultiModeItemState(player)) {
				ItemStack heldItem = getHeldMultiModeStack(player);
				((IMultiModeBlade) heldItem.getItem()).onModeChange(player, heldItem);
			}
		});
		return null;
	}
	public static boolean incrHeldMultiModeItemState(EntityPlayer player) {

		if (!(!player.getHeldItemMainhand().isEmpty() || !player.getHeldItemOffhand().isEmpty())) {
			return false;
		}
		ItemStack heldItem =  player.getHeldItemMainhand();
		Item equipped = heldItem.getItem();
		if (equipped instanceof IMultiModeBlade) {
			IMultiModeBlade multiModeItem = (IMultiModeBlade) equipped;
			return multiModeItem.incrMode(heldItem);
		}
		heldItem =  player.getHeldItemOffhand();
		equipped = heldItem.getItem();
		IMultiModeBlade multiModeItem = (IMultiModeBlade) equipped;
		return multiModeItem.incrMode(heldItem);
	}
	
	public static ItemStack getHeldMultiModeStack(EntityPlayer player) {
		ItemStack stack = player.getHeldItemMainhand();
		if (stack.isEmpty() || !(stack.getItem() instanceof IMultiModeBlade)) {
			stack = player.getHeldItemOffhand();
		}
		return stack;
	}
	public static boolean isPlayerHoldingMultiModeItem(EntityPlayer player) {

		if (!(!player.getHeldItemMainhand().isEmpty() || !player.getHeldItemOffhand().isEmpty())) {
			return false;
		}
		ItemStack heldItem = player.getHeldItemMainhand();
		if (heldItem.getItem() instanceof IMultiModeBlade) {
			return true;
		}
		heldItem = player.getHeldItemOffhand();
		return heldItem.getItem() instanceof IMultiModeBlade;
	}
}
