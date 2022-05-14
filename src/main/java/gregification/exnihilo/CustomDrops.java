package gregification.exnihilo;

import exnihilocreatio.ModItems;
import exnihilocreatio.blocks.BlockSieve;
import exnihilocreatio.items.seeds.ItemSeedBase;
import exnihilocreatio.registries.manager.ISieveDefaultRegistryProvider;
import exnihilocreatio.registries.registries.SieveRegistry;
import exnihilocreatio.util.ItemInfo;
import net.minecraft.init.Items;

import javax.annotation.Nonnull;

public class CustomDrops implements ISieveDefaultRegistryProvider {
    @Override
    public void registerRecipeDefaults(@Nonnull SieveRegistry registry) {
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
        for (ItemSeedBase seed : ModItems.itemSeeds)  {
            registry.register("dirt", new ItemInfo(seed), 0.05f, BlockSieve.MeshType.STRING.getID());
        }
        registry.register("dust", new ItemInfo(Items.DYE, 15), 0.1f, BlockSieve.MeshType.STRING.getID());
        registry.register("dust", new ItemInfo(Items.GLOWSTONE_DUST), 0.025f, BlockSieve.MeshType.IRON.getID());
        registry.register("dust", new ItemInfo(Items.BLAZE_POWDER), 0.037f, BlockSieve.MeshType.IRON.getID());
    }
}

