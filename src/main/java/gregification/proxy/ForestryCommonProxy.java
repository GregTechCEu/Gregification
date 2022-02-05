package gregification.proxy;

import forestry.modules.ModuleHelper;
import gregification.common.GFLog;
import gregification.common.GFValues;
import gregification.config.GFConfig;
import gregification.forestry.bees.*;
import gregification.forestry.frames.GTFrameType;
import gregification.forestry.frames.GTItemFrame;
import gregification.forestry.recipes.CombRecipes;
import gregification.forestry.recipes.DropRecipes;
import gregification.forestry.recipes.ElectrodeRecipes;
import gregtech.api.unification.OreDictUnifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional.Method;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

import static gregification.common.GFValues.FORESTRY;

@Mod.EventBusSubscriber(modid = GFValues.MODID)
public class ForestryCommonProxy {

    public static GTItemComb combs;
    public static GTItemDrop drops;
    public static final List<GTItemFrame> frames = new ArrayList<>();

    @Method(modid = FORESTRY)
    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        if (GFConfig.forestry.enableForestry) {
            if (GFConfig.forestry.gtElectrodes) {
                ElectrodeRecipes.initGTRecipes();
            }
            if (GFConfig.forestry.gtBees) {
                for (GTCombType type : GTCombType.VALUES) {
                    OreDictUnifier.registerOre(new ItemStack(ForestryCommonProxy.combs, 1, type.ordinal()), "beeComb");
                }
                CombRecipes.init();
                DropRecipes.init();
            }
        }
    }

    @Method(modid = FORESTRY)
    public void preInit() {
        if (GFConfig.forestry.enableForestry) {
            if (GFConfig.forestry.gtBees) {
                if (ModuleHelper.isEnabled("apiculture")) {
                    combs = new GTItemComb();
                    drops = new GTItemDrop();
                } else {
                    GFLog.forestryLogger.error("GT Bees is enabled, but Forestry Apiculture module is disabled. Skipping GT Bees...");
                }
            }
            if (GFConfig.forestry.gtFrames) {
                if (ModuleHelper.isEnabled("apiculture")) {
                    for (GTFrameType type : GTFrameType.values()) {
                        frames.add(new GTItemFrame(type));
                    }
                } else {
                    GFLog.forestryLogger.error("GT Frames is enabled, but Forestry Apiculture module is disabled. Skipping GT Frames...");
                }
            }
        }
    }

    @Method(modid = FORESTRY)
    public void init() {
        if (GFConfig.forestry.enableForestry) {
            if (GFConfig.forestry.gtElectrodes) {
                ElectrodeRecipes.removeForestryRecipes();
                ElectrodeRecipes.addForestryMachineRecipes();
            }
            if (GFConfig.forestry.gtBees && ModuleHelper.isEnabled("apiculture")) {
                // GTAlleleHelper.init();
                GTAlleleBeeSpecies.setupGTAlleles();
                GTBeeDefinition.initBees();
            }
        }
    }

    @Method(modid = FORESTRY)
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        if (GFConfig.forestry.enableForestry) {
            if (ModuleHelper.isEnabled("apiculture")) {
                if (GFConfig.forestry.gtBees) {
                    event.getRegistry().register(combs);
                    event.getRegistry().register(drops);
                }
                if (GFConfig.forestry.gtFrames) {
                    frames.forEach(f -> event.getRegistry().register(f));
                }
            }
        }
    }
}
