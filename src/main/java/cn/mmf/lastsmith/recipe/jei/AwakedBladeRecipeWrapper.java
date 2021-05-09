package cn.mmf.lastsmith.recipe.jei;

import cn.mmf.lastsmith.recipe.RecipeAwakeBladeTLS;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.plugins.vanilla.crafting.ShapedOreRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class AwakedBladeRecipeWrapper extends ShapedOreRecipeWrapper {
	private RecipeAwakeBladeTLS recipe;
	public AwakedBladeRecipeWrapper(IJeiHelpers jeiHelpers, RecipeAwakeBladeTLS recipe) {
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
