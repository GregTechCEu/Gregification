package gregification.base;

import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.common.items.MetaTool;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Module.Root(name = "Gregification: Base")
public class BaseModule implements Module {

    // Base MetaItem
    // Current ID Ranges:
    // - Forestry Module: 0-999
    public static MetaItem<?> baseMetaItem;

    // Base MetaTool
    // Current ID Ranges:
    // - Forestry Module: 0-9
    public static MetaTool baseMetaTool;

    @Override
    public boolean isModuleActive() {
        return true;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        baseMetaItem = new StandardMetaItem();
        baseMetaItem.setRegistryName("gf_meta_item");

        baseMetaTool = new MetaTool() {@Override public void registerSubItems() {}};
        baseMetaTool.setRegistryName("gf_meta_tool");
    }
}
