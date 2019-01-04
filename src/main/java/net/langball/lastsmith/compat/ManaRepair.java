package net.langball.lastsmith.compat;

import mods.flammpfeil.slashblade.entity.EntityBladeStand;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.util.SlashBladeEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import vazkii.botania.common.block.tile.mana.TilePool;

public class ManaRepair
{
  public static final String EffectKey = "ManaRepair";
  
  @SubscribeEvent
  public void onUpdateBladeStand(SlashBladeEvent.OnEntityBladeStandUpdateEvent event)
  {
    try
    {
      EntityBladeStand stand = (EntityBladeStand)ReflectionHelper.getPrivateValue(SlashBladeEvent.OnEntityBladeStandUpdateEvent.class, event, new String[] { "entityBladeStand" });
      if (!stand.hasBlade()) {
        return;
      }
      ItemStack blade = event.blade;
      if (!blade.isItemDamaged()) {
        return;
      }
      NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(blade);
      
      NBTTagCompound etag = tag.getCompoundTag("SB.SEffect");
      
      int reqiredLevel = etag.getInteger("ManaRepair");
      if (reqiredLevel == 0) {
        return;
      }
      int x = (int)Math.floor(stand.posX);
      int y = (int)Math.floor(stand.posY);
      int z = (int)Math.floor(stand.posZ);
      
      y--;
      
      TileEntity tile = stand.world.getTileEntity(new BlockPos(x, y, z));
      if (tile == null) {
        return;
      }
      if (!(tile instanceof TilePool)) {
        return;
      }
      TilePool pool = (TilePool)tile;
      

      int current = pool.getCurrentMana();
      if (current < 75) {
        return;
      }
      pool.recieveMana(-75);
      
      blade.setItemDamage(blade.getItemDamage() - 3);
    }
    catch (Throwable localThrowable) {}
  }
}
