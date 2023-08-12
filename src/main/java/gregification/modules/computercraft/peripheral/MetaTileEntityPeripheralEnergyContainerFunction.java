package gregification.modules.computercraft.peripheral;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.metatileentity.MetaTileEntity;
import net.minecraft.util.EnumFacing;

import java.util.HashMap;
import java.util.Map;

class MetaTileEntityPeripheralEnergyContainerFunction implements MetaTileEntityPeripheralFunction {

    private final IEnergyContainer energyContainer;

    public MetaTileEntityPeripheralEnergyContainerFunction(MetaTileEntity metaTileEntity, EnumFacing enumFacing) {
        this.energyContainer = metaTileEntity.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, enumFacing);
    }

    @Override
    public boolean isEnabled() {
        return this.energyContainer != null;
    }

    @Override
    public String getName() {
        return "getEnergy";
    }

    @Override
    public Object[] call(IComputerAccess iComputerAccess, ILuaContext iLuaContext, int i, Object[] objects) {
        Map<String, Object> data = new HashMap<>();
        data.put("stored", energyContainer.getEnergyStored());
        data.put("capacity", energyContainer.getEnergyCapacity());
        data.put("average_input", energyContainer.getInputPerSec());
        data.put("average_output", energyContainer.getOutputPerSec());
        data.put("input_voltage", energyContainer.getInputVoltage());
        data.put("output_voltage", energyContainer.getOutputVoltage());
        data.put("input_amperage", energyContainer.getInputAmperage());
        data.put("output_amperage", energyContainer.getOutputAmperage());
        return new Object[]{data};
    }
}
