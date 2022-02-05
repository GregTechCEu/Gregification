/*
    Copyright 2022, GregoriusT, dan
    GregTech5

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package gregification.forestry.bees;

import binnie.extrabees.genetics.ExtraBeeDefinition;
import binnie.extrabees.genetics.ExtraBeesFlowers;
import forestry.api.apiculture.*;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IAlleleFlowers;
import forestry.api.genetics.IMutationBuilder;
import forestry.apiculture.ModuleApiculture;
import forestry.apiculture.genetics.Bee;
import forestry.apiculture.genetics.BeeDefinition;
import forestry.apiculture.genetics.BeeVariation;
import forestry.apiculture.genetics.IBeeDefinition;
import forestry.apiculture.genetics.alleles.AlleleEffects;
import forestry.apiculture.items.EnumHoneyComb;
import forestry.core.ModuleCore;
import forestry.core.genetics.alleles.AlleleHelper;
import forestry.core.genetics.alleles.EnumAllele.*;
import gregification.common.GFUtility;
import gregification.common.GFValues;
import gregification.config.GFConfig;
import gregification.proxy.ForestryCommonProxy;
import gregtech.api.GTValues;
import gregtech.common.items.MetaItems;
import magicbees.bees.EnumBeeSpecies;
import magicbees.item.types.EnumCombType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.Optional.Method;
import org.apache.commons.lang3.text.WordUtils;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static forestry.api.apiculture.EnumBeeChromosome.*;

public enum GTBeeDefinition implements IBeeDefinition {

    // Organic
    CLAY(GTBranchDefinition.ORGANIC, "Clay", true, 0xC8C8DA, 0x0000FF,
            beeSpecies -> {
                if (GTValues.isModLoaded(GFValues.MODID_EB)) {
                    beeSpecies.addProduct(getExtraBeesComb(binnie.extrabees.items.types.EnumHoneyComb.CLAY), 0.30f);
                } else {
                    beeSpecies.addProduct(getForestryComb(EnumHoneyComb.HONEY), 0.30f);
                }
                beeSpecies.addProduct(new ItemStack(Items.CLAY_BALL), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.DAMP);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
                if (GTValues.isModLoaded(GFValues.MODID_BOP)) {
                    beeSpecies.addSpecialty(GFUtility.getModItem(GFValues.MODID_BOP, "mudball", 0), 0.05f);
                }
            },
            template -> {
                AlleleHelper.getInstance().set(template, FLOWERING, Flowering.SLOWER);
                AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, Tolerance.NONE);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, Flowers.VANILLA);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(BeeDefinition.INDUSTRIOUS, BeeDefinition.DILIGENT, 10);
                mutation.requireResource(Blocks.CLAY.getDefaultState());
            }
    ),
    SLIMEBALL(GTBranchDefinition.ORGANIC, "SlimeBall", true, 0x4E9E55, 0x00FF15,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.MOSSY), 0.30f);
                beeSpecies.addProduct(new ItemStack(Items.SLIME_BALL), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.STICKY), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.DAMP);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
                if (GTValues.isModLoaded(GFValues.MODID_TCON)) {
                    beeSpecies.addProduct(GFUtility.getModItem(GFValues.MODID_TCON, "edible", 1), 0.10f);
                    beeSpecies.addSpecialty(GFUtility.getModItem(GFValues.MODID_TCON, "slime_congealed", 2), 0.01f);
                } else {
                    beeSpecies.addSpecialty(new ItemStack(Blocks.SLIME_BLOCK), 0.01f);
                }
            },
            template -> {
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, Flowers.MUSHROOMS);
                AlleleHelper.getInstance().set(template, FLOWERING, Flowering.SLOWER);
                AlleleHelper.getInstance().set(template, TEMPERATURE_TOLERANCE, Tolerance.BOTH_1);
                AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, Tolerance.BOTH_1);
                if (GTValues.isModLoaded(GFValues.MODID_EB)) { // todo check for if module is enabled?
                    AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, ExtraBeesFlowers.WATER);
                }
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(BeeDefinition.MARSHY, CLAY, 7);
                mutation.requireResource(Blocks.SLIME_BLOCK.getDefaultState());
            }
    ),
    PEAT(GTBranchDefinition.ORGANIC, "Peat", true, 0x906237, 0x58300B,
            beeSpecies -> {
                beeSpecies.addProduct(ModuleCore.getItems().peat.getItemStack(), 0.30f);
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.HONEY), 0.15f);
                beeSpecies.addProduct(getGTComb(GTCombType.COAL), 0.15f);
                beeSpecies.addProduct(ModuleCore.getItems().mulch.getItemStack(), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.SHORTER);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, Flowers.WHEAT);
                AlleleHelper.getInstance().set(template, FLOWERING, Flowering.FASTER);
                AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, Tolerance.NONE);
            },
            dis -> dis.registerMutation(BeeDefinition.RURAL, CLAY, 10)
    ),
    STICKYRESIN(GTBranchDefinition.ORGANIC, "StickyResin", true, 0x2E8F5B, 0xDCC289,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.HONEY), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.STICKY), 0.15f);
                beeSpecies.addSpecialty(MetaItems.STICKY_RESIN.getStackForm(), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> {
                AlleleHelper.getInstance().set(template, FLOWERING, Flowering.SLOWER);
                AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, Tolerance.NONE);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(SLIMEBALL, PEAT, 15);
                mutation.requireResource("logRubber");
            }
    ),
    COAL(GTBranchDefinition.ORGANIC, "Coal", true, 0x666666, 0x525252,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.COAL), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.COKE), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> {
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, Flowers.CACTI);
                AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWEST);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.LONGER);
                AlleleHelper.getInstance().set(template, TEMPERATURE_TOLERANCE, Tolerance.DOWN_2);
                AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, Tolerance.DOWN_1);
                AlleleHelper.getInstance().set(template, NEVER_SLEEPS, true);
                AlleleHelper.getInstance().set(template, EFFECT, AlleleEffects.effectCreeper);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(BeeDefinition.INDUSTRIOUS, PEAT, 9);
                mutation.requireResource("blockCoal");
            }
    ),
    OIL(GTBranchDefinition.ORGANIC, "Oil", true, 0x4C4C4C, 0x333333,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.HONEY), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.OIL), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.DAMP);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, FLOWERING, Flowering.SLOWER);
                AlleleHelper.getInstance().set(template, NEVER_SLEEPS, true);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.NORMAL);
                AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER);
                AlleleHelper.getInstance().set(template, TEMPERATURE_TOLERANCE, Tolerance.NONE);
                AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, Tolerance.NONE);
                if (GTValues.isModLoaded(GFValues.MODID_EB)) {
                    AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, ExtraBeesFlowers.WATER);
                }
            },
            dis -> dis.registerMutation(COAL, STICKYRESIN, 4)
    ),
    ASH(GTBranchDefinition.ORGANIC, "Ash", true, 0x1E1A18, 0xC6C6C6,
            beeSpecies -> {
                if (GTValues.isModLoaded(GFValues.MODID_EB)) {
                    beeSpecies.addProduct(getExtraBeesComb(binnie.extrabees.items.types.EnumHoneyComb.SEED), 0.30f);
                } else {
                    beeSpecies.addProduct(getForestryComb(EnumHoneyComb.HONEY), 0.30f);
                }
                beeSpecies.addSpecialty(getGTComb(GTCombType.ASH), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.HOT);
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, Speed.NORMAL);
                AlleleHelper.getInstance().set(template, TERRITORY, Territory.LARGE);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.SHORTER);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, Flowers.WHEAT);
                AlleleHelper.getInstance().set(template, FLOWERING, Flowering.FASTER);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(COAL, CLAY, 10);
                mutation.restrictTemperature(EnumTemperature.HELLISH);
            }
    ),
    APATITE(GTBranchDefinition.ORGANIC, "Apatite", true, 0x7FCEF5, 0x654525,
            beeSpecies -> {
                if (GTValues.isModLoaded(GFValues.MODID_EB)) {
                    beeSpecies.addProduct(getExtraBeesComb(binnie.extrabees.items.types.EnumHoneyComb.SEED), 0.15f);
                } else {
                    beeSpecies.addProduct(getForestryComb(EnumHoneyComb.HONEY), 0.15f);
                }
                beeSpecies.addSpecialty(getGTComb(GTCombType.APATITE), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.WARM);
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, Speed.FASTEST);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.LONGER);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, Flowers.WHEAT);
                AlleleHelper.getInstance().set(template, FLOWERING, Flowering.FASTER);
                if (GTValues.isModLoaded(GFValues.MODID_EB)) {
                    AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, ExtraBeesFlowers.ROCK);
                }
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(ASH, COAL, 10);
                mutation.requireResource("blockApatite");
            }
    ),
    BIOMASS(GTBranchDefinition.ORGANIC, "Biomass", true, 0x21E118, 0x17AF0E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.MOSSY), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.BIOMASS), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, Speed.FASTEST);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.LONGEST);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, Flowers.WHEAT);
                AlleleHelper.getInstance().set(template, FLOWERING, Flowering.FASTER);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(BeeDefinition.INDUSTRIOUS, BeeDefinition.RURAL, 10);
                mutation.restrictBiomeType(BiomeDictionary.Type.FOREST);
            }
    ),

    // Gems
    REDSTONE(GTBranchDefinition.GEM, "Redstone", true, 0x7D0F0F, 0xD11919,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.STONE), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.REDSTONE), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.RAREEARTH), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(BeeDefinition.INDUSTRIOUS, BeeDefinition.DEMONIC, 10);
                mutation.requireResource("blockRedstone");
            }
    ),
    LAPIS(GTBranchDefinition.GEM, "Lapis", true, 0x1947D1, 0x476CDA,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.STONE), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.LAPIS), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(BeeDefinition.DEMONIC, BeeDefinition.IMPERIAL, 10);
                mutation.requireResource("blockLapis");
            }
    ),
    CERTUS(GTBranchDefinition.GEM, "CertusQuartz", true, 0x57CFFB, 0xBBEEFF,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.STONE), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.CERTUS), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(BeeDefinition.HERMITIC, LAPIS, 10);
                mutation.requireResource("blockCertusQuartz"); // todo check on AE2 oredict
            }
    ),
    FLUIX(GTBranchDefinition.GEM, "Fluix", true, 0xA375FF, 0xB591FF,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.STONE), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.FLUIX), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(REDSTONE, LAPIS, 7);
                // todo AE2 Fluix Block requireResource()
            },
            () -> GTValues.isModLoaded(GFValues.MODID_AE)
    ),
    DIAMOND(GTBranchDefinition.GEM, "Diamond", false, 0xCCFFFF, 0xA3CCCC,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.STONE), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.DIAMOND), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.HOT);
                beeSpecies.setHasEffect();
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(CERTUS, COAL, 3);
                mutation.requireResource("blockDiamond");
            }
    ),
    RUBY(GTBranchDefinition.GEM, "Ruby", false, 0xE6005C, 0xCC0052,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.STONE), 0.30f);
                beeSpecies.addProduct(getGTComb(GTCombType.RUBY), 0.15f);
                beeSpecies.addProduct(getGTComb(GTCombType.REDGARNET), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.DAMP);
                beeSpecies.setTemperature(EnumTemperature.HOT);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(REDSTONE, DIAMOND, 5);
                mutation.requireResource("blockRuby");
            }
    ),
    SAPPHIRE(GTBranchDefinition.GEM, "Sapphire", true, 0x0033CC, 0x00248F,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.STONE), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.SAPPHIRE), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(CERTUS, LAPIS, 5);
                mutation.requireResource("blockSapphire");
            }
    ),
    OLIVINE(GTBranchDefinition.GEM, "Olivine", true, 0x248F24, 0xCCFFCC,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.STONE), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.OLIVINE), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.MAGNESIUM), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> dis.registerMutation(CERTUS, BeeDefinition.ENDED, 5)
    ),
    EMERALD(GTBranchDefinition.GEM, "Emerald", false, 0x248F24, 0x2EB82E,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.STONE), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.EMERALD), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.ALUMINIUM), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.COLD);
                beeSpecies.setHasEffect();
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(OLIVINE, DIAMOND, 4);
                mutation.requireResource("blockEmerald");
            }
    ),
    REDGARNET(GTBranchDefinition.GEM, "RedGarnet", false, 0xBD4C4C, 0xECCECE,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.STONE), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.REDGARNET), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.PYROPE), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.DAMP);
                beeSpecies.setTemperature(EnumTemperature.WARM);
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, Speed.FAST);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.SHORTEST);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(DIAMOND, RUBY, 4);
                mutation.requireResource("blockGarnetRed");
            }
    ),
    YELLOWGARNET(GTBranchDefinition.GEM, "YellowGarnet", false, 0xA3A341, 0xEDEDCE,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.STONE), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.YELLOWGARNET), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.GROSSULAR), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.DAMP);
                beeSpecies.setTemperature(EnumTemperature.WARM);
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, Speed.FAST);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.SHORTEST);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(EMERALD, REDGARNET, 3);
                mutation.requireResource("blockGarnetYellow");
            }
    ),

    // Metals
    COPPER(GTBranchDefinition.METAL, "Copper", true, 0xFF6600, 0xE65C00,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(GTCombType.COPPER), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.GOLD), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(BeeDefinition.MAJESTIC, CLAY, 13);
                mutation.requireResource("blockCopper");
            }
    ),
    TIN(GTBranchDefinition.METAL, "Tin", true, 0xD4D4D4, 0xDDDDDD,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(GTCombType.TIN), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.ZINC), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(CLAY, BeeDefinition.DILIGENT, 13);
                mutation.requireResource("blockTin");
            }
    ),
    LEAD(GTBranchDefinition.METAL, "Lead", true, 0x666699, 0xA3A3CC,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(GTCombType.LEAD), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.SULFUR), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.DAMP);
                beeSpecies.setTemperature(EnumTemperature.WARM);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(COAL, COPPER, 13);
                mutation.requireResource("blockLead");
            }
    ),
    IRON(GTBranchDefinition.METAL, "Iron", true, 0xDA9147, 0xDE9C59,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(GTCombType.IRON), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.TIN), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(TIN, COPPER, 13);
                mutation.requireResource("blockIron");
            }
    ),
    STEEL(GTBranchDefinition.METAL, "Steel", true, 0x808080, 0x999999,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(GTCombType.STEEL), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.IRON), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.WARM);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(IRON, COAL, 10);
                mutation.requireResource("blockSteel");
                mutation.restrictTemperature(EnumTemperature.HOT);
            }
    ),
    NICKEL(GTBranchDefinition.METAL, "Nickel", true, 0x8585AD, 0x8585AD,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(GTCombType.NICKEL), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.PLATINUM), 0.02f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(IRON, COPPER, 13);
                mutation.requireResource("blockNickel");
            }
    ),
    ZINC(GTBranchDefinition.METAL, "Zinc", true, 0xF0DEF0, 0xF2E1F2,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(GTCombType.ZINC), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.GALLIUM), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(IRON, TIN, 13);
                mutation.requireResource("blockZinc");
            }
    ),
    SILVER(GTBranchDefinition.METAL, "Silver", true, 0xC2C2D6, 0xCECEDE,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(GTCombType.SILVER), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.SULFUR), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.COLD);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(LEAD, TIN, 10);
                mutation.requireResource("blockSilver");
            }
    ),
    GOLD(GTBranchDefinition.METAL, "Gold", true, 0xEBC633, 0xEDCC47,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(GTCombType.GOLD), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.NICKEL), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.WARM);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(LEAD, COPPER, 13);
                mutation.requireResource("blockGold");
                mutation.restrictTemperature(EnumTemperature.HOT);
            }
    ),
    ARSENIC(GTBranchDefinition.METAL, "Arsenic", true, 0x736C52, 0x292412,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(GTCombType.ARSENIC), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.WARM);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(ZINC, SILVER, 10);
                mutation.requireResource("blockArsenic");
            }
    ),

    // Rare Metals
    ALUMINIUM(GTBranchDefinition.RAREMETAL, "Aluminium", true, 0xB8B8FF, 0xD6D6FF,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(GTCombType.ALUMINIUM), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.BAUXITE), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.HOT);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(NICKEL, ZINC, 9);
                mutation.requireResource("blockAluminium");
            }
    ),
    TITANIUM(GTBranchDefinition.RAREMETAL, "Titanium", true, 0xCC99FF, 0xDBB8FF,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(GTCombType.TITANIUM), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.ALMANDINE), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.HOT);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(REDSTONE, ALUMINIUM, 5);
                mutation.requireResource("blockTitanium");
            }
    ),
    CHROME(GTBranchDefinition.RAREMETAL, "Chrome", true, 0xEBA1EB, 0xF2C3F2,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(GTCombType.CHROME), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.MAGNESIUM), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.HOT);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(TITANIUM, RUBY, 5);
                mutation.requireResource("blockChrome");
            }
    ),
    MANGANESE(GTBranchDefinition.RAREMETAL, "Manganese", true, 0xD5D5D5, 0xAAAAAA,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(GTCombType.MANGANESE), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.IRON), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.HOT);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(TITANIUM, ALUMINIUM, 5);
                mutation.requireResource("blockManganese");
            }
    ),
    TUNGSTEN(GTBranchDefinition.RAREMETAL, "Tungsten", false, 0x5C5C8A, 0x7D7DA1,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(GTCombType.TUNGSTEN), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.MOLYBDENUM), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.HOT);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(BeeDefinition.HEROIC, MANGANESE, 5);
                mutation.requireResource("blockTungsten");
            }
    ),
    PLATINUM(GTBranchDefinition.RAREMETAL, "Platinum", false, 0xE6E6E6, 0xFFFFCC,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(GTCombType.PLATINUM), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.IRIDIUM), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.HOT);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(DIAMOND, CHROME, 5);
                mutation.requireResource("blockPlatinum");
            }
    ),
    IRIDIUM(GTBranchDefinition.RAREMETAL, "Iridium", false, 0xDADADA, 0xD1D1E0,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.IRIDIUM), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.OSMIUM), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.HELLISH);
                beeSpecies.setHasEffect();
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(TUNGSTEN, PLATINUM, 5);
                mutation.requireResource("blockIridium");
            }
    ),
    OSMIUM(GTBranchDefinition.RAREMETAL, "Osmium", false, 0x2B2BDA, 0x8B8B8B,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.OSMIUM), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.IRIDIUM), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.COLD);
                beeSpecies.setHasEffect();
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(TUNGSTEN, PLATINUM, 5);
                mutation.requireResource("blockOsmium");
            }
    ),
    SALTY(GTBranchDefinition.RAREMETAL, "Salt", true, 0xF0C8C8, 0xFAFAFA,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.SALT), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.LITHIUM), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.WARM);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(CLAY, ALUMINIUM, 5);
                mutation.requireResource("blockSalt");
            }
    ),
    LITHIUM(GTBranchDefinition.RAREMETAL, "Lithium", false, 0xF0328C, 0xE1DCFF,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.LITHIUM), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.SALT), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.COLD);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(SALTY, ALUMINIUM, 5);
                mutation.requireResource("blockLithium");
            }
    ),
    ELECTROTINE(GTBranchDefinition.RAREMETAL, "Electrotine", false, 0x1E90FF, 0x3CB4C8,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.ELECTROTINE), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.REDSTONE), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.HOT);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(REDSTONE, GOLD, 5);
                mutation.requireResource("blockElectrotine");
            }
    ),

    // Industrial
    ENERGY(GTBranchDefinition.INDUSTRIAL, "Energy", false, 0xC11F1F, 0xEBB9B9,
            beeSpecies -> {
                beeSpecies.addProduct(getExtraBeesComb(binnie.extrabees.items.types.EnumHoneyComb.IC2ENERGY), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.ENERGY), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.WARM);
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.LONGER);
                AlleleHelper.getInstance().set(template, EFFECT, AlleleEffects.effectIgnition);
                AlleleHelper.getInstance().set(template, TEMPERATURE_TOLERANCE, Tolerance.DOWN_2);
                AlleleHelper.getInstance().set(template, NEVER_SLEEPS, true);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, Flowers.NETHER);
                AlleleHelper.getInstance().set(template, FLOWERING, Flowering.AVERAGE);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(BeeDefinition.DEMONIC, ExtraBeeDefinition.VOLCANIC, 10);
                mutation.requireResource("blockRedstone");
                //mutation.addMutationCondition() TODO Biome?
            },
            () -> GTValues.isModLoaded(GFValues.MODID_EB)
    ),
    LAPOTRON(GTBranchDefinition.INDUSTRIAL, "Lapotron", false, 0xFFEBC4, 0xE36400,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.LAPIS), 0.20f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.ENERGY), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.LAPOTRON), 0.10f);
                beeSpecies.setHumidity(EnumHumidity.DAMP);
                beeSpecies.setTemperature(EnumTemperature.ICY);
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.LONGER);
                AlleleHelper.getInstance().set(template, EFFECT, AlleleEffects.effectIgnition);
                AlleleHelper.getInstance().set(template, TEMPERATURE_TOLERANCE, Tolerance.UP_1);
                AlleleHelper.getInstance().set(template, NEVER_SLEEPS, true);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, Flowers.SNOW);
                AlleleHelper.getInstance().set(template, FLOWERING, Flowering.AVERAGE);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(LAPIS, ENERGY, 6);
                mutation.requireResource("blockLapis");
                mutation.restrictTemperature(EnumTemperature.ICY);
                //mutation.addMutationCondition(); TODO Moon, if available
            },
            () -> GTValues.isModLoaded(GFValues.MODID_EB)
    ),
    // TODO Pyrotheum, Cryotheum

    // Alloys
    REDALLOY(GTBranchDefinition.GTALLOY, "RedAlloy", false, 0xE60000, 0xB80000,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.PARCHED), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.REDALLOY), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.SHORTER);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(COPPER, REDSTONE, 10);
                mutation.requireResource("blockRedAlloy");
            }
    ),
    STAINLESSSTEEL(GTBranchDefinition.GTALLOY, "StainlessSteel", false, 0xC8C8DC, 0x778899,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(GTCombType.STEEL), 0.10f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.STAINLESSSTEEL), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.CHROME), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.HOT);
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, Speed.FAST);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.SHORTEST);
                AlleleHelper.getInstance().set(template, EFFECT, AlleleEffects.effectIgnition);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(CHROME, STEEL, 9);
                mutation.requireResource("blockStainlessSteel");
            }
    ),
    // TODO EIO Alloys
    // Redstone Alloy
    // Conductive Iron
    // Energetic Alloy
    // Vibrant Alloy
    // Electrical Steel
    // Dark Steel
    // Pulsating Iron
    // Enderium

    // Thaumic
    THAUMIUM(GTBranchDefinition.THAUMIC, "Thaumium", true, 0x7A007A, 0x5C005C,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.STRINGY), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.THAUMIUM), 0.20f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWEST);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.LONGER);
                AlleleHelper.getInstance().set(template, TEMPERATURE_TOLERANCE, Tolerance.BOTH_2);
                AlleleHelper.getInstance().set(template, EFFECT, AlleleEffects.effectExploration);
                AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, Tolerance.UP_1);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, Flowers.JUNGLE);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(EnumBeeSpecies.FIREY.getSpecies(), BeeDefinition.EDENIC, 10);
                mutation.requireResource("blockThaumium");
                //mutation.addMutationCondition(); todo magical forest
            },
            () -> GTBees.THAUMIC_BEES
    ),
    AMBER(GTBranchDefinition.THAUMIC, "Amber", true, 0xEE7700, 0x774B15,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.STRINGY), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.AMBER), 0.20f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER);
                AlleleHelper.getInstance().set(template, TEMPERATURE_TOLERANCE, Tolerance.NONE);
                AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, Tolerance.NONE);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(THAUMIUM, STICKYRESIN, 10);
                mutation.requireResource("blockAmber"); // todo oredict this in TC
            },
            () -> GTBees.THAUMIC_BEES
    ),
    QUICKSILVER(GTBranchDefinition.THAUMIC, "Quicksilver", true, 0x7A007A, 0x5C005C,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.STRINGY), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.QUICKSILVER), 0.20f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, TEMPERATURE_TOLERANCE, Tolerance.UP_1);
                AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, Tolerance.UP_1);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, Flowers.JUNGLE);
                AlleleHelper.getInstance().set(template, EFFECT, AlleleEffects.effectMiasmic);
            },
            dis -> dis.registerMutation(THAUMIUM, SILVER, 10),
            () -> GTBees.THAUMIC_BEES
    ),
    SALISMUNDUS(GTBranchDefinition.THAUMIC, "SalisMundus", true, 0xF7ADDE, 0x592582,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.STRINGY), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.SALISMUNDUS), 0.20f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, TEMPERATURE_TOLERANCE, Tolerance.UP_1);
                AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, Tolerance.UP_1);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, Flowers.JUNGLE);
                AlleleHelper.getInstance().set(template, EFFECT, AlleleEffects.effectMiasmic);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.SHORT);
                AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(THAUMIUM, THAUMIUM, 8);
                // magical forest biome
            },
            () -> GTBees.THAUMIC_BEES
    ),
    TAINTED(GTBranchDefinition.THAUMIC, "Tainted", true, 0x904BB8, 0xE800FF,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.STRINGY), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.TAINTED), 0.20f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, NEVER_SLEEPS, true);
                AlleleHelper.getInstance().set(template, CAVE_DWELLING, true);
                AlleleHelper.getInstance().set(template, TOLERATES_RAIN, true);
                AlleleHelper.getInstance().set(template, FERTILITY, Fertility.LOW);
                AlleleHelper.getInstance().set(template, TEMPERATURE_TOLERANCE, Tolerance.BOTH_1);
                AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, Tolerance.BOTH_1);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.SHORT);
                if (GTValues.isModLoaded(GFValues.MODID_EB)) {
                    AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, ExtraBeesFlowers.ROCK);
                }
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(THAUMIUM, THAUMIUM, 7);
                // tainted biome
            },
            () -> GTBees.THAUMIC_BEES
    ),
    DIVIDED(GTBranchDefinition.THAUMIC, "Unstable", true, 0xF0F0F0, 0xDCDCDC,
            beeSpecies -> {
                beeSpecies.addProduct(GFUtility.getModItem(GFValues.MODID_EB, "honey_comb", 76), 0.20f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.DIVIDED), 0.125f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWER);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.SHORT);
                AlleleHelper.getInstance().set(template, FLOWERING, Flowering.SLOW);
                AlleleHelper.getInstance().set(template, NEVER_SLEEPS, true);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(DIAMOND, IRON, 3);
                mutation.requireResource("blockMagicalWood");
            },
            () -> GTValues.isModLoaded(GFValues.MODID_XU2) && GTValues.isModLoaded(GFValues.MODID_EB)
    ),
    SPARKLING(GTBranchDefinition.THAUMIC, "NetherStar", true, 0x7A007A, 0xFFFFFF,
            beeSpecies -> {
                beeSpecies.addProduct(GFUtility.getModItem(GFValues.MODID_MB, "resource", 5), 0.20f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.SPARKLING), 0.125f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> {
                AlleleHelper.getInstance().set(template, TEMPERATURE_TOLERANCE, Tolerance.DOWN_2);
                AlleleHelper.getInstance().set(template, NEVER_SLEEPS, true);
                AlleleHelper.getInstance().set(template, CAVE_DWELLING, true);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, Flowers.NETHER);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.SHORT);
                AlleleHelper.getInstance().set(template, EFFECT, AlleleEffects.effectAggressive);
                AlleleHelper.getInstance().set(template, FLOWERING, Flowering.AVERAGE);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(EnumBeeSpecies.WITHERING.getSpecies(), EnumBeeSpecies.DRACONIC.getSpecies(), 1);
                mutation.requireResource("blockNetherStar");
                // todo end biome
            },
            () -> GTValues.isModLoaded(GFValues.MODID_MB)
    ),

    // Radioactive
    URANIUM(GTBranchDefinition.RADIOACTIVE, "Uranium", true, 0x19AF19, 0x169E16,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.URANIUM), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.COLD);
                beeSpecies.setNocturnal();
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWEST);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.LONGEST);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(BeeDefinition.AVENGING, PLATINUM, 2);
                mutation.requireResource("blockUranium");
            }
    ),
    PLUTONIUM(GTBranchDefinition.RADIOACTIVE, "Plutonium", true, 0x570000, 0x240000,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(GTCombType.LEAD), 0.15f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.PLUTONIUM), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.ICY);
                beeSpecies.setNocturnal();
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWEST);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.LONGEST);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(URANIUM, EMERALD, 2);
                mutation.requireResource("blockPlutonium");
            }
    ),
    NAQUADAH(GTBranchDefinition.RADIOACTIVE, "Naquadah", false, 0x003300, 0x002400,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.NAQUADAH), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.ICY);
                beeSpecies.setNocturnal();
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWEST);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.LONGEST);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(PLUTONIUM, IRIDIUM, 1);
                mutation.requireResource("blockNaquadah");
            }
    ),
    NAQUADRIA(GTBranchDefinition.RADIOACTIVE, "Naquadria", false, 0x000000, 0x002400,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.SLAG), 0.30f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.NAQUADAH), 0.20f);
                beeSpecies.addSpecialty(getGTComb(GTCombType.NAQUADRIA), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.ICY);
                beeSpecies.setNocturnal();
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWEST);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.LONGEST);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(PLUTONIUM, IRIDIUM, 1);
                mutation.requireResource("blockNaquadria");
            }
    ),
    THORIUM(GTBranchDefinition.RADIOACTIVE, "Thorium", false, 0x005000, 0x001E00,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.THORIUM), 0.75f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.COLD);
                beeSpecies.setNocturnal();
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWEST);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.LONGEST);
            },
            dis -> {
                IMutationBuilder mutation = dis.registerMutation(COAL, URANIUM, 2).setIsSecret();
                mutation.requireResource("blockThorium");
            }
    ),
    LUTETIUM(GTBranchDefinition.RADIOACTIVE, "Lutetium", false, 0x00AAFF, 0x0059FF,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.LUTETIUM), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
                beeSpecies.setNocturnal();
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWEST);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.LONGEST);
            },
            dis -> {
                IMutationBuilder mutation = dis.registerMutation(THORIUM, ExtraBeeDefinition.ROTTEN, 1).setIsSecret();
                mutation.requireResource("blockLutetium");
            },
            () -> GTValues.isModLoaded(GFValues.MODID_EB)
    ),
    AMERICIUM(GTBranchDefinition.RADIOACTIVE, "Americium", false, 0x287869, 0x0C453A,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.AMERICIUM), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
                beeSpecies.setNocturnal();
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWEST);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.LONGEST);
            },
            dis -> {
                IMutationBuilder mutation = dis.registerMutation(LUTETIUM, CHROME, 1).setIsSecret();
                mutation.requireResource("blockAmericium");
            },
            () -> GTValues.isModLoaded(GFValues.MODID_EB)
    ),
    NEUTRONIUM(GTBranchDefinition.RADIOACTIVE, "Neutronium", false, 0xFFF0F0, 0xFAFAFA,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(GTCombType.NEUTRONIUM), 0.0001f);
                beeSpecies.setHumidity(EnumHumidity.DAMP);
                beeSpecies.setTemperature(EnumTemperature.HELLISH);
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, Speed.SLOWEST);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.LONGEST);
                AlleleHelper.getInstance().set(template, NEVER_SLEEPS, true);
            },
            dis -> {
                IMutationBuilder mutation = dis.registerMutation(NAQUADRIA, AMERICIUM, 1).setIsSecret();
                mutation.requireResource("blockNeutronium");
            },
            () -> GTValues.isModLoaded(GFValues.MODID_EB)
    ),

    // Twilight
    NAGA(GTBranchDefinition.TWILIGHT, "Naga", true, 0x0D5A0D, 0x28874B,
            beeSpecies -> {
                if (GTBees.THAUMIC_BEES) {
                    beeSpecies.addProduct(getGTComb(GTCombType.SALISMUNDUS), 0.02f);
                } else {
                    beeSpecies.addProduct(getMagicBeesComb(EnumCombType.MUNDANE), 0.20f);
                }
                beeSpecies.addSpecialty(getGTComb(GTCombType.NAGA), 0.10f);
                beeSpecies.setHumidity(EnumHumidity.DAMP);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, Speed.FAST);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.SHORTEST);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(EnumBeeSpecies.ELDRITCH.getSpecies(), BeeDefinition.IMPERIAL, 8);
                mutation.restrictHumidity(EnumHumidity.DAMP);
            },
            () -> GTBees.TWILIGHT_BEES
    ),
    LICH(GTBranchDefinition.TWILIGHT, "Lich", true, 0xC5C5C5, 0x5C605E,
            beeSpecies -> {
                if (GTBees.THAUMIC_BEES) {
                    beeSpecies.addProduct(getGTComb(GTCombType.SALISMUNDUS), 0.04f);
                } else {
                    beeSpecies.addProduct(getMagicBeesComb(EnumCombType.OTHERWORLDLY), 0.30f);
                }
                beeSpecies.addSpecialty(getGTComb(GTCombType.LICH), 0.10f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, Speed.FAST);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.SHORTEST);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(EnumBeeSpecies.SUPERNATURAL.getSpecies(), NAGA, 7);
                mutation.restrictHumidity(EnumHumidity.ARID);
            },
            () -> GTBees.TWILIGHT_BEES
    ),
    HYDRA(GTBranchDefinition.TWILIGHT, "Hydra", true, 0x872836, 0xB8132C,
            beeSpecies -> {
                if (GTBees.TWILIGHT_BEES) {
                    beeSpecies.addProduct(getGTComb(GTCombType.SALISMUNDUS), 0.06f);
                } else {
                    beeSpecies.addProduct(getMagicBeesComb(EnumCombType.FIREY), 0.30f);
                }
                beeSpecies.addSpecialty(getGTComb(GTCombType.HYDRA), 0.10f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.HELLISH);
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, Speed.FAST);
                AlleleHelper.getInstance().set(template, LIFESPAN, Lifespan.SHORTEST);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(LICH, EnumBeeSpecies.FIREY.getSpecies(), 6);
                // todo custom biome?
            },
            () -> GTBees.TWILIGHT_BEES
    ),
    URGHAST(GTBranchDefinition.TWILIGHT, "UrGhast", true, 0xA7041C, 0x7C0618,
            beeSpecies -> {

            },
            template -> {

            },
            dis -> {

            },
            () -> GTBees.TWILIGHT_BEES
    ),

    // Space

    // Planet
    ;

    // TODO Sandwich Bee
    // TODO Fertilizer Bee?
    // TODO Firestone Bee? (Railcraft)
    // TODO Cryolite Bee? (probably not)
    // TODO Need to add Arsenic Block
    // TODO Coolant Bee? (When we add nuclear?)
    // TODO DOB Bee?

    private final GTBranchDefinition branch;
    private final GTAlleleBeeSpecies species;
    private final Consumer<GTAlleleBeeSpecies> speciesProperties;
    private final Consumer<IAllele[]> alleles;
    private final Consumer<GTBeeDefinition> mutations;
    private IAllele[] template;
    private IBeeGenome genome;
    private final Supplier<Boolean> generationCondition;

    GTBeeDefinition(GTBranchDefinition branch,
                    String binomial,
                    boolean dominant,
                    int primary,
                    int secondary,
                    Consumer<GTAlleleBeeSpecies> speciesProperties,
                    Consumer<IAllele[]> alleles,
                    Consumer<GTBeeDefinition> mutations) {
        this(branch, binomial, dominant, primary, secondary, speciesProperties, alleles, mutations, () -> true);
    }

    GTBeeDefinition(GTBranchDefinition branch,
                    String binomial,
                    boolean dominant,
                    int primary,
                    int secondary,
                    Consumer<GTAlleleBeeSpecies> speciesProperties,
                    Consumer<IAllele[]> alleles,
                    Consumer<GTBeeDefinition> mutations,
                    Supplier<Boolean> generationCondition) {
        this.alleles = alleles;
        this.mutations = mutations;
        this.speciesProperties = speciesProperties;
        String lowercaseName = this.toString().toLowerCase(Locale.ENGLISH);
        String species = WordUtils.capitalize(lowercaseName);

        String uid = "gregtech.bee.species" + species;
        String description = "for.description." + species;
        String name = "for.bees.species." + lowercaseName;

        this.branch = branch;
        this.species = new GTAlleleBeeSpecies(GTValues.MODID, uid, name, "GregTech", description, dominant, branch.getBranch(), binomial, primary, secondary);
        this.generationCondition = generationCondition;
    }

    public static void initBees() {
        for (GTBeeDefinition bee : values()) {
            bee.init();
        }
        for (GTBeeDefinition bee : values()) {
            bee.registerMutations();
        }
    }

    protected static ItemStack getForestryComb(EnumHoneyComb type) {
        return ModuleApiculture.getItems().beeComb.get(type, 1);
    }

    @Method(modid = GFValues.MODID_EB)
    protected static ItemStack getExtraBeesComb(binnie.extrabees.items.types.EnumHoneyComb type) {
        return GFUtility.getModItem(GFValues.MODID_EB, "honey_comb", type.ordinal());
    }

    @Method(modid = GFValues.MODID_MB)
    protected static ItemStack getMagicBeesComb(EnumCombType type) {
        return GFUtility.getModItem(GFValues.MODID_MB, "beecomb", type.ordinal());
    }

    protected static ItemStack getGTComb(GTCombType type) {
        return ForestryCommonProxy.combs.get(type, 1);
    }

    protected final void setSpeciesProperties(GTAlleleBeeSpecies beeSpecies) {
        this.speciesProperties.accept(beeSpecies);
    }

    protected final void setAlleles(IAllele[] template) {
        this.alleles.accept(template);
    }

    protected final void registerMutations() {
        if (generationCondition.get()) {
            this.mutations.accept(this);
        }
    }

    private void init() {
        if (generationCondition.get()) {
            setSpeciesProperties(species);

            template = branch.getTemplate();
            AlleleHelper.getInstance().set(template, SPECIES, species);
            setAlleles(template);

            genome = BeeManager.beeRoot.templateAsGenome(template);

            BeeManager.beeRoot.registerTemplate(template);
        }
    }

    protected final IBeeMutationBuilder registerMutation(IBeeDefinition parent1, IBeeDefinition parent2, int chance) {
        return registerMutation(parent1.getGenome().getPrimary(), parent2.getGenome().getPrimary(), chance);
    }

    protected final IBeeMutationBuilder registerMutation(IAlleleBeeSpecies parent1, IBeeDefinition parent2, int chance) {
        return registerMutation(parent1, parent2.getGenome().getPrimary(), chance);
    }

    protected final IBeeMutationBuilder registerMutation(IBeeDefinition parent1, IAlleleBeeSpecies parent2, int chance) {
        return registerMutation(parent1.getGenome().getPrimary(), parent2, chance);
    }

    protected final IBeeMutationBuilder registerMutation(IAlleleBeeSpecies parent1, IAlleleBeeSpecies parent2, int chance) {
        return BeeManager.beeMutationFactory.createMutation(parent1, parent2, getTemplate(), chance);
    }

    @Override
    @Nonnull
    public final IAllele[] getTemplate() {
        return Arrays.copyOf(template, template.length);
    }

    @Override
    @Nonnull
    public final IBeeGenome getGenome() {
        return genome;
    }

    @Override
    public final IBee getIndividual() {
        return new Bee(genome);
    }

    @Override
    public final ItemStack getMemberStack(@Nonnull EnumBeeType beeType) {
        return BeeManager.beeRoot.getMemberStack(getIndividual(), beeType);
    }
}
