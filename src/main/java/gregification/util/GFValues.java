package gregification.util;

import gregtech.api.unification.material.MaterialIconType;
import gregtech.api.unification.material.type.Material;

import java.util.function.Predicate;

public class GFValues {

    public static final String MODID = "gregification";
    public static final String MOD_NAME = "Gregification";
    public static final String VERSION = "@VERSION@";

    public static final String MODID_EXNI = "exnihilocreatio";

    public static final Class<?>[] ORE_PREFIX = {String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class};
}
