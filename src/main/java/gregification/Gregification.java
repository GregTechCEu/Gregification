package gregification;

import gregification.common.GFMetaItem;
import gregification.common.GFOrePrefix;
import gregification.proxy.*;
import gregification.common.GFLog;
import gregification.common.GFValues;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = GFValues.MODID, name = GFValues.MOD_NAME, version = GFValues.VERSION,
        dependencies = "required-after:gregtech@[2.0-beta,);" + "required-after:mixinbooter;" +
                "after:gregicality@[1.0,);" +
                "after:forestry;" +
                "after:tconstruct;" +
                "after:exnihilocreatio;" +
                "after:mysticalagradditions;" +
                "after:binniecore;" +
                "after:binniedesign;" +
                "after:extrabees;" +
                "after:extratrees;" +
                "after:botany;" +
                "after:genetics")
public class Gregification {

    // Proxy for Gregification itself
    @SidedProxy(modId = GFValues.MODID, serverSide = "gregification.proxy.CommonProxy", clientSide = "gregification.proxy.ClientProxy")
    public static CommonProxy GregificationProxy;

    // Mystical Aggraditions Proxy
    @SidedProxy(modId = GFValues.MODID, serverSide = "gregification.proxy.MysticalCommonProxy", clientSide = "gregification.proxy.MysticalClientProxy")
    public static MysticalCommonProxy MysticalProxy;

    // Forestry Proxy
    @SidedProxy(modId = GFValues.MODID, serverSide = "gregification.proxy.ForestryCommonProxy", clientSide = "gregification.proxy.ForestryClientProxy")
    public static ForestryCommonProxy ForestryProxy;

    // Ex Nihilo Proxy
    @SidedProxy(modId = GFValues.MODID, serverSide = "gregification.proxy.ExNihiloCommonProxy", clientSide = "gregification.proxy.ExNihiloCommonProxy")
    public static ExNihiloCommonProxy ExNihiloProxy;

    // Open Computers Proxy
    @SidedProxy(modId = GFValues.MODID, serverSide = "gregification.proxy.OCCommonProxy", clientSide = "gregification.proxy.OCCommonProxy")
    public static OCCommonProxy OCProxy;

    public Gregification() {
        MinecraftForge.EVENT_BUS.register(GFOrePrefix.class);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        GFLog.init(event.getModLog());
        GFMetaItem.init();
        if (Loader.isModLoaded(GFValues.MODID_EXNI)) {
            ExNihiloProxy.preInit();
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        if (Loader.isModLoaded(GFValues.MODID_EXNI)) {
            ExNihiloProxy.init();
        }
        if (Loader.isModLoaded(GFValues.MODID_OC)) {
            OCProxy.init();
        }
        if (Loader.isModLoaded(GFValues.MODID_FR)) {
            ForestryProxy.init();
        }
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
}
