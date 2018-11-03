package net.langball.lastsmith.gui;

import net.langball.lastsmith.Last_worker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class GuiLoader implements IGuiHandler {
	public static final int GUI_BLAST = 1;

    public GuiLoader()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(Last_worker.instance, this);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
    	switch (ID)
        {
        case GUI_BLAST:
            return new ContainerBlast(player.inventory,(IInventory) world.getTileEntity(new BlockPos(x, y, z)));
        default:
            return null;
        }

    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
    	switch (ID)
        {
        case GUI_BLAST:
            return new GuiBlast(player.inventory,(IInventory) world.getTileEntity(new BlockPos(x, y, z)));
        default:
            return null;
        }

    }

}
