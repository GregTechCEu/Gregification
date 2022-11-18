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
        MaterialProcessing.getBuilder(Materials.Emerald).cancel();

        // Modifications
        MaterialProcessing.getBuilder(Materials.Opal);
        MaterialProcessing.getBuilder(Materials.Invar);
        MaterialProcessing.getBuilder(Materials.Duranium);
        MaterialProcessing.getBuilder(Materials.StainlessSteel);
        MaterialProcessing.getBuilder(Materials.Titanium);
        MaterialProcessing.getBuilder(Materials.TungstenSteel);
        MaterialProcessing.getBuilder(Materials.Naquadah);
        MaterialProcessing.getBuilder(Materials.VanadiumSteel);
        MaterialProcessing.getBuilder(Materials.BismuthBronze);
        MaterialProcessing.getBuilder(Materials.Osmium);
        MaterialProcessing.getBuilder(Materials.Olivine);
        MaterialProcessing.getBuilder(Materials.BlackSteel);
        MaterialProcessing.getBuilder(Materials.HSSS);
        MaterialProcessing.getBuilder(Materials.GreenSapphire);
        MaterialProcessing.getBuilder(Materials.SterlingSilver);
        MaterialProcessing.getBuilder(Materials.RhodiumPlatedPalladium);
        MaterialProcessing.getBuilder(Materials.HSSG);
        MaterialProcessing.getBuilder(Materials.Topaz);
        MaterialProcessing.getBuilder(Materials.WroughtIron);
        MaterialProcessing.getBuilder(Materials.GarnetRed);
        MaterialProcessing.getBuilder(Materials.Tungsten);
        MaterialProcessing.getBuilder(Materials.Chrome);
        MaterialProcessing.getBuilder(Materials.Neutronium);
        MaterialProcessing.getBuilder(Materials.Ruby);
        MaterialProcessing.getBuilder(Materials.GarnetYellow);
        MaterialProcessing.getBuilder(Materials.RoseGold);
        MaterialProcessing.getBuilder(Materials.HSSE);
        MaterialProcessing.getBuilder(Materials.DamascusSteel);
        MaterialProcessing.getBuilder(Materials.TungstenCarbide);

        MaterialProcessing.getBuilder(Materials.Uranium238)
                .addTrait(TRAIT_RADIOACTIVE, HEAD, BOW, SHAFT, PROJECTILE);

        MaterialProcessing.getBuilder(Materials.Osmiridium);
        MaterialProcessing.getBuilder(Materials.BlueSteel);
        MaterialProcessing.getBuilder(Materials.Manganese);
        MaterialProcessing.getBuilder(Materials.Sapphire);
        MaterialProcessing.getBuilder(Materials.Amethyst);
        MaterialProcessing.getBuilder(Materials.Aluminium);
        MaterialProcessing.getBuilder(Materials.BlackBronze);
        MaterialProcessing.getBuilder(Materials.Palladium);
        MaterialProcessing.getBuilder(Materials.CobaltBrass);
        MaterialProcessing.getBuilder(Materials.Molybdenum);
        MaterialProcessing.getBuilder(Materials.NaquadahAlloy);

        MaterialProcessing.getBuilder(Materials.Thorium)
                .addTrait(TRAIT_RADIOACTIVE, HEAD, BOW, SHAFT, PROJECTILE);

        MaterialProcessing.getBuilder(Materials.Brass);

        MaterialProcessing.getBuilder(Materials.Neodymium)
                .addTrait(magnetic2, HEAD);

        MaterialProcessing.getBuilder(Materials.Uranium235)
                .addTrait(TRAIT_RADIOACTIVE, HEAD, BOW, SHAFT, PROJECTILE);

        MaterialProcessing.getBuilder(Materials.Tritanium);

        MaterialProcessing.getBuilder(Materials.NaquadahEnriched)
                .addTrait(TRAIT_RADIOACTIVE, HEAD, BOW, SHAFT, PROJECTILE);

        MaterialProcessing.getBuilder(Materials.RedSteel);
        MaterialProcessing.getBuilder(Materials.Iridium);
        MaterialProcessing.getBuilder(Materials.Ultimet);
        MaterialProcessing.getBuilder(Materials.BlueTopaz);
        MaterialProcessing.getBuilder(Materials.Magnalium);

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
