package gregification.config;

import gregification.common.GFValues;
import net.minecraftforge.common.config.Config;

@Config(modid = GFValues.MODID)
public class ForestryCfg {

    @Config.Comment({
            "Enable Forestry Integration. Overrides all other options in this category.",
            "Default: true"
    })
    public boolean enableForestry = true;

    @Config.Comment({
            "Enable GT Electrodes. Also overrides Forestry recipes.",
            "Default: true"
    })
    public boolean gtElectrodes = true;

    @Config.Comment({
            "Enable GT Bees",
            "Requirements: Forestry Apiculture module",
            "Additional mod compatibility:",
            "ExtraBees (HIGHLY recommended): Adds several late game bees like Americium, Lutetium, and Neutronium, as well as changing some other Bee recipes",
            "Applied Energistics 2: Adds Fluix Bee",
            "GregTech Food Option: Adds Sandwich Bee",
            "Extra Utilities 2: Adds Divided Bee for making Stable/Unstable Nuggets (also requires ExtraBees)",
            "Default: true"
    })
    public boolean gtBees = true;

    @Config.Comment({
            "Whether or not to nerf GT Comb processing recipes.",
            "Requirements: Forestry Apiculture modile, gtBees config enabled",
            "Primarily affects whether the Centrifuge (GT or Forestry) can process GT combs",
            "Default: false"
    })
    public boolean nerfGTCombs = false;

    @Config.Comment({
            "Enable GT Apiary Frames",
            "Requirements: Forestry Apiculture module",
            "Default: true"
    })
    public boolean gtFrames = true;

    @Config.Comment({
            "Enable GT Twilight Forest Bees",
            "Requirements: Forestry Apiculture module, The Twilight Forest, MagicBees, gtBees config option",
            "Has additional compatibility with the thaumicBees config option, such as the Urghast and SnowQueen Bees",
            "Default: true"
    })
    public boolean twilightBees = true;

    @Config.Comment({
            "Enable GT Thaumcraft 6 Bees",
            "Requirements: Forestry Apiculture module, Thaumcraft 6, MagicBees, gtBees config option",
            "Default: true"
    })
    public boolean thaumicBees = true;

    @Config.Comment({
            "Enable GT Advanced Rocketry/Galacticraft Bees",
            "Requirements: Forestry Apiculture module, Advanced Rocketry OR Galacticraft, gtBees config option",
            "Default: true"
    })
    public boolean spaceBees = true;

    @Config.Comment({
            "Replace Forestry recipes with GT-style recipes",
            "Currently changes:",
            "",
            "Default: true"
    })
    public boolean gregifyForestry = true;
}
