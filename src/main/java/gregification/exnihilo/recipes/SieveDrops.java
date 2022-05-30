/*
    Copyright 2019, TheLimePixel, dan
    GregBlock Utilities

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
package gregification.exnihilo.recipes;

import exnihilocreatio.ModBlocks;
import exnihilocreatio.ModItems;
import exnihilocreatio.blocks.BlockSieve;
import exnihilocreatio.items.seeds.ItemSeedBase;
import exnihilocreatio.registries.manager.ISieveDefaultRegistryProvider;
import exnihilocreatio.registries.registries.SieveRegistry;
import exnihilocreatio.util.ItemInfo;
import gregification.exnihilo.ExNihiloConfig;
import gregification.exnihilo.ExNihiloModule;
import gregtech.api.GregTechAPI;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SieveDrops implements ISieveDefaultRegistryProvider {

    private static Map<SieveDropType, List<SieveDrop>> SIEVE_DROPS_MAP = new HashMap<>();

    public static void readSieveDropsFromConfig() {
        readSieveDropsFromConfig(ExNihiloConfig.drops.sandSieveDrops, SieveDropType.SAND);
        readSieveDropsFromConfig(ExNihiloConfig.drops.gravelSieveDrops, SieveDropType.GRAVEL);
        readSieveDropsFromConfig(ExNihiloConfig.drops.graniteSieveDrops, SieveDropType.GRANITE);
        readSieveDropsFromConfig(ExNihiloConfig.drops.dioriteSieveDrops, SieveDropType.DIORITE);
        readSieveDropsFromConfig(ExNihiloConfig.drops.andesiteSieveDrops, SieveDropType.ANDESITE);
        readSieveDropsFromConfig(ExNihiloConfig.drops.netherrackSieveDrops, SieveDropType.NETHERRACK);
        readSieveDropsFromConfig(ExNihiloConfig.drops.endstoneSieveDrops, SieveDropType.END);
    }

    private static void readSieveDropsFromConfig(String[] recipes, SieveDropType type) {
        if (recipes == null || recipes.length == 0) {
            ExNihiloModule.logger.info("No configurations found for {} sieve category, skipping...", type.getName());
            return;
        }

        for (int i = 0; i < recipes.length; i += 3) {
            SieveDrop drop = validateInputs(recipes, type, i);
            if (drop != null) {
                SIEVE_DROPS_MAP.putIfAbsent(type, new ArrayList<>());
                SIEVE_DROPS_MAP.get(type).add(drop);
            }
        }
    }

    private static SieveDrop validateInputs(String[] recipe, SieveDropType type, int offset) {
        String materialName = null;
        float chance;
        int tier;
        try {
            materialName = recipe[offset];
            chance = Float.parseFloat(recipe[1 + offset]);
            tier = Integer.parseInt(recipe[2 + offset]);
        } catch (Exception e) {
            ExNihiloModule.logger.error("Invalid sieve configuration found for {} in {} category, skipping...",
                    materialName == null ? "unknown material" : materialName, type.getName());
            return null;
        }

        if (materialName == null) {
            ExNihiloModule.logger.error("Invalid sieve configuration found in {} category, material cannot be null. Skipping...",
                    type.getName());
            return null;
        }
        Material material = GregTechAPI.MaterialRegistry.get(materialName);
        if (material == null) {
            ExNihiloModule.logger.error("Could not find material with name {} in {} category, skipping...",
                    materialName, type.getName());
            return null;
        }
        if (!material.hasProperty(PropertyKey.ORE)) {
            ExNihiloModule.logger.error("Material {} in {} category has no Ore associated, skipping...",
                    materialName, type.getName());
            return null;
        }
        if (chance < 0.0f || chance > 1.0f) {
            ExNihiloModule.logger.error("Chance value out of range for {} in {} category, must be between 0.0 and 1.0! Skipping...",
                    materialName, type.getName());
            return null;
        }
        if (tier < 1 || tier > 4) {
            ExNihiloModule.logger.error("Sifting tier out of range for {} in {} category, must be between 1 and 4 inclusive! Skipping...",
                    materialName, type.getName());
            return null;
        }
        return new SieveDrop(material, chance, tier);
    }

    // TODO Clean this up
    @Override
    public void registerRecipeDefaults(@Nonnull SieveRegistry registry) {
        if (ExNihiloConfig.overrideAllSiftDrops) {
            registry.clearRegistry();

            registry.register("dirt", new ItemInfo(ModItems.pebbles), 1f, BlockSieve.MeshType.STRING.getID());
            registry.register("dirt", new ItemInfo(ModItems.pebbles), 1f, BlockSieve.MeshType.STRING.getID());
            registry.register("dirt", new ItemInfo(ModItems.pebbles), 0.5f, BlockSieve.MeshType.STRING.getID());
            registry.register("dirt", new ItemInfo(ModItems.pebbles), 0.5f, BlockSieve.MeshType.STRING.getID());
            registry.register("dirt", new ItemInfo(ModItems.pebbles), 0.1f, BlockSieve.MeshType.STRING.getID());
            registry.register("dirt", new ItemInfo(ModItems.pebbles), 0.1f, BlockSieve.MeshType.STRING.getID());
            registry.register("dirt", new ItemInfo(ModItems.pebbles, 1), 0.5f, BlockSieve.MeshType.STRING.getID());
            registry.register("dirt", new ItemInfo(ModItems.pebbles, 1), 0.1f, BlockSieve.MeshType.STRING.getID());
            registry.register("dirt", new ItemInfo(ModItems.pebbles, 2), 0.5f, BlockSieve.MeshType.STRING.getID());
            registry.register("dirt", new ItemInfo(ModItems.pebbles, 2), 0.1f, BlockSieve.MeshType.STRING.getID());
            registry.register("dirt", new ItemInfo(ModItems.pebbles, 3), 0.5f, BlockSieve.MeshType.STRING.getID());
            registry.register("dirt", new ItemInfo(ModItems.pebbles, 3), 0.1f, BlockSieve.MeshType.STRING.getID());
            registry.register("dirt", new ItemInfo(Items.WHEAT_SEEDS), 0.7f, BlockSieve.MeshType.STRING.getID());
            registry.register("dirt", new ItemInfo(Items.MELON_SEEDS), 0.35f, BlockSieve.MeshType.STRING.getID());
            registry.register("dirt", new ItemInfo(Items.PUMPKIN_SEEDS), 0.35f, BlockSieve.MeshType.STRING.getID());
            registry.register("dirt", new ItemInfo(ModItems.resources, 3), 0.05f, BlockSieve.MeshType.STRING.getID());
            registry.register("dirt", new ItemInfo(ModItems.resources, 4), 0.05f, BlockSieve.MeshType.STRING.getID());
            for (ItemSeedBase seed : ModItems.itemSeeds) {
                registry.register("dirt", new ItemInfo(seed), 0.05f, BlockSieve.MeshType.STRING.getID());
            }
            registry.register("dust", new ItemInfo(Items.DYE, 15), 0.1f, BlockSieve.MeshType.STRING.getID());
            registry.register("dust", new ItemInfo(Items.GLOWSTONE_DUST), 0.025f, BlockSieve.MeshType.IRON.getID());
            registry.register("dust", new ItemInfo(Items.BLAZE_POWDER), 0.037f, BlockSieve.MeshType.IRON.getID());
        }

        for (Map.Entry<SieveDropType, List<SieveDrop>> drops : SIEVE_DROPS_MAP.entrySet()) {
            OrePrefix prefix = drops.getKey().getPrefix();
            for (SieveDrop drop : drops.getValue()) {
                ItemStack stack = OreDictUnifier.get(prefix, drop.material);
                if (drops.getKey() != SieveDropType.NETHERRACK && drops.getKey() != SieveDropType.END) {
                    registry.register(drops.getKey().getName(), new ItemInfo(stack.getItem(), stack.getMetadata()), drop.chance, drop.level);
                } else {
                    registry.register(new ItemStack(drops.getKey() == SieveDropType.END ? ModBlocks.endstoneCrushed : ModBlocks.netherrackCrushed), new ItemInfo(stack.getItem(), stack.getMetadata()), drop.chance, drop.level);
                }
            }
        }
        SIEVE_DROPS_MAP = null; // can let this get GC'd now
    }

    // TODO Move away from valueOf for GTCEu
    private enum SieveDropType implements IStringSerializable {
        SAND("sand", ExNihiloModule.oreSandyChunk),
        GRAVEL("gravel", ExNihiloModule.oreChunk),
        NETHERRACK("nether", ExNihiloModule.oreNetherChunk),
        END("end", ExNihiloModule.oreEnderChunk),
        GRANITE("crushedGranite", ExNihiloModule.oreChunk),
        DIORITE("crushedDiorite", ExNihiloModule.oreChunk),
        ANDESITE("crushedAndesite", ExNihiloModule.oreChunk);

        private final String name;
        private final OrePrefix prefix;

        SieveDropType(String name, OrePrefix prefix) {
            this.name = name;
            this.prefix = prefix;
        }

        @Override
        @Nonnull
        public String getName() {
            return name;
        }

        public OrePrefix getPrefix() {
            return prefix;
        }
    }

    private static class SieveDrop {
        public Material material;
        public float chance;
        public int level;

        public SieveDrop(Material material, float chance, int level) {
            this.material = material;
            this.chance = chance;
            this.level = level;
        }
    }
}
