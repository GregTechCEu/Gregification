package gregification.common;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GFUtility {

    public static ItemStack getModItem(String modid, String name, int meta) {
        return getModItem(modid, name, meta, 1);
    }

    public static ItemStack getModItem(String modid, String name, int meta, int amount) {
        return getModItem(modid, name, meta, amount, null);
    }

    public static ItemStack getModItem(String modid, String name, int meta, int amount, String nbt) {
        if (!Loader.isModLoaded(modid)) {
            return ItemStack.EMPTY;
        }
        return GameRegistry.makeItemStack(modid+":"+name, meta, amount, nbt);
    }
}
