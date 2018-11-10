package net.langball.lastsmith.recipe;

import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FakeRecipeLoader {
	 @SubscribeEvent
	 public void init(InitEvent event) {
		  {
			  String YuzukiTukumo = "flammpfeil.slashblade.named.yuzukitukumo";
			  ItemStack customblade = SlashBlade.getCustomBlade(YuzukiTukumo);
              ItemStack custombladeReqired = new ItemStack(SlashBlade.weapon,1,0);
              NBTTagCompound tagReqired = new NBTTagCompound();
              custombladeReqired.setTagCompound(tagReqired);
              custombladeReqired.addEnchantment(Enchantments.FIRE_ASPECT, 1);
              ItemSlashBlade.KillCount.set(tagReqired, 1000);
              SlashBlade.addRecipe(YuzukiTukumo,
                      new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,"tukumo_fake"),
                              customblade,
                              custombladeReqired,
                              "ESD",
                              "RBL",
                              "ISG",
                              'E', new ItemStack(Blocks.EMERALD_BLOCK),
                              'D', new ItemStack(Blocks.DIAMOND_BLOCK),
                              'R', new ItemStack(Blocks.REDSTONE_BLOCK),
                              'L', new ItemStack(Blocks.LAPIS_BLOCK),
                              'I', new ItemStack(Blocks.IRON_BLOCK),
                              'G', new ItemStack(Blocks.GOLD_BLOCK),
                              'S', SlashBlade.findItemStack(SlashBlade.modid, SlashBlade.SphereBladeSoulStr, 1),
                              'B', custombladeReqired));
          }
		  {
			  String nameTrue = "flammpfeil.slashblade.named.yamato";
			  ItemStack reqiredBlade = SlashBlade.findItemStack(SlashBlade.modid,nameTrue,1);
              NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
              ItemSlashBlade.IsBroken.set(reqTag, true);
              ItemSlashBlade.IsNoScabbard.set(reqTag, true);
              ItemSlashBlade.IsSealed.set(reqTag, true);
              ItemSlashBlade.ProudSoul.set(reqTag, 1000);

              ItemStack yamato = SlashBlade.findItemStack(SlashBlade.modid,nameTrue,1);
              SlashBlade.addRecipe(nameTrue,
                      new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,"yamato_fake"),
                              yamato,
                              reqiredBlade,
                              "XXX",
                              "XBX",
                              "XXX",
                              'X', SlashBlade.findItemStack(SlashBlade.modid,SlashBlade.SphereBladeSoulStr,1),
                              'B', reqiredBlade));
		  }
		  {
	            ItemStack reqiredBlade = new ItemStack(SlashBlade.bladeWood);
	            NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
	            ItemSlashBlade.KillCount.set(reqTag,1000);

	            String Tagayasan =  "flammpfeil.slashblade.named.tagayasan";
	            ItemStack customblade = SlashBlade.findItemStack(SlashBlade.modid,Tagayasan,1);
	            SlashBlade.addRecipe(Tagayasan,
	                    new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,"tagayasan_fake"),
	                            customblade,
	                    reqiredBlade,
	                    "XEX",
	                    "PBP",
	                    "XEX",
	                    'X', SlashBlade.findItemStack(SlashBlade.modid,SlashBlade.SphereBladeSoulStr,1),
	                    'B',reqiredBlade,
	                    'P',new ItemStack(Items.ENDER_PEARL),
	                    'E',new ItemStack(Items.ENDER_EYE)));
	     }
		  {
			   String name = "flammpfeil.slashblade.named.muramasa";
		        ItemStack proudsoul = SlashBlade.findItemStack(SlashBlade.modid,SlashBlade.SphereBladeSoulStr,1);
		        {
		            ItemStack blade = SlashBlade.getCustomBlade(SlashBlade.modid,name);
		            ItemStack reqiredBlade = SlashBlade.findItemStack(SlashBlade.modid,"slashblade",1);
		            {
		                NBTTagCompound tag = new NBTTagCompound();
		                reqiredBlade.setTagCompound(tag);
		                ItemSlashBlade.ProudSoul.set(tag, 10000);
		                ItemSlashBlade.RepairCount.set(tag,20);
		            }
		            IRecipe recipe = new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,"muramasa_fake"),
		                    blade,reqiredBlade,
		                    "PPP",
		                    "PXP",
		                    "PPP",
		                    'X', reqiredBlade,
		                    'P', proudsoul);

		            SlashBlade.addRecipe(name, recipe);
		        }
		  }
		  {
			  ItemStack itemProudSoul = SlashBlade.findItemStack(SlashBlade.modid, SlashBlade.ProudSoulStr , 1);
		        ItemStack itemSphereBladeSoul = SlashBlade.findItemStack(SlashBlade.modid, SlashBlade.SphereBladeSoulStr , 1);
	            String nameAgito = "flammpfeil.slashblade.named.agito";
	            String nameAgitoRust = nameAgito + ".rust";
	            String nameOrotiagito = "flammpfeil.slashblade.named.orotiagito";
	            String nameOrotiagitoSeald = nameOrotiagito + ".seald";
	            String nameOrotiagitoRust = nameOrotiagito + ".rust";
	            {
                    ItemStack reqiredBlade = SlashBlade.getCustomBlade(SlashBlade.modid,nameAgitoRust);
                    NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
                    ItemSlashBlade.KillCount.set(reqTag,100);
                    ItemSlashBlade.RepairCount.set(reqTag,1);

                    ItemStack destBlade = SlashBlade.findItemStack(SlashBlade.modid,nameAgito,1);
                    SlashBlade.addRecipe(nameAgito,
                            new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,"agito_fake"),
                                    destBlade,
                            reqiredBlade,
                            " X ",
                            "XBX",
                            " X ",
                            'X',itemProudSoul,
                            'B',reqiredBlade));
                }
	            {
                    ItemStack reqiredBlade =SlashBlade.getCustomBlade(SlashBlade.modid,nameOrotiagitoSeald);
                    NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
                    ItemSlashBlade.KillCount.set(reqTag, 1000);
                    ItemSlashBlade.ProudSoul.set(reqTag,1000);
                    ItemSlashBlade.RepairCount.set(reqTag, 10);
                    ItemStack destBlade = SlashBlade.findItemStack(SlashBlade.modid,nameOrotiagito,1);
                    SlashBlade.addRecipe(nameOrotiagito,
                            new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,"agito_ex_fake"),
                                    destBlade,
                            reqiredBlade,
                            "PXP",
                            "XBX",
                            "PXP",
                            'X',itemSphereBladeSoul,
                            'P',itemProudSoul,
                            'B',reqiredBlade));
               }
	            {
                    ItemStack reqiredBlade = SlashBlade.getCustomBlade(SlashBlade.modid,nameOrotiagitoRust);
                    NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
                    ItemSlashBlade.KillCount.set(reqTag, 100);
                    ItemSlashBlade.RepairCount.set(reqTag, 1);

                    ItemStack destBlade = SlashBlade.findItemStack(SlashBlade.modid,nameOrotiagitoSeald,1);
                    SlashBlade.addRecipe(nameOrotiagitoSeald,
                            new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,"agito2_fake"),
                                    destBlade,
                            reqiredBlade,
                            " X ",
                            "XBX",
                            " X ",
                            'X',itemProudSoul,
                            'B',reqiredBlade));
                }  
		  }
		  {
			    String name = "flammpfeil.slashblade.named.sabigatana";
			    String namedou = "flammpfeil.slashblade.named.doutanuki";
			    {
			    	 ItemStack reqiredBlade = SlashBlade.getCustomBlade(SlashBlade.modid,name);
	                 NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
	                 ItemSlashBlade.IsNoScabbard.set(reqTag, true);
	                 SlashBlade.addRecipe("sheath_fake", new RecipeAwakeBlade
	                		 (new ResourceLocation(SlashBlade.modid,"recipex_fake"),SlashBlade.getCustomBlade(SlashBlade.modid,name),reqiredBlade, new Object[]{
	                         "  P",
	                         " S ",
	                         "B  ",
	                         'P', SlashBlade.findItemStack(SlashBlade.modid, SlashBlade.ProudSoulStr, 1),
	                         'S', SlashBlade.findItemStack(SlashBlade.modid, "slashbladeWrapper", 1),
	                         'B', reqiredBlade}
	                		 ));
	                 ItemStack reqiredBlade1 = SlashBlade.getCustomBlade(SlashBlade.modid,name);
	                 NBTTagCompound reqTag1 = ItemSlashBlade.getItemTagCompound(reqiredBlade1);
	                 ItemSlashBlade.IsNoScabbard.set(reqTag1, true);
	                 ItemSlashBlade.IsBroken.set(reqTag1, true);
	                 SlashBlade.addRecipe("break_fake", new RecipeAwakeBlade
	                		 (new ResourceLocation(SlashBlade.modid,"recipexx_fake"),reqiredBlade,reqiredBlade1, new Object[]{
	                         "  P",
	                         " P ",
	                         "B  ",
	                         'P', SlashBlade.findItemStack(SlashBlade.modid, SlashBlade.IngotBladeSoulStr, 1),
	                         'B', reqiredBlade1}
	                		 ));
	                 
			    }
			    ItemStack reqiredBlade = SlashBlade.getCustomBlade(SlashBlade.modid,name);
                NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
                ItemSlashBlade.RepairCount.set(reqTag, 5);
                ItemSlashBlade.ProudSoul.set(reqTag, 1000);
                ItemSlashBlade.KillCount.set(reqTag, 100);
                SlashBlade.addRecipe("doutanuki_fake", new RecipeAwakeBlade
               		 (new ResourceLocation(SlashBlade.modid,"doutanuki_fake"),SlashBlade.getCustomBlade(SlashBlade.modid,namedou),reqiredBlade, new Object[]{
                        "  P",
                        " B ",
                        "P  ",
                        'P', SlashBlade.findItemStack(SlashBlade.modid, SlashBlade.SphereBladeSoulStr, 1),
                        'B', reqiredBlade}
               		 )); 
		  }
		  
	 }
}
