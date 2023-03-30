package gregification.modules.computercraft.peripheral;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import gregtech.api.metatileentity.MetaTileEntity;
import net.minecraft.util.EnumFacing;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;

public class MetaTileEntityPeripheral implements IPeripheral {
    private final MetaTileEntityPeripheralFunction[] functions;

    public MetaTileEntityPeripheral(MetaTileEntity metaTileEntity, EnumFacing enumFacing) {
        this.functions = Arrays.stream(new MetaTileEntityPeripheralFunction[]{
                new MetaTileEntityPeripheralEnergyContainerFunction(metaTileEntity, enumFacing),
                new MetaTileEntityPeripheralStatusFunction(metaTileEntity),
                new MetaTileEntityPeripheralSetEnabledFunction(metaTileEntity),
        }).filter(MetaTileEntityPeripheralFunction::isEnabled).toArray(MetaTileEntityPeripheralFunction[]::new);
    }

    @Nonnull
    public String getType() {
        return "gregtech_meta_timer_entity";
    }

    @Nonnull
    public String[] getMethodNames() {
        return Arrays.stream(functions).map(MetaTileEntityPeripheralFunction::getName).toArray(String[]::new);
    }

    @Nullable
    public Object[] callMethod(@Nonnull IComputerAccess iComputerAccess, @Nonnull ILuaContext iLuaContext, int i, @Nonnull Object[] objects) throws LuaException, InterruptedException {
        return functions[i].call(iComputerAccess, iLuaContext, i, objects);
    }

    public boolean equals(@Nullable IPeripheral iPeripheral) {
        return iPeripheral == this;
    }

}

