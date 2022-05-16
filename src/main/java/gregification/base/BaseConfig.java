package gregification.base;

import gregification.Gregification;
import gregification.exnihilo.ExNihiloConfig;
import gregification.forestry.ForestryConfig;
import gregification.opencomputers.OpenComputersConfig;
import net.minecraftforge.common.config.Config;

@Config(modid = Gregification.MODID)
public class BaseConfig {

    // Ex Nihilo

    @Config.Comment({"Enable Ex Nihilo integration.", "Default: true"})
    @Config.RequiresMcRestart
    public static boolean enableExNihiloModule = true;

    @Config.Comment("Config options for Ex Nihilo features")
    @Config.Name("Module - Ex Nihilo")
    public static ExNihiloConfig exNihilo = new ExNihiloConfig();


    // Forestry

    @Config.Comment({"Enable Forestry Integration.", "Default: true"})
    @Config.RequiresMcRestart
    public static boolean enableForestryModule = true;

    @Config.Comment("Config options for Forestry features")
    @Config.Name("Module - Forestry")
    public static ForestryConfig forestry = new ForestryConfig();


    // Open Computers

    @Config.Comment({"Enable Open Computers integration.", "Default: true"})
    @Config.RequiresMcRestart
    public static boolean enableOpenComputersModule = true;

    @Config.Comment("Config options for Open Computers features")
    @Config.Name("Module - Open Computers")
    public static OpenComputersConfig openComputers = new OpenComputersConfig();
}
