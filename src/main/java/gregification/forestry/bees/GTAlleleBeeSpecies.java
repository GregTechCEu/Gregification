/*
    Copyright 2022, GregoriusT, dan
    GregTech5

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package gregification.forestry.bees;

import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.apiculture.IAlleleBeeSpeciesBuilder;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAlleleFloat;
import forestry.api.genetics.IClassification;
import forestry.apiculture.genetics.alleles.AlleleBeeSpecies;
import forestry.core.genetics.alleles.AlleleFloat;
import gregification.common.GFValues;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class GTAlleleBeeSpecies extends AlleleBeeSpecies {

    public static IAlleleFloat speedBlinding;

    public GTAlleleBeeSpecies(String modId,
                              String uid,
                              String unlocalizedName,
                              String authority,
                              String unlocalizedDescription,
                              boolean dominant,
                              IClassification branch,
                              String binomial,
                              int primaryColor,
                              int secondaryColor) {
        super(modId, uid, unlocalizedName, authority, unlocalizedDescription, dominant, branch, binomial, primaryColor, secondaryColor);
        AlleleManager.alleleRegistry.registerAllele(this, EnumBeeChromosome.SPECIES);
    }

    @Override
    @Nonnull
    public IAlleleBeeSpeciesBuilder addProduct(@Nonnull ItemStack product, @Nonnull Float chance) {
        if (product == ItemStack.EMPTY) {
            product = new ItemStack(Items.BOAT); // why? who knows
        }
        if (chance <= 0.0f || chance > 1.0f) {
            chance = 0.1f;
        }
        return super.addProduct(product, chance);
    }

    @Override
    @Nonnull
    public IAlleleBeeSpeciesBuilder addSpecialty(@Nonnull ItemStack specialty, @Nonnull Float chance) {
        if (specialty == ItemStack.EMPTY) {
            specialty = new ItemStack(Items.BOAT);
        }
        if (chance <= 0.0f || chance > 1.0f) {
            chance = 0.1f;
        }
        return super.addSpecialty(specialty, chance);
    }

    public static void setupGTAlleles() {
        IAlleleFloat allele = (IAlleleFloat) AlleleManager.alleleRegistry.getAllele("magicbees.speedBlinding");
        if (allele == null) {
            allele = new AlleleFloat(GFValues.MODID, "gregtech.speedBlinding", "gregtech.speedBlinding", 2f, false);
            AlleleManager.alleleRegistry.registerAllele(allele, EnumBeeChromosome.SPEED);
        }
        speedBlinding = allele;
    }
}
