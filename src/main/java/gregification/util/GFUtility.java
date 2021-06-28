package gregification.util;

import gregtech.api.unification.material.type.Material;
import net.minecraft.util.ResourceLocation;

import java.util.function.Predicate;

public class GFUtility {

    public static ResourceLocation gregificationId(String name) {
        return new ResourceLocation(GFValues.MODID, name);
    }

    public static Predicate<Material> pred(Predicate<Material> in) {
        return in;
    }
}
