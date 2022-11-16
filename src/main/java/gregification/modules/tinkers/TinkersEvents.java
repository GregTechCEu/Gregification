package gregification.modules.tinkers;

import gregtech.api.GregTechAPI;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.library.events.TinkerRegisterEvent.MeltingRegisterEvent;
import slimeknights.tconstruct.library.smeltery.MeltingRecipe;

import java.util.HashMap;
import java.util.Map;

import static gregtech.api.unification.ore.OrePrefix.*;

public class TinkersEvents {

    private static Map<Fluid, Material> blastMaterials;

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onMeltingRegisterEvent(MeltingRegisterEvent event) {
        MeltingRecipe recipe = event.getRecipe();
        Material m = getBlastMaterials().get(recipe.getResult().getFluid());
        if (m != null && (matches(recipe.input, ore, m) || matches(recipe.input, dust, m))) {
            event.setCanceled(true);
        }
    }

    private static Map<Fluid, Material> getBlastMaterials() {
        Map<Fluid, Material> map = blastMaterials;
        if (map == null) {
            map = new HashMap<>();
            for (Material m : GregTechAPI.MATERIAL_REGISTRY) {
                if (m.hasProperty(PropertyKey.BLAST) && m.hasProperty(PropertyKey.FLUID)) {
                    map.put(m.getFluid(), m);
                }
            }
            blastMaterials = map;
        }
        return map;
    }

    private static boolean matches(RecipeMatch input, OrePrefix p, Material m) {
        return input.matches(NonNullList.withSize(1, OreDictUnifier.get(p, m))).isPresent();
    }
}
