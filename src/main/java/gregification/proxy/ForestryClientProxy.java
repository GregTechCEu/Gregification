package gregification.proxy;

import forestry.api.core.ForestryAPI;
import forestry.core.items.IColoredItem;
import gregification.common.GFValues;
import gregification.config.GFConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static gregification.common.GFValues.FORESTRY;

@Mod.EventBusSubscriber(modid = GFValues.MODID, value = Side.CLIENT)
public class ForestryClientProxy extends ForestryCommonProxy {

    @Method(modid = FORESTRY)
    @Override
    public void init() {
        super.init();
        if (GFConfig.forestry.enableForestry && GFConfig.forestry.gtBees) {
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler(CombItemColor.INSTANCE, combs, drops);
        }
    }

    @Method(modid = FORESTRY)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        if (GFConfig.forestry.enableForestry) {
            if (GFConfig.forestry.gtBees) {
                combs.registerModel(combs, ForestryAPI.modelManager);
                drops.registerModel(drops, ForestryAPI.modelManager);
            }
            if (GFConfig.forestry.gtFrames) {
                frames.values().forEach(f -> f.registerModel(f, ForestryAPI.modelManager));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    private static class CombItemColor implements IItemColor {

        private static final CombItemColor INSTANCE = new CombItemColor();

        private CombItemColor() {
        }

        @Method(modid = FORESTRY)
        @Override
        public int colorMultiplier(ItemStack stack, int tintIndex) {
            if (stack.getItem() instanceof IColoredItem) {
                return ((IColoredItem) stack.getItem()).getColorFromItemstack(stack, tintIndex);
            }
            return 0xFFFFFF;
        }
    }
}
