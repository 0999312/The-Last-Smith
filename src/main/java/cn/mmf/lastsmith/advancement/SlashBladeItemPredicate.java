package cn.mmf.lastsmith.advancement;

import com.google.gson.JsonObject;

import cn.mmf.lastsmith.blades.BladeLoader;
import cn.mmf.lastsmith.util.BladeUtil;
import mods.flammpfeil.slashblade.SlashBlade;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;


public class SlashBladeItemPredicate extends ItemPredicate {
    private final String blade_name;

    public SlashBladeItemPredicate(String ore) {
        this.blade_name = ore;
    }

    public SlashBladeItemPredicate(JsonObject jsonObject) { this(JsonUtils.getString(jsonObject, "name")); }

    @Override
    public boolean test(ItemStack stack) {
        return !stack.isEmpty() && 
        		(BladeUtil.getInstance().isMatchedBlade(stack, SlashBlade.getCustomBlade(blade_name))||
        		BladeUtil.getInstance().isMatchedBlade(stack, SlashBlade.getCustomBlade(blade_name+".sample"))||
        		BladeUtil.getInstance().isMatchedBlade(stack, BladeLoader.getInstance().getCustomBlade(blade_name)));
    }
	
}
