package gregification.modules.computercraft;

import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import gregification.base.BaseConfig;
import gregification.base.ModIDs;
import gregification.base.Module;
import gregification.modules.computercraft.peripheral.MetaTileEntityPeripheral;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.util.GTUtility;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Module.Root(name = "Gregification: Computercraft")
public class ComputerCraftModule implements Module {

    @Log
    public static Logger logger;

    @Override
    public boolean isModuleActive() {
        return BaseConfig.enableComputerCraftModule && Loader.isModLoaded(ModIDs.MODID_CC);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        ComputerCraftAPI.registerPeripheralProvider(new IPeripheralProvider() {
            @Nullable
            @Override
            public IPeripheral getPeripheral(@Nonnull World world, @Nonnull BlockPos blockPos, @Nonnull EnumFacing enumFacing) {
                MetaTileEntity metaTileEntity = GTUtility.getMetaTileEntity(world, blockPos);
                if (metaTileEntity != null) {
                    return new MetaTileEntityPeripheral(metaTileEntity, enumFacing);
                }
                return null;
            }
        });
    }
}
