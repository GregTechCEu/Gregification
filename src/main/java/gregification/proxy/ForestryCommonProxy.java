package gregification.proxy;

import gregification.common.GFValues;
import gregification.config.GFConfig;
import gregification.forestry.recipes.ElectrodeRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = GFValues.MODID)
public class ForestryCommonProxy {

    @Optional.Method(modid = GFValues.MODID_FR)
    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        if (GFConfig.forestry.enableForestry) {
            if (GFConfig.forestry.gtElectrodes) {
                ElectrodeRecipes.initGTRecipes();
            }
        }
    }

    @Optional.Method(modid = GFValues.MODID_FR)
    public void init() {
        if (GFConfig.forestry.enableForestry) {
            if (GFConfig.forestry.gtElectrodes) {
                ElectrodeRecipes.removeForestryRecipes();
                ElectrodeRecipes.addForestryMachineRecipes();
            }
        }
    }
}
