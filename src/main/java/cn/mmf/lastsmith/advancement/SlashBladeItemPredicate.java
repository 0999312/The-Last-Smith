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
        		(BladeUtil.isMatchedBlade(stack, SlashBlade.getCustomBlade(blade_name))||
        		BladeUtil.isMatchedBlade(stack, SlashBlade.getCustomBlade(blade_name+".sample"))||
        		BladeUtil.isMatchedBlade(stack, BladeLoader.getCustomBlade(blade_name)));
    }
//    public static void init() {
//        ItemPredicates.register(new ResourceLocation(TLSMain.MODID, "slashblade"), SlashBladeItemPredicate::fromJson);
//    }
//
//    public static ItemPredicate fromJson(JsonObject json) {
//        return null;
//    }
	
}
