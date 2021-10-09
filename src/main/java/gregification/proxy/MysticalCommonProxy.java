package gregification.proxy;

import gregification.common.GFValues;
import gregification.config.GFConfig;
import gregification.forestry.recipes.ElectrodeRecipes;
import net.minecraftforge.fml.common.Optional;

public class MysticalCommonProxy {
    @Optional.Method(modid = GFValues.MODID)
    public void init() {
        if (GFConfig.mysticalAgriculture.enableMysticalAgriculture) {
        }
    }
}
