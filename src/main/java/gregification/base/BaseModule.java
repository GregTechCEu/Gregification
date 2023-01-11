package gregification.base;

import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.StandardMetaItem;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Module.Root(name = "Gregification: Base")
public class BaseModule implements Module {

    // Base MetaItem
    // Current ID Ranges:
    // - Forestry Module: 0-999
    public static MetaItem<?> baseMetaItem;

    @Override
    public boolean isModuleActive() {
        return true;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        baseMetaItem = new StandardMetaItem();
        baseMetaItem.setRegistryName("gf_meta_item");
    }
}
