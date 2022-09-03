package gregification.modules.forestry.bees;

import forestry.core.items.IColoredItem;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CombItemColor implements IItemColor {

    public static final CombItemColor INSTANCE = new CombItemColor();

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
