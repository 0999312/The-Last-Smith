package cn.mmf.lastsmith.blades.handmade;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.mcmod_mmf.mmlib.util.OreWildcardIngredient;
import cn.mcmod_mmf.mmlib.util.RecipesUtil;
import cn.mmf.lastsmith.TLSMain;
import cn.mmf.lastsmith.blades.BladeLoader;
import cn.mmf.lastsmith.event.RegisterSlashBladeRecipeEvent;
import cn.mmf.lastsmith.item.ItemLoader;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;

@EventBusSubscriber
public class HandMadeRecipeRegEvent {
	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		if(Loader.isModLoaded("tfc")) {
			RecipesUtil.getInstance().addRecipe(TLSMain.MODID, "lastsmith.handmade_saya",
				new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid, "handmade_saya"),
				BladeLoader.bladeSayaHandmade,
				new Object[] { "  W", " B ", "LSH", 'W', SlashBlade.wrapBlade, 'B',
					new ItemStack(ItemLoader.MATERIALS, 1, 0), 'L', "logWood", 'H',
					new OreWildcardIngredient("toolForginghammer"), 'S', new OreWildcardIngredient("saw") }));
			RecipesUtil.getInstance().addRecipe(TLSMain.MODID, "lastsmith.handmade_saya_1",
					new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid, "handmade_saya_1"),
					BladeLoader.getInstance().getCustomBlade("lastsmith.handmade_saya_1"),
					new Object[] { " AW", "AB ", "LSH", 'W', SlashBlade.wrapBlade, 'B',
						new ItemStack(ItemLoader.MATERIALS, 1, 0), 'L', "logWood", 'H',
						new OreWildcardIngredient("toolForginghammer"), 'S', new OreWildcardIngredient("saw"),'A',"leafSakura" }));
			RecipesUtil.getInstance().addRecipe(TLSMain.MODID, "lastsmith.handmade_saya_2",
					new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid, "handmade_saya_2"),
					BladeLoader.getInstance().getCustomBlade("lastsmith.handmade_saya_2"),
					new Object[] { " AW", "AB ", "LSH", 'W', SlashBlade.wrapBlade, 'B',
						new ItemStack(ItemLoader.MATERIALS, 1, 0), 'L', "logWood", 'H',
						new OreWildcardIngredient("toolForginghammer"),'S', new OreWildcardIngredient("saw"),'A',"gemPrismarine" }));
		}else {
			RecipesUtil.getInstance().addRecipe(TLSMain.MODID, "lastsmith.handmade_saya",
				new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid, "handmade_saya"),
				BladeLoader.bladeSayaHandmade,
				new Object[] { "  W", " B ", "L H", 'W', SlashBlade.wrapBlade, 'B',
					new ItemStack(ItemLoader.MATERIALS, 1, 0), 'L', "logWood", 'H',
					new OreWildcardIngredient("toolForginghammer") }));
			RecipesUtil.getInstance().addRecipe(TLSMain.MODID, "lastsmith.handmade_saya_1",
					new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid, "handmade_saya_1"),
					BladeLoader.getInstance().getCustomBlade("lastsmith.handmade_saya_1"),
					new Object[] { " AW", "AB ", "L H", 'W', SlashBlade.wrapBlade, 'B',
						new ItemStack(ItemLoader.MATERIALS, 1, 0), 'L', "logWood", 'H',
						new OreWildcardIngredient("toolForginghammer"),'A',"leafSakura" }));
			RecipesUtil.getInstance().addRecipe(TLSMain.MODID, "lastsmith.handmade_saya_2",
					new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid, "handmade_saya_2"),
					BladeLoader.getInstance().getCustomBlade("lastsmith.handmade_saya_2"),
					new Object[] { " AW", "AB ", "L H", 'W', SlashBlade.wrapBlade, 'B',
						new ItemStack(ItemLoader.MATERIALS, 1, 0), 'L', "logWood", 'H',
						new OreWildcardIngredient("toolForginghammer"),'A',"gemPrismarine" }));
		}
	}
	@SubscribeEvent
	public static void BladeRecipe(AnvilUpdateEvent event) {
        if (!(event.getLeft().getItem() instanceof ItemSlashBladeHandmade))
            return;
        if (event.getRight() == null)
            return;
        if (!(event.getRight().getItem() instanceof ItemSlashBladeSayaHandmade)||event.getRight().getItem() instanceof ItemEnchantedBook)
            return;

        ItemStack out = event.getLeft().copy();
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(out);
        if(ItemSlashBladeHandmade.SheathName.exists(tag))
        	return ;
        
        ItemStack in = event.getRight().copy();
        NBTTagCompound tagin = ItemSlashBlade.getItemTagCompound(in);
        String string_in = "handmade/sheath_0";
        if(!ItemSlashBlade.TextureName.get(tagin).isEmpty())
        	string_in = ItemSlashBlade.TextureName.get(tagin).substring(0, 17);
        StringBuilder builderin = new StringBuilder(string_in);

        String string_out = "";
        if(!ItemSlashBlade.TextureName.get(tag).isEmpty())
        	string_out = ItemSlashBlade.TextureName.get(tag).substring(14);
        
        ItemSlashBlade.TextureName.set(tag, builderin.append(string_out).toString());
		ItemSlashBlade.ModelName.set(tag, "named/smith/model");
		
		{
            Map<Enchantment,Integer> newItemEnchants = EnchantmentHelper.getEnchantments(out);
            Map<Enchantment,Integer> oldItemEnchants = EnchantmentHelper.getEnchantments(in);
            for(Enchantment enchantIndex : oldItemEnchants.keySet())
            {
                Enchantment enchantment = enchantIndex;
                int destLevel = newItemEnchants.containsKey(enchantIndex) ? newItemEnchants.get(enchantIndex) : 0;
                int srcLevel = oldItemEnchants.get(enchantIndex);
                srcLevel = Math.max(srcLevel, destLevel);
                srcLevel = Math.min(srcLevel, enchantment.getMaxLevel());
                boolean canApplyFlag = enchantment.canApply(out);
                if(canApplyFlag){
                    for(Enchantment curEnchantIndex : newItemEnchants.keySet()){
                        if (curEnchantIndex != enchantIndex && !enchantment.isCompatibleWith(curEnchantIndex) /*canApplyTogether*/)
                        {
                            canApplyFlag = false;
                            break;
                        }
                    }
                    if (canApplyFlag)
                        newItemEnchants.put(enchantIndex, Integer.valueOf(srcLevel));
                }
            }
            EnchantmentHelper.setEnchantments(newItemEnchants, out);
        }
        
        event.setCost(1);
        
        if (StringUtils.isBlank(event.getName())){
            if (event.getLeft().hasDisplayName())
                out.clearCustomName();
        }else if (!event.getName().equals(event.getLeft().getDisplayName()))
            out.setStackDisplayName(event.getName());

        event.setOutput(out);
	}
}
