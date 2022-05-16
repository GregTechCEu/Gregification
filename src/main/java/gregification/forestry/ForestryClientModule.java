package gregification.forestry;

import forestry.api.core.ForestryAPI;
import forestry.core.items.IColoredItem;
import gregification.base.BaseConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings("unused")
public class ForestryClientModule extends ForestryModule {

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        if (ForestryUtils.apicultureEnabled()) {
            if (BaseConfig.forestry.gtBees) {
                Minecraft.getMinecraft().getItemColors().registerItemColorHandler(CombItemColor.INSTANCE, ForestryModule.gtCombs, ForestryModule.gtDrops);
            }
        }
    }

    @Override
    public void registerModels(ModelRegistryEvent event) {
        if (ForestryUtils.apicultureEnabled()) {
            if (BaseConfig.forestry.gtBees) {
                ForestryModule.gtCombs.registerModel(ForestryModule.gtCombs, ForestryAPI.modelManager);
                ForestryModule.gtDrops.registerModel(ForestryModule.gtDrops, ForestryAPI.modelManager);
            }
            if (BaseConfig.forestry.gtFrames) {
                ForestryModule.gtFrames.values().forEach(f -> f.registerModel(f, ForestryAPI.modelManager));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    private static class CombItemColor implements IItemColor {

        private static final CombItemColor INSTANCE = new CombItemColor();

        private CombItemColor() {
        }

        @Override
        public int colorMultiplier(ItemStack stack, int tintIndex) {
            if (stack.getItem() instanceof IColoredItem) {
                return ((IColoredItem) stack.getItem()).getColorFromItemstack(stack, tintIndex);
            }
            return 0xFFFFFF;
        }
    }
}
