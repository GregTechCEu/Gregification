package gregification.exnihilo;

import exnihilocreatio.ModItems;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class MeshRecipes {
    public static void init() {
        ModHandler.removeRecipeByName("exnihilocreatio:item_mesh_2");
        ModHandler.addShapedRecipe("tin_alloy_mesh", new ItemStack(ModItems.mesh, 1, 2), "TST", "STS", "TST",
                'T', new UnificationEntry(OrePrefix.stick, Materials.TinAlloy),
                'S', new ItemStack(Items.STRING)
                );
        ModHandler.removeRecipeByName("exnihilocreatio:item_mesh_3");
        ModHandler.addShapedRecipe("steel_mesh", new ItemStack(ModItems.mesh, 1, 3), "TST", "STS", "TST",
                'T', new UnificationEntry(OrePrefix.stick, Materials.Steel),
                'S', new ItemStack(Items.STRING)
        );
        ModHandler.removeRecipeByName("exnihilocreatio:item_mesh_4");
        ModHandler.addShapedRecipe("aluminium_mesh", new ItemStack(ModItems.mesh, 1, 4), "TST", "STS", "TST",
                'T', new UnificationEntry(OrePrefix.stick, Materials.Aluminium),
                'S', new ItemStack(Items.STRING)
        );
    }

}
