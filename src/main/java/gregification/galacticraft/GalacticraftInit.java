package gregification.galacticraft;

import gregification.common.GFLog;
import gregtech.api.worldgen.generator.WorldGeneratorImpl;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.lang.reflect.Field;
import java.util.List;

public class GalacticraftInit {

    public static void initWorldGen() {
        try {
            Class<?> transformerHooksClass = Class.forName("micdoodle8.mods.galacticraft.core.TransformerHooks");
            Field otherModGeneratorsWhitelistField = transformerHooksClass.getDeclaredField("otherModGeneratorsWhitelist");
            otherModGeneratorsWhitelistField.setAccessible(true);
            List<IWorldGenerator> otherModGeneratorsWhitelist = (List<IWorldGenerator>) otherModGeneratorsWhitelistField.get(null);
            otherModGeneratorsWhitelist.add(WorldGeneratorImpl.INSTANCE);
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            GFLog.gcLogger.fatal("Failed to inject world generator into Galacticraft's whitelist.", e);
        }
    }

}
