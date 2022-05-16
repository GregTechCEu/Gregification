package gregification.base;

import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.StandardMetaItem;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseModule implements IModule {

    public static final Logger logger = LogManager.getLogger("Gregification");

    // Base MetaItem
    // Current ID Ranges:
    // - Forestry Module: 0-999
    public static MetaItem<?> baseMetaItem;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        baseMetaItem = new StandardMetaItem();
        baseMetaItem.setRegistryName("gf_meta_item");
    }
}
