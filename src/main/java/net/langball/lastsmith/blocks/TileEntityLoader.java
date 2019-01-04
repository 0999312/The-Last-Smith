package net.langball.lastsmith.blocks;

import net.langball.lastsmith.Last_worker;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityLoader
{
    public TileEntityLoader(FMLPreInitializationEvent event)
    {
        registerTileEntity(TileEntityBlast.class, "BlasrFurnace");
    }

    @SuppressWarnings("deprecation")
	public void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id)
    {
        GameRegistry.registerTileEntity(tileEntityClass, Last_worker.MODID + ":" + id);
    }
}
