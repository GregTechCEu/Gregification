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

import forestry.api.apiculture.BeeManager;
import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IClassification;
import forestry.apiculture.genetics.alleles.AlleleEffects;
import forestry.core.genetics.alleles.AlleleHelper;
import forestry.core.genetics.alleles.EnumAllele.*;
import gregification.common.GFValues;
import gregtech.api.GTValues;

import java.util.Arrays;
import java.util.function.Consumer;

import static forestry.api.apiculture.EnumBeeChromosome.*;

public enum GTBranchDefinition {

    GT_ORGANIC("Fuelis", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, Tolerance.NONE);
        AlleleHelper.getInstance().set(alleles, HUMIDITY_TOLERANCE, Tolerance.BOTH_2);
        AlleleHelper.getInstance().set(alleles, NEVER_SLEEPS, false);
        AlleleHelper.getInstance().set(alleles, FLOWER_PROVIDER, Flowers.WHEAT);
        AlleleHelper.getInstance().set(alleles, FLOWERING, Flowering.SLOW);
        AlleleHelper.getInstance().set(alleles, LIFESPAN, Lifespan.SHORTER);
        AlleleHelper.getInstance().set(alleles, SPEED, Speed.SLOWEST);
    }),
    GT_INDUSTRIAL("Industrialis", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, Tolerance.UP_1);
        AlleleHelper.getInstance().set(alleles, HUMIDITY_TOLERANCE, Tolerance.BOTH_1);
        AlleleHelper.getInstance().set(alleles, NEVER_SLEEPS, false);
        AlleleHelper.getInstance().set(alleles, FLOWER_PROVIDER, Flowers.SNOW);
        AlleleHelper.getInstance().set(alleles, FLOWERING, Flowering.FASTER);
        AlleleHelper.getInstance().set(alleles, LIFESPAN, Lifespan.SHORT);
        AlleleHelper.getInstance().set(alleles, SPEED, Speed.SLOW);
    }),
    GT_ALLOY("Amalgamis", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, Tolerance.NONE);
        AlleleHelper.getInstance().set(alleles, TOLERATES_RAIN, true);
        AlleleHelper.getInstance().set(alleles, NEVER_SLEEPS, false);
        AlleleHelper.getInstance().set(alleles, FLOWER_PROVIDER, Flowers.VANILLA);
        AlleleHelper.getInstance().set(alleles, FLOWERING, Flowering.AVERAGE);
        AlleleHelper.getInstance().set(alleles, LIFESPAN, Lifespan.SHORTEST);
        AlleleHelper.getInstance().set(alleles, SPEED, Speed.FAST);
    }),
    GT_GEM("Ornamentis", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, Tolerance.NONE);
        AlleleHelper.getInstance().set(alleles, NEVER_SLEEPS, false);
        AlleleHelper.getInstance().set(alleles, FLOWER_PROVIDER, Flowers.NETHER);
        AlleleHelper.getInstance().set(alleles, FLOWERING, Flowering.AVERAGE);
    }),
    GT_METAL("Metaliferis", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, Tolerance.DOWN_2);
        AlleleHelper.getInstance().set(alleles, CAVE_DWELLING, true);
        AlleleHelper.getInstance().set(alleles, NEVER_SLEEPS, false);
        AlleleHelper.getInstance().set(alleles, FLOWER_PROVIDER, Flowers.JUNGLE);
        AlleleHelper.getInstance().set(alleles, FLOWERING, Flowering.SLOWER);
    }),
    GT_RAREMETAL("Mineralis", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, Tolerance.DOWN_1);
        AlleleHelper.getInstance().set(alleles, NEVER_SLEEPS, false);
        AlleleHelper.getInstance().set(alleles, FLOWER_PROVIDER, Flowers.CACTI);
        AlleleHelper.getInstance().set(alleles, FLOWERING, Flowering.FAST);
    }),
    GT_RADIOACTIVE("Criticalis", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, Tolerance.NONE);
        AlleleHelper.getInstance().set(alleles, NEVER_SLEEPS, false);
        AlleleHelper.getInstance().set(alleles, FLOWER_PROVIDER, Flowers.END);
        AlleleHelper.getInstance().set(alleles, FLOWERING, Flowering.AVERAGE);
        AlleleHelper.getInstance().set(alleles, SPEED, GTAlleleBeeSpecies.speedBlinding);
        if (GTValues.isModLoaded(GFValues.MODID_EB)) {
            AlleleHelper.getInstance().set(alleles, EFFECT, GTBees.getEffect(GFValues.MODID_EB, "radioactive"));
        }
    }),
    // For Twilight Forest compat
    GT_TWILIGHT("Nemoris Obscuri", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, Tolerance.BOTH_1);
        AlleleHelper.getInstance().set(alleles, HUMIDITY_TOLERANCE, Tolerance.BOTH_1);
        AlleleHelper.getInstance().set(alleles, NEVER_SLEEPS, false);
        AlleleHelper.getInstance().set(alleles, FLOWER_PROVIDER, Flowers.VANILLA);
        AlleleHelper.getInstance().set(alleles, FLOWERING, Flowering.FASTER);
    }),
    // For Thaumcraft compat
    GT_THAUMIC("Arcanis", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, Tolerance.BOTH_1);
        AlleleHelper.getInstance().set(alleles, HUMIDITY_TOLERANCE, Tolerance.BOTH_1);
        AlleleHelper.getInstance().set(alleles, FLOWERING, Flowering.FASTER);
        AlleleHelper.getInstance().set(alleles, LIFESPAN, Lifespan.LONGEST);
        if (GTValues.isModLoaded(GFValues.MODID_EB)) {
            AlleleHelper.getInstance().set(alleles, FLOWER_PROVIDER, GTBees.getFlowers(GFValues.MODID_EB, "book"));
        }
    }),
    // For Advanced Rocketry/Galacticraft compat
    GT_SPACE("Cosmicis", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, Tolerance.DOWN_2);
        AlleleHelper.getInstance().set(alleles, TOLERATES_RAIN, true);
        AlleleHelper.getInstance().set(alleles, NEVER_SLEEPS, true);
        AlleleHelper.getInstance().set(alleles, FLOWERING, Flowering.FASTEST);
        AlleleHelper.getInstance().set(alleles, LIFESPAN, Lifespan.LONGEST);
        AlleleHelper.getInstance().set(alleles, SPEED, Speed.FAST);
        AlleleHelper.getInstance().set(alleles, TERRITORY, Territory.LARGEST);
        if (GTValues.isModLoaded(GFValues.MODID_EB)) {
            AlleleHelper.getInstance().set(alleles, FLOWER_PROVIDER, GTBees.getFlowers(GFValues.MODID_EB, "rock"));
        }
    }),
    GT_PLANET("Planetaris", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, Tolerance.BOTH_1);
        AlleleHelper.getInstance().set(alleles, TOLERATES_RAIN, true);
        AlleleHelper.getInstance().set(alleles, FLOWERING, Flowering.FASTEST);
        AlleleHelper.getInstance().set(alleles, LIFESPAN, Lifespan.NORMAL);
        AlleleHelper.getInstance().set(alleles, SPEED, Speed.FASTEST);
        AlleleHelper.getInstance().set(alleles, TERRITORY, Territory.LARGER);
        if (GTValues.isModLoaded(GFValues.MODID_EB)) {
            AlleleHelper.getInstance().set(alleles, FLOWER_PROVIDER, GTBees.getFlowers(GFValues.MODID_EB, "rock"));
        }
    }),
    ;

    private static IAllele[] defaultTemplate;
    private final IClassification branch;
    private final Consumer<IAllele[]> branchProperties;

    GTBranchDefinition(String scientific, Consumer<IAllele[]> branchProperties) {
        //noinspection ConstantConditions
        this.branch = BeeManager.beeFactory.createBranch(this.name().toLowerCase(), scientific);
        AlleleManager.alleleRegistry.getClassification("family.apidae").addMemberGroup(this.branch);
        this.branchProperties = branchProperties;
    }

    private static IAllele[] getDefaultTemplate() {
        if (defaultTemplate == null) {
            defaultTemplate = new IAllele[EnumBeeChromosome.values().length];

            AlleleHelper.getInstance().set(defaultTemplate, SPEED, Speed.SLOWEST);
            AlleleHelper.getInstance().set(defaultTemplate, LIFESPAN, Lifespan.SHORTER);
            AlleleHelper.getInstance().set(defaultTemplate, FERTILITY, Fertility.NORMAL);
            AlleleHelper.getInstance().set(defaultTemplate, TEMPERATURE_TOLERANCE, Tolerance.NONE);
            AlleleHelper.getInstance().set(defaultTemplate, NEVER_SLEEPS, false);
            AlleleHelper.getInstance().set(defaultTemplate, HUMIDITY_TOLERANCE, Tolerance.NONE);
            AlleleHelper.getInstance().set(defaultTemplate, TOLERATES_RAIN, false);
            AlleleHelper.getInstance().set(defaultTemplate, CAVE_DWELLING, false);
            AlleleHelper.getInstance().set(defaultTemplate, FLOWER_PROVIDER, Flowers.VANILLA);
            AlleleHelper.getInstance().set(defaultTemplate, FLOWERING, Flowering.SLOWEST);
            AlleleHelper.getInstance().set(defaultTemplate, TERRITORY, Territory.AVERAGE);
            AlleleHelper.getInstance().set(defaultTemplate, EFFECT, AlleleEffects.effectNone);
        }
        return Arrays.copyOf(defaultTemplate, defaultTemplate.length);
    }

    protected final void setBranchProperties(IAllele[] template) {
        this.branchProperties.accept(template);
    }

    public final IAllele[] getTemplate() {
        IAllele[] template = getDefaultTemplate();
        setBranchProperties(template);
        return template;
    }

    public final IClassification getBranch() {
        return branch;
    }
}
