package cn.mmf.lastsmith.event;

import org.apache.commons.lang3.StringUtils;

import cn.mmf.lastsmith.item.ItemLoader;
import cn.mmf.lastsmith.util.BladeUtil;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class AnvilEvent {
	@SubscribeEvent
	public static void MuninOldRecipe(AnvilUpdateEvent event) {
        if (!(event.getLeft().getItem() instanceof ItemSlashBlade))
            return;
        if (event.getRight() == null)
            return;
        if (!(event.getRight().getItem() == ItemLoader.SCROLL)||(event.getRight().getMetadata()!=17))
            return;
        event.setMaterialCost(1);
        ItemStack out = event.getLeft().copy();
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(out);
        if(!ItemSlashBladeNamed.CurrentItemName.get(tag).equalsIgnoreCase("flammpfeil.slashblade.named.smith.final"))
        	return;
		ItemSlashBlade.TextureName.set(tag, "named/smith/texture_final_old");
		ItemSlashBlade.ModelName.set(tag, "named/smith/model");
        out.setItemDamage(0);
        event.setCost(1);
        event.setOutput(out);
	}
	@SubscribeEvent
	public static void bewitchedBladeRecipe(AnvilUpdateEvent event) {
        if (!(event.getLeft().getItem() instanceof ItemSlashBlade))
            return;
        if (event.getRight() == null)
            return;
        if (!(event.getRight().getItem() == ItemLoader.MATERIALS))
            return;
        event.setMaterialCost(1);
        ItemStack out = event.getLeft().copy();
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(out);
        int cost = event.getCost();
        float repairFactor;
        switch (event.getRight().getItemDamage()) {
        case 3:
            cost = Math.max(2, cost);
            repairFactor = 0.4f;
            ItemSlashBlade.ProudSoul.add(tag, 100);
            break;
        case 4:
            cost = Math.max(3, cost);
            repairFactor = 0.6f;
            ItemSlashBlade.ProudSoul.add(tag, 500);
            break;
        case 5:
            cost = Math.max(4, cost);
            repairFactor = 0.7f;
            ItemSlashBlade.ProudSoul.add(tag, 2500);
            break;
        case 6:
            cost = Math.max(1, cost);
            repairFactor = 1f;
            ItemSlashBlade.ProudSoul.add(tag, 10000);
    		ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
    		BladeUtil.getInstance().IsBewitchedActived.set(tag, true);
            break;
        default:
            return;  
        }
        event.setCost(cost);

        ItemSlashBlade.RepairCount.add(tag, 1);

        int repair = Math.min(out.getItemDamage(), (int) (out.getMaxDamage() * repairFactor));

        out.setItemDamage(out.getItemDamage() - repair);

        if (StringUtils.isBlank(event.getName())){
            if (event.getLeft().hasDisplayName())
                out.clearCustomName();
        }else if (!event.getName().equals(event.getLeft().getDisplayName()))
            out.setStackDisplayName(event.getName());

        event.setOutput(out);
	}
}
