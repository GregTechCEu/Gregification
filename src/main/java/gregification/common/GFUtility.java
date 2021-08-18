package gregification.common;

import net.minecraft.util.ResourceLocation;

public class GFUtility {

    public static ResourceLocation gregificationId(String name) {
        return new ResourceLocation(GFValues.MODID, name);
    }
}
