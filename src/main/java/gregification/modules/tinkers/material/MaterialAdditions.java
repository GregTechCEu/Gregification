package gregification.modules.tinkers.material;

import gregtech.api.unification.material.Materials;

import static gregification.modules.tinkers.traits.TinkersTraits.*;
import static slimeknights.tconstruct.library.materials.MaterialTypes.*;
import static slimeknights.tconstruct.tools.TinkerTraits.*;

public class MaterialAdditions {

    /**
     * IDEAS:
     * - Unburnable
     *     - Like Netherrite, cannot be destroyed in lava if a tool has this
     *     - Auto-apply to any fluid above MC lava temp (1300K)
     *
     * - Salty
     *     - Modifier applied with Salt
     *     - Gives a potion effect to the entity hit preventing them from healing
     *
     * - Explosive
     *     - Modifier applied with Gelled Toluene
     *     - Has a chance to cause a small explosion on entity hit (ideally not damage player)
     *
     * - Rework radioactive trait to have levels, give different levels depending on material and material type
     */

    public static void init() {

        // Cancellations, to avoid autogen nastiness
        MaterialProcessing.getBuilder(Materials.Diamond).cancel();

        // Modifications
        MaterialProcessing.getBuilder(Materials.Flint);
        MaterialProcessing.getBuilder(Materials.Iron);
        MaterialProcessing.getBuilder(Materials.Bronze);
        MaterialProcessing.getBuilder(Materials.WroughtIron);
        MaterialProcessing.getBuilder(Materials.Invar);
        MaterialProcessing.getBuilder(Materials.DamascusSteel);

        MaterialProcessing.getBuilder(Materials.Steel);
        MaterialProcessing.getBuilder(Materials.CobaltBrass);

        MaterialProcessing.getBuilder(Materials.Aluminium);
        MaterialProcessing.getBuilder(Materials.VanadiumSteel);
        MaterialProcessing.getBuilder(Materials.SterlingSilver);
        MaterialProcessing.getBuilder(Materials.RoseGold);

        MaterialProcessing.getBuilder(Materials.StainlessSteel);
        MaterialProcessing.getBuilder(Materials.BlueSteel);
        MaterialProcessing.getBuilder(Materials.RedSteel);
        // MaterialProcessing.getBuilder(Materials.NeodymiumMagnetic);

        MaterialProcessing.getBuilder(Materials.Titanium);
        MaterialProcessing.getBuilder(Materials.Ultimet);

        MaterialProcessing.getBuilder(Materials.TungstenSteel);
        MaterialProcessing.getBuilder(Materials.HSSE);
        MaterialProcessing.getBuilder(Materials.TungstenCarbide);

        MaterialProcessing.getBuilder(Materials.NaquadahAlloy);
        MaterialProcessing.getBuilder(Materials.Duranium);
        MaterialProcessing.getBuilder(Materials.Neutronium);

        // Additions

        MaterialStats.createPolymerTemplate(Materials.Polyethylene)
                .setBowString(1.2F)
                .setFletching(0.85F, 1.4F)
                .build();

        MaterialStats.createPolymerTemplate(Materials.PolyvinylChloride)
                .setBowString(1.3F)
                .setFletching(0.90F, 1.8F)
                .build();

        MaterialStats.createPolymerTemplate(Materials.Polytetrafluoroethylene)
                .setBowString(1.5F)
                .setFletching(0.95F, 2.1F)
                .build();

        MaterialStats.createPolymerTemplate(Materials.Polycaprolactam)
                .setBowString(1.7F)
                .build();

        MaterialStats.createPolymerTemplate(Materials.Polybenzimidazole)
                .setBowString(2.0F)
                .setFletching(1.0F, 2.5F)
                .build();
    }
}
