package cn.mmf.lastsmith.recipe.jei;

import cn.mmf.lastsmith.recipe.RecipeTriBladeTLS;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.plugins.vanilla.crafting.ShapedOreRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class TriBladeRecipeWrapper extends ShapedOreRecipeWrapper {
	private RecipeTriBladeTLS recipe;
	public TriBladeRecipeWrapper(IJeiHelpers jeiHelpers, RecipeTriBladeTLS recipe) {
		super(jeiHelpers, recipe);
		this.recipe=recipe;
	}
    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        super.drawInfo(minecraft, recipeWidth, recipeHeight, mouseX, mouseY);
        StringBuilder builder = new StringBuilder("jei.tls.tip.stage.");
        builder.append(recipe.getAdvancementName());
        minecraft.fontRenderer.drawString(I18n.format(builder.toString()), 0, -11, 0xBF0000);
    }
}
