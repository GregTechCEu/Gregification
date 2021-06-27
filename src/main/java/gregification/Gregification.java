package gregification;

import gregification.proxy.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = GFValues.MODID, name = GFValues.MOD_NAME, version = GFValues.VERSION,
        dependencies = "required-after:gregtech@[1.15.0.721,);" +
                "after:forestry;" +
                "after:tconstruct;" +
                "after:exnihilocreatio;" +
                "after:mysticalagradditions;" +
                "after:binniecore;" +
                "after:extrabees;" +
                "after:botany;" +
                "after:binniedesign;" +
                "after:extratrees;" +
                "after:botany;" +
                "after:genetics"
)
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
    @SidedProxy(modId = GFValues.MODID, serverSide = "gregification.proxy.ExNihiloCommonProxy", clientSide = "gregification.proxy.ExNihiloClientProxy")
    public static ExNihiloCommonProxy ExNihiloProxy;

    // Open Computers Proxy
    @SidedProxy(modId = GFValues.MODID, serverSide = "gregification.proxy.OCCommonProxy", clientSide = "gregification.proxy.OCClientProxy")
    public static OCCommonProxy OCProxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
}
