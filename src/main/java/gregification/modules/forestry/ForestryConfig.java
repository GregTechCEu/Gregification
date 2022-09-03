package gregification.modules.forestry;

import gregification.Gregification;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.*;

@LangKey("gregification.config.forestry")
@Config(modid = Gregification.MODID, name = Gregification.MODID + "/forestry", category = "Forestry")
public class ForestryConfig {

    @Comment({
            "Enable GT Electrodes. Also overrides Forestry recipes.",
            "Default: true"
    })
    public static boolean gtElectrodes = true;

    @Comment({
            "Enable the GT Scoop",
            "Default: true"
    })
    public static boolean gtScoop = true;

    @Comment({
            "Enable GT Bees",
            "Requirements: Forestry Apiculture module",
            "Additional mod compatibility:",
            "ExtraBees (HIGHLY recommended): Adds several late game bees like Americium, Lutetium, and Neutronium, as well as changing some other Bee recipes",
            "Applied Energistics 2: Adds Fluix Bee",
            "GregTech Food Option: Adds Sandwich Bee",
            "Extra Utilities 2: Adds Divided Bee for making Stable/Unstable Nuggets (also requires ExtraBees)",
            "Default: true"
    })
    public static boolean gtBees = true;

    @Comment({
            "Whether or not to nerf GT Comb processing recipes.",
            "Requirements: Forestry Apiculture module, gtBees config enabled",
            "Primarily affects whether the Centrifuge (GT or Forestry) can process GT combs",
            "Default: false"
    })
    public static boolean nerfGTCombs = false;

    @Comment({
            "Enable GT Apiary Frames",
            "Requirements: Forestry Apiculture module",
            "Default: true"
    })
    public static boolean gtFrames = true;

    @Comment({
            "Enable GT Twilight Forest Bees",
            "Requirements: Forestry Apiculture module, The Twilight Forest, MagicBees, gtBees config option",
            "Has additional compatibility with the thaumicBees config option, such as the Urghast and SnowQueen Bees",
            "Default: true"
    })
    public static boolean twilightBees = true;

    @Comment({
            "Enable GT Thaumcraft 6 Bees",
            "Requirements: Forestry Apiculture module, Thaumcraft 6, MagicBees, gtBees config option",
            "Default: true"
    })
    public static boolean thaumicBees = true;

    //@Comment({
    //        "Enable GT Advanced Rocketry/Galacticraft Bees",
    //        "Requirements: Forestry Apiculture module, Advanced Rocketry OR Galacticraft, gtBees config option",
    //        "Default: true"
    //})
    //public static boolean spaceBees = true;
}
