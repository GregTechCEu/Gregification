/*
    Copyright 2022, Alkalus, dan
    GregTech++

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
package gregification.forestry.frames;

import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeModifier;
import org.jetbrains.annotations.Nullable;

public enum GTFrameType implements IBeeModifier {

    // increased mutation and production, slightly lower lifespan
    ACCELERATED("Accelerated", 175, 1.0f, 1.2f, 0.9f, 1.8f, 1.0f, 1.0f),

    // significantly lower lifespan, much higher production and mutation
    MUTAGENIC("Mutagenic", 3, 1.0f, 5.0f, 0.0001f, 10.0f, 1.0f, 1.0f),

    // no mutation, much higher production and lifespan
    WORKING("Working", 2000, 1.0f, 0.0f, 3.0f, 4.0f, 1.0f, 1.0f),

    // enhances decay to 10x
    DECAYING("Decaying", 240, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 10.0f),

    // reduces mutation and production, enhances lifespan
    SLOWING("Slowing", 175, 1.0f, 0.5f, 2.0f, 0.5f, 1.0f, 1.0f),

    // reduces mutation, production, and decay
    STABILIZING("Stabilizing", 60, 1.0f, 0.1f, 1.0f, 0.1f, 1.0f, 0.5f),

    // 3x territory and lifespan, but no mutation and production
    ARBORIST("Arborist", 240, 3.0f, 0.0f, 3.0f, 0.0f, 1.0f, 1.0f);

    private final String frameName;
    public final int maxDamage;
    private final float territoryMod;
    private final float mutationMod;
    private final float lifespanMod;
    private final float productionMod;
    private final float floweringMod;
    private final float geneticDecayMod;

    GTFrameType(String name, int maxDamage, float territory, float mutation, float lifespan, float production, float flowering, float geneticDecay) {
        this.frameName = name;
        this.maxDamage = maxDamage;
        this.territoryMod = territory;
        this.mutationMod = mutation;
        this.lifespanMod = lifespan;
        this.productionMod = production;
        this.floweringMod = flowering;
        this.geneticDecayMod = geneticDecay;
    }

    public String getName() {
        return frameName;
    }

    @Override
    public float getTerritoryModifier(@Nullable IBeeGenome iBeeGenome, float v) {
        return territoryMod;
    }

    @Override
    public float getMutationModifier(@Nullable IBeeGenome iBeeGenome, @Nullable IBeeGenome iBeeGenome1, float v) {
        return mutationMod;
    }

    @Override
    public float getLifespanModifier(@Nullable IBeeGenome iBeeGenome, @Nullable IBeeGenome iBeeGenome1, float v) {
        return lifespanMod;
    }

    @Override
    public float getProductionModifier(@Nullable IBeeGenome iBeeGenome, float v) {
        return productionMod;
    }

    @Override
    public float getFloweringModifier(@Nullable IBeeGenome iBeeGenome, float v) {
        return floweringMod;
    }

    @Override
    public float getGeneticDecay(@Nullable IBeeGenome iBeeGenome, float v) {
        return geneticDecayMod;
    }

    @Override
    public boolean isSealed() {
        return false;
    }

    @Override
    public boolean isSelfLighted() {
        return false;
    }

    @Override
    public boolean isSunlightSimulated() {
        return false;
    }

    @Override
    public boolean isHellish() {
        return false;
    }
}
