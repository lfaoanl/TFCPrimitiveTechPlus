package tfcprimitivetech.core;

import com.dunk.tfc.api.Constant.Global;
import com.dunk.tfc.api.Crafting.BarrelManager;
import com.dunk.tfc.api.Crafting.BarrelMultiItemRecipe;
import com.dunk.tfc.api.Crafting.BarrelRecipe;
import com.dunk.tfc.api.Crafting.CraftingManagerTFC;
import com.dunk.tfc.api.HeatIndex;
import com.dunk.tfc.api.HeatRegistry;
import com.dunk.tfc.api.TFCFluids;
import com.dunk.tfc.api.TFCItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tfcprimitivetech.TFCPrimitiveTech;

import java.util.List;

public class ModRecipes {


    // Plans

    public static void initialise() {

        System.out.println("[" + ModDetails.ModName + "] Registering Recipes");

        registerRecipes();

        RegisterItemHeat();

        registerBarrelRecipes();

        System.out.println("[" + ModDetails.ModName + "] Done Registering Recipes");
    }

    public static void initialiseAnvil() {
        // check if the plans/recipes have already been initialised.
        if (ModRecipes.areAnvilRecipesInitialised()) return;

        System.out.println("[" + ModDetails.ModName + "] Registering Anvil Recipes");


        System.out.println("[" + ModDetails.ModName + "] Done Registering Anvil Recipes");
    }

    public static void RegisterItemHeat() {
        HeatRegistry heatRegistry = HeatRegistry.getInstance();
        ItemStack ash = new ItemStack(TFCItems.powder, 1, 13);
        heatRegistry.addIndex(new HeatIndex(new ItemStack(ModItems.itemWoodenTwig, 1), 0.7f, 500f, ash));
    }

    public static boolean areAnvilRecipesInitialised() {
        return true;
    }


    private static void registerBarrelRecipes() {
        // A full barrel (10 limewater buckets) can process a 64 reeds
        BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.reeds, 1, 0), new FluidStack(TFCFluids.LIMEWATER, 156), new ItemStack(ModItems.itemCelluloseFibers, 1, 0), new FluidStack(TFCFluids.LIMEWATER, 156), 24))
                .setMinTechLevel(0)
                .setSealedRecipe(true)
                .setAllowAnyStack(false));

    }

    private static void RemoveRecipe(ItemStack resultItem) {
        List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
        for (int i = 0; i < recipes.size(); i++) {
            IRecipe tmpRecipe = recipes.get(i);
            ItemStack recipeResult = null;
            if (tmpRecipe instanceof ShapedRecipes) {
                ShapedRecipes recipe = (ShapedRecipes) tmpRecipe;
                recipeResult = recipe.getRecipeOutput();
            } else if (tmpRecipe instanceof ShapedOreRecipe) {
                ShapedOreRecipe recipe = (ShapedOreRecipe) tmpRecipe;
                recipeResult = recipe.getRecipeOutput();
            } else if (tmpRecipe instanceof ShapelessOreRecipe) {
                ShapelessOreRecipe recipe = (ShapelessOreRecipe) tmpRecipe;
                recipeResult = recipe.getRecipeOutput();
            }
            if (ItemStack.areItemStacksEqual(resultItem, recipeResult)) {
                recipes.remove(i--);
            }
        }
    }

    private static void registerRecipes() {
        // log + hammer = twig
        GameRegistry.addRecipe(new ShapelessOreRecipe(
                new ItemStack(ModItems.itemWoodenTwig, 3, 0),
                new Object[]{"logWood", "itemHammer"}
                ));

        // twig + knife = 2 stick
        GameRegistry.addRecipe(new ShapelessOreRecipe(
                new ItemStack(TFCItems.stick, 1, 0),
                new Object[]{ new ItemStack(ModItems.itemWoodenTwig, 1, 0), "itemKnife"}
                ));

        if (TFCPrimitiveTech.instance.isPaperEnabled) {
            // fibre + press = wet paper press
            GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.WoodenPressWet, 1, 0), new Object[]{new ItemStack(ModItems.itemWoodenPress, 1, 0), new ItemStack(ModItems.itemCelluloseFibers, 1, 0), new ItemStack(ModItems.itemCelluloseFibers, 1, 0), new ItemStack(ModItems.itemWoodenPress, 1, 0)});

            // 2 woodLumber = woodenpress
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemWoodenPress, 1, 0), new Object[]{"##", '#', "woodLumber"}));
        }

        if (TFCPrimitiveTech.instance.isSlingshotEnabled) {
            // twig + leather belt = slingshot
//            GameRegistry.addShapelessRecipe(new ItemStack(ModItems.itemSlingshot, 1, 0), new Object[]{ ModItems.itemWoodenTwig, ModItems.itemLeatherBelt });
            GameRegistry.addShapedRecipe(new ItemStack(ModItems.itemSlingshot, 1, 0),  new Object[] { "y]", 'y', ModItems.itemWoodenTwig, ']', ModItems.itemLeatherBelt});
        }

        CraftingManagerTFC.getInstance().addRecipe(new ItemStack(ModItems.itemLeatherBelt, 1), new Object[]{"#####", Character.valueOf('#'), TFCItems.flatLeather});
        CraftingManagerTFC.getInstance().addRecipe(new ItemStack(ModItems.itemLeatherBelt, 3), new Object[]{"#####", "     ", "#####", "     ", "#####", Character.valueOf('#'), TFCItems.flatLeather});

        for (int i = 0; i < Global.STONE_IGIN.length; i++) {
            CraftingManagerTFC.getInstance().addRecipe(new ItemStack(ModItems.itemHardStone, 4), new Object[]
                    {"## ##", "## ##", "     ", "## ##", "## ##", Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, i + Global.STONE_IGIN_START)});
        }
        for (int i = 0; i < Global.STONE_IGEX.length; i++) {
            CraftingManagerTFC.getInstance().addRecipe(new ItemStack(ModItems.itemHardStone, 4), new Object[]
                    {"## ##", "## ##", "     ", "## ##", "## ##", Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, i + Global.STONE_IGEX_START)});
        }
        for (int i = 0; i < Global.STONE_MM.length; i++) {
            CraftingManagerTFC.getInstance().addRecipe(new ItemStack(ModItems.itemSharpStone, 4), new Object[]
                    {"## ##", "## ##", "     ", "## ##", "## ##", Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, i + Global.STONE_MM_START)});
        }
        for (int i = 0; i < Global.STONE_SED.length; i++) {
            CraftingManagerTFC.getInstance().addRecipe(new ItemStack(ModItems.itemSoftStone, 4), new Object[]
                    {"## ##", "## ##", "     ", "## ##", "## ##", Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, i + Global.STONE_SED_START)});
        }

        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.itemWoodenClub, 1, 0), new Object[]{new ItemStack(Items.flint, 1, 0), new ItemStack(Items.flint, 1, 0), new ItemStack(Items.flint, 1, 0), new ItemStack(TFCItems.logs, 1, -1)});

        if (TFCPrimitiveTech.instance.isPaperEnabled)
            RemoveRecipe(new ItemStack(Items.paper, 3));

    }
}
