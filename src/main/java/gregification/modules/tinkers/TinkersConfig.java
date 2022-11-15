package gregification.modules.tinkers;

import gregification.Gregification;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.*;

@LangKey("gregification.config.tinkers")
@Config(modid = Gregification.MODID, name = Gregification.MODID + "/tinkers_construct", category = "Tinkers Construct")
public class TinkersConfig {

    public static boolean smelteryAlloying = true;

    public static boolean smelteryMelting = true;
}
