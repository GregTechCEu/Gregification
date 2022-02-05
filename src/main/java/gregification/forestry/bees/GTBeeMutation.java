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

import forestry.api.apiculture.*;
import forestry.api.climate.IClimateProvider;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IGenome;
import forestry.api.genetics.IMutationCondition;
import forestry.apiculture.genetics.BeeMutation;
import forestry.core.genetics.mutations.Mutation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.commons.lang3.reflect.FieldUtils;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.List;

@SuppressWarnings("unchecked, ConstantConditions")
public class GTBeeMutation extends BeeMutation {

    private final float split;

    public GTBeeMutation(IAlleleBeeSpecies bee0, IAlleleBeeSpecies bee1, IAllele[] result, int chance, float split) {
        super(bee0, bee1, result, chance);
        this.split = split;
        BeeManager.beeRoot.registerMutation(this);
    }

    @Override
    public float getBaseChance() {
        return super.getBaseChance() / split;
    }

    @Override
    public float getChance(@Nonnull IBeeHousing housing, @Nonnull IAlleleBeeSpecies allele0, @Nonnull IAlleleBeeSpecies allele1, @Nonnull IBeeGenome genome0, @Nonnull IBeeGenome genome1) {
        World world = housing.getWorldObj();
        BlockPos housingPos = housing.getCoordinates();

        float processedChance = getBasicChance(world, housingPos, allele0, allele1, genome0, genome1, housing);
        if (processedChance <= 0f) {
            return 0f;
        }

        IBeeModifier beeHousingModifier = BeeManager.beeRoot.createBeeHousingModifier(housing);
        IBeeModifier beeModeModifier = BeeManager.beeRoot.getBeekeepingMode(world).getBeeModifier();

        processedChance *= beeHousingModifier.getMutationModifier(genome0, genome1, processedChance);
        processedChance *= beeModeModifier.getMutationModifier(genome0, genome1, processedChance);

        return processedChance;
    }

    private float getBasicChance(World world, BlockPos pos, IAllele allele0, IAllele allele1, IGenome genome0, IGenome genome1, IClimateProvider climate) {
        float mutationChance = this.getBaseChance();
        List<IMutationCondition> mutationConditions = null;
        Field f = FieldUtils.getDeclaredField(Mutation.class, "mutationConditions", true);
        if (f == null) {
            f = FieldUtils.getField(Mutation.class, "mutationConditions", true);
        }
        if (f == null) {
            return mutationChance;
        }
        try {
            mutationConditions = f.get(this) instanceof List ? (List<IMutationCondition>) f.get(this) : null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (mutationConditions != null) {
            for (IMutationCondition mutationCondition : mutationConditions) {
                mutationChance *= mutationCondition.getChance(world, pos, allele0, allele1, genome0, genome1, climate);
                if (mutationChance == 0) {
                    return 0;
                }
            }
        }
        return mutationChance;
    }
}
