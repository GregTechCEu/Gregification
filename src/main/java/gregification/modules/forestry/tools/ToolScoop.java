package gregification.modules.forestry.tools;

import gregtech.api.items.metaitem.MetaItem;
import gregtech.common.tools.ToolBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

import java.util.Collections;
import java.util.Set;

public class ToolScoop extends ToolBase {

    private static final int DAMAGE_FOR_SCOOP = 1;
    private static final Set<String> SCOOP_TOOL_CLASSES = Collections.singleton("scoop");

    @Override
    public int getToolDamagePerBlockBreak(ItemStack stack) {
        return 2;
    }

    @Override
    public boolean canMineBlock(IBlockState block, ItemStack stack) {
        String tool = block.getBlock().getHarvestTool(block);
        return tool != null && SCOOP_TOOL_CLASSES.contains(tool);
    }

    @Override
    public void onStatsAddedToTool(MetaItem.MetaValueItem item) {
        item.addComponents(new ScoopBehavior(DAMAGE_FOR_SCOOP));
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        return SCOOP_TOOL_CLASSES;
    }
}
