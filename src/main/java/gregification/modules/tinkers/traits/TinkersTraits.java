package gregification.modules.tinkers.traits;

import gregtech.api.unification.stack.UnificationEntry;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.traits.ITrait;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class TinkersTraits {

    // Traits
    public static final ITrait TRAIT_RADIOACTIVE = new TraitRadioactive();

    // Modifiers
    public static final ModifierTrait MOD_SALTY = new ModifierSalty();

    public static void registerModifiers() {
        MOD_SALTY.addItem(new UnificationEntry(dust, Salt).toString());
        MOD_SALTY.addItem(new UnificationEntry(block, Salt).toString(), 1, 9);
        MOD_SALTY.addItem(new UnificationEntry(gem, Salt).toString());
        MOD_SALTY.addItem(new UnificationEntry(dust, RockSalt).toString());
        MOD_SALTY.addItem(new UnificationEntry(block, RockSalt).toString(), 1, 9);
        MOD_SALTY.addItem(new UnificationEntry(gem, RockSalt).toString());
    }
}
