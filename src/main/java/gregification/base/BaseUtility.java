package gregification.base;

import gregification.Gregification;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseUtility {

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
        return GameRegistry.makeItemStack(modid + ":" + name, meta, amount, nbt);
    }

    @Nonnull
    public static ResourceLocation gregificationId(@Nonnull String name) {
        return new ResourceLocation(Gregification.MODID, name);
    }

    /** Should only be called after {@link net.minecraftforge.fml.common.event.FMLPreInitializationEvent} */
    public static void throwIncompatibilityIfLoaded(String modID, String... customMessages) {
        if (Loader.isModLoaded(modID)) {
            String modName = TextFormatting.BOLD + modID + TextFormatting.RESET;
            List<String> messages = new ArrayList<>();
            messages.add(modName + " mod detected, this mod is incompatible with Gregification.");
            messages.addAll(Arrays.asList(customMessages));
            throwIncompatibility(messages);
        }
    }

    /** Should only be called after {@link net.minecraftforge.fml.common.event.FMLPreInitializationEvent} */
    public static void throwIncompatibility(List<String> messages) {
        if (FMLLaunchHandler.side() == Side.SERVER) {
            throw new RuntimeException(String.join(",", messages));
        } else {
            throwClientIncompatibility(messages);
        }
    }

    @SideOnly(Side.CLIENT)
    private static void throwClientIncompatibility(List<String> messages) {
        throw new ModIncompatibilityException(messages);
    }
}
