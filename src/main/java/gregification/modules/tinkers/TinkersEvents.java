package gregification.modules.tinkers;

import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.events.TinkerRegisterEvent.MeltingRegisterEvent;
import slimeknights.tconstruct.library.smeltery.MeltingRecipe;

import java.util.ArrayList;
import java.util.List;

public class TinkersEvents {

    private static List<Fluid> blastMaterials;

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onMeltingRegisterEvent(MeltingRegisterEvent event) {
        MeltingRecipe recipe = event.getRecipe();
        if (getBlastMaterials().contains(recipe.getResult().getFluid())) {
            event.setCanceled(true);
        }
    }

    private static List<Fluid> getBlastMaterials() {
        if (blastMaterials != null) return blastMaterials;
        blastMaterials = new ArrayList<>();
        for (Material m : GregTechAPI.MATERIAL_REGISTRY) {
            if (m.hasProperty(PropertyKey.BLAST) && m.hasProperty(PropertyKey.FLUID)) {
                blastMaterials.add(m.getFluid());
            }
        }
        return blastMaterials;
    }
}
